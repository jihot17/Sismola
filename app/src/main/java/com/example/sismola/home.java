package com.example.sismola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class home extends AppCompatActivity {
    private TextView viewCahaya;
    private TextView viewHujan;
    private TextView viewPHTanah;
    private TextView viewKLTanah;
    private TextView viewSuhutanah;
    private TextView viewKLUDara;
    private TextView viewSuhuUdara;

    private RequestQueue mQueque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setContentView(R.layout.fragment_control_panel);
        viewCahaya = findViewById(R.id.itc);
        viewHujan = findViewById(R.id.ch);
        viewPHTanah = findViewById(R.id.ph);
        viewKLTanah = findViewById(R.id.kt);
        viewSuhutanah = findViewById(R.id.st);
        viewKLUDara = findViewById(R.id.ku);
        viewSuhuUdara = findViewById(R.id.su);
        mQueque = Volley.newRequestQueue(this);
        String url = "https://api.myjson.com/bins/13723r";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.getString("success");
                            JSONObject js = response.getJSONObject("response");
                            JSONObject jso = js.getJSONObject("devices");
                            JSONObject info_dev = jso.getJSONObject("info_device");
                            JSONObject C_data_dev = jso.getJSONObject("current_data_device");

                            String devId = info_dev.getString("device_id");

                            String lsUpdateUnix = C_data_dev.getString("last_update_unix");
                            String lsUpdate = C_data_dev.getString("last_update");
                            String LInten = C_data_dev.getString("light_intensity");
                            String SoilM = C_data_dev.getString("soil_moisture");
                            String ph = C_data_dev.getString("ph");
                            String STemp = C_data_dev.getString("soil_temp");
                            String hum = C_data_dev.getString("humidity");
                            String airTemp = C_data_dev.getString("air_temp");
                            String rain = C_data_dev.getString("rainfall");
                            viewCahaya.append("test"+LInten);
                            viewHujan.append(rain);
                            viewPHTanah.append(ph);
                            viewKLTanah.append(SoilM);
                            viewSuhutanah.append(STemp);
                            viewKLUDara.append(hum);
                            viewSuhuUdara.append(airTemp);


                        } catch (Exception err) {
                            err.getMessage();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueque.add(request);
    }
}
