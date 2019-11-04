package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Login extends AppCompatActivity {


    Button abrirRegistro;
    Button abrirRecuperar;
    Button abrirMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        abrirRegistro = (Button) findViewById(R.id.bregistrar);
        abrirRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirRegistro = new Intent(Login.this,Registro.class);
                startActivity(abrirRegistro);
            }
        });

        abrirRecuperar = (Button) findViewById(R.id.brecuperar);
        abrirRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirRecuperar = new Intent(Login.this,Recuperar.class);
                startActivity(abrirRecuperar);
            }
        });

        abrirMenuPrincipal = (Button) findViewById(R.id.biniciars);
        abrirMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirMenuPrincipal = new Intent(Login.this,MainActivity.class);
                startActivity(abrirMenuPrincipal);
            }
        });
    }
}
