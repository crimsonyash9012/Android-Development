package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView welcome, counter;
    Button myBtn;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtn = findViewById(R.id.myBtn);
        counter = findViewById(R.id.counter);
        welcome = findViewById(R.id.welcome);

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(count!=50000000){
                    counter.setText("" + increaseCounter());
                }

            }
        });

    }

    public int increaseCounter(){
        return ++count;
    }
}