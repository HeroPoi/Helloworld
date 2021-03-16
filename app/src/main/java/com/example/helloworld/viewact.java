package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;



public class viewact extends AppCompatActivity {

    private ListView list;
    private Button bti;
    private Button btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewact);

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(new ListAdapter(viewact.this));

        bti=(Button)findViewById(R.id.Bx);
        bti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewact.this,pages.class);
                startActivity(intent);
            }
        });
        btt=(Button)findViewById(R.id.Bs);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewact.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}