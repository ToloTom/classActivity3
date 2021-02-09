package com.example.classactivity3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> weathers; //populate this from the main method

    public WeatherAdapter(List<Weather> weathers){
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View weatherView = inflater.inflate(R.layout.item_weather, parent, false);

        ViewHolder viewHolder = new ViewHolder(weatherView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Weather weather = weathers.get(position);

        holder.textView_date.setText(weather.getDate());
        holder.textView_description.setText(weather.getDescription());
        holder.textView_temp.setText(weather.getFeelsLike() + " ℉");

    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_date;
        TextView textView_temp;
        TextView textView_description;

        public ViewHolder(View itemView){
            super(itemView);

            textView_date = itemView.findViewById(R.id.textView_Date);
            textView_temp = itemView.findViewById(R.id.textView_Temp);
            textView_description = itemView.findViewById(R.id.textView_Description);
        }
    }
}
