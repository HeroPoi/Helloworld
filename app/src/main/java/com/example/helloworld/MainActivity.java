package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private Button input;
    private Button aview;
    private Button search;
    private Button cme;
    private Button cue;
    private Button cqe;
    private long DELAY = 10;
    private TimerTask task;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database data=new database(this);
        data.getWritableDatabase();
        dao dao=new dao(this);

        Timer timer=new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
                if(dao.serp()==0) {
                    Intent it = new Intent(MainActivity.this, searchact.class);
                    startActivity(it);
                }
            }
        };
        timer.schedule(tast,DELAY);//0.1秒后

        input=(Button)findViewById(R.id.B1);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至体温录入界面
                Intent intent=new Intent(MainActivity.this,inputact.class);
                startActivity(intent);
            }
        });
        aview=(Button)findViewById(R.id.B2);
        aview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至体温显示界面
                Intent it=new Intent(MainActivity.this,viewact.class);
                startActivity(it);
            }
        });
        search=(Button)findViewById(R.id.B3);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至新用户界面
                Intent it=new Intent(MainActivity.this,checkMuch.class);
                startActivity(it);
            }
        });

        cme=(Button)findViewById(R.id.B4);
        cme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至新用户界面
                Intent it=new Intent(MainActivity.this,chengeMe.class);
                startActivity(it);
            }
        });

        cue=(Button)findViewById(R.id.B5);
        cue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至新用户界面
                Intent it=new Intent(MainActivity.this,countdown.class);
                startActivity(it);
            }
        });

        cqe=(Button)findViewById(R.id.B6);
        cqe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至新用户界面
                Intent it=new Intent(MainActivity.this,show_list.class);
                startActivity(it);
            }
        });
    }
}