package com.example.phonetime;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

public class Data {
    public static SharedPreferences sharedPreferences;
    private static Data dataInstance = null;
    private Data(){}

    public static Data getInstance(Context context){
        if (dataInstance == null)
            dataInstance = new Data();
        sharedPreferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        return dataInstance;
    }

    //save total second in shared preferences
    public void saveTimeInSecond (int timeInSecond){
        sharedPreferences.edit().putInt("second", timeInSecond).apply();
    }
    //make shared preferences date to today
    public void updateDate(){
        sharedPreferences.edit().putInt("date", Calendar.getInstance().get(Calendar.DAY_OF_YEAR)).apply();
    }
    //check if the app is open the first time
    public void saveCalled(boolean called){
        sharedPreferences.edit().putBoolean("called", called).apply();
    }

    public boolean getCalled(){return sharedPreferences.getBoolean("called", false);}
    public int getTimeInSecondFromPreference(){
        return sharedPreferences.getInt("second", 0);
    }
    public int getDateFromPreference(){
        return sharedPreferences.getInt("date", 0);
    }


}
