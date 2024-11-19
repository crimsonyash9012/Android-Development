package com.tiramisu.flagquiz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiramisu.flagquiz.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Thread.sleep(3000L)
        setContentView(R.layout.activity_main)
    }
}