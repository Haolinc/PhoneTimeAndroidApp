package com.example.phonetime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MainUIService mainUIService;
    TextView mainGuiText;
    Data data;
    Timer timer;
    Handler guiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variables setup
        mainUIService = new MainUIService(this);
        mainGuiText = findViewById(R.id.TimeText);
        guiHandler = new Handler();
        data = Data.getInstance(this);
        //if the app is open first time
        if (!data.getCalled()) {
            //set the date to today
            data.updateDate();
            data.saveCalled(true);
        }

        Button graphButton = findViewById(R.id.main_graphButton);
        graphButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserStatisticActivity.class);
            startActivity(intent);
        });

        Button listButton = findViewById(R.id.main_listButton);
        listButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, TimeListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

        //intent to repeat doWork in WorkerClass every 15 minutes
        PeriodicWorkRequest saveRequest = new PeriodicWorkRequest.Builder(WorkerClass.class, 15, TimeUnit.MINUTES)
                .build();
        //intent to replace previous doWork background process, so the work can continue after user start the app
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("Keep", ExistingPeriodicWorkPolicy.REPLACE, saveRequest);


        //create thread to make textView change base on shared preference
        timer = new Timer();
        mainUIService.mainGui(guiHandler, mainGuiText, timer);

    }

    @Override
    protected void onPause(){
        super.onPause();
        //stop the thread to save space
        timer.cancel();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}