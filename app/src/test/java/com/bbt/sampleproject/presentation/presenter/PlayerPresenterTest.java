package com.bbt.sampleproject.presentation.presenter;

import com.bbt.sampleproject.data.DataCallback;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.data.network.models.Player;
import com.bbt.sampleproject.presentation.view.PlayerView;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerPresenterTest {

    private static final String PLAYER_NAME = "Antony";
    private static final java.lang.String PLAYER_AVATAR = "https://test.com/some.png";
    private static final double PLAYER_BALANCE = 9001;
    private static final String PLAYER_LAST_LOGIN = "04/05/2016T16:45";

    @Mock
    Player mPlayer;

    @Mock
    PlayerView mPlayerView;

    @Mock
    GameDataProvider mGameDataProvider;

    @Captor
    ArgumentCaptor<DataCallback<Player>> mPlayerDataCallbackCaptor;

    PlayerPresenter mGameScreenPlayerPresenter;
    PlayerPresenter mGameDetailsScreenPlayerPresenter;

    public PlayerPresenterTest() {
        MockitoAnnotations.initMocks(this);
        when(mPlayer.getName()).thenReturn(PLAYER_NAME);
        when(mPlayer.getAvatarLink()).thenReturn(PLAYER_AVATAR);
        when(mPlayer.getBalance()).thenReturn(PLAYER_BALANCE);
        when(mPlayer.getLastLoginDate()).thenReturn(PLAYER_LAST_LOGIN);

        mGameScreenPlayerPresenter = new PlayerPresenter(mPlayerView, mGameDataProvider, true);
        mGameDetailsScreenPlayerPresenter = new PlayerPresenter(mPlayerView, mGameDataProvider, false);
    }

    @Test
    public void requestPlayerDataWhenResumed() {
        mGameScreenPlayerPresenter.onResume();
        verify(mGameDataProvider).getPlayer(anyString(), any(DataCallback.class));
    }

    @Test
    public void updateViewWhenDataRequestSuccessful() {
        mGameScreenPlayerPresenter.onResume();
        verify(mGameDataProvider).getPlayer(anyString(), mPlayerDataCallbackCaptor.capture());
        mPlayerDataCallbackCaptor.getValue().onSuccess(mPlayer);
        verify(mPlayerView).setName(mPlayer.getName());
    }

    @Test
    public void showLastLoginDate() {
        mGameScreenPlayerPresenter.updateView(mPlayer);
        verify(mPlayerView).setLastLoginDate(PLAYER_LAST_LOGIN);
    }

    @Test
    public void hideLastLoginDate() {
        mGameDetailsScreenPlayerPresenter.updateView(mPlayer);
        verify(mPlayerView).hideLastLoginDate();
    }

}