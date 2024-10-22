package com.tiramisu.dialogfragment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    lateinit var show : Button
    lateinit var name : TextView
    lateinit var age : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        show = findViewById(R.id.button)
        name = findViewById(R.id.tvName)
        age = findViewById(R.id.tvAge)

        show.setOnClickListener {
            val fragmentManager : FragmentManager= supportFragmentManager
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.show(fragmentManager, "MyDialogFragment")
            myDialogFragment.isCancelable = false// cancelled when tapped outside the dialog box
        }
    }

    fun getUserData(userName : String , userAge : Int){
        name.text = userName
        age.text = userAge.toString()
    }
}