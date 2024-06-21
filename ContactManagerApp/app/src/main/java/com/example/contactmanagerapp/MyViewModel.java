package com.example.contactmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


// androidViewModel - subclass of view model and similar to them designed to manage UI data

public class MyViewModel  extends AndroidViewModel {

    // if need to use context inside view model
    // should use AVM (android virtual machine)
    // bcz it contains application context

    private Repository repository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }


    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact){
        repository.addContact(contact);
    }

    public void deleteContacts(Contacts contact){
        repository.deleteContact(contact);
    }
}
