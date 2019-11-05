package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    private Button regis;
    private EditText NUsuario, Nombre, Ciudad, Estado, Correo, Telefono, Contra, ConfContra;
    private String nusuario, nombre, ciudad, estado, correo, telefono, contra, confcontra;
    private Connection conexion = null;
    boolean bol = false;
    private List<RegisAux> Usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro);

        regis = (Button) findViewById(R.id.registrarse);
        NUsuario = (EditText) findViewById(R.id.rcusuario);
        Nombre = (EditText) findViewById(R.id.rcnombre);
        Ciudad = (EditText) findViewById(R.id.rciudad);
        Estado = (EditText) findViewById(R.id.restado);
        Correo = (EditText) findViewById(R.id.rccorreo);
        Telefono = (EditText) findViewById(R.id.rctelefono);
        Contra = (EditText) findViewById(R.id.rcontraseña);
        ConfContra = (EditText) findViewById(R.id.rrecucontraseña);
        Conexcion o= new Conexcion();
        conexion= o.conexionBd();
    }

    public void Reg (View v){
        ejecutarConsulta(conexion);
        bol = false;
        vars();
        for(int i = 0; i < Usuarios.size(); i++) {
            if(nusuario.equals(Usuarios.get(i).getNUsuario())) {
                bol = true;
                Toast.makeText(this,"Nombre de usuario ocupado",Toast.LENGTH_LONG).show();
                break;
            }
            if( correo.equals(Usuarios.get(i).getCorreo())) {
                bol = true;
                Toast.makeText(this,"Correo ya existente",Toast.LENGTH_LONG).show();
                break;
            }
            if(!contra.equals(confcontra)){
                bol = true;
                Toast.makeText(this,"La contraseña no fue confirmada correctamente",Toast.LENGTH_LONG).show();
                break;
            }
        }
        if (!bol){
            try {
                Usuarios.clear();
                String SQL = "INSERT INTO Usuarios VALUES ('" + nusuario + "', '" + nombre + "', '" + ciudad + "', '"
                        + estado + "', '" + correo + "', '" + telefono + "', '" + contra + "')";
                Statement stmt = conexion.createStatement();
                int count = stmt.executeUpdate(SQL);
                stmt.close();
                Toast.makeText(this,"Registro creado exitosamente!",Toast.LENGTH_LONG).show();
            }catch(Exception Ex){
                Toast.makeText(this, Ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            CamPant(v);
        }
    }

    public void vars(){
        nombre = Nombre.getText().toString();
        nusuario = NUsuario.getText().toString();
        ciudad = Ciudad.getText().toString();
        estado = Estado.getText().toString();
        correo = Correo.getText().toString();
        telefono = Telefono.getText().toString();
        contra = Contra.getText().toString();
        confcontra = ConfContra.getText().toString();
    }

    public void ejecutarConsulta(Connection con) {
        try {
            String arreglo[];
            String linea="";
            String SQL = "SELECT NUsuario, Correo FROM Usuarios";
            Conexcion ob=new Conexcion();
            ResultSet rs= ob.ConsultaBD(SQL);
            while (rs.next()) {
                linea = rs.getString("NUsuario") + "," + rs.getString("Correo");
                arreglo = linea.split(",");
                Usuarios.add(new RegisAux(arreglo[0], arreglo[1]));
            }
            //Toast.makeText(this,"Consulta ejecutada, "+linea+"",Toast.LENGTH_LONG).show();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CamPant(View v) {
        Intent regis = new Intent(Registro.this,Login.class);
        startActivity(regis);
    }
}
