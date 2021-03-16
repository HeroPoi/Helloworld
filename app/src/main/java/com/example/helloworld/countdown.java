package com.example.helloworld;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class countdown extends AppCompatActivity {

    dao dao=new dao(countdown.this);
    TextView tod;
    TextView all;
    TextView nop;
    TextView error;
    Button bup;
    Button bmp;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        bup=(Button)findViewById(R.id.bup);
        bmp=(Button)findViewById(R.id.maps);
        bup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(countdown.this,MainActivity.class);
                startActivity(it);
            }
        });
        bmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(countdown.this, Maps.class);
                startActivity(it);
            }
        });


        tod=(TextView)findViewById(R.id.tod);
        all=(TextView)findViewById(R.id.all);
        nop=(TextView)findViewById(R.id.nop);
        error=(TextView)findViewById(R.id.error);

        tod.setText("今日体温填报人数："+dao.sert());
        all.setText("班级现有人数："+dao.serc());
        nop.setText("未上报人数："+(dao.serc()-dao.sert()));
        error.setText("体温异常人数："+dao.serq());

    }
}