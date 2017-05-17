package com.bbt.sampleproject.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.bbt.sampleproject.R;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;
import com.bbt.sampleproject.presentation.presenter.GamePresenter;
import com.bbt.sampleproject.presentation.view.GameView;
import com.bbt.sampleproject.ui.ScreenNavigator;
import com.bbt.sampleproject.ui.adapter.GameSessionAdapter;
import com.bbt.sampleproject.ui.fragment.PlayerFragment;

import java.io.File;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity implements GameView {

    private static final String TAG_PLAYER_INFO_FRAGMENT = "game_view_player_info_fragment";

    @BindView(R.id.game_player_info_container)
    FrameLayout mPlayerInfoContainer;

    @BindView(R.id.game_sessions_recycler_view)
    RecyclerView mGameSessionRecyclerView;

    private GamePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        setupRecyclerView();

        if (savedInstanceState == null) {
            setupPlayerInfoView();
        }

        mPresenter = new GamePresenter(this, new GameDataProvider(getCacheDir()), new ScreenNavigator(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showGameSessions(List<GameSession> gameSessions, OnGameSessionClickedListener onGameSessionClickedListener) {
        GameSessionAdapter gameSessionAdapter = new GameSessionAdapter(gameSessions, onGameSessionClickedListener);
        mGameSessionRecyclerView.setAdapter(gameSessionAdapter);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GameSessionAdapter gameSessionAdapter = new GameSessionAdapter(Collections.<GameSession>emptyList(), null);

        mGameSessionRecyclerView.setHasFixedSize(true);
        mGameSessionRecyclerView.setLayoutManager(layoutManager);
        mGameSessionRecyclerView.setAdapter(gameSessionAdapter);
    }

    private void setupPlayerInfoView() {
        PlayerFragment playerFragment = PlayerFragment.newInstance(true);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.game_player_info_container, playerFragment, TAG_PLAYER_INFO_FRAGMENT)
                .commit();
    }
}
