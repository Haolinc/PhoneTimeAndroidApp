package com.example.phonetime;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserStatisticActivity extends AppCompatActivity {

    BarChart barChart;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_statistic);
        barChart = findViewById(R.id.barChart);
        data = Data.getInstance(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setUpBarChart();
    }

    private void setUpBarChart(){
        data.saveUserDataDate(data.convertTodayCalendarToString(), data.getTimeInSecondFromPreference());
        data.saveUserDataDate("3/27/2022", 523102);
        data.saveUserDataDate("1/28/2021", 236386);
        Map<String, ?> map = Data.getInstance(this).getUserDataDate();
        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (String key : map.keySet()){
            labels.add(key);
            values.add((Integer)map.get(key));
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i =0;i <values.size();i++){
            entries.add(new BarEntry(i+1, values.get(i)));
        }


        BarDataSet barDataSet = new BarDataSet(entries, "Data");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);

        XAxis xAxis= barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        barChart.setFitBars(true);
        barChart.setClickable(true);
        barChart.setKeepScreenOn(true);
        barChart.setBorderWidth(20);
        barChart.setData(data);



    }
}