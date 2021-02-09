package com.example.classactivity3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Weather> weathers;
    private RecyclerView recyclerView;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        textView = findViewById(R.id.textView_cityName);
        recyclerView = findViewById(R.id.recyclerView_weather);
        weathers = new ArrayList<>();

        textView.setText(intent.getStringExtra("title"));

        if(!textView.getText().equals("No City Found")) {

            JSONArray weatherJSON = null;
            try {

                weatherJSON = new JSONArray(intent.getStringExtra("list"));
                for (int i = 0; i < weatherJSON.length(); i++) {
                    JSONObject weatherObject = weatherJSON.getJSONObject(i);

                    String[] time = weatherObject.get("dt_txt").toString().split(" ");

                    Weather weather = new Weather(time[0] + "\n\n" + time[1], weatherObject.getJSONArray("weather").getJSONObject(0).get("description").toString(), weatherObject.getJSONObject("main").get("feels_like").toString());
                    weathers.add(weather);
                }

                WeatherAdapter adapter = new WeatherAdapter(weathers);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }
}
