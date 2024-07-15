package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // write a message to the database

        // initialize the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // get a reference to a specific node in database
        DatabaseReference myRef = database.getReference("Users");

        // write a value to the specified database location

//        User user1 = new User("Jack", "jack@gmail.com");
//        myRef.setValue(user1);
//        myRef.setValue("Hello from our course!!");

        myRef.addValueEventListener(new ValueEventListener() { // attach an event listener to a firebase realtime database reference

            //called when data at specified database reference changes
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // data snapshot - contains the updated data
                User user = snapshot.getValue(User.class);
                textView.setText("Email:- " +user.getEmail());

                /*
                String newValue = snapshot.getValue(String.class);
                textView.setText(newValue);

                 */
            }

            // when error while reading data from database
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}