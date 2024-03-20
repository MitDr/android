package com.example.my_application;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {
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
            v = inflater.inflate(R.layout.contact_item, null);
            ImageView imageProfile = v.findViewById(R.id.imageView);
            TextView tvName = v.findViewById(R.id.textView3);
            TextView tvPhone = v.findViewById(R.id.textView4);
            tvName.setText(data.get(position).getName());
            tvPhone.setText(data.get(position).getPhone());
            Uri uri = Uri.parse(data.get(position).getImages());
            imageProfile.setImageURI(uri);
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (dataBackUp == null) {
                    dataBackUp = new ArrayList<>(data);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = dataBackUp.size();
                    results.values = dataBackUp;
                } else {
                    ArrayList<Contact> filteredList = new ArrayList<>();
                    for (Contact contact : dataBackUp) {
                        if (contact.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(contact);
                        }
                    }
                    results.count = filteredList.size();
                    results.values = filteredList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = new ArrayList<Contact>();
                ArrayList<Contact> tmp = (ArrayList<Contact>) results.values;
                for (Contact contact : tmp) {
                    data.add(contact);
                }
                notifyDataSetChanged();
            }
        };
        return f;
    }
}
