package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class House extends AppCompatActivity {

    private ListView list;
    private Button bti;
    private Button btt;
    private TextView iso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        Intent intent = getIntent();
        String str = intent.getStringExtra("land");

        list=(ListView)findViewById(R.id.list_p);
        list.setAdapter(new Contral(House.this,str));
        iso=(TextView)findViewById(R.id.ops);
        iso.setText("定位于："+str);

        bti=(Button)findViewById(R.id.Bs_p);
        bti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(House.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}