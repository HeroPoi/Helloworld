package com.example.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.graphics.PathParser;
import androidx.core.view.GestureDetectorCompat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TaiWanMapView extends View {
    private static final String TAG = TaiWanMapView.class.getName();
    public List<TaiWanItem> taiwanItems = new ArrayList<TaiWanItem>();
    //被点击的区域
    private TaiWanItem selectItem;
    //缩放1.3倍
    public float scale = 1.3f;
    private Context mContext;
    private Paint mPaint;
    private int[] colors = new int[]{0xFF239BD7,0xFF30A9E5,0xFF80CBF1,0xFFF0E68C};
    GestureDetectorCompat gestureDetectorCompat;

    public TaiWanMapView(Context context) {
        super(context);
    }

    public TaiWanMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        gestureDetectorCompat = new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(MotionEvent e) {
                Log.d(TAG,"onDown x:"+e.getX()+";y:"+e.getY());
                handleTouch(e.getX(),e.getY());
                return true;
            }
        });
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale,scale);
        if(taiwanItems != null){
            for(TaiWanItem item:taiwanItems){
                if(item != selectItem){
                    item.draw(canvas,mPaint,false);
                }
            }
            if(selectItem != null){
                selectItem.draw(canvas,mPaint,true);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
    }

    public void handleTouch(float x,float y){
        TaiWanItem taiWanItem = null;
        if(taiwanItems != null){
            for(TaiWanItem item : taiwanItems){
                if(item.isTouch((int)(x/scale),(int)(y/scale))){
                    taiWanItem = item;
                    break;
                }
            }
            if(taiWanItem != null) {
                selectItem = taiWanItem;
                postInvalidate();
            }
        }
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.chinahigh);
            //采用Dom解析器解析xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
                Document doc = builder.parse(inputStream);
                Element rootelement = doc.getDocumentElement();
                NodeList items = rootelement.getElementsByTagName("path");
                for(int i=0;i<items.getLength();i++){
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    TaiWanItem item = new TaiWanItem(path);
                    taiwanItems.add(item);
                }
                handler.sendEmptyMessage(1);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(taiwanItems == null || taiwanItems.size()==0)
                return;

            int colorNum = taiwanItems.size();
            int color = Color.WHITE;
            //赋予颜色
            for(int i=0;i<colorNum;i++){
                int flag = i % 4;
                switch(flag){
                    case 1:
                        color = colors[0];
                        break;
                    case 2:
                        color = colors[1];
                        break;
                    case 3:
                        color = colors[1];
                        break;
                    default:
                        color = colors[2];
                }
                taiwanItems.get(i).setDrawColor(color);
            }
            postInvalidate();
        }
    };
}
