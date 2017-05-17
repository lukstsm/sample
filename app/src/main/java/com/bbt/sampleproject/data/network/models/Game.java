package com.bbt.sampleproject.data.network.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Game {

    @SerializedName("response")
    private String mResponse;

    @SerializedName("currency")
    private String mCurrency;

    @SerializedName("data")
    private List<GameSession> mGameSessions;

    public String getResponse() {
        return mResponse;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public List<GameSession> getGameSessions() {
        return mGameSessions;
    }
}
