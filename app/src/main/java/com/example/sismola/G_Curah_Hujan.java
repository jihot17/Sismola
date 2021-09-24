package com.example.sismola;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.example.sismola.adapters.SpinAdapter;
import com.example.sismola.models.dashboard.GDashboard;
import com.example.sismola.models.devices.GDevices;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class G_Curah_Hujan extends Fragment implements OnChartValueSelectedListener {
    private RequestQueue mQueque;
    private LineChart chart;
    String[] date, rainfall, time;
    Spinner spBar;
    String TAG;
    Context c;
    LinearLayout loTime;
    private ProgressDialog pd;
    private GDashboard lists;
    private TextView tvLU;
    public G_Curah_Hujan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_g__curah__hujan, container, false);
        chart = rootView.findViewById(R.id.chart1);
        tvLU = rootView.findViewById(R.id.tvDate);
        spBar = (Spinner)rootView.findViewById(R.id.spBar);
        loTime = rootView.findViewById(R.id.loTime);
        c = getActivity();
        TAG = c.getClass().getSimpleName();
        loTime.setVisibility(View.INVISIBLE);
        pd = new ProgressDialog(c);
        pd.setMessage("Loading");
        pd.setCancelable(false);

        AndroidNetworking.get("http://sismola.com/api/datas.php?req=aJ906CNS0k7RxaK&type=1&owner=rayzerdodo@gmail.com")
                .setPriority(Priority.HIGH)
                .build()
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
//                                    initDatas(v);
                                    show_graph(v);
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

//        show_graph();

        return rootView;
    }

    private void pdShow() {
        if (!pd.isShowing())
            pd.show();
    }

    private void pdHide() {
        if (pd.isShowing())
            pd.dismiss();
    }

    private void show_graph(String serial){
        loTime.setVisibility(View.INVISIBLE);
        mQueque = Volley.newRequestQueue(getContext()   );
//        String url = "http://sismola.com/api/datas.php?req=aJ906CNS0k7RxaK&type=3&device=SmUvUnpmQlo1cXQ0aUVtZERjVmFmZz&date=17-08-2019&json";
        String url = "http://sismola.com/api/datas.php?req=aJ906CNS0k7RxaK&type=3&device="+serial+"&date=17-08-2019&json";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.getString("success");

                            JSONObject r = response.getJSONObject("response");
                            JSONObject dvs = r.getJSONObject("devices");
                            JSONObject cdv = dvs.getJSONObject("current_data_device");
                            String last_update = cdv.getString("last_update");
                            tvLU.setText(last_update);

                            JSONObject js = response.getJSONObject("response");
                            JSONObject jso = js.getJSONObject("devices");
                            JSONObject info_dev = jso.getJSONObject("info_device");
                            JSONObject C_data_dev = jso.getJSONObject("current_data_device");
                            JSONArray data_dev = jso.getJSONArray("data_device");
                            date = new String[data_dev.length()];
                            rainfall = new String[data_dev.length()];
                            time = new String[data_dev.length()];
                            for(int i = 0; i < data_dev.length(); i++){
                                JSONObject json = data_dev.getJSONObject(i);
                                date[i] = json.getString("date");
                                rainfall[i] = json.getString("rainfall");
                                time[i] = "00:00";
                                Log.d("CETAK",date[i]+" - "+rainfall[i]+" - "+time[i]);
                            }
                            // add data
                            setData(date.length, 180);
//        setData(date.length, 180);

                            // draw points over time
                            chart.animateX(1500);

                            // get the legend (only possible after setting data)
                            Legend l = chart.getLegend();

                            // draw legend entries as lines
                            l.setForm(Legend.LegendForm.LINE);
                            loTime.setVisibility(View.VISIBLE);




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

        {   // // Chart Style // //

            // background color
            chart.setBackgroundColor(Color.WHITE);
            // disable description text
            chart.getDescription().setEnabled(false);
            // enable touch gestures
            chart.setTouchEnabled(true);

            //Tambahan
//            chart.getAxisLeft().setDrawLabels(false);
            chart.getAxisRight().setDrawLabels(false);
            chart.getXAxis().setDrawLabels(false);
            chart.getLegend().setEnabled(false);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart);
            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);
        }
        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
//            final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
//            yAxis.setAxisMaximum(200f);
//            yAxis.setAxisMinimum(-50f);
        }


        /*{   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(4000f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            llXAxis.setTypeface(Typeface.SERIF);

            LimitLine ll1 = new LimitLine(8000f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTypeface(Typeface.SERIF);

            LimitLine ll2 = new LimitLine(0f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
            ll2.setTypeface(Typeface.SERIF);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);

        }*/



    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

//            float val = (float) (Math.random() * range) - 30;
            float val = (float) Float.parseFloat(rainfall[i]);
//            float val = (float) Float.parseFloat("120");
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "Curah Hujan");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOW HIGH", "low: " + chart.getLowestVisibleX() + ", high: " + chart.getHighestVisibleX());
        Log.i("MIN MAX", "xMin: " + chart.getXChartMin() + ", xMax: " + chart.getXChartMax() + ", yMin: " + chart.getYChartMin() + ", yMax: " + chart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {

    }

}
