package com.example.ark_versiondos.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ark_versiondos.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Busqueda extends Fragment {
    private RecyclerView recyclerView;
    private EditText Busqueda;
    private Spinner Temp, Esp;
    private List<razaVacas> vaca = new ArrayList<>();
    public static String razas, especie = "Vaca";
    private ResultSet rs;
    private String consulta;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        recyclerView = root.findViewById(R.id.lista_animales);
        recyclerView.setAdapter(new RecyclerViewAdapter(obtenerVacasDB()));
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Busqueda = root.findViewById(R.id.etBuscar);

        Button buscar = root.findViewById(R.id.send);
        Button reset = root.findViewById(R.id.reset);

        Button vacas = root.findViewById(R.id.Vacas);
        Button caballos = root.findViewById(R.id.Caballos);
        Button cerdos = root.findViewById(R.id.Cerdos);
        Button borregos = root.findViewById(R.id.Borregos);
        Button pollos = root.findViewById(R.id.Pollos);

        Temp = root.findViewById(R.id.spTemp);
        Esp = root.findViewById(R.id.spEsp);

        //Onclicks de las especies
        vacas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Vaca";
            }
        });
        caballos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Caballo";
            }
        });
        cerdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Cerdo";
            }
        });
        borregos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Borrego";
            }
        });
        pollos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                especie = "Pollo";
            }
        });

        //Este inicializa el recycler y manda el nombre a la clase Vacas_informacion, para hacer la consulta
        recyclerView.setAdapter(new RecyclerViewAdapter(obtenerVacasDB(), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                razas = vaca.get(position).getNombre();
                Acceder_PantInf();
            }
        }));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setAdapter(new RecyclerViewAdapter(obtenerVacasDB(), new RecyclerViewOnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        razas = vaca.get(position).getNombre();
                        Acceder_PantInf();
                    }
                }));
            }
        });

        //Busqueda con filtros
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Busqueda.getText().toString().equals("")) {//Campo no vacio, busca por texto
                    if (Temp.getSelectedItem().toString().equals("Temperatura") && Esp.getSelectedItem().toString().equals("Especialidad")) {
                        //Busqueda de texto sin filtros aplicados
                        recyclerView.setAdapter(new RecyclerViewAdapter(SinFiltro(Busqueda.getText().toString()), new RecyclerViewOnItemClickListener() {
                            @Override
                            public void onClick(View v, int position) {
                                razas = vaca.get(position).getNombre();
                                Acceder_PantInf();
                            }
                        }));
                    } else { //Busqueda de texto con filtros aplicados
                        recyclerView.setAdapter(new RecyclerViewAdapter(FiltroyNombre(), new RecyclerViewOnItemClickListener() {
                            @Override
                            public void onClick(View v, int position) {
                                razas = vaca.get(position).getNombre();
                                Acceder_PantInf();
                            }
                        }));
                    }
                } else {// Busqueda sin texto, solo filtros
                    if (!Temp.getSelectedItem().toString().equals("Temperatura") && !Esp.getSelectedItem().toString().equals("Especialidad")) {
                        recyclerView.setAdapter(new RecyclerViewAdapter(SoloFiltro(), new RecyclerViewOnItemClickListener() {
                            @Override
                            public void onClick(View v, int position) {
                                razas = vaca.get(position).getNombre();
                                Acceder_PantInf();
                            }
                        }));
                    }
                }
            }
        });
        return root;
    }

    private void Acceder_PantInf() {
        Intent intent = new Intent(getContext(), Vacas_informacion.class);
        startActivity(intent);
    }

    private List<razaVacas> SinFiltro(String filtro) {
        vaca.clear();
        try {
            switch (especie) {
                default:
                case "Vaca":
                    rs = new Conexcion().ConsultaBD("Select Raza FROM Vacas WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%'");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Caballo":
                    rs = new Conexcion().ConsultaBD("Select Raza FROM Caballos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%'");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Borrego":
                    rs = new Conexcion().ConsultaBD("Select Raza FROM Borregos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%'");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Cerdo":
                    rs = new Conexcion().ConsultaBD("Select Raza FROM Cerdos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%'");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Pollo":
                    rs = new Conexcion().ConsultaBD("Select Raza FROM Pollos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%'");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return vaca;
    }

    private List<razaVacas> obtenerVacasDB() {
        vaca.clear();
        try {
            switch (especie) {
                default:
                case "Vaca":
                    rs = new Conexcion().ConsultaBD("Select Raza from Vacas");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Caballo":
                    rs = new Conexcion().ConsultaBD("Select Raza from Caballos");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Borrego":
                    rs = new Conexcion().ConsultaBD("Select Raza from Borregos");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Cerdo":
                    rs = new Conexcion().ConsultaBD("Select Raza from Cerdos");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
                case "Pollo":
                    rs = new Conexcion().ConsultaBD("Select Raza from Pollos");
                    while (rs.next()) {
                        vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                                R.drawable.vaca, R.drawable.ic_carne));
                    }
                    break;
            }
        } catch (Exception e) {
            //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return vaca;
    }

    private List<razaVacas> SoloFiltro() {
        vaca.clear();
        try {
            switch (especie) {
                default:
                case "Vaca":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Vacas WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Vacas WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() +
                                    "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Vacas WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Vacas WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Caballo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Caballos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Caballos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() +
                                    "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Caballos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Caballos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Borrego":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Borregos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Borregos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() +
                                    "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Borregos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Borregos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Cerdo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Cerdos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Cerdos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() +
                                    "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Cerdos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Cerdos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Pollo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Pollos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Pollos WHERE Temperatura_minima = '" + Temp.getSelectedItem().toString() +
                                    "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Pollos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Pollos WHERE Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
            }
            rs = new Conexcion().ConsultaBD("" + consulta + "");
            while (rs.next()) {
                vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                        R.drawable.vaca, R.drawable.ic_carne));
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return vaca;
    }

    private List<razaVacas> FiltroyNombre() {
        vaca.clear();
        try {
            switch (especie) {
                default:
                case "Vaca":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Vacas WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '" +
                                Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Vacas WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '"
                                    + Temp.getSelectedItem().toString() + "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Vacas WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Vacas WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Caballo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Caballos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '" +
                                Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Caballos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '"
                                    + Temp.getSelectedItem().toString() + "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Caballos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Caballos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Borrego":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Borregos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '" +
                                Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Borregos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '"
                                    + Temp.getSelectedItem().toString() + "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Borregos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Borregos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Cerdo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Cerdos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '" +
                                Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Cerdos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '"
                                    + Temp.getSelectedItem().toString() + "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Cerdos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Cerdos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
                case "Pollo":
                    consulta = "";
                    if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                        consulta = "Select Raza FROM Pollos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '" +
                                Temp.getSelectedItem().toString() + "'";
                        if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                            consulta = "Select Raza FROM Pollos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Temperatura_minima = '"
                                    + Temp.getSelectedItem().toString() + "' AND Especialidad= '" + Esp.getSelectedItem().toString() + "'";
                        }
                    } else if (!Esp.getSelectedItem().toString().equals("Especialidad")) {
                        consulta = "Select Raza FROM Pollos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "'";
                        if (!Temp.getSelectedItem().toString().equals("Temperatura")) {
                            consulta = "Select Raza FROM Pollos WHERE Raza LIKE '%" + Busqueda.getText().toString() + "%' AND Especialidad = '" + Esp.getSelectedItem().toString() + "' AND " +
                                    "Temperatura_minima= '" + Temp.getSelectedItem().toString() + "'";
                        }
                    }
                    break;
            }
            rs = new Conexcion().ConsultaBD("" + consulta + "");
            while (rs.next()) {
                vaca.add(new razaVacas(rs.getString("Raza"), "" + Temp.getSelectedItem().toString() + "",
                        R.drawable.vaca, R.drawable.ic_carne));
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return vaca;
    }

}
