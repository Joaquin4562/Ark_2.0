package com.example.ark_versiondos.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ark_versiondos.Conexcion;
import com.example.ark_versiondos.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Perfil extends Fragment {

    private PerfilViewModel mViewModel;
    EditText nom, us, corr, tel, pass, ciudad, estado ;
    TextView nomusuario;
    Button guarda;
    View v;
    public static Perfil newInstance() {
        return new Perfil();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.perfil_fragment, container, false);
        nom = v.findViewById(R.id.nombreUser);
        us = v.findViewById(R.id.nombreUsuario);
        corr = v.findViewById(R.id.nombreCorreo);
        tel = v.findViewById(R.id.numeroTelefono);
        guarda = v.findViewById(R.id.btGuardar);
        pass = v.findViewById(R.id.contraseñaUser);
        ciudad = v.findViewById(R.id.nombreCiudad);
        estado = v.findViewById(R.id.nombreEstado);
        nomusuario = v.findViewById(R.id.nomUsuario);
        ejecutarConsulta();
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel


    }

    public void ejecutarConsulta() {




        //------------------------------------------------------------------------------------

        try {

            Conexcion ob = new Conexcion();
            ResultSet rs = ob.ConsultaBD( "SELECT Nombre, Nusuario, Correo, Telefono, Ciudad, Estado, Contraseña FROM Usuarios where id =1");

            while (rs.next()) {
                Toast.makeText(getContext(), "Hecho", Toast.LENGTH_SHORT).show();
                nom.setText(rs.getString("Nombre"));
                us.setText(rs.getString("Nusuario"));
                corr.setText(rs.getString("Correo"));
                tel.setText(rs.getString("Telefono"));
                pass.setText(rs.getString("Contraseña"));
                ciudad.setText(rs.getString("Ciudad"));
                estado.setText(rs.getString("Estado"));
                nomusuario.setText(rs.getString("Nusuario"));
            }
            Toast.makeText(getContext(),"Consulta ejecutada" ,Toast.LENGTH_LONG).show();
            rs.close();
        }catch(Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}


