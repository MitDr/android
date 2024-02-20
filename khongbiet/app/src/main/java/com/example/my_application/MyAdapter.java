package com.example.my_application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contact> data;
    private ArrayList<Contact> dataBackUp;
    private Activity context;
    private LayoutInflater inflater;


    public MyAdapter(ArrayList<Contact> data, Activity activity){
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public MyAdapter(ArrayList<Contact> data, ArrayList<Contact> dataBackUp, Activity context, LayoutInflater inflater) {
        this.data = data;
        this.dataBackUp = dataBackUp;
        this.context = context;
        this.inflater = inflater;
    }
    public MyAdapter() {
    }

    public ArrayList<Contact> getData() {
        return data;
    }

    public void setData(ArrayList<Contact> data) {
        this.data = data;
    }

    public void test(){
        //buble sort
    }

    public ArrayList<Contact> getDataBackUp() {
        return dataBackUp;
    }

    public void setDataBackUp(ArrayList<Contact> dataBackUp) {
        this.dataBackUp = dataBackUp;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.contact_item, null);
            ImageView imageProfile = v.findViewById(R.id.imageView);
            TextView tvName = v.findViewById(R.id.textView3);
            TextView tvPhone = v.findViewById(R.id.textView4);
            tvName.setText(data.get(position).getName());
            tvPhone.setText(data.get(position).getPhone());
//            imageProfile.setImageResource(data.get(position).getImages().);
        }
        return v;
    }
}
