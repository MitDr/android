package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

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
        imgView = findViewById(R.id.imageView2);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            int id = b.getInt("Id");
            String name = b.getString("Name");
            String phone = b.getString("Phone");
            String image = b.getString("Image");
            etId.setText(String.valueOf(id));
            etFullname.setText(name);
            etPhone.setText(phone);
            Toast.makeText(this, image, Toast.LENGTH_SHORT).show();
            //imgView.setTag(image);
            //image.
            //imgView.setImageURI(Uri.parse(image));
        }
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etId.getText().toString());
                String name = etFullname.getText().toString();
                String phone = etPhone.getText().toString();
                String image = imgView.getTag().toString();

                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SubActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putInt("Id",id);
                b.putString("Name",name);
                b.putString("Phone",phone);
                b.putString("Image",image);
                intent.putExtras(b);
                setResult(150,intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity2.class);
                startActivityForResult(intent, 101);
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 201);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 201) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgView.setImageURI(selectedImageUri);
                    imgView.setTag(selectedImageUri);
                    Toast.makeText(this, imgView.getTag().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}