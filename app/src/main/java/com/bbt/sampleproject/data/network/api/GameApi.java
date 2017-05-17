package com.bbt.sampleproject.data.network.api;


import com.bbt.sampleproject.data.network.models.Game;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameApi {

    @GET("/s/{gameId}/gameData.json")
    Call<Game> fetchGame(@Path("gameId") String userId);

}
