package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class checkMuch extends AppCompatActivity {


    private Button legon;
    dao dao=new dao(checkMuch.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_much);
        legon=(Button)findViewById(R.id.sp_do);
        legon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.sp_name);
                EditText code=(EditText)findViewById(R.id.sp_code);
                EditText phone=(EditText)findViewById(R.id.sp_phone);
                EditText cla=(EditText)findViewById(R.id.sp_class);
                String na=name.getText().toString();
                String co=code.getText().toString();
                String ph=phone.getText().toString();
                String cl=cla.getText().toString();
                if(na.equals("")||co.equals("")||ph.equals("")||cl.equals("")){
                    Toast.makeText(checkMuch.this,"还有信息没有填写哦",Toast.LENGTH_SHORT).show();
                    return;
                }
                Context ctx = checkMuch.this;
                dao.insuser(na,co,cl,ph);
                Intent it=new Intent(checkMuch.this,MainActivity.class);
                startActivity(it);
            }
        });


    }
}