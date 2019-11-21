package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private List<Usuarios> Usuarios = new ArrayList<>();
    private EditText Usuario, Contra;
    private CheckBox Recordar;
    boolean bol=false;
    private Connection conexion = null;
    private static  final String PREFS_KEY = "tu_contexto";
    private static  final String ESTADO_BOTON = "estado.boton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Usuario = (EditText) findViewById(R.id.usuario);
        Contra = (EditText) findViewById(R.id.contraseña);

        Recordar = (CheckBox) findViewById(R.id.recordar);

        Usuarios objE = new Usuarios ("Admin", "12345");
        Usuarios.add(objE);

        if (obtener_estado_boton()) {
            Intent abrirPrincipal = new Intent(Login.this, MainActivity.class);
            startActivity(abrirPrincipal);
            //this.finish();
        }
    }

    public void Iniciar (View v) {
        new Conexcion().conexionBd();
        ejecutarConsulta(conexion);
        bol = false;
        for(int i = 0; i < Usuarios.size(); i++) {
            if(Usuario.getText().toString().equals(Usuarios.get(i).getUsuario()) &&
                    Contra.getText().toString().equals(Usuarios.get(i).getContraseña())) {
                guardar_estado_boton();
                Acceder_P(v);
                bol = true;
                break;
                //Toast.makeText(this,"Estas dentro!!!",Toast.LENGTH_LONG).show();
            }
        }
        if(!bol){
            Toast.makeText(this,"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show();
        }
    }

    public void guardar_estado_boton() {
        SharedPreferences settings = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        settings.edit().putBoolean(ESTADO_BOTON,Recordar.isChecked()).apply();
    }

    public boolean obtener_estado_boton(){
        SharedPreferences settings = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return settings.getBoolean(ESTADO_BOTON,false);

    }

    public void ejecutarConsulta(Connection con) {
        try {
            String arreglo[];
            String linea;
            String SQL = "SELECT Nusuario, Contraseña FROM Usuarios";
            ResultSet rs = new Conexcion().ConsultaBD(SQL);
            while (rs.next()) {
                linea = rs.getString("Nusuario") + "," + rs.getString("Contraseña");
                arreglo = linea.split(",");
                Usuarios.add(new Usuarios(arreglo[0], arreglo[1]));
            }
            rs.close();
        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Acceder_P(View v) {
        Intent abrirPrincipal = new Intent(Login.this,MainActivity.class);
        startActivity(abrirPrincipal);
    }

    public void Acceder_R(View v) {
        Intent abrirRegistro = new Intent(Login.this,Registro.class);
        startActivity(abrirRegistro);
    }

    public void Acceder_C(View v) {
        Intent abrirRecuperarContra = new Intent(Login.this,Recuperar.class);
        startActivity(abrirRecuperarContra);
    }
}
