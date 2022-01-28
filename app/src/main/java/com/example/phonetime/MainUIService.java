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

public class MainUIService {
    Context context;
    public MainUIService(Context context){
        this.context = context;
    }
    //change pass in textView every second
    public void mainGui(Handler handler, TextView textView, Timer timer){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run(){
                        textView.setText(getUsedTime());
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
    private String getUsedTime(){
        int totalSecond = Data.getInstance(context).getTimeInSecondFromPreference();
        return "You have used your phone for \n" + convertTimeToString(totalSecond) + "\ntoday.";
    }

    public String convertTimeToString(int totalSecond){
        int second = totalSecond % 60;
        int minute = (totalSecond/60)%60;
        int hour = totalSecond/3600;
        String time = "";
        if (hour != 0)
            time = hour + " hour ";
        if (minute != 0)
            time = time + minute + " minute ";
        if (second != 0)
            time = time + second + " second";
        return time;
    }

    //incomplete
    public void notificationDisplay(){

    }

}
