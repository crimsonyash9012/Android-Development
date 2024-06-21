package com.example.contactmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// by using DAO in Room, we can interact with database in much more structured and efficient manner
@Dao
public interface ContactDAO {
    @Insert
    void insert(Contacts contact);


    @Delete
    void delete(Contacts contact);

    @Query("SELECT * FROM contacts_table") // allows to use custom SQL querries
    // if have query annotation and select command -> always use live data
    LiveData<List<Contacts>> getAllContacts();
}
