package com.example.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class JobIntent : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Log.d("Service", "Job intent service started")
        Log.d("ServiceThread", Thread.currentThread().name)
    }

    companion object{
        fun myBackgroundService(context: Context, intent: Intent){
            enqueueWork(context, JobIntent::class.java, 1,intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Service", "Job intent service stopped")
    }
}