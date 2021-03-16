package com.example.helloworld;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class chengeMe extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenge_me);
        list=(ListView)findViewById(R.id.list_me);
        list.setAdapter(new AgainAdp(chengeMe.this));
    }
}