package com.example.contactmanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    // adapter
    private MyAdapter adapter;

    // Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets the content view of an activity or a fragment with data binding enabled
        mainBinding  = DataBindingUtil.setContentView(
                this,R.layout.activity_main
                );
        handlers = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handlers);

        // recycler view
        // obtaining from a layout that uses data binding
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



         // Database

        contactDatabase = ContactDatabase.getInstance(this);

        // View Model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Contacts c1 = new Contacts("yash","yash@gmail.com");
        //don't deal directly with the database, deal with view model
        //and it'll deal with repository and it'll deal with room database
        viewModel.addNewContact(c1);

        //loading data from room DB
        // observe - used to observe or listen changes
        // in underlying data of live data object
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {
                        contactsArrayList.clear();
                        for(Contacts c: contacts){
                            // first get them into logcat and then display into recycler view
                            Log.v("TAGY",c.getName());
                            contactsArrayList.add(c);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        // Adapter
        adapter = new MyAdapter(contactsArrayList);

        // linking recylcer view with adapter
        recyclerView.setAdapter(adapter);

        // swipe to delete


        // utility class provides support for handling touch gestures, especially for swipe and drag
        // 0 - no dragging ; left - swipe items to left
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContacts(c);
            }
        }).attachToRecyclerView(recyclerView);


    }
}