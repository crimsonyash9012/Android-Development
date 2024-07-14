package com.example.phonebookapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.phonebookapp.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    private ArrayList<User> users;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ActivityMainBinding binding;

    // Firebase
    DatabaseReference databaseReference;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");




        // fetch the data from firebase into recycler view
        databaseReference.addValueEventListener(new ValueEventListener() {
            // fundamental mechanism for building realtime applications with firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }
                // notify an adapter associated with a recycler view
                // that underlying dataset has changed
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // binding instance



        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );



        // recycler view with binding
        users= new ArrayList<>();
        recyclerView = binding.recyclerView;
        // adapter
        myAdapter = new MyAdapter(this, users);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}