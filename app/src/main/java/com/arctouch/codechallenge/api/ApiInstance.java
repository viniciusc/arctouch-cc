package com.arctouch.codechallenge.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiInstance {

    private static String URL = "https://api.themoviedb.org/3/";
    public static TmdbApi instance;

    public static TmdbApi getInstance() {
        if(instance != null){
            return instance;
        } else {
            instance = new Retrofit.Builder()
                .baseUrl(URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TmdbApi.class);
            return instance;
        }
    }

}
