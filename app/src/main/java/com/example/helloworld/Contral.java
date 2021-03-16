package com.example.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Contral extends BaseAdapter {
    private static final String TAG = "" ;
    private Context context;
    private LayoutInflater lif;
    private Object ViewHolder;
    String place="";
    dao dao;
    ArrayList<info> Anif;
    int host=0;


    Contral(Context context,String place){
        this.context=context;
        this.place=place;
        Log.d(TAG,place);
        lif=LayoutInflater.from(context);
        dao=new dao(context);
        Anif=dao.sere(place);
        host=0;

    }

    @Override
    public int getCount() {
        return dao.sere(place).size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class viewhold{
        public TextView tev;
        public TextView nv;
        public TextView tv;
        public TextView pv;
        public TextView pt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListAdapter.viewhold holder=null;
        if(convertView==null){
            convertView=lif.inflate(R.layout.layout_list_item,null);
            holder = new ListAdapter.viewhold();
            holder.tev=convertView.findViewById(R.id.tev);
            holder.nv=convertView.findViewById(R.id.nv);
            holder.pv=convertView.findViewById(R.id.pv);
            holder.tv=convertView.findViewById(R.id.tv);
            holder.pt=convertView.findViewById(R.id.pt);
            convertView.setTag(holder);
        }else {
            holder=(ListAdapter.viewhold) convertView.getTag();
        }
        //赋值
        holder.tev.setText(Anif.get(host).getTem());
        holder.nv.setText(Anif.get(host).getName());
        holder.tv.setText(Anif.get(host).getTime());
        holder.pv.setText(Anif.get(host).getPlace());
        holder.pt.setText(Anif.get(host).getSide());
        host++;

        return convertView;
    }
}
