package com.example.my_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;

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
    private ImageView imgView;
    private MyDB db;
    private ContentProvider cp;

    private int selectedItemID;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            Bundle b = data.getExtras();
            int id = b.getInt("Id");
            String name = b.getString("Name");
            String phone = b.getString("Phone");
            String image = b.getString("Image");

            //imgView.setImageURI(Uri.parse(image));
            Contact newcontact = new Contact(id,image,name,phone);

            if(requestCode==100 && resultCode==150){
                contactList.add(newcontact);
                db.addContact(newcontact);
                listAdapt.notifyDataSetChanged();
            }
            if(requestCode== 200 && resultCode==150){
                //Toast.makeText(this, "This is for debug", Toast.LENGTH_SHORT).show();
                contactList.set(selectedItemID,newcontact);
                //Contact a =  contactList.get(selectedItemID);
                listAdapt.notifyDataSetChanged();
            }
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
        else if(item.getItemId() == R.id.mnuSortPhone){
            Toast.makeText(this, "Sort by phone", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.mnuBroadcast) {
            Toast.makeText(this, "Broadcast", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Contact c = contactList.get(selectedItemID);
        if(item.getItemId()==R.id.mnuEdit){
            Intent intent = new Intent(MainActivity2.this,SubActivity.class);
            Bundle b = new Bundle();
            b.putInt("Id",c.getId());
            b.putString("Name",c.getName());
            b.putString("Phone",c.getPhone());
            b.putString("Image",c.getImages());
            intent.putExtras(b);
            startActivityForResult(intent,200);
        }
        else if(item.getItemId()==R.id.mnuDelete){
            db.deleteContact(c.getId());
            contactList.remove(c);
            listAdapt.notifyDataSetChanged();
        }
        else if(item.getItemId()==R.id.mnuCall){
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + c.getPhone()));
            startActivity(i);
        }
        else if(item.getItemId() == R.id.mnuSms){
            Toast.makeText(this, "Sort by phone", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.mnuEdit) {
            Toast.makeText(this, "Broadcast", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showContact(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != android.content.pm.PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, 100);
        }
        else{
            cp = new ContentProvider(this);
            contactList = cp.getAllContact();
            listAdapt = new MyAdapter(contactList,this);
            listStudent.setAdapter(listAdapt);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        listStudent = new ArrayList<>();
        db = new MyDB(this, "ContactDB.db", null, 1);
        setContentView(R.layout.activity_main2);
//        txtName = findViewById(R.id.editTextText3);
//        txtPhone = findViewById(R.id.editTextText4);
        btnAdd = findViewById(R.id.floatingActionButton2);
        listStudent = findViewById(R.id.listView);
        //contactList = new ArrayList<Contact>();
        txtName = findViewById(R.id.editTextText3);
//        listName = new ArrayList();
//        listName.add("Tran van A");
//        listName.add("Tran Van B");
        //contactList.add(new Contact(1, "img", "Nguyen Van A", "0954096054"));
//        contactList.add(new Contact(2, "img", "Nguyen Van A", "0954096054"));
//        contactList.add(new Contact(3, "img", "Nguyen Van A", "0954096054"));
//        db.addContact(new Contact(1, "img", "Nguyen Van A", "0954096054"));
//        db.addContact(new Contact(2, "img", "Nguyen Van B", "0954096055"));
//        db.addContact(new Contact(3, "img", "Nguyen Van C", "0954096056"));
//    contactList = db.getData();
        //listAdapt = new MyAdapter(contactList, this);
        //listStudent.setAdapter(listAdapt);
        showContact();
        //Load Callogs
        //Load browser history
        //Load va
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity2.this, SubActivity.class);
                startActivityForResult(intent,100);
            }
        });
        registerForContextMenu(listStudent);
        listStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemID = position;
                return false;
            }
        });
//        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                contactList.remove(position);
//                listAdapt.notifyDataSetChanged();
//            }
//        });
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapt.getFilter().filter(s.toString());
                listAdapt.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}