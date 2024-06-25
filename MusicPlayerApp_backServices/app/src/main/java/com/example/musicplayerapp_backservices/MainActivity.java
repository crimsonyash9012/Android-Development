package com.example.musicplayerapp_backservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // to successfully run the service, we need to mention it in the android manifest XML file
    TextView txt;
    Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start_btn);
        stop = findViewById(R.id.stop_btn);
        txt = findViewById(R.id.textView);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), MyCustomService.class);
                startService(serviceIntent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), MyCustomService.class);
                stopService(serviceIntent);
            }
        });
    }
}
