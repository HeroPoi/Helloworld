package com.example.helloworld;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

public class TaiWanItem {

    private Path path;
    private int drawColor;
    String name;

    public TaiWanItem(Path path) {
        this.path = path;
    }

    /**
     * 绘制地图path
     * @param canvas
     * @param paint
     * @param isSelect
     */
    public void draw(Canvas canvas, Paint paint, boolean isSelect){
        if(isSelect){
            //画阴影图层
            paint.setStrokeWidth(2);
            paint.setShadowLayer(8,0,0,0xffffff);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPath(path,paint);
            //画区域path
            paint.clearShadowLayer();
            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor("#FBED6C"));

            canvas.drawPath(path,paint);
        }else{
            //画线条
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(0xFFD0E8F4);
            canvas.drawPath(path,paint);
            //画区域
            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(drawColor);
            canvas.drawPath(path,paint);
        }
    }

    /**
     * 判断当前点击坐标是否在path范围内
     * @param x
     * @param y
     * @return
     */
    public boolean isTouch(int x,int y){
        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        Region region = new Region();
        region.setPath(path,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));//判断X,Y是否在region区域范围内
        if(region.contains(x,y)) return true;
        return false;
    }

    public void setDrawColor(int drawColor) {
        this.drawColor = drawColor;
    }
}