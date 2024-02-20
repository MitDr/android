package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    private TextView tv;
    private EditText editText3;
//    private Switch sw;
    private Button button3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tham chiếu tới phần tử trên layout bằng id
//        tv = findViewById(R.id.hello);
        editText3 = findViewById(R.id.editTextText);
        button3 = findViewById(R.id.button3);
        //getText để đọc dữ liệu cũ
//        String text = tv.getText().toString();
//        text = text.toUpperCase();
        //Viết hoa + ngày sinh
        //Set text
//        tv.setText(text + "13/01/2003");

        //editText
        editText3.setText("Họ tên");
        button3.setText("Ok");
//        button3.setOnLongClickListener
//            @Override
//                    public void onClick(){
//                Toast.makeText(this, "Xin chào", Toast.LENGTH_SHORT).show();
//            }
//        });

        //switch
//        sw = findViewById(R.id.switch1);

//        sw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Vừa bấm vào switch đó \n vui không??", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}