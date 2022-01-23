package com.example.phonetime;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Calendar;

public class WorkerClass extends Worker {
    Context context;
    Data data;
    public WorkerClass(Context context, WorkerParameters params){
        super(context, params);
        this.context = context;
        data = Data.getInstance(context);
    }

    @NonNull
    @Override
    public Result doWork(){
        for (int i=0;i<900;i++){
            try {
                //wait for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //add one on shared preference
            backgroundCounting(context);
        }

        return Result.success();
    }

    private void backgroundCounting(Context context){
        Log.d("workerThread:", ""+data.getTimeInSecondFromPreference());

        //if the date change, reset the time to 0
        if (data.getDateFromPreference() != Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
            data.updateDate();
            data.saveTimeInSecond(0);
        }

        //only add when using the phone
        PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        if (powerManager.isScreenOn()){
            data.saveTimeInSecond(data.getTimeInSecondFromPreference()+1);
        }
    }
}
