package com.example.photoalbum.utils

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager

class ControlPermission {
    companion object{
        fun checkPermission(context : Context) : Boolean{
            return if(Build.VERSION.SDK_INT >= 33){
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_MEDIA_IMAGES
                )== PackageManager.PERMISSION_GRANTED
            }
            else{
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )== PackageManager.PERMISSION_GRANTED

            }
        }
    }
}