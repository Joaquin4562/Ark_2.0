package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ark_versiondos.ui.home.Busqueda;
import com.example.ark_versiondos.ui.home.Comparar;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Vacas_informacion extends AppCompatActivity {

    //private List<Vaca> Usuarios = new ArrayList<>();
    private TextView Raza, TempMax, TempMin, Longevidad, PesoPromedio, LitrosLeche, Crias,
            Enfermedades, Especialidad, Gestacion, CalidadLeche, PropiedadesLeche, Temperamento, TipodeCuero;
    private TextView Animal;
    private ImageView animalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vacas_informacion);

        Raza = findViewById(R.id.raza);
        TempMax = findViewById(R.id.tempMaxima);
        TempMin = findViewById(R.id.tempMinima);
        Longevidad = findViewById(R.id.longevidad);
        PesoPromedio = findViewById(R.id.peso);
        LitrosLeche = findViewById(R.id.litrosLeche);
        Crias = findViewById(R.id.crias);
        Enfermedades = findViewById(R.id.enfermedades);
        Especialidad = findViewById(R.id.especialidad);
        Gestacion = findViewById(R.id.periodoGestacion);
        CalidadLeche = findViewById(R.id.calidadLeche);
        PropiedadesLeche = findViewById(R.id.propiedadesLeche);
        Temperamento = findViewById(R.id.temperamento);
        TipodeCuero = findViewById(R.id.cuerpo);

        Animal = findViewById(R.id.tvAnimal);
        animalImage = findViewById(R.id.image);

        ObtenerAnimal();
    }

    public void ObtenerAnimal() {
        try {
            ResultSet rs = null;
            Animal.setText(Busqueda.especie);
            Conexcion ob = new Conexcion();
            switch (Busqueda.especie) {
                case "Vaca":
                    //animalImage.setImageDrawable();
                    rs = ob.ConsultaBD("Select * from Vacas where Raza='" + Busqueda.razas + "'");
                    break;
                case "Caballo":
                    //animalImage.setImageDrawable();
                    rs = ob.ConsultaBD("Select * from Caballos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Borrego":
                    //animalImage.setImageDrawable();
                    rs = ob.ConsultaBD("Select * from Borregos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Cerdo":
                    //animalImage.setImageDrawable();
                    rs = ob.ConsultaBD("Select * from Cerdos where Raza='" + Busqueda.razas + "'");
                    break;
                case "Pollo":
                    //animalImage.setImageDrawable();
                    rs = ob.ConsultaBD("Select * from Pollos where Raza='" + Busqueda.razas + "'");
                    break;
            }
            while (rs.next()) {
                if (rs.getString("Raza").equals(Busqueda.razas)) {
                    switch (Busqueda.especie) {
                        case "Vaca":
                            Raza.setText(rs.getString("Raza"));
                            Longevidad.setText(rs.getString("Longevidad"));
                            TempMin.setText(rs.getString("Temperatura_minima"));
                            TempMax.setText(rs.getString("Temperatura_maxima"));
                            PesoPromedio.setText(rs.getString("Peso_Promedio"));
                            LitrosLeche.setText(rs.getString("Litros_de_leche_al_año"));
                            Crias.setText(rs.getString("Crias_en_vida_productiva"));
                            Enfermedades.setText(rs.getString("Enfermedades_propensas"));
                            Especialidad.setText(rs.getString("Especialidad"));
                            Gestacion.setText(rs.getString("Periodo_de_gestacion"));
                            CalidadLeche.setText(rs.getString("Calidad_de_leche"));
                            PropiedadesLeche.setText(rs.getString("Propiedades_de_la_leche"));
                            Temperamento.setText(rs.getString("Temperamento"));
                            TipodeCuero.setText(rs.getString("Tipo_de_Cuero"));
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
            rs.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}
