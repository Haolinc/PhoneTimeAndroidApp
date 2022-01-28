package com.example.phonetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);
        Log.d("timelist: ", "onCreated");
        init();
    }
    private void init(){
        Log.d("timelist: ", "init");
        MainUIService service = new MainUIService(this);
        ArrayList <String> dates = new ArrayList<>();
        ArrayList <String> totalTime = new ArrayList<>();
        Map<String, ?> map = Data.getInstance(this).getUserDataDate();
        for (String date: map.keySet()) {
            Log.d("timelist key:", date);

            dates.add(date);
            String totalTimeConversion = service.convertTimeToString((int)map.get(date));
            Log.d("timelist totaltime: ", totalTimeConversion);
            totalTime.add(totalTimeConversion);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TimeListAdapter timeListAdapter = new TimeListAdapter(this, dates, totalTime);
        recyclerView.setAdapter(timeListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}