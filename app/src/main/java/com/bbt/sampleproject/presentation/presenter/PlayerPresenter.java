package com.bbt.sampleproject.presentation.presenter;


import com.bbt.sampleproject.data.DataCallback;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.data.network.models.Player;
import com.bbt.sampleproject.presentation.view.PlayerView;

public class PlayerPresenter extends BasePresenter<PlayerView> {

    private static final String PLAYER_ID = "5zz3hibrxpspoe5";

    private final GameDataProvider mGameDataProvider;
    private final boolean mShowLastLogin;

    public PlayerPresenter(PlayerView playerView, GameDataProvider gameDataProvider, boolean showLastLogin) {
        super(playerView);
        mGameDataProvider = gameDataProvider;
        mShowLastLogin = showLastLogin;
    }

    public void onResume() {
        mGameDataProvider.getPlayer(PLAYER_ID, new DataCallback<Player>() {
            @Override
            public void onSuccess(Player player) {
                updateView(player);
            }

            @Override
            public void onFailure(String error) {
                //show error
            }
        });
    }

    protected void updateView(Player player) {
        getView().setAvatar(player.getAvatarLink());
        getView().setName(player.getName());
        getView().setBalance(player.getBalance());

        if (mShowLastLogin) {
            getView().setLastLoginDate(player.getLastLoginDate());
        } else {
            getView().hideLastLoginDate();
        }
    }

}
