package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ark_versiondos.ui.home.Comparar;

import java.sql.ResultSet;

public class Vacas_informacion extends AppCompatActivity {

    TextView Raza,TempMax,TempMin,Longevidad,PesoPromedio,LitrosLeche,Crias,Enfermedades,Especialidad,Gestacion,CalidadLeche
            ,PropiedadesLeche,Temperamento,TipodeCuero;
    //String raza;
    private Button comparar;

    String Razas;
    public Vacas_informacion() {

    }

    public Vacas_informacion(String razas) {
        this.Razas = razas;
    }

    public String getRazas() {
        return Razas;
    }

    public void setRazas(String razas) {
        Razas = razas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vacas_informacion);

        comparar=findViewById(R.id.bComparar);

        Raza=findViewById(R.id.raza);
        TempMax=findViewById(R.id.tempmax);
        TempMin=findViewById(R.id.tempmin);
        Longevidad=findViewById(R.id.longevidad);
        PesoPromedio=findViewById(R.id.pesoP);
        LitrosLeche=findViewById(R.id.litrosLeche);
        Crias=findViewById(R.id.crias);
        Enfermedades=findViewById(R.id.enfermedades);
        Especialidad=findViewById(R.id.especialidad);
        Gestacion=findViewById(R.id.periodoGestacion);
        CalidadLeche=findViewById(R.id.calidadLeche);
        PropiedadesLeche=findViewById(R.id.propiedadesLeche);
        Temperamento=findViewById(R.id.temperamento);
        TipodeCuero=findViewById(R.id.cuerpo);

        Razas = this.getRazas();
        Toast.makeText(getApplicationContext(),""+Razas+"", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),""+this.getRazas()+"", Toast.LENGTH_SHORT).show();
        ObtenerVaca(Razas);

        comparar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Vacas_informacion.this, Comparar.class);
                startActivity(intent);
            }
        });
    }
    public void ObtenerVaca(String raza){
        try {
            Conexcion ob=new Conexcion();
            ResultSet rs= ob.ConsultaBD("Select * from Vacas where Raza='"+raza+"'");
            while (rs.next()) {
                Raza.setText(Raza.getText().toString()+"    "+rs.getString("Raza"));
                PesoPromedio.setText(PesoPromedio.getText().toString()+"    "+rs.getString("Peso_Promedio"));
                Longevidad.setText(Longevidad.getText().toString()+"    "+rs.getString("Longevidad"));
                TempMin.setText(TempMin.getText().toString()+"    "+rs.getString("Temperatura_minima"));
                TempMax.setText(TempMax.getText().toString()+"    "+rs.getString("Temperatura_maxima"));
                LitrosLeche.setText(LitrosLeche.getText().toString()+"    "+rs.getString("Litros_de_leche_al_año"));
                Crias.setText(Crias.getText().toString()+"    "+rs.getString("Crias_en_vida_productiva"));
                Enfermedades.setText(Enfermedades.getText().toString()+"    "+rs.getString("Enfermedades_propensas"));
                Especialidad.setText(Especialidad.getText().toString()+"    "+rs.getString("Especialidad"));
                Gestacion.setText(Gestacion.getText().toString()+"    "+rs.getString("Periodo_de_gestacion"));
                CalidadLeche.setText(CalidadLeche.getText().toString()+"    "+rs.getString("Calidad_de_leche"));
                PropiedadesLeche.setText(PropiedadesLeche.getText().toString()+"    "+rs.getString("Propiedades_de_la_leche"));
                Temperamento.setText(Temperamento.getText().toString()+"    "+rs.getString("Temperamento"));
                TipodeCuero.setText(TipodeCuero.getText().toString()+"    "+rs.getString("Tipo_de_cuero"));
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
}
