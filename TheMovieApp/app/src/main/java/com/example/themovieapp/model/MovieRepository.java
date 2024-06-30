package com.example.themovieapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.themovieapp.R;
import com.example.themovieapp.serviceapi.MovieApiService;
import com.example.themovieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    // used to abstract the data source details and
    // provides a clean API for the view model to
    // fetch and manage data

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<List<Movie>>();

    // passing this to access resource folder later on
    // bcz going to place string API in resource tag in string.xml
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }


    //V imp.
    public MutableLiveData<List<Movie>> getMutableLiveData(){
        // you often create retrofit instances and use it to create service instances
        // these service instances are interfaces that define API endpoints
        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<Result> call = movieApiService.getPopularMovies(
                application.getApplicationContext().getString(R.string.api_key));


        // asynchronous method for making http requests
        // used when need to handle network request in background thread
        // and handle responses in main UI thread
        // execute() - handle network request in main UI thread
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // success
                Result result = response.body();
                if(result!=null && result.getResults()!=null){
                    movies = (ArrayList<Movie>) result.getResults();
                    // setValue() - can be called on the main UI thread & sets the new value immediately
                    // if called from BG thread => throw exception
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });
        return mutableLiveData;
    }
}
