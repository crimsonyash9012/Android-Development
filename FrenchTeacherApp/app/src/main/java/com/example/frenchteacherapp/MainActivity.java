package com.example.frenchteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button black,green,purple,red,yellow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        black = findViewById(R.id.blackBtn);
        green = findViewById(R.id.greenBtn);
        purple = findViewById(R.id.purpleBtn);
        red = findViewById(R.id.redBtn);
        yellow = findViewById(R.id.yellowBtn);

        black.setOnClickListener(this);
        green.setOnClickListener(this);
        purple.setOnClickListener(this);
        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int clickedBtn = v.getId();
        if(clickedBtn==R.id.redBtn){
            PlaySounds(R.raw.red);
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
        }else if(clickedBtn==R.id.blackBtn){
            PlaySounds(R.raw.black);
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
        }else if(clickedBtn==R.id.greenBtn){
            PlaySounds(R.raw.green);
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
        }else if(clickedBtn==R.id.yellowBtn){
            PlaySounds(R.raw.yellow);
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
        }else if(clickedBtn==R.id.purpleBtn){
            PlaySounds(R.raw.purple);
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
        }

    }
    public void PlaySounds(int id){
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(),id);
        mp.start();
    }
}