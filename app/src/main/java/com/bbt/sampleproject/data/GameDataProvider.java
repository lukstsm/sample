package com.bbt.sampleproject.data;


import com.bbt.sampleproject.data.network.RetrofitFactory;
import com.bbt.sampleproject.data.network.api.GameApi;
import com.bbt.sampleproject.data.network.api.PlayerApi;
import com.bbt.sampleproject.data.network.models.Game;
import com.bbt.sampleproject.data.network.models.Player;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameDataProvider {

    private static final String BASE_URL = "https://dl.dropboxusercontent.com";
    private final File mHttpCacheDirectory;

    public GameDataProvider(File cacheDirectory) {
        mHttpCacheDirectory = new File(cacheDirectory, "responses");
    }

    public void getGame(String gameId, final DataCallback<Game> callback) {
        GameApi gameApi = RetrofitFactory
                .create(BASE_URL, mHttpCacheDirectory)
                .create(GameApi.class);

        gameApi.fetchGame(gameId).enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getPlayer(String playerId, final DataCallback<Player> callback) {
        PlayerApi playerApi = RetrofitFactory
                .create(BASE_URL, null)
                .create(PlayerApi.class);

        playerApi.fetchPlayer(playerId).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
