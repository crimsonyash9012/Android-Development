package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var userName:EditText
    lateinit var userMessage:EditText
    lateinit var counter: Button
    lateinit var remember:CheckBox

    var count : Int = 0

    // lateinit can't be used with primitive data types
    var name:String? = null
    var message:String? = null
    var isChecked:Boolean? = null

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName = findViewById(R.id.name)
        userMessage = findViewById(R.id.message)
        counter = findViewById(R.id.counter)
        remember = findViewById(R.id.remember)

        counter.setOnClickListener {
            count++;
            counter.text = ""+count

        }
    }

    override fun onPause() {
        super.onPause()

        saveData()
    }

    fun saveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = userName.text.toString()
        message = userMessage.text.toString()
        isChecked = remember.isChecked

        val editor = sharedPreferences.edit()

        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key remember", isChecked!!)

        editor.apply()

        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
    }

    fun retrieveData(){

        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name", null)
        message = sharedPreferences.getString("key message", null)
        count = sharedPreferences.getInt("key count", 0)
        isChecked = sharedPreferences.getBoolean("key remember", false)

        userName.setText(name)
        userMessage.setText(message)
        counter.text = ""+count
        remember.isChecked = isChecked!!
    }

    override fun onResume() {
        super.onResume()

        retrieveData()
    }
}