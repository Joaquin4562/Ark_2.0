package com.example.ark_versiondos.ui.home;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ark_versiondos.R;
import com.example.ark_versiondos.RecyclerViewAdapter;
import com.example.ark_versiondos.razaVacas;

import java.util.ArrayList;
import java.util.List;


public class Busqueda extends Fragment {
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        recyclerView = root.findViewById(R.id.lista_animales);
        recyclerView.setAdapter(new RecyclerViewAdapter(obtenerRazas()));
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        return root;
    }

    public List<razaVacas> obtenerRazas(){
        List<razaVacas> raza = new ArrayList<>();
        raza.add(new razaVacas("Brahma","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Simbrah","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        raza.add(new razaVacas("Pardo Suiza","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Brahma","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        raza.add(new razaVacas("Simbrah","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Pardo Suiza","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        raza.add(new razaVacas("Brahma","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Simbrah","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        raza.add(new razaVacas("Pardo Suiza","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Brahma","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        raza.add(new razaVacas("Simbrah","mexico",R.drawable.vaca,R.drawable.ic_carne));
        raza.add(new razaVacas("Pardo Suiza","mexico",R.drawable.vaca,R.drawable.ic_botella_de_leche));
        return raza;
    }
}