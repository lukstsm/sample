package com.bbt.sampleproject.data.network;


import com.google.gson.Gson;

import java.io.File;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final Gson mGson = new Gson();

    public static Retrofit create(String baseUrl, File cacheDirectory) {
        final retrofit2.Converter.Factory factory = GsonConverterFactory.create(mGson);
        final OkHttpClient client = OkHttpFactory.create(cacheDirectory);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(factory)
                .build();
    }

}
