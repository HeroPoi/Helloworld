package com.example.helloworld;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class pages extends AppCompatActivity {

    private LineChart lineChart; //折线图控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);

        lineChart = findViewById(R.id.lc);
        initLineChart();
    }

    private void initLineChart() {
        lineChart.animateXY(2000, 2000); // 呈现动画
        Description description = new Description();
        description.setText(""); //自定义描述
        lineChart.setDescription(description);
        Legend legend = lineChart.getLegend();
        legend.setTextColor(0);
        setYAxis();
        setXAxis();
        setData();
    }

    private void setData() {
// 模拟数据1
        dao dao=new dao(pages.this);
        SharedPreferences sp = pages.this.getSharedPreferences("temper", MODE_PRIVATE);
        //存入数据
        String name=sp.getString("code","未设定");
        ArrayList<info> pa= dao.sermer(name);
        List<Entry> yVals1 = new ArrayList<>();
        for (int i = 0;i<pa.size(); i++) {
            yVals1.add(new Entry(i, Float.parseFloat(pa.get(i).getTem())));
        }
        // 2. 分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, "体温变化");
        lineDataSet1.setCircleRadius(5); //设置点圆的半径
        lineDataSet1.setDrawCircleHole(false); // 不绘制圆洞，即为实心圆点
        // 值的字体大小为12dp
        lineDataSet1.setValueTextSize(12f);
        //将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lineDataSet1);
        //设置颜色
        lineData.setValueTextColor(Color.BLACK);
        //将折线数据设置给图表
        lineChart.setData(lineData);
    }


    private void setXAxis() {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(false); // 不绘制X轴
        xAxis.setDrawGridLines(false); // 不绘制网格线
        // 模拟X轴标签数据
        dao dao=new dao(pages.this);
        SharedPreferences sp = pages.this.getSharedPreferences("temper", MODE_PRIVATE);
        //存入数据
        String name=sp.getString("code","未设定");
        ArrayList<info> pa= dao.sermer(name);
        String[] weekStrs=new String[10];
        for(int i=0;i<pa.size();i++){
             weekStrs[i]=pa.get(i).getTime();
        }
        xAxis.setLabelCount(pa.size()); // 设置标签数量
        xAxis.setTextColor(Color.GREEN); // 文本颜色
        xAxis.setTextSize(5f); // 文本大小为18dp
        xAxis.setGranularity(1f); // 设置间隔尺寸
        // 使图表左右留出点空位
        xAxis.setAxisMinimum(-0.1f); // 设置X轴最小值
        //设置颜色
        xAxis.setTextColor(Color.BLACK);
        // 设置标签的显示格式
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return weekStrs[(int) value];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部显示

    }

    private void setYAxis() {
        YAxis yAxisLeft = lineChart.getAxisLeft();// 左边Y轴
        yAxisLeft.setDrawAxisLine(true); // 绘制Y轴
        yAxisLeft.setDrawLabels(true); // 绘制标签
        yAxisLeft.setAxisMaxValue(40); // 设置Y轴最大值
        yAxisLeft.setAxisMinValue(34); // 设置Y轴最小值
        yAxisLeft.setGranularity(2f); // 设置间隔尺寸
        yAxisLeft.setTextColor(Color.BLACK); //设置颜色
        yAxisLeft.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return (int)value  + "℃";
            }
        });
        // 右侧Y轴
        lineChart.getAxisRight().setEnabled(false); // 不启用

    }
}