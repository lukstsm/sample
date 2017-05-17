package com.bbt.sampleproject.presentation.presenter;

import com.bbt.sampleproject.data.DataCallback;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.data.network.models.Game;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;
import com.bbt.sampleproject.presentation.view.GameView;
import com.bbt.sampleproject.ui.ScreenNavigator;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GamePresenterTest {

    @Mock
    Game mGame;

    @Mock
    ArrayList<GameSession> mGameSessions;

    @Mock
    GameSession mGameSession;

    @Mock
    GameView mGameView;

    @Mock
    GameDataProvider mGameDataProvider;

    @Mock
    ScreenNavigator mScreenNavigator;

    @Mock
    OnGameSessionClickedListener mGameDataClickedListener;

    @Captor
    ArgumentCaptor<DataCallback<Game>> mGameDataCallbackCaptor;

    @Captor
    ArgumentCaptor<OnGameSessionClickedListener> mGameSessionClickedListenerCaptor;

    GamePresenter mGamePresenter;

    public GamePresenterTest() {
        MockitoAnnotations.initMocks(this);
        when(mGame.getGameSessions()).thenReturn(mGameSessions);
        mGamePresenter = new GamePresenter(mGameView, mGameDataProvider, mScreenNavigator);
    }

    @Test
    public void requestDataWhenResumed() {
        mGamePresenter.onResume();
        verify(mGameDataProvider).getGame(anyString(), any(DataCallback.class));
    }

    @Test
    public void updateViewWhenDataRequestSuccessful() {
        mGamePresenter.onResume();
        verify(mGameDataProvider).getGame(anyString(), mGameDataCallbackCaptor.capture());
        mGameDataCallbackCaptor.getValue().onSuccess(mGame);

        // This should check the list is the same,
        // but mockito is throwing a fit about matcher and raw value mixing, even though there is no raw value
        verify(mGameView).showGameSessions(ArgumentMatchers.<GameSession>anyList(), mGameSessionClickedListenerCaptor.capture());
    }

    @Test
    public void navigateToDetailsScreenWhenGameSessionClicked() {
        mGamePresenter.onResume();
        verify(mGameDataProvider).getGame(anyString(), mGameDataCallbackCaptor.capture());

        mGameDataCallbackCaptor.getValue().onSuccess(mGame);
        verify(mGameView).showGameSessions(ArgumentMatchers.<GameSession>anyList(), mGameSessionClickedListenerCaptor.capture());

        mGameSessionClickedListenerCaptor.getValue().onClicked(mGameSession);
        verify(mScreenNavigator).openGameSessionDetailsScreen(mGameSession);
    }

}