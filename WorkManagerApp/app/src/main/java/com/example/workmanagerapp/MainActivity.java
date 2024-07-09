package com.example.workmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        test = findViewById(R.id.test);


        // using to send ours data to the worker
        Data data  = new Data.Builder()// construct data object & add key value pairs to it
                        .putInt("max_limit", 500).build();


        // constraints - only for work manager
        Constraints constraints = new Constraints.Builder() // specifying constraint for a request
                                    .setRequiresCharging(true)
                                    .build(); // app will only work if the phone is charging


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                                "This will be displayed only if the phone is charging",
                                Toast.LENGTH_SHORT).show();
            }
        });

        // Making use of worker
        WorkRequest wr = new OneTimeWorkRequest
                            .Builder(myWorker.class)
                                .setConstraints(constraints)
                                .setInputData(data)
                                .build(); //request to  execute a one time background task

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // enqueue a work request
                WorkManager.getInstance(getApplicationContext()).enqueue(wr);
            }
        });

        // monitoring the status of work manager

        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(wr.getId()) // live data object which observes the status ofa specific work request
                .observe(
                        this, // owner of the lifecycle
                        new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if(workInfo==null){
                                    Toast.makeText(MainActivity.this, "Status: "+workInfo.getState().name(), Toast.LENGTH_SHORT).show();
                                    if(workInfo.getState().isFinished()){
                                        Data data1 = workInfo.getOutputData();
                                        Toast.makeText(MainActivity.this,
                                                ""+data1.getString("msg"),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }); // setting up an observer on live data object

    }
}