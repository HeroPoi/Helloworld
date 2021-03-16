package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Maps extends AppCompatActivity {

    String[] pos=new String[]{"安徽","北京","重庆","福建","广东","甘肃","广西"
            ,"贵州","海南","河北","河南","香港","黑龙江","湖南","湖北","吉林"
            ,"江苏","江西","辽宁","澳门","内蒙古","宁夏","青海","陕西","四川","山东","上海","山西","天津","台湾","新疆","西藏","云南","浙江"};
    TextView op;
    TextView po;
    Button os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dao dao=new dao(Maps.this);
        op=(TextView)findViewById(R.id.land);
        po=(TextView)findViewById(R.id.uo);
        TaiWanMapView tp=(TaiWanMapView)findViewById(R.id.tp);
        os=(Button)findViewById(R.id.all);

        tp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for(int i=0;i<tp.taiwanItems.size();i++) {
                    if(tp.taiwanItems.get(i).isTouch((int)(event.getX()/tp.scale),(int)(event.getY()/tp.scale))){
                        op.setText(pos[i]);
                        tp.handleTouch(event.getX(),event.getY());
                        po.setText("该省/直辖市/特别行政区有"+dao.sere(pos[i]).size()+"条记录，其中异常记录"+dao.sered(pos[i]).size()+"条");
                        os.setVisibility(View.VISIBLE);
                        break;
                    }else {
                        os.setVisibility(View.INVISIBLE);
                    }
                }
                return true;
            }
        });

        os.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Maps.this,House.class);
                it.putExtra("land",op.getText());
                startActivity(it);
            }
        });

    }
}