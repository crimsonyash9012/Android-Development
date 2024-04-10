package com.example.luckydraw;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class secondActivity extends AppCompatActivity {

    TextView luckyTxt;
    TextView number;
    Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        int random = generateRandom();
        number.setText("" + random);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random);
            }
        });
    }

    public int generateRandom(){
        Random random = new Random();
        int upper_limit = 1000;
        return random.nextInt(upper_limit);
    }

    public void shareData(String userName, int random){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // Additional info
        i.putExtra(Intent.EXTRA_SUBJECT, userName + " got his Lucky Number!!");
        i.putExtra(Intent.EXTRA_TEXT, "His Lucky Number is " + random);

        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}