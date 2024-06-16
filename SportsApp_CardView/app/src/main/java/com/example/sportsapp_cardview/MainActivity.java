package com.example.sportsapp_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<Sport> sportList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        sportList = new ArrayList<>();

        Sport s1 = new Sport(R.drawable.football, "Football");
        Sport s2 = new Sport(R.drawable.basketball, "Basketball");
        Sport s3 = new Sport(R.drawable.volley, "Volleyball");
        Sport s4 = new Sport(R.drawable.tennis, "Tennis");
        Sport s5 = new Sport(R.drawable.ping, "Ping Pong");

        sportList.add(s1);
        sportList.add(s2);
        sportList.add(s3);
        sportList.add(s4);
        sportList.add(s5);

        adapter = new CustomAdapter(sportList);
        // below - this means items will be arranged linearly depending on how to configure the linear layout
        // by default - vertical orientation
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setClickListener((ItemClickListener) this);


    }

    @Override
    public void onClick(View v, int pos) {
        Toast.makeText(this, "you chose "+sportList.get(pos).sportName, Toast.LENGTH_SHORT).show();
    }
}