package com.example.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class NextAdp extends BaseAdapter {
    private Context context;
    private LayoutInflater lif;
    private Object ViewHolder;
    dao dao;
    ArrayList<info> Anif;
    int host=0;
    String code;

    NextAdp(Context context){
        this.context=context;
        lif=LayoutInflater.from(context);
        dao=new dao(context);
        SharedPreferences sp = context.getSharedPreferences("temper", MODE_PRIVATE);
        code=sp.getString("code","未设定");
        Anif=dao.serme(code);
        host=0;

    }

    @Override
    public int getCount() {
        return dao.serme(code).size();
    }

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

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
            convertView=lif.inflate(R.layout.layout_pro_item,null);
            holder = new ListAdapter.viewhold();
            holder.tev=convertView.findViewById(R.id.lte);
            holder.pv=convertView.findViewById(R.id.ldt);
            holder.tv=convertView.findViewById(R.id.lpl);
            holder.pt=convertView.findViewById(R.id.lsi);
            holder.nv=convertView.findViewById(R.id.lhel);
            convertView.setTag(holder);
        }else {
            holder=(ListAdapter.viewhold) convertView.getTag();
        }
        //赋值
        holder.pv.setText(Anif.get(host).getTime());
        holder.tv.setText(Anif.get(host).getPlace());
        holder.tev.setText(Anif.get(host).getTem());
        holder.pt.setText(Anif.get(host).getSide());

        host++;

        return convertView;
    }
}
