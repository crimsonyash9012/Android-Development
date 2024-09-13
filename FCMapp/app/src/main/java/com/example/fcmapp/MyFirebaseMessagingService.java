package com.example.fcmapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.v(TAG, "From: " + message.getFrom());

        // check for data payload in message
        if(message.getData().size()>0){
            Log.v(TAG, "Message Data Payload: "+message.getData());
            if(true){
                scheduleJob();
            }else handleNow();
        }

        // checking for notification payload in message
        if(message.getNotification()!=null){
            Log.v(TAG, "Message Notification Body: " + message.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "onNewToken"+token);
        sendRegistrationToServer(token);
    }

    private void scheduleJob(){
        OneTimeWorkRequest work  = new OneTimeWorkRequest
                .Builder(MyWorker.class).build();
        WorkManager.getInstance(this).beginWith(work).enqueue();
    }
    private void handleNow(){
        Log.v(TAG,"Short Lived Task is Done!");
    }

    private void sendNotification(String body){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,i, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificaitonBuilder = new NotificationCompat.Builder(
                this,channelId
        ).setSmallIcon(R.drawable.notf)
                .setContentTitle(getString(R.string.fcm_message))
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService((Context.NOTIFICATION_SERVICE));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.0){
            NotificationChannel channel = new NotificationChannel(channelId
            ,"Channel Human readable Title", NotificationManager.IMPORTANCE_DEFAULT);


        }


    }
}
