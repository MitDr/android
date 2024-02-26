package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etFullname;
    private EditText etPhone;
    private ImageView imgView;
    private Button btnOK;
    private Button btnCancel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        etId=findViewById(R.id.etId);
        etFullname = findViewById(R.id.etFullname);
        etPhone = findViewById(R.id.etPhone);
        btnOK = findViewById(R.id.button4);
        btnCancel = findViewById(R.id.button5);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                String name = etFullname.getText().toString();
                String phone = etPhone.getText().toString();
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putInt("Id",id);
                b.putString("Name",name);
                b.putString("Phone",phone);
                intent.putExtras(b);
                setResult(150,intent);
                finish();
            }
        });
    }
}