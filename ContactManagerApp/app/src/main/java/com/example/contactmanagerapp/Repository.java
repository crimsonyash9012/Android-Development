package com.example.contactmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repository {
        // the available data sources:
        // - ROOM databases


    private final ContactDAO contactDAO;

    ExecutorService executor;
    Handler handler;
    public Repository(Application application) {


        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        // used for background service operations
         executor = Executors.newSingleThreadExecutor();
        // handler - updates changes in main UI from background thread
         handler = new Handler(Looper.getMainLooper());


    }

    public void addContact(Contacts contact){


        executor.execute(new Runnable() { // Runnable - interface defines a task to be executed typically on seperate thread
            @Override
            public void run() {
                // execute this code asynchronously on separate thread
                contactDAO.insert(contact);
            }
        });
    }


    public void deleteContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }

    // Room database operations (insertions, updates and queries) should not be executed on the main thread
    // bcz they can potentially block the UI => use them in background threads


}
