package com.example.ark_versiondos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public List<razaVacas> listaRazas;
    private Context context;

    private RecyclerView recyclerView;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;


    public RecyclerViewAdapter(List<razaVacas> listaRazas,  @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
        this.listaRazas = listaRazas;
    }

    public RecyclerViewAdapter( List<razaVacas> listaRazas) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView raza, descri;
        private ImageView foto_vaca;
        private ImageView ic_vaca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            raza=itemView.findViewById(R.id.tvRaza_vaca);
            descri=itemView.findViewById(R.id.tvEsp_vaca);
            foto_vaca=itemView.findViewById(R.id.iv_vaca);
            ic_vaca=itemView.findViewById(R.id.ivIcon_vaca);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewOnItemClickListener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista,null,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.raza.setText(listaRazas.get(position).getNombre());
        holder.descri.setText(listaRazas.get(position).getEspecialidad());
        holder.foto_vaca.setImageResource(listaRazas.get(position).getFotoRaza());
        holder.ic_vaca.setImageResource(listaRazas.get(position).getIcon_vaca());
    }

    @Override
    public int getItemCount() {
        return listaRazas.size();
    }


}
