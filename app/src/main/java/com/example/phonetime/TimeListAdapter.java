package com.example.phonetime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder>{

    private final List<String> dateList;
    private final List<String> totalTimeList;
    private final Context context;

    public TimeListAdapter(Context context, List<String> dateList, List<String> totalTimeList){
        this.dateList = dateList;
        this.totalTimeList = totalTimeList;
        this.context = context;
    }

    //Must have
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("oncreateViewHolder", "called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("onbind: ", "Called");
        holder.date.setText(dateList.get(position));
        holder.totalTime.setText(totalTimeList.get(position));
        holder.layout.setOnClickListener(view -> {
            Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, totalTime;
        ConstraintLayout layout;
        public ViewHolder(View itemViews){
            super(itemViews);
            date = itemViews.findViewById(R.id.list_item_date);
            totalTime = itemViews.findViewById(R.id.list_item_totalTime);
            layout = itemViews.findViewById(R.id.list_items_layout);
        }
    }
}
