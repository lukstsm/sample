package com.bbt.sampleproject.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bbt.sampleproject.R;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.presenter.GameSessionDetailsPresenter;
import com.bbt.sampleproject.presentation.view.GameSessionDetailsView;
import com.bbt.sampleproject.ui.DateFormatter;
import com.bbt.sampleproject.ui.LocaleUtils;
import com.bbt.sampleproject.ui.fragment.PlayerFragment;

import org.joda.time.DateTime;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GameSessionDetailsActivity extends AppCompatActivity implements GameSessionDetailsView {

    private static final String EXTRA_GAME_SESSION = "extra_game_session";
    private static final String TAG_PLAYER_INFO_FRAGMENT = "tag_game_session_player_info";

    @BindView(R.id.game_session_details_name)
    TextView mGameSessionDetailsName;

    @BindView(R.id.game_session_details_jackpot)
    TextView mGameSessionDetailsJackpot;

    @BindView(R.id.game_session_details_date)
    TextView mGameSessionDetailsDate;

    @BindString(R.string.jackpot_format)
    String mJackpotFormat;

    private GameSessionDetailsPresenter mPresenter;
    private DateFormatter mDateFormatter;

    public static Intent getIntent(Context context, GameSession gameSession) {
        Intent intent = new Intent(context, GameSessionDetailsActivity.class);
        intent.putExtra(EXTRA_GAME_SESSION, gameSession);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session_details);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            setupPlayerInfoView();
        }

        GameSession gameSession = getIntent().getExtras().getParcelable(EXTRA_GAME_SESSION);
        mDateFormatter = new DateFormatter(getResources().getConfiguration().locale);
        mPresenter = new GameSessionDetailsPresenter(this, gameSession);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void setupPlayerInfoView() {
        PlayerFragment playerFragment = PlayerFragment.newInstance(false);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.game_session_player_info_container, playerFragment, TAG_PLAYER_INFO_FRAGMENT)
                .commit();
    }

    @Override
    public void setName(String name) {
        mGameSessionDetailsName.setText(name);
    }

    @Override
    public void setJackpot(double jackpot) {
        String localisedJackpot = LocaleUtils.getLocalisedCurrency(jackpot);
        String jackpotMessage = String.format(mJackpotFormat, localisedJackpot);
        mGameSessionDetailsJackpot.setText(jackpotMessage);
    }

    @Override
    public void setDate(String date) {
        DateTime dateTime = new DateTime(date);
        String userFriendlyDate = mDateFormatter.getFormattedDate(dateTime);
        mGameSessionDetailsDate.setText(userFriendlyDate);
    }
}
