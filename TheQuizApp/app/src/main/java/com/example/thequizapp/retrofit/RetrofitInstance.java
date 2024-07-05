package com.example.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    String baseUrl = "http://10.0.2.2/quiz/";

    // create and return a configured retrofit instance
    public Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().
                baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
