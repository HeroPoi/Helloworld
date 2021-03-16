package com.example.helloworld;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import static android.content.Context.MODE_PRIVATE;

public class Excel {

    private static final String TAG = "SEL";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void excelCreate(String name, Context con) {
        SharedPreferences sp = con.getSharedPreferences("temper", MODE_PRIVATE);
        //存入数据
        String code=sp.getString("code","未设定");
        String cla=sp.getString("class","未设定");
        String phone=sp.getString("phone","未设定");
        String safe="良好";

        dao poi=new dao(con);

        ArrayList<info> pop=poi.serme(code);

        try {
            ContextWrapper cw = new ContextWrapper(con);
            File directory = cw.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(directory, name + "体温报表.xls");
            Toast.makeText(con, "文件储存路径为" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, file.getAbsolutePath());
            WritableWorkbook wwb = Workbook.createWorkbook(file);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);

            WritableCellFormat headerFormat = new WritableCellFormat();
            //水平居中对齐
            headerFormat.setAlignment(Alignment.CENTRE);
            headerFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            //竖直方向居中对齐
            headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

            sheet.mergeCells(0, 0, 6, 1);
            Label label = new Label(0, 0, "学生体温信息登记表", headerFormat);
            sheet.addCell(label);

            label = new Label(0, 2, "班级", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 2, 3, 2);
            label = new Label(1, 2, cla, headerFormat);
            sheet.addCell(label);

            label = new Label(4, 2, "填表日期", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(5, 2, 6, 2);
            label = new Label(5, 2, poi.getTime(), headerFormat);
            sheet.addCell(label);

            label = new Label(0, 3, "姓名", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 3, 3, 3);
            label = new Label(1, 3, name, headerFormat);
            sheet.addCell(label);

            label = new Label(4, 3, "学号", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(5, 3, 6, 3);
            label = new Label(5, 3, code, headerFormat);
            sheet.addCell(label);

            label = new Label(0, 4, "健康状况", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(1, 4, 3, 4);
            label = new Label(1, 4, "良好", headerFormat);
            sheet.addCell(label);

            label = new Label(4, 4, "手机号", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(5, 4, 6, 4);
            label = new Label(5, 4, phone, headerFormat);
            sheet.addCell(label);

            sheet.mergeCells(0, 5, 6, 6);
            label = new Label(0, 5, "每日体温状况监测", headerFormat);
            sheet.addCell(label);

            label = new Label(0, 7, "日期", headerFormat);
            sheet.addCell(label);

            label = new Label(1, 7, "体温", headerFormat);
            sheet.addCell(label);

            label = new Label(2, 7, "健康状况", headerFormat);
            sheet.addCell(label);

            sheet.mergeCells(3, 7, 4, 7);
            sheet.mergeCells(5, 7, 6, 7);
            label = new Label(3, 7, "填报时位置", headerFormat);
            sheet.addCell(label);
            label = new Label(5, 7, "备注", headerFormat);
            sheet.addCell(label);

            for (int i = 8; i <= 21; i++) {

                sheet.mergeCells(3, i, 4, i);
                sheet.mergeCells(5, i, 6, i);
                if((i-8)<pop.size()){
                label = new Label(0, i, pop.get(i-8).getTime(), headerFormat);
                sheet.addCell(label);

                label = new Label(1, i, pop.get(i-8).getTem(), headerFormat);
                sheet.addCell(label);

                label = new Label(2, i, "良好", headerFormat);
                sheet.addCell(label);

                label = new Label(3, i, pop.get(i-8).getPlace(), headerFormat);
                sheet.addCell(label);

                label = new Label(5, i, pop.get(i-8).getSide(), headerFormat);
                sheet.addCell(label);
                }
            }

            sheet.mergeCells(0, 22, 6, 23);
            label = new Label(0, 22, "  本人承诺：自觉履行疫情防控责任和义务，保证以上填报信息全部属实，如有隐瞒，自愿承担相应法律后果。");
            sheet.addCell(label);


            sheet.mergeCells(0, 24, 1, 25);
            label = new Label(0, 24, "本人签字", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(2, 24, 3, 25);

            sheet.mergeCells(4, 24, 4, 25);
            label = new Label(4, 24, "签字日期", headerFormat);
            sheet.addCell(label);
            sheet.mergeCells(5, 24, 6, 25);
            label = new Label(5, 24, poi.getsTime(), headerFormat);
            sheet.addCell(label);

            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}