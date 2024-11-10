package com.tiramisu.sendsms

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.os.Build
import android.provider.Telephony.Sms
import android.telephony.SmsManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var message : EditText
    lateinit var number : EditText
    lateinit var send : Button

    var userMsg :String? = ""
    var userNumber :String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        message = findViewById(R.id.editTextTextMultiLine)
        number = findViewById(R.id.editTextText)
        send = findViewById(R.id.button3)

        send.setOnClickListener {
            userMsg = message.text.toString()
            userNumber = number.text.toString()

            sendSMS(userMsg.toString(), userNumber.toString())

        }
    }

    fun sendSMS(userMsg : String, userNumber : String ){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1)
        }
        else{
            val smsManager :  SmsManager
            if(Build.VERSION.SDK_INT >= 23){
                smsManager = this.getSystemService(SmsManager::class.java)
            }
            else{
                smsManager = SmsManager.getDefault()
            }

            smsManager.sendTextMessage(userNumber, null, userMsg, null, null)

            Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // for the first time when user didn't had the permission but just has granted it

        if(requestCode==1 && grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            val smsManager :  SmsManager
            if(Build.VERSION.SDK_INT >= 23){
                smsManager = this.getSystemService(SmsManager::class.java)
            }
            else{
                smsManager = SmsManager.getDefault()
            }

            smsManager.sendTextMessage(userNumber, null, userMsg, null, null)

            Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_SHORT).show()
        }
    }
}