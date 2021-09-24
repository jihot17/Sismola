package com.example.sismola;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.sismola.models.dashboard.CurrentDataDevice;
import com.example.sismola.models.dashboard.GDashboard;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//    private TextView viewCahaya;
//    private TextView viewHujan;
//    private TextView viewPHTanah;
//    private TextView viewKLTanah;
//    private TextView viewSuhutanah;
//    private TextView viewKLUDara;
//    private TextView viewSuhuUdara;

//    private RequestQueue mQueque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_control_panel);
//        viewCahaya = findViewById(R.id.itc);
//        viewHujan = findViewById(R.id.ch);
//        viewPHTanah = findViewById(R.id.ph);
//        viewKLTanah = findViewById(R.id.kt);
//        viewSuhutanah = findViewById(R.id.st);
//        viewKLUDara = findViewById(R.id.ku);
//        viewSuhuUdara = findViewById(R.id.su);

//        mQueque = Volley.newRequestQueue(this);
//        String url = "https://api.myjson.com/bins/13723r";
//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            response.getString("success");
//                            JSONObject js = response.getJSONObject("response");
//                            JSONObject jso = js.getJSONObject("devices");
//                            JSONObject info_dev = jso.getJSONObject("info_device");
//                            JSONObject C_data_dev = jso.getJSONObject("current_data_device");
//
//                            String devId = info_dev.getString("device_id");
//
//                            String lsUpdateUnix = C_data_dev.getString("last_update_unix");
//                            String lsUpdate = C_data_dev.getString("last_update");
//                            String LInten = C_data_dev.getString("light_intensity");
//                            String SoilM = C_data_dev.getString("soil_moisture");
//                            String ph = C_data_dev.getString("ph");
//                            String STemp = C_data_dev.getString("soil_temp");
//                            String hum = C_data_dev.getString("humidity");
//                            String airTemp = C_data_dev.getString("air_temp");
//                            String rain = C_data_dev.getString("rainfall");
//                            viewCahaya.append("test" + LInten);
//                            viewHujan.append(rain);
//                            viewPHTanah.append(ph);
//                            viewKLTanah.append(SoilM);
//                            viewSuhutanah.append(STemp);
//                            viewKLUDara.append(hum);
//                            viewSuhuUdara.append(airTemp);
//
//
//                        } catch (Exception err) {
//                            err.getMessage();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mQueque.add(request);

        ////////
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Control_Panel fragment = new Control_Panel();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Control_Panel fragment = new Control_Panel();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {
            G_Intensitas_Cahaya fragment = new G_Intensitas_Cahaya();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            G_Curah_Hujan fragment = new G_Curah_Hujan();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_tools) {
            G_PH_Tanah fragment = new G_PH_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {
            G_Kelembapan_Tanah fragment = new G_Kelembapan_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_send) {
            G_Suhu_Tanah fragment = new G_Suhu_Tanah();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.parent) {
            G_Kelembapan_Udara fragment = new G_Kelembapan_Udara();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.percent) {
            G_Suhu_Udara fragment = new G_Suhu_Udara();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLaoyut, fragment);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
