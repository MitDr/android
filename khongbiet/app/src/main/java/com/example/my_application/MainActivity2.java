package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private ArrayList<Contact> contactList;
    protected EditText txtName;
    protected EditText txtPhone;
    protected FloatingActionButton btnAdd;
    protected ListView listStudent;
    protected MyAdapter listAdapt;
    protected ArrayList listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtName = findViewById(R.id.editTextText3);
        txtPhone = findViewById(R.id.editTextText4);
        btnAdd = findViewById(R.id.floatingActionButton2);
        listStudent = findViewById(R.id.listView);
        contactList = new ArrayList<Contact>();
//        listName = new ArrayList();
//        listName.add("Tran van A");
//        listName.add("Tran Van B");
        contactList.add(new Contact(1, "img", "Nguyen Van A", "0954096054"));
        contactList.add(new Contact(2, "img", "Nguyen Van A", "0954096054"));
        contactList.add(new Contact(3, "img", "Nguyen Van A", "0954096054"));


//        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listName);
//        listStudent.setAdapter(adapter);\
        listAdapt = new MyAdapter(contactList, this);
        listStudent.setAdapter(listAdapt);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String phone = txtPhone.getText().toString();
                contactList.add(new Contact(contactList.size() + 1, "img", name, phone));
                listAdapt.notifyDataSetChanged();
                txtName.setText("");
                txtPhone.setText("");
            }
        });
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactList.remove(position);
                listAdapt.notifyDataSetChanged();
            }
        });
    }

}