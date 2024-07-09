package com.example.workmanagerapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class myWorker extends Worker {

    public myWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    // doWork() - runs asynchronously on a background
    // thread provided by work manager
    @NonNull
    @Override
    public Result doWork() {

        Data data = getInputData();

        int countingLimit = data.getInt("max_limit",0);

        // count to 1000
        for(int i=0; i<countingLimit; i++){
            Log.i("TAGY", "Count is " + i);
        }

        // sending data and done notification
        Data dataToSend = new Data.Builder()
                                .putString("msg", "Task Done Successfully").build();
        return Result.success(dataToSend);
    }
}
