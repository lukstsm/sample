package com.bbt.sampleproject.data.network.api;


import com.bbt.sampleproject.data.network.models.Player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayerApi {

    @GET("/s/{userId}/playerInfo.json")
    Call<Player> fetchPlayer(@Path("userId") String userId);

}
