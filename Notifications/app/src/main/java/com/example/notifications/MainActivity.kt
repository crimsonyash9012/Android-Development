package com.example.notifications

import android.Manifest
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notifications.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMin = calendar.get(Calendar.MINUTE)

        mainBinding.button.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(currentHour)
                .setMinute(currentMin)
                .setTitleText("Set notification time")
                .build()

            timePicker.show(supportFragmentManager,"1")
            timePicker.addOnPositiveButtonClickListener{
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

                val intent = Intent(applicationContext, NotificationReciever::class.java)
                val pendingIntent = if(Build.VERSION.SDK_INT>=23){
                    PendingIntent.getBroadcast(applicationContext,
                        100,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)}
                    else{
                        PendingIntent.getBroadcast(applicationContext,
                            100,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT)
                    }

                    val alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP
                        ,calendar.timeInMillis
                        ,AlarmManager.INTERVAL_DAY
                        ,pendingIntent)

                    Toast.makeText(applicationContext, "The alarm has been set", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    /*

    fun startNotification(){

        val builder = NotificationCompat.Builder(this@MainActivity,CHANNEL_ID)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),200)
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(CHANNEL_ID,"1", NotificationManager.IMPORTANCE_DEFAULT)
                val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
                builder.setSmallIcon(R.drawable.small_icon)
                    .setContentTitle("Title")
                    .setContentText("Notification Text")
            }else{
                builder.setSmallIcon(R.drawable.small_icon)
                    .setContentTitle("Title")
                    .setContentText("Notification Text")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            val notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
            notificationManagerCompat.notify(1,builder.build())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 200 && grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            startNotification()
        }
    }


     */
