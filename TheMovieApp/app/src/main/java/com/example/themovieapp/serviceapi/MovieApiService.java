package com.example.themovieapp.serviceapi;

import com.example.themovieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {

    // the service interface defines the structure
    // and behavior of the API requests
    // acts as a bridge between your app and the API

    // get - get data from the server
    @GET("movie/popular") // specify the end point
    // retrofit uses call class to represent a network request
    // and it's response type should be replaced with the
    // actual model class that represents expected response from API
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}
