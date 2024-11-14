package com.example.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class NotificationReciever : BroadcastReceiver() {

    private val CHANNEL_ID = "1"


    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val builder = context?.let { NotificationCompat.Builder(it,CHANNEL_ID) }


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(CHANNEL_ID,"1", NotificationManager.IMPORTANCE_HIGH)
                val manager : NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
                builder?.setSmallIcon(R.drawable.small_icon)
                    ?.setContentTitle("Title")
                    ?.setContentText("Notification Text")
            }else{
                builder?.setSmallIcon(R.drawable.small_icon)
                    ?.setContentTitle("Title")
                    ?.setContentText("Notification Text")
                    ?.setPriority(NotificationCompat.PRIORITY_HIGH)
            }
            // runs if context !=null
            val notificationManagerCompat = context?.let { NotificationManagerCompat.from(it) }
            notificationManagerCompat?.notify(1,builder!!.build())

    }


}