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
        while(true){
            try {
                //wait for 1 second
                Thread.sleep(1000);

                if (isStopped())
                    break;
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
        if (!data.getDateFromPreference().equals(data.convertTodayCalendarToString()) ){
            data.saveUserDataDate(data.getDateFromPreference(), data.getTimeInSecondFromPreference());

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
