package com.example.viewmodelapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
//    int counter = 0;
    private MutableLiveData<Integer> counter= new MutableLiveData<>();

    public void increaseCounter(){
        int current_value = counter.getValue() !=null ? counter.getValue():0;

        counter.setValue(current_value+1);

    }

    public LiveData<Integer> getCounter() {
        return counter;
    }
}
