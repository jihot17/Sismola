package com.example.sismola;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.sismola.adapters.SpinAdapter;
import com.example.sismola.models.dashboard.CurrentDataDevice;
import com.example.sismola.models.dashboard.GDashboard;
import com.example.sismola.models.devices.GDevices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Control_Panel extends Fragment {

    String TAG;
    Context c;
    private ProgressDialog pd;
    private GDashboard lists;

    private TextView viewCahaya, tvLU;
//    private TextView tvDev;
    private TextView viewHujan;
    private TextView viewPHTanah;
    private TextView viewKLTanah;
    private TextView viewSuhutanah;
    private TextView viewKLUDara;
    private TextView viewSuhuUdara;

    Spinner spBar;

    public Control_Panel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_control_panel, container, false);

        viewCahaya = v.findViewById(R.id.itc);
        viewHujan = v.findViewById(R.id.ch);
        viewPHTanah = v.findViewById(R.id.ph);
        viewKLTanah = v.findViewById(R.id.kt);
        viewSuhutanah = v.findViewById(R.id.st);
        viewKLUDara = v.findViewById(R.id.ku);
        viewSuhuUdara = v.findViewById(R.id.su);
//        tvDev = v.findViewById(R.id.tvDevices);
        tvLU = v.findViewById(R.id.tvDate);
        spBar = (Spinner)v.findViewById(R.id.spBar);


        c = getActivity();
        TAG = c.getClass().getSimpleName();

        pd = new ProgressDialog(c);
        pd.setMessage("Loading");
        pd.setCancelable(false);
//        pdShow();

        //devices
        AndroidNetworking.get("http://sismola.com/api/datas.php?req=aJ906CNS0k7RxaK&type=1&owner=rayzerdodo@gmail.com")
                .setPriority(Priority.HIGH)
                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // do anything with response
