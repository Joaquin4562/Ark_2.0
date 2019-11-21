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
import android.widget.ImageView;
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
    private TextView Raza, TempMax, TempMin, Longevidad, PesoPromedio, LitrosLeche, Crias, Enfermedades, Especialidad, Gestacion, CalidadLeche, PropiedadesLeche, Temperamento, TipodeCuero;
    private TextView Raza2, TempMax2, TempMin2, Longevidad2, PesoPromedio2, LitrosLeche2, Crias2, Enfermedades2, Especialidad2, Gestacion2, CalidadLeche2, PropiedadesLeche2, Temperamento2, TipodeCuero2;
    private ImageView animalImage, animalImage2;
    private String raza, raza2, especie = "Vaca";
    private ArrayAdapter<String> adapter, adapter2;
    //declaracion de araylists
    private ArrayList<String> razas = new ArrayList<>();
    private ArrayList<String> razas2 = new ArrayList<>();
    private ArrayList<String> especies = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.comparar_fragment, container, false);

        //Declaracion de listViews
        final ListView lv1 = root.findViewById(R.id.lvUno);
        final ListView lv2 = root.findViewById(R.id.lvDos);

        //Declaracion Botones
        Button reset = root.findViewById(R.id.bReset);
        Button vacas = root.findViewById(R.id.Vacas);
        Button caballos = root.findViewById(R.id.Caballos);
        Button cerdos = root.findViewById(R.id.Cerdos);
        Button borregos = root.findViewById(R.id.Borregos);
        Button pollos = root.findViewById(R.id.Pollos);

        //Declaracion de searchviews
        svPrim = root.findViewById(R.id.svPrimero);
        svSeg = root.findViewById(R.id.svSegundo);

        //Declaracion de los campos
        Raza = root.findViewById(R.id.tvRaza1);
        TempMax = root.findViewById(R.id.tvTempMax1);
        TempMin = root.findViewById(R.id.tvTempMin1);
        Longevidad = root.findViewById(R.id.tvLonge1);
        PesoPromedio = root.findViewById(R.id.tvPesoP1);
        LitrosLeche = root.findViewById(R.id.tvLitros1);
        Crias = root.findViewById(R.id.tvCrias1);
        Gestacion = root.findViewById(R.id.tvGest1);
        CalidadLeche = root.findViewById(R.id.tvCantL1);
        animalImage = root.findViewById(R.id.ivUno);

        Raza2 = root.findViewById(R.id.tvRaza2);
        TempMax2 = root.findViewById(R.id.tvTempMax2);
        TempMin2 = root.findViewById(R.id.tvTempMin2);
        Longevidad2 = root.findViewById(R.id.tvLonge2);
        PesoPromedio2 = root.findViewById(R.id.tvPesoP2);
        LitrosLeche2 = root.findViewById(R.id.tvLitros2);
        Crias2 = root.findViewById(R.id.tvCrias2);
        Gestacion2 = root.findViewById(R.id.tvGest2);
        CalidadLeche2 = root.findViewById(R.id.tvCantL2);
        animalImage2 = root.findViewById(R.id.ivDos);

        //programa inicia
        ObtenerAnimal();

        //Onclicks para las especies
        vacas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Vaca";
                ObtenerAnimal();
            }
        });
        caballos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Caballo";
                ObtenerAnimal();
            }
        });
        cerdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Cerdo";
                ObtenerAnimal();
            }
        });
        borregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Borrego";
                ObtenerAnimal();
            }
        });
        pollos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Pollo";
                ObtenerAnimal();
            }
        });

        //Aqui se llenan los listview por medio del adapter... con el arraylist lleno
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_comp, razas);
        lv1.setAdapter(adapter);
        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item_comp, razas2);
        lv2.setAdapter(adapter2);


        //Aqui funcionan los searchviews y filtran los listviews
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
                adapter2.getFilter().filter(s);
                return false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Reiniciar fragment

            }
        });

        //Aqui los onclickItemlisteners de los listview
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                raza = razas.get(i);
                ObtenerDatosAnimal();
                svPrim.setEnabled(false);
                lv1.setEnabled(false);
            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                raza2 = razas2.get(i);
                ObtenerDatosAnimal2();
                svSeg.setEnabled(false);
                lv2.setEnabled(false);
            }
        });
        return root;
    }

    public void ObtenerAnimal() {
        try {
            razas.clear();
            razas2.clear();
            Conexcion ob = new Conexcion();
            ResultSet rs = ob.ConsultaBD("Select Raza from Vacas");
            String linea= "";
            switch (especie) {
                case "Vaca":
                    rs = ob.ConsultaBD("Select Raza from Vacas");
                    break;
                case "Caballo":
                    rs = ob.ConsultaBD("Select raza from Caballos");
                    break;
                case "Borrego":
                    rs = ob.ConsultaBD("Select raza from Borregos");
                    break;
                case "Cerdo":
                    rs = ob.ConsultaBD("Select raza from Cerdos");
                    break;
                case "Pollo":
                    rs = ob.ConsultaBD("Select raza from Pollos");
                    break;
            }
            while (rs.next()) {
                switch (especie) {
                    case "Vaca":
                        linea = rs.getString("Raza");
                        break;
                    case "Caballo":
                    case "Borrego":
                    case "Cerdo":
                    case "Pollo":
                        linea = rs.getString("raza");
                        break;
                }
                razas.add(linea);
                razas2.add(linea);
            }
            rs.close();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void ObtenerDatosAnimal() {
        try {
            razas.clear();
            Conexcion ob = new Conexcion();
            ResultSet rs = ob.ConsultaBD("Select Raza from Vacas");
            switch (especie) {
                case "Vaca":
                    rs = ob.ConsultaBD("Select * from Vacas where Raza='" + raza + "'");
                    break;
                case "Caballo":
                    rs = ob.ConsultaBD("Select * from Caballos where Raza='" + raza + "'");
                    break;
                case "Borrego":
                    rs = ob.ConsultaBD("Select * from Borregos where Raza='" + raza + "'");
                    break;
                case "Cerdo":
                    rs = ob.ConsultaBD("Select * from Cerdos where Raza='" + raza + "'");
                    break;
                case "Pollo":
                    rs = ob.ConsultaBD("Select * from Pollos where Raza='" + raza + "'");
                    break;
            }
            while (rs.next()) {
                if (rs.getString("Raza").equals(raza)) {
                    switch (especie) {
                        case "Vaca":
                            Raza.setText(rs.getString("Raza"));
                            PesoPromedio.setText(rs.getString("Peso_Promedio"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Temperatura_minima"));
                            TempMax.setText(rs.getString("Temperatura_maxima"));
                            LitrosLeche.setText(rs.getString("Litros_de_leche_al_año"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            Gestacion.setText(rs.getString("Periodo_de_gestacion"));
                            CalidadLeche.setText(rs.getString("Calidad_de_leche"));
                            break;
                        case "Caballo":
                            Raza.setText(rs.getString("raza"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Peso_Promedio"));
                            TempMax.setText(rs.getString("Calidad_de_fisico"));
                            PesoPromedio.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche.setText(rs.getString("Temperamento"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades.setText(rs.getString("Resistencia"));
                            break;
                        case "Borrego":
                            //ResultSet rs= ob.ConsultaBD("Select * from Borregos where Raza='"+Busqueda.razas+"'");
                            break;
                        case "Cerdo":
                            Raza.setText(rs.getString("raza"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Peso_Promedio"));
                            TempMax.setText(rs.getString("Calidad_de_su_carne"));
                            PesoPromedio.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche.setText(rs.getString("Temperamento"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            break;
                        case "Pollo":
                            Raza.setText(rs.getString("raza"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Peso_promedio"));
                            TempMax.setText(rs.getString("Calidad_de_la_carne"));
                            PesoPromedio.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche.setText(rs.getString("Temperamento"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades.setText(rs.getString("Resistencia_al_clima"));
                            Especialidad.setText(rs.getString("Cantidad_de_huevos_al_año"));
                            Gestacion.setText(rs.getString("Especialidad"));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void ObtenerDatosAnimal2() {
        try {
            razas2.clear();
            Conexcion ob = new Conexcion();
            ResultSet rs = ob.ConsultaBD("Select Raza from Vacas");
            switch (especie) {
                case "Vaca":
                    rs = ob.ConsultaBD("Select * from Vacas where Raza='" + raza2 + "'");
                    break;
                case "Caballo":
                    rs = ob.ConsultaBD("Select * from Caballos where Raza='" + raza2 + "'");
                    break;
                case "Borrego":
                    rs = ob.ConsultaBD("Select * from Borregos where Raza='" + raza2 + "'");
                    break;
                case "Cerdo":
                    rs = ob.ConsultaBD("Select * from Cerdos where Raza='" + raza2 + "'");
                    break;
                case "Pollo":
                    rs = ob.ConsultaBD("Select * from Pollos where Raza='" + raza2 + "'");
                    break;
            }
            while (rs.next()) {
                if (rs.getString("Raza").equals(raza2)) {
                    switch (especie) {
                        case "Vaca":
                            Raza2.setText(rs.getString("Raza"));
                            PesoPromedio2.setText(rs.getString("Peso_Promedio"));
                            Longevidad2.setText(rs.getString("Longevidad"));
                            TempMin2.setText(rs.getString("Temperatura_minima"));
                            TempMax2.setText(rs.getString("Temperatura_maxima"));
                            LitrosLeche2.setText(rs.getString("Litros_de_leche_al_año"));
                            Crias2.setText(rs.getString("Crias_en_vida_productiva"));
                            Gestacion2.setText(rs.getString("Periodo_de_gestacion"));
                            CalidadLeche2.setText(rs.getString("Calidad_de_leche"));
                            break;
                        case "Caballo":
                            Raza2.setText(rs.getString("raza"));
                            Longevidad2.setText(rs.getString("Longevidad"));
                            TempMin2.setText(rs.getString("Peso_Promedio"));
                            TempMax2.setText(rs.getString("Calidad_de_fisico"));
                            PesoPromedio2.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche2.setText(rs.getString("Temperamento"));
                            Crias2.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades2.setText(rs.getString("Resistencia"));
                            break;
                        case "Borrego":
                            //ResultSet rs= ob.ConsultaBD("Select * from Borregos where Raza='"+Busqueda.razas+"'");
                            break;
                        case "Cerdo":
                            Raza2.setText(rs.getString("raza"));
                            Longevidad2.setText(rs.getString("Longevidad"));
                            TempMin2.setText(rs.getString("Peso_Promedio"));
                            TempMax2.setText(rs.getString("Calidad_de_su_carne"));
                            PesoPromedio2.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche2.setText(rs.getString("Temperamento"));
                            Crias2.setText(rs.getString("Crias_en_vida_productiva"));
                            break;
                        case "Pollo":
                            Raza2.setText(rs.getString("raza"));
                            Longevidad2.setText(rs.getString("Longevidad"));
                            TempMin2.setText(rs.getString("Peso_promedio"));
                            TempMax2.setText(rs.getString("Calidad_de_la_carne"));
                            PesoPromedio2.setText(rs.getString("Enfermedades_propensas"));
                            LitrosLeche2.setText(rs.getString("Temperamento"));
                            Crias2.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades2.setText(rs.getString("Resistencia_al_clima"));
                            Especialidad2.setText(rs.getString("Cantidad_de_huevos_al_año"));
                            Gestacion2.setText(rs.getString("Especialidad"));
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
