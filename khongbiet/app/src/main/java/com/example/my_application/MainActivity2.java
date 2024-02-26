package com.example.my_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("Id");
        String name = b.getString("Name");
        String phone = b.getString("Phone");

        Contact newcontact = new Contact(id,"Image",name,phone);

        if(requestCode==100 && resultCode==150){
            contactList.add(newcontact);
            listAdapt.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu,menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contactmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuSortName){
            Toast.makeText(this,"Sort by name",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        txtName = findViewById(R.id.editTextText3);
//        txtPhone = findViewById(R.id.editTextText4);
        btnAdd = findViewById(R.id.floatingActionButton2);
        listStudent = findViewById(R.id.listView);
        contactList = new ArrayList<Contact>();
//        listName = new ArrayList();
//        listName.add("Tran van A");
//        listName.add("Tran Van B");
        contactList.add(new Contact(1, "img", "Nguyen Van A", "0954096054"));
        contactList.add(new Contact(2, "img", "Nguyen Van A", "0954096054"));
        contactList.add(new Contact(3, "img", "Nguyen Van A", "0954096054"));



        listAdapt = new MyAdapter(contactList, this);
        listStudent.setAdapter(listAdapt);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity2.this, SubActivity.class);
                startActivityForResult(intent,100);
            }
        });
        registerForContextMenu(listStudent);
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactList.remove(position);
                listAdapt.notifyDataSetChanged();
            }
        });
    }

}