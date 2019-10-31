package com.example.ark_versiondos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<razaVacas> listaRazas;
    public RecyclerViewAdapter(List<razaVacas> listaRazas) {
        this.listaRazas = listaRazas;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.raza.setText(listaRazas.get(position).getNombre());
        holder.descri.setText(listaRazas.get(position).getDescripcion());
        holder.foto_vaca.setImageResource(listaRazas.get(position).getFotoRaza());
        holder.ic_vaca.setImageResource(listaRazas.get(position).getIcon_vaca());
    }

    @Override
    public int getItemCount() {
        return listaRazas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView raza, descri;
        private ImageView foto_vaca;
        private ImageView ic_vaca;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
