package com.example.section16_i;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        String []settings = {"Accessibility", "About", "Display", "Brightness", "Date & Time", "Network & Internet"};

        myCustomAdapter adapter = new myCustomAdapter(this,settings);
        listView.setAdapter(adapter);
    }
}