package com.bbt.sampleproject.data.network;


import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpFactory {

    private static final int HOUR = 60 * 60;

    public static OkHttpClient create(File cacheDirectory) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(createLogginInterceptor());

        if (cacheDirectory != null) {
            builder.addInterceptor(new ResponseCacheInterceptor(HOUR));
            builder.cache(createCache(cacheDirectory));
        }

        return builder.build();
    }

    private static HttpLoggingInterceptor createLogginInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static Cache createCache(File cacheDirectory) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(cacheDirectory, cacheSize);
    }

    private static class ResponseCacheInterceptor implements Interceptor {

        private final int mMaxAge;

        public ResponseCacheInterceptor(int maxAge) {
            mMaxAge = maxAge;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + mMaxAge)
                    .build();
        }
    }
}
