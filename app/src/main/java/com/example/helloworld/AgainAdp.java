package com.example.helloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AgainAdp extends BaseAdapter {

    private Context context;
    private LayoutInflater lif;
    private Object ViewHolder;
    dao dao;
    ArrayList<infoc> Anif;
    int host=0;

    AgainAdp(Context context){
        this.context=context;
        lif=LayoutInflater.from(context);
        dao=new dao(context);
        Anif=dao.serevery();
        host=0;

    }

    @Override
    public int getCount() {
        return dao.serevery().size();
    }

    @Override
    public Object getItem(int position) {return null;}

    @Override
    public long getItemId(int position) {return 0;}

    static class viewhold{
        public TextView tev;
        public Button nv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AgainAdp.viewhold holder=null;
        if(convertView==null){
            convertView=lif.inflate(R.layout.layout_hello_item,null);
            holder = new AgainAdp.viewhold();
            holder.tev=convertView.findViewById(R.id.code);
            holder.nv=convertView.findViewById(R.id.but);
            convertView.setTag(holder);
        }else {
            holder=(AgainAdp.viewhold) convertView.getTag();
        }
        //赋值
        holder.tev.setText(Anif.get(host).getCode()+" "+Anif.get(host).getName());
        int hop=host;
        holder.nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = context;
                SharedPreferences sp = ctx.getSharedPreferences("temper", MODE_PRIVATE);
                //存入数据
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("code", Anif.get(hop).getCode());
                editor.putString("name", Anif.get(hop).getName());
                editor.putString("class", Anif.get(hop).getCla());
                editor.putString("phone", Anif.get(hop).getTel());
                editor.commit();
                Toast.makeText(context,"切换成功！",Toast.LENGTH_SHORT).show();
            }
        });
        host++;

        return convertView;
    }
}
