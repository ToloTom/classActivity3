package com.example.classactivity3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText user_input;
    private Button button_go;
    private RequestParams params = new RequestParams();
    private static AsyncHttpClient client = new AsyncHttpClient();
    private String api_url = "https://api.openweathermap.org/data/2.5/forecast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_input = findViewById(R.id.textInputEditText_cityName);
        button_go = findViewById(R.id.go_button);

        button_go.setOnClickListener(v -> {
            launchNextActivity(v);
        });
    }

    private void launchNextActivity(View view){
        params.put("q", user_input.getText());
        params.put("Appid", "746a94822b8753f1ce5ac64ad873b8f9");
        params.put("units", "imperial");

        client.get(api_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject json = new JSONObject(new String(responseBody));

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra("title",json.getJSONObject("city").get("name").toString() + ", " + json.getJSONObject("city").get("country").toString());
                    intent.putExtra("list", json.getJSONArray("list").toString());

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    intent.putExtra("title", "No City Found");
                    startActivity(intent);
            }
        });
    }
}