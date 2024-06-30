package com.example.themovieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.themovieapp.model.Movie;
import com.example.themovieapp.model.MovieRepository;

import java.util.List;

// ViewModel - not android specific and doesn't have a reference to the android app or context
// instances are created using viewModelProvider => don't require access to android specific compo.
// AndroidViewModel - subclass of viewModel aware of android app context

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    // live data

    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }
}
