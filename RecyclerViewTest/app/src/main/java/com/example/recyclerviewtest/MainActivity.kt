package com.example.recyclerviewtest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var adapter : CountriesAdapter

    var countryNames = ArrayList<String>()
    var detailsList =  ArrayList<String>()
    var imageList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        countryNames.add("United Kingdom")
        countryNames.add("Russia")
        countryNames.add("India")

        detailsList.add("UK Flag")
        detailsList.add("Rus Flag")
        detailsList.add("Ind Flag")

        imageList.add(R.drawable.uk)
        imageList.add(R.drawable.rus)
        imageList.add(R.drawable.india)

        adapter = CountriesAdapter(countryNames, detailsList, imageList, this@MainActivity)
        recyclerView.adapter = adapter

    }
}