package com.example.sismola.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.sismola.R;
import com.example.sismola.models.devices.GDevices;

public class SpinAdapter extends BaseAdapter implements SpinnerAdapter {

    GDevices lists;
    Context c;
    String[] colors = {"#13edea","#e20ecd","#15ea0d"};
    String[] colorsback = {"#FF000000","#FFF5F1EC","#ea950d"};

    public SpinAdapter(Context c, GDevices lists) {
        this.lists = lists;
        this.c = c;
    }

    @Override
    public int getCount() {int a;
        if (lists != null && !lists.getResponse().getDevices().isEmpty()) {
            a = lists.getResponse().getDevices().size();
        } else {
            a = 0;
        }

        return a;
    }

    @Override
    public Object getItem(int position) {
        return lists.getResponse().getDevices().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  View.inflate(c, R.layout.spinner_barc, null);
        TextView textView = (TextView) view.findViewById(R.id.tvSpBarc);
        textView.setText(lists.getResponse().getDevices().get(position).getInfoDevice().getDeviceId());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view;
        view =  View.inflate(c, R.layout.spinner_barc_dropdown, null);
        final TextView textView = (TextView) view.findViewById(R.id.tvSpBarcDropdown);
        final TextView tvValue = (TextView) view.findViewById(R.id.tvSpBarcValue);
        textView.setText(lists.getResponse().getDevices().get(position).getInfoDevice().getDeviceId());
        tvValue.setText(lists.getResponse().getDevices().get(position).getInfoDevice().getDeviceSerial());
//        textView.setTextColor(Color.parseColor(colors[position]));
//        textView.setBackgroundColor(Color.parseColor(colorsback[position]));
        return view;
    }
}