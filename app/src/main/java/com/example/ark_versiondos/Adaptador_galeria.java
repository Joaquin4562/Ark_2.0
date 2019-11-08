package com.example.ark_versiondos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class Adaptador_galeria extends BaseAdapter {
    List<galeria_adaptador>animales;
    private Context context;
    public Adaptador_galeria(Context context,List<galeria_adaptador>id){
        this.animales=id;
        this.context=context;
  }
    @Override
    public int getCount() {
        return animales.size();
    }

    @Override
    public Object getItem(int position) {
        return animales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        galeria_adaptador item = (galeria_adaptador) getItem(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_galeria,null);
        ImageView imageView=convertView.findViewById(R.id.imagen);
        imageView.setImageResource(item.getImagen());
        return convertView;
    }
}
