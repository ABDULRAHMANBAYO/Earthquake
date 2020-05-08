package com.example.earthquake.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.earthquake.R;
import com.example.earthquake.model.EarthQuake;
import com.example.earthquake.utils.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuakesListActivity extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ListView listView;
    private RequestQueue queue;
    private ArrayAdapter arrayAdapter;
    private List<EarthQuake> quakeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes_list);

        quakeList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);


        queue = Volley.newRequestQueue(this);

        arrayList = new ArrayList<>();

        getAllQuakes(Constants.url);
    }

    void getAllQuakes(String url) {
        final EarthQuake earthQuake = new EarthQuake();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray features = response.getJSONArray("features");
                    for (int i = 0; i < Constants.LIMIT; i++) {
                        JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
                        Log.d("properties", properties.getString("detail"));
                        JSONObject geometry = features.getJSONObject(i).getJSONObject("geometry");
                        JSONArray coordinates = geometry
                                .getJSONArray("coordinates");
                        double log = coordinates.getDouble(0);
                        double lat = coordinates.getDouble(1);

                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(properties.getLong("time"))).getTime());

                        earthQuake.setDetailLink(properties.getString("detail"));
                        earthQuake.setLat(lat);
                        earthQuake.setLog(log);
                        earthQuake.setMagnitude(properties.getDouble("mag"));
                        earthQuake.setPlace(properties.getString("place"));
                        earthQuake.setTime(properties.getLong("time"));
                        earthQuake.setType(properties.getString("type"));


                        arrayList.add(earthQuake.getPlace());

                        arrayAdapter = new ArrayAdapter<>(QuakesListActivity.this,
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1, arrayList);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getApplicationContext(),"Clicked: " + position,Toast.LENGTH_LONG).show();

                            }
                        });
                        arrayAdapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        queue.add(objectRequest);
    }
}
