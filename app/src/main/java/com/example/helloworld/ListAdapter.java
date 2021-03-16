package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater lif;
    private Object ViewHolder;
    dao dao;
    ArrayList<info> Anif;
    int host=0;


    ListAdapter(Context context){
        this.context=context;
        lif=LayoutInflater.from(context);
        dao=new dao(context);
        Anif=dao.ser();
        host=0;

    }

    @Override
    public int getCount() {
        return dao.ser().size();
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
        viewhold holder=null;
        if(convertView==null){
            convertView=lif.inflate(R.layout.layout_list_item,null);
            holder = new viewhold();
            holder.tev=convertView.findViewById(R.id.tev);
            holder.nv=convertView.findViewById(R.id.nv);
            holder.pv=convertView.findViewById(R.id.pv);
            holder.tv=convertView.findViewById(R.id.tv);
            holder.pt=convertView.findViewById(R.id.pt);
            convertView.setTag(holder);
        }else {
            holder=(viewhold) convertView.getTag();
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