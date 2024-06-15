package com.example.volumecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cylinder extends AppCompatActivity {

    EditText radi, height;
    TextView title, result;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cylinder);

        radi = findViewById(R.id.radi);
        height = findViewById(R.id.height1);
        title = findViewById(R.id.titleCyl);
        result = findViewById(R.id.result);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String radius = radi.getText().toString();
                String height2 = height.getText().toString();

                int r = Integer.parseInt(radius);
                int h = Integer.parseInt(height2);

                double volume =    3.14159 * r * r * h;
                result.setText("V = " + volume+ " m^3");

            }
        });
    }
}