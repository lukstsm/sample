package com.bbt.sampleproject.presentation.view;


import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.presentation.OnGameSessionClickedListener;

import java.util.List;

public interface GameView extends BaseView {

    void showGameSessions(List<GameSession> gameSessions, OnGameSessionClickedListener onGameSessionClickedListener);
}
