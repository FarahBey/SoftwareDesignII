package com.example.assignment6weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {
    public void fetchData(){
        //TextView textViewone = (TextView)findViewById(R.id.textView1);
        // ...
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.open-meteo.com/v1/forecast?latitude=30.28&longitude=-97.76&hourly=temperature_2m,visibility,precipitation_probability&temperature_unit=fahrenheit";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textViewone.setText("Response is: " + response.substring(0,500));
                        TextView[] daysofWeek =
                                {
                                        findViewById(R.id.textView1),
                                        findViewById(R.id.textView2),
                                        findViewById(R.id.textView3),
                                        findViewById(R.id.textView4),
                                        findViewById(R.id.textView5),
                                        findViewById(R.id.textView6),
                                        findViewById(R.id.textView7)
                                };
                        String Data = response;
                        JSONObject hold;  //= new JSONObject(unparsedData);
                        try {
                            hold = new JSONObject(Data);

                            JSONArray date = hold.getJSONObject("hourly").getJSONArray("time");
                            JSONArray temp = hold.getJSONObject("hourly").getJSONArray("temperature_2m");
                            JSONArray rain = hold.getJSONObject("hourly").getJSONArray("precipitation_probability");
                            JSONArray clear = hold.getJSONObject("hourly").getJSONArray("visibility");

                            for (int i = 0; i < 7; i++) {
                                //To get the day
                                String time = date.getString(i * 24); //depends on the day
                                LocalDateTime time2 = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                                String structuredTimeStamp = time2.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));

                                double avgtemp = 0;
                                int avgrain = 0;
                                double avgvis = 0;

                                int j = i * 24;
                                while (j < (i * 24) + 24) {
                                    avgtemp = avgtemp + temp.getDouble(j);
                                    avgrain = avgrain + rain.getInt(j);
                                    avgvis = avgvis + clear.getDouble(j);
                                    j++;
                                }
                                avgtemp = avgtemp / 24;
                                avgrain = avgrain / 24;
                                avgvis = avgvis / 24;

                                String prettytemp = String.format("%.0f", avgtemp);
                                String prettyvis = String.format("%.2f", avgvis)+" meters";
                                String ahhhh = Integer.toString(avgrain);

                                daysofWeek[i].setText(structuredTimeStamp + ":" +
                                        "\nTemperature - " + prettytemp + "°" +
                                        "\nChance of Rain - " + ahhhh + "%" +
                                        "\nVisibility - " + prettyvis);

                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            //textViewTwo.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
    }
}