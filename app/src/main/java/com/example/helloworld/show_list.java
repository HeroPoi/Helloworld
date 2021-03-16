package com.example.helloworld;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class show_list extends AppCompatActivity {

    private TextView hname;
    private TextView hcode;
    private TextView hhel;
    private TextView htel;
    private ListView list;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        hname=(TextView)findViewById(R.id.hname);
        hcode=(TextView)findViewById(R.id.hcode);
        hhel=(TextView)findViewById(R.id.hhel);
        htel=(TextView)findViewById(R.id.htel);

        SharedPreferences sp = show_list.this.getSharedPreferences("temper", MODE_PRIVATE);
        //存入数据
        String code=sp.getString("code","未设定");
        String cla=sp.getString("class","未设定");
        String phone=sp.getString("phone","未设定");
        String name=sp.getString("name","未设定");

        dao dao=new dao(show_list.this);
        Excel excel=new Excel();
        excel.excelCreate(name+dao.getsTime(),show_list.this);

        hname.setText(name);
        hcode.setText(code);
        hhel.setText(cla);
        htel.setText(phone);

        list=(ListView)findViewById(R.id.list_pro);
        list.setAdapter(new NextAdp(show_list.this));

    }
}