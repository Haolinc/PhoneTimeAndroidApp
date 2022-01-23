package com.example.phonetime;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Service {
    //change pass in textView every second
    public void mainGui(Context context, Handler handler, TextView textView, Timer timer){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run(){
                        textView.setText(getUsedTime(context));
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
    private String getUsedTime(Context context){
        int totalSecond = Data.getInstance(context).getTimeInSecondFromPreference();
        int second = totalSecond % 60;
        int minute = (totalSecond/60)%60;
        int hour = totalSecond/3600;
        return "You have used your phone for \n" + hour + " Hour " +
                minute + " Minute " + second + " Second \ntoday.";
    }

    //incomplete
    public void notificationDisplay(){

    }

}
