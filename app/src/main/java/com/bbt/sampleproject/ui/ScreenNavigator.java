package com.bbt.sampleproject.ui;


import android.content.Context;
import android.content.Intent;

import com.bbt.sampleproject.data.network.models.GameSession;
import com.bbt.sampleproject.ui.activity.GameSessionDetailsActivity;

public class ScreenNavigator {

    private final Context mContext;

    public ScreenNavigator(Context context) {
        mContext = context;
    }

    public void openGameSessionDetailsScreen(GameSession gameSession) {
        Intent intent = GameSessionDetailsActivity.getIntent(mContext, gameSession);
        mContext.startActivity(intent);
    }
}
