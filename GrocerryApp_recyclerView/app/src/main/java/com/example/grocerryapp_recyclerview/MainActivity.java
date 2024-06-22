package com.example.grocerryapp_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView recyclerView;
    List<item> itemList;

    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleview);
        itemList = new ArrayList<>();

        item item1 = new item(R.drawable.cube, "Cube", "I am a cube");
        item item2 = new item(R.drawable.cylinder, "Cylinder", "I am a cylinder");
        item item3 = new item(R.drawable.sphere, "Sphere", "I am a sphere");

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(itemList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setClickListener((ItemClickListener) this);

    }

    @Override
    public void onClick(View v, int pos) {
        Toast.makeText(this, "you chose" + itemList.get(pos).getItemName(), Toast.LENGTH_SHORT).show();
    }
}