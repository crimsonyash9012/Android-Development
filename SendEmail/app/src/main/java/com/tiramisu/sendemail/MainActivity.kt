package com.tiramisu.sendemail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tiramisu.sendemail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.button.setOnClickListener {
            val usrEmail = mainBinding.editTextTextEmailAddress.text.toString()
            val usrSub = mainBinding.editTextText.text.toString()
            val usrBody = mainBinding.editTextTextMultiLine.text.toString()

            sendEmail(usrEmail,usrSub, usrBody)
        }

    }

    fun sendEmail(usrEmail:String , usrSub:String , usrBody:String){
        val emailAddresses = arrayOf(usrEmail)
        // SENDTO - for email
        // SEND - for general apps
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, usrSub)
        emailIntent.putExtra(Intent.EXTRA_TEXT, usrBody)

        if(emailIntent.resolveActivity(packageManager)!=null){
            startActivity(Intent.createChooser(emailIntent,"Choose an app"))
        }

    }
}