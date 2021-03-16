package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class searchact extends AppCompatActivity {

    private Button legon;
    dao dao=new dao(searchact.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchact);
        legon=(Button)findViewById(R.id.sa_do);
        legon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.sa_name);
                EditText code=(EditText)findViewById(R.id.sa_code);
                EditText phone=(EditText)findViewById(R.id.sa_phone);
                EditText cla=(EditText)findViewById(R.id.sa_class);
                String na=name.getText().toString();
                String co=code.getText().toString();
                String ph=phone.getText().toString();
                String cl=cla.getText().toString();
                if(na.equals("")||co.equals("")||ph.equals("")||cl.equals("")){
                    Toast.makeText(searchact.this,"还有信息没有填写哦",Toast.LENGTH_SHORT).show();
                    return;
                }
                Context ctx = searchact.this;
                SharedPreferences sp = ctx.getSharedPreferences("temper", MODE_PRIVATE);
                //存入数据
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("code", co);
                editor.putString("name", na);
                editor.putString("class", cl);
                editor.putString("phone", ph);
                editor.commit();
                Toast.makeText(searchact.this,"用户名"+sp.getString("name","me"),Toast.LENGTH_SHORT).show();
                dao.insuser(na,co,cl,ph);
                Intent it=new Intent(searchact.this,MainActivity.class);
                startActivity(it);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(searchact.this,"您还没有完成注册哦",Toast.LENGTH_SHORT).show();
    }
}