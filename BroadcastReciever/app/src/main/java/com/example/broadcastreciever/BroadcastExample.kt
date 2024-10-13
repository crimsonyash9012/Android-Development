package com.example.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastExample : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val isAirplane : Boolean = intent!!.getBooleanExtra("state", false)

        if(isAirplane){
            Toast.makeText(context, "Airplane Mode is ON", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context, "Airplane Mode is OFF", Toast.LENGTH_LONG).show()
        }
    }
}