package com.example.luckydraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    EditText edit;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);
        edit = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edit.getText().toString();

                //Explicit intent usage ahead

//                Intent i = new Intent(this, secondActivity.class); -> we need a context, but 'this' isn't one
                Intent i = new Intent(getApplicationContext(), secondActivity.class);
                i.putExtra("name", userName);
                startActivity(i);



            }
        });
    }
}
