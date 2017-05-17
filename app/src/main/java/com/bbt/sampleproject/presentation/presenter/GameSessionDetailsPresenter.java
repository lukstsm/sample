package com.bbt.sampleproject.presentation.presenter;


import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.view.GameSessionDetailsView;

public class GameSessionDetailsPresenter extends BasePresenter<GameSessionDetailsView> {

    private final GameSession mGameSession;

    public GameSessionDetailsPresenter(GameSessionDetailsView gameSessionDetailsView, GameSession gameSession) {
        super(gameSessionDetailsView);
        mGameSession = gameSession;
    }

    public void onResume() {
        getView().setName(mGameSession.getName());
        getView().setJackpot(mGameSession.getJackpot());
        getView().setDate(mGameSession.getDate());
    }
}
