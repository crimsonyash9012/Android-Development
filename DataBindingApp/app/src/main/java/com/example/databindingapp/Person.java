package com.example.databindingapp;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


// not compulsary to implement BaseObservable - essential for automatic update UI elements
public class Person extends BaseObservable {
    String name;
    String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        // used by android data binding library to notify that specific data has changed it's value
        notifyPropertyChanged(BR.name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
    }
}
