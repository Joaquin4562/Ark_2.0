package com.example.ark_versiondos.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

import com.example.ark_versiondos.Conexcion;
import com.example.ark_versiondos.R;

import java.util.ArrayList;

public class Comparar extends Fragment {

    private CompararViewModel mViewModel;
    private SearchView svPrim, svSeg;
    private Button BuscarPrimero, BuscarSegundo, Comparar, Reset;
    private ListView lv1, lv2;
    private TextView Raza, TempMax, TempMin, Longevidad, PesoPromedio, LitrosLeche, Crias, Enfermedades, Especialidad, Gestacion, CalidadLeche, PropiedadesLeche, Temperamento, TipodeCuero;
    private ArrayList<String> razas, especies;
    private String raza, especie;
    private ArrayAdapter<String> adapter;
    private ResultSet rs;

    public static Comparar newInstance() {
        return new Comparar();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.comparar_fragment, container, false);
        //Declaracion de listViews
        lv1 = root.findViewById(R.id.lvUno);
        lv2 = root.findViewById(R.id.lvDos);
        //Declaracion Botones
        BuscarPrimero = root.findViewById(R.id.bBuscarPrim);
        BuscarSegundo = root.findViewById(R.id.bBuscarSeg);
        Comparar = root.findViewById(R.id.bComparar);
        Reset = root.findViewById(R.id.bReset);
        //Declaracion de searchviews
        svPrim = root.findViewById(R.id.svPrimero);
        svSeg = root.findViewById(R.id.svSegundo);
        //declaracion de araylists
        razas = new ArrayList<String>();
        especies = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_comp, razas);
        lv1.setAdapter(adapter);
        lv2.setAdapter(adapter);

        ObtenerAnimal();

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        svPrim.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        svSeg.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


        return root;
    }

    public void ObtenerAnimal() {
        try {
            Conexcion ob = new Conexcion();
            switch (Busqueda.especie) {
                case "Vaca":
                    rs = ob.ConsultaBD("Select * from Vacas where Raza='" + Busqueda.razas + "'");
                    break;
                case "Caballo":
                    rs = ob.ConsultaBD("Select * from Caballos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Borrego":
                    rs = ob.ConsultaBD("Select * from Borregos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Cerdo":
                    rs = ob.ConsultaBD("Select * from Cerdos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Pollo":
                    rs = ob.ConsultaBD("Select * from Pollos where Raza='" + Busqueda.razas + "'");
                    break;
            }
            while (rs.next()) {

                if (rs.getString("Raza").equals(Busqueda.razas)) {
                    switch (Busqueda.especie) {
                        case "Vaca":
                            Raza.setText(rs.getString("Raza"));
                            PesoPromedio.setText(rs.getString("Peso_Promedio"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Temperatura_minima"));
                            TempMax.setText(rs.getString("Temperatura_maxima"));
                            LitrosLeche.setText(rs.getString("Litros_de_leche_al_año"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades.setText(rs.getString("Enfermedades_propensas"));
                            Especialidad.setText(rs.getString("Especialidad"));
                            Gestacion.setText(rs.getString("Periodo_de_gestacion"));
                            CalidadLeche.setText(rs.getString("Calidad_de_leche"));
                            PropiedadesLeche.setText(rs.getString("Propiedades_de_la_leche"));
                            Temperamento.setText(rs.getString("Temperamento"));
                            TipodeCuero.setText(rs.getString("Tipo_de_cuero"));
                            break;
                        case "Caballo":
                            //ResultSet rs= ob.ConsultaBD("Select * from Caballos where Raza='"+Busqueda.razas+"'");
                            break;
                        case "Borrego":
                            //ResultSet rs= ob.ConsultaBD("Select * from Borregos where Raza='"+Busqueda.razas+"'");
                            break;
                        case "Cerdo":
                            //ResultSet rs= ob.ConsultaBD("Select * from Cerdos where Raza='"+Busqueda.razas+"'");
                            break;
                        case "Pollo":
                            //ResultSet rs= ob.ConsultaBD("Select * from Pollos where Raza='"+Busqueda.razas+"'");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CompararViewModel.class);
        // TODO: Use the ViewModel
    }

}
