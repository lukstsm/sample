package com.bbt.sampleproject.presentation.presenter;


import com.bbt.sampleproject.data.DataCallback;
import com.bbt.sampleproject.data.GameDataProvider;
import com.bbt.sampleproject.data.network.models.Game;
import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;
import com.bbt.sampleproject.presentation.view.GameView;
import com.bbt.sampleproject.ui.ScreenNavigator;

public class GamePresenter extends BasePresenter<GameView> {

    private static final String GAME_ID = "2ewt6r22zo4qwgx";

    private final GameDataProvider mGameDataProvider;
    private final ScreenNavigator mScreenNavigator;

    private final DataCallback<Game> mCallback = new DataCallback<Game>() {
        @Override
        public void onSuccess(Game deliverable) {
            getView().showGameSessions(deliverable.getGameSessions(), mOnGameSessionClickedListener);
        }

        @Override
        public void onFailure(String error) {
            //show error
        }
    };

    private final OnGameSessionClickedListener mOnGameSessionClickedListener = new OnGameSessionClickedListener() {
        @Override
        public void onClicked(GameSession gameSession) {
            mScreenNavigator.openGameSessionDetailsScreen(gameSession);
        }
    };

    public GamePresenter(GameView view, GameDataProvider gameDataProvider, ScreenNavigator screenNavigator) {
        super(view);
        mGameDataProvider = gameDataProvider;
        mScreenNavigator = screenNavigator;
    }

    public void onResume() {
        mGameDataProvider.getGame(GAME_ID, mCallback);
    }

}