//                        pdHide();
//                        try {
//                            JSONObject r = response.getJSONObject("response");
//                            JSONArray dvs = r.getJSONArray("devices");
//                            for (int i = 0; i < dvs.length(); i++) {
////                                JSONObject info = dvs.getJSONObject();
////                                String devId = info.getString("device_id");
////                                tvDev.setText(devId);
//                                JSONObject info_device = info.getJSONObject("info_device");
////                                JSONObject device = info.getJSONObject("device_id");
//                                Log.d("cekInfo",info_device.getString("device_id"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
                .getAsObject(GDevices.class, new ParsedRequestListener<GDevices>() {
                    @Override
                    public void onResponse(final GDevices r) {
                        // do anything with response
                        if(r.getResponse().getDevices().size()>0) {
                            SpinAdapter adapter = new SpinAdapter(c, r);
                            spBar.setAdapter(adapter);
                            spBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                                    Toast.makeText(c, lists.getData().get(pos).getValue(), Toast.LENGTH_SHORT).show();
                                    final String v = r.getResponse().getDevices().get(pos).getInfoDevice().getDeviceSerial();
                                    initDatas(v);
                                }

                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });
                        }else {
                            BE.TShort("Sorry, No Data Available");
                        }
                    }
                    public void onError(ANError error) {
                        // handle error
                        pdHide();
                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        Log.d(TAG, "onError message : " + error.getMessage());
                    }
                });

        return v;
    }

    private void initDatas(String serials){
                pdShow();
                AndroidNetworking.get("http://sismola.com/api/datas.php?req=aJ906CNS0k7RxaK&type=3&device="+serials+"&date=17-08-2019&json")
                .setPriority(Priority.HIGH)
                .build()
//                .getAsObject(GDashboard.class, new ParsedRequestListener<GDashboard>() {
//                    @Override
//                    public void onResponse(GDashboard r) {
//                        pdHide();
//                        lists = r;
//                        Log.d("cek",r.toString());
//
////                        if (lists.size() > 0) {
////                            for (int i = 0; i < r.size(); i++) {
////                                viewCahaya.setText(r.get(i).getResponse().getDevices().getCurrentDataDevice().getLightIntensity());
////                                viewHujan.setText(r.get(i).getResponse().getDevices().getCurrentDataDevice().getRainfall());
////                                viewPHTanah.setText(String.valueOf(r.get(i).getResponse().getDevices().getCurrentDataDevice().getPh()));
////                                viewKLTanah.setText(String.valueOf(r.get(i).getResponse().getDevices().getCurrentDataDevice().getSoilTemp()));
////                                viewSuhutanah.setText(String.valueOf(r.get(i).getResponse().getDevices().getCurrentDataDevice().getSoilMoisture()));
////                                viewKLUDara.setText(String.valueOf(r.get(i).getResponse().getDevices().getCurrentDataDevice().getHumidity()));
////                                viewSuhuUdara.setText(String.valueOf(r.get(i).getResponse().getDevices().getCurrentDataDevice().getAirTemp()));
////                            }
////                        } else {
////                            Toast.makeText(c, "No Data Available", Toast.LENGTH_SHORT).show();
////                        }
//
//                        CurrentDataDevice cdv = lists.getResponse().getDevices().getCurrentDataDevice();
//                        viewCahaya.setText(String.valueOf(cdv.getLightIntensity()));
//                        Log.d("cek",String.valueOf(cdv.getLightIntensity()));
//                        viewCahaya.setText(r.getResponse().getDevices().getCurrentDataDevice().getLightIntensity());
//                        viewHujan.setText(r.getResponse().getDevices().getCurrentDataDevice().getRainfall());
//                        viewPHTanah.setText(String.valueOf(r.getResponse().getDevices().getCurrentDataDevice().getPh()));
//                        viewKLTanah.setText(String.valueOf(r.getResponse().getDevices().getCurrentDataDevice().getSoilTemp()));
//                        viewSuhutanah.setText(String.valueOf(r.getResponse().getDevices().getCurrentDataDevice().getSoilMoisture()));
//                        viewKLUDara.setText(String.valueOf(r.getResponse().getDevices().getCurrentDataDevice().getHumidity()));
//                        viewSuhuUdara.setText(String.valueOf(r.getResponse().getDevices().getCurrentDataDevice().getAirTemp()));
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        pdHide();
//                        Toast.makeText(c, error.getErrorDetail(), Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
//                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
//                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
//                        Log.d(TAG, "onError message : " + error.getMessage());
//                    }
//                });
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        pdHide();
                        try {
                            JSONObject r = response.getJSONObject("response");
                            JSONObject dvs = r.getJSONObject("devices");
                            JSONObject cdv = dvs.getJSONObject("current_data_device");
                            JSONObject indev = dvs.getJSONObject("info_device");

                            String devId = indev.getString("device_id");
//                            tvDev.setText(devId);

                            String light_intensity = cdv.getString("light_intensity");
                            String last_update = cdv.getString("last_update");
                            int rainfall = cdv.getInt("rainfall");
                            double ph = cdv.getDouble("ph");
                            double soil_temp = cdv.getDouble("soil_temp");
                            double soil_moisture = cdv.getDouble("soil_moisture");
                            double humidity = cdv.getDouble("humidity");
                            double air_temp = cdv.getDouble("air_temp");

                            tvLU.setText(last_update);
                            viewCahaya.setText(light_intensity);
                            viewHujan.setText(String.valueOf(rainfall));
                            viewPHTanah.setText(String.valueOf(ph));
                            viewKLTanah.setText(String.valueOf(soil_temp));
                            viewSuhutanah.setText(String.valueOf(soil_moisture));
                            viewKLUDara.setText(String.valueOf(humidity));
                            viewSuhuUdara.setText(String.valueOf(air_temp));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        pdHide();
                        Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                        Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        Log.d(TAG, "onError message : " + error.getMessage());
                    }
                });
    }

    private void pdShow() {
        if (!pd.isShowing())
            pd.show();
    }

    private void pdHide() {
        if (pd.isShowing())
            pd.dismiss();
    }

}
