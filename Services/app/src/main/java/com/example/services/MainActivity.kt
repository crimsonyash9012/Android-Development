package com.example.services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var startClassic : Button
    lateinit var startJob : Button
    lateinit var stopClassic : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startClassic = findViewById(R.id.startClassic);
        startJob = findViewById(R.id.startJob);
        stopClassic = findViewById(R.id.stopClassic);

        startClassic.setOnClickListener {
            val intent = Intent(this@MainActivity, ClassicService::class.java)
            startService(intent)
        }

        startJob.setOnClickListener {
            val intent = Intent(this,JobIntent::class.java)
            JobIntent.myBackgroundService(this, intent)
        }

        stopClassic.setOnClickListener {

        }

    }
}