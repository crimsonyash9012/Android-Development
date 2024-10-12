package com.example.gridview

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var gridView: GridView
    var nameList = ArrayList<String>()
    var imgList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView)

        fillArrays()

        val adapter = AndroidAdapter(this, nameList, imgList)
        gridView.adapter = adapter
        
        gridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, "You selected the ${nameList[position]}", Toast.LENGTH_SHORT).show()
        }

    }

    fun fillArrays(){
        nameList.add("Bird")
        nameList.add("Cat")
        nameList.add("Chicken")
        nameList.add("Dog")
        nameList.add("Fish")
        nameList.add("Monkey")

        imgList.add(R.drawable.bird)
        imgList.add(R.drawable.cat)
        imgList.add(R.drawable.dog)
        imgList.add(R.drawable.chicken)
        imgList.add(R.drawable.fish)
        imgList.add(R.drawable.monkey)
    }
}