package com.example.ark_versiondos.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ark_versiondos.Adaptador_galeria;
import com.example.ark_versiondos.Conexcion;
import com.example.ark_versiondos.R;
import com.example.ark_versiondos.galeria_adaptador;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Galeria extends Fragment {

    ListView lista;
    Button vacas,borregos,caballos,pollos,cerdos;
    Adaptador_galeria adaptador_galeria;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View vista=inflater.inflate(R.layout.galeria_fragment, container, false);
        lista=vista.findViewById(R.id.lista);
        borregos=vista.findViewById(R.id.g_borrego);
        caballos=vista.findViewById(R.id.g_caballo);
        pollos=vista.findViewById(R.id.g_pollo);
        cerdos=vista.findViewById(R.id.g_cerdo);
        vacas=vista.findViewById(R.id.g_vaca);
        adaptador_galeria= new Adaptador_galeria(vista.getContext(),lista());
        vacas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.setAdapter(adaptador_galeria);
            }
        });
        borregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vista.getContext(),"Aun no se ha registrado ninguna animal de este tipo",Toast.LENGTH_LONG).show();
            }
        });
        caballos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vista.getContext(),"Aun no se ha registrado ninguna animal de este tipo",Toast.LENGTH_LONG).show();
            }
        });
        cerdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vista.getContext(),"Aun no se ha registrado ninguna animal de este tipo",Toast.LENGTH_LONG).show();
            }
        });
        pollos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(vista.getContext(),"Aun no se ha registrado ninguna animal de este tipo",Toast.LENGTH_LONG).show();
            }
        });


        return vista;
    }

public List<galeria_adaptador> lista(){
    List<galeria_adaptador> item = new ArrayList<>();
    int id;
    int []imagenes={R.drawable.im1,R.drawable.im2,R.drawable.im3};
        try {
            ResultSet rs = new Conexcion().ConsultaBD("SELECT Id FROM Vacas");
            while (rs.next()){

                id=rs.getInt("Id");
                item.add(new galeria_adaptador(imagenes[id-1]));
            }

        }catch (Exception e){
            Toast.makeText(this.getContext(),e.getMessage(),Toast.LENGTH_SHORT);

        }
    return item;
}
}
