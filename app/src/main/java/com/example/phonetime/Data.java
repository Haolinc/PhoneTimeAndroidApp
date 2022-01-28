package com.example.phonetime;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Data {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences userData;
    private static Data dataInstance = null;
    private Data(){}

    public static Data getInstance(Context context){
        if (dataInstance == null)
            dataInstance = new Data();
        sharedPreferences = context.getSharedPreferences("misc", Context.MODE_PRIVATE);
        userData = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        return dataInstance;
    }

    //---------------------------userData---------------------------
    //date string store in calendar millis
    public void saveUserDataDate(String dateString, int timeInSecond){
        userData.edit().putInt(dateString, timeInSecond).apply();
    }
    public Map<String, ?> getUserDataDate(){
        return userData.getAll();
    }


    //---------------------------misc---------------------------
    //save total second in sharedPreferences
    public void saveTimeInSecond (int timeInSecond){
        sharedPreferences.edit().putInt("second", timeInSecond).apply();
    }
    //make sharedPreferences date to today
    public void updateDate(){
        sharedPreferences.edit().putString("date", convertTodayCalendarToString()).apply();
    }
    public String convertTodayCalendarToString(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/"  + cal.get(Calendar.YEAR);
    }
    //check if the app is open the first time
    public void saveCalled(boolean called){
        sharedPreferences.edit().putBoolean("called", called).apply();
    }

    public boolean getCalled(){return sharedPreferences.getBoolean("called", false);}
    public int getTimeInSecondFromPreference(){
        return sharedPreferences.getInt("second", 0);
    }
    public String getDateFromPreference(){
        return sharedPreferences.getString("date", "");
    }


}
