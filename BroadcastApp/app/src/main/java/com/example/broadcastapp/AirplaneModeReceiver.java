package com.example.broadcastapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // called when broadcast receiver receives a broadcast

        if(intent.getAction()!=null && intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){
            boolean isAirplaneModeOn =  intent.getBooleanExtra("state",false);
            String msg = isAirplaneModeOn ? "Airplane mode is ON" : "Airplane mode is OFF";
            Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();


        }
    }
}
