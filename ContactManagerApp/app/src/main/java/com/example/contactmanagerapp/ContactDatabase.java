package com.example.contactmanagerapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

    // Singleton Pattern

    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context){
        if(dbInstance==null){
            // factory method provided by room library to create a new database or access an existing one
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();
            // fallbackToDestructiveMigration() - used to specify database migration strategy
            // if new version of database schema is detected => room will drop and recreate database effectively
        }
        return dbInstance;
        // if any instance created in previous executions and commands => return it
    }
}
