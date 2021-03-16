package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.Date;


public class inputact extends AppCompatActivity {

    public LocationClient mLocationClient;
    //private MapView mapview;
    private TextView positionText;
    private StringBuilder currentPosition;
    private MyLocationListener myListener = new MyLocationListener();
    private Button up;
    private RadioGroup ss;
    private RadioButton s1;
    private RadioButton s2;
    private RadioButton s3;
    private RadioButton s4;
    private RadioButton so;
    private int side=0;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        option.setCoorType("bd09ll");
        option.setScanSpan(1000000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        option.setAddrType("all");

        mLocationClient.setLocOption(option);

        mLocationClient.start();

        setContentView(R.layout.activity_inputact);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// HH:mm:ss//获取当前时间

        Context ctx = inputact.this;
        SharedPreferences sp = ctx.getSharedPreferences("temper", MODE_PRIVATE);

        TextView time1 = (TextView) findViewById(R.id.T_time);
        Date date = new Date(System.currentTimeMillis());
        time1.setText(simpleDateFormat.format(date));

        TextView name = (TextView) findViewById(R.id.et_name);
        name.setText(sp.getString("name","未设定"));

        TextView code = (TextView) findViewById(R.id.et_code);
        code.setText(sp.getString("code","未设定"));

        EditText tep = (EditText) findViewById(R.id.et_temp);
        tep.setText("36.2");

        EditText s21=(EditText)findViewById(R.id.side2_1);
        EditText s31=(EditText)findViewById(R.id.side3_1);
        EditText s41=(EditText)findViewById(R.id.side4_1);

        s1=(RadioButton)findViewById(R.id.side1);
        s2=(RadioButton)findViewById(R.id.side2);
        s3=(RadioButton)findViewById(R.id.side3);
        s4=(RadioButton)findViewById(R.id.side4);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s21.setVisibility(View.GONE);
                s31.setVisibility(View.GONE);
                s41.setVisibility(View.GONE);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s21.setVisibility(View.VISIBLE);
                s31.setVisibility(View.GONE);
                s41.setVisibility(View.GONE);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s21.setVisibility(View.GONE);
                s31.setVisibility(View.VISIBLE);
                s41.setVisibility(View.GONE);
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s21.setVisibility(View.GONE);
                s31.setVisibility(View.GONE);
                s41.setVisibility(View.VISIBLE);
            }
        });

        up=(Button)findViewById(R.id.Bup);
        up.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "";

            @Override
            public void onClick(View v) {
                TextView editText1 =(TextView)findViewById(R.id.et_name);
                String str1=editText1.getText().toString();
                EditText editText2 =(EditText)findViewById(R.id.et_temp);
                String str2=editText2.getText().toString();
                TextView vi1=(TextView)findViewById(R.id.position_text_view);
                TextView vi2=(TextView)findViewById(R.id.T_time);
                String str3=vi1.getText().toString();
                String str4=vi2.getText().toString();
                String code=sp.getString("code","未设定");
                String cla=sp.getString("class","未设定");
                ss=(RadioGroup)findViewById(R.id.side);
                RadioButton r=null;
                for (int i = 0; i < ss.getChildCount(); i=i+2) {
                     r= (RadioButton) ss.getChildAt(i);
                    if (r.isChecked()) {
                        side = i;
                        break;
                    }
                }
                String side0="";
                EditText si2=(EditText)findViewById(R.id.side2_1);
                EditText si3=(EditText)findViewById(R.id.side3_1);
                EditText si4=(EditText)findViewById(R.id.side4_1);
                switch(side){
                    case 0:
                        side0="无特殊情况";
                        break;
                    case 2:
                        side0="本人或家人正在集中隔离："+si2.getText();
                        break;
                    case 4:
                        side0="居住地异动："+si3.getText();
                        break;
                    case 6:
                        side0="其他情况："+si4.getText();
                        break;
                }
                dao dao = new dao(inputact.this);
                if(dao.repeat(code)==0) {
                    dao.insert(str1, str2, str4, str3, code, side0, cla);
                    Intent it = new Intent(inputact.this, viewact.class);
                    startActivity(it);
                }else{
                    Toast.makeText(inputact.this,"您今天已经填写过了哦",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            TextView place = (TextView) findViewById(R.id.position_text_view);
            place.setText(addr);
        }

    }
}

