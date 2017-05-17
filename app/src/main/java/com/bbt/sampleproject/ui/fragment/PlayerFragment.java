package com.bbt.sampleproject.ui.fragment;


import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbt.sampleproject.R;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.presentation.presenter.PlayerPresenter;
import com.bbt.sampleproject.presentation.view.PlayerView;
import com.bbt.sampleproject.ui.LocaleUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlayerFragment extends Fragment implements PlayerView {

    private static final String EXTRA_SHOW_LAST_LOGIN = "extra_show_last_login";

    @BindView(R.id.player_avatar)
    ImageView mPlayerAvatar;

    @BindView(R.id.player_name)
    TextView mPlayerName;

    @BindView(R.id.player_balance)
    TextView mPlayerBalance;

    @BindView(R.id.player_last_login)
    TextView mPlayerLastLogin;

    @BindString(R.string.balance_format)
    String mBalanceFormat;

    private Unbinder unbinder;
    private boolean mShowLastLogin;
    private PlayerPresenter mPresenter;

    public static PlayerFragment newInstance(boolean showLastLogin) {
        PlayerFragment fragment = new PlayerFragment();

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_SHOW_LAST_LOGIN, showLastLogin);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            mShowLastLogin = getArguments().getBoolean(EXTRA_SHOW_LAST_LOGIN, true);
        }

        mPresenter = new PlayerPresenter(this, new GameDataProvider(getActivity().getCacheDir()), mShowLastLogin);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setAvatar(String url) {
        Picasso.with(getActivity())
                .load(url)
                .resize(100, 100)
                .centerCrop()
                .into(mPlayerAvatar);
    }

    @Override
    public void setName(String name) {
        mPlayerName.setText(name);
    }

    @Override
    public void setBalance(double balance) {
        String localisedBalance = LocaleUtils.getLocalisedCurrency(balance);
        String balanceMessage = String.format(mBalanceFormat, localisedBalance);
        mPlayerBalance.setText(balanceMessage);
    }

    @Override
    public void setLastLoginDate(String date) {
        mPlayerLastLogin.setText(date);
    }

    @Override
    public void hideLastLoginDate() {
        mPlayerLastLogin.setVisibility(View.INVISIBLE);
    }
}
