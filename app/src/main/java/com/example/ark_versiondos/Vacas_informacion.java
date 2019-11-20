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

import com.example.ark_versiondos.ui.home.Busqueda;
import com.example.ark_versiondos.ui.home.Comparar;

import java.sql.ResultSet;

public class Vacas_informacion extends AppCompatActivity {

    TextView Raza,TempMax,TempMin,Longevidad,PesoPromedio,LitrosLeche,Crias,Enfermedades,Especialidad,Gestacion,CalidadLeche
            ,PropiedadesLeche,Temperamento,TipodeCuero;

    private Button comparar;

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



        //Toast.makeText(getApplicationContext(),""+Busqueda.razas+"", Toast.LENGTH_SHORT).show();
        ObtenerVaca();

        comparar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Vacas_informacion.this, Comparar.class);
                startActivity(intent);
            }
        });
    }

    public void ObtenerVaca(){
        try {
			ResultSet rs=null;
            Conexcion ob=new Conexcion();
	    switch (Busqueda.especie){
		case "Vaca":
		     rs= ob.ConsultaBD("Select * from Vacas where Raza='"+Busqueda.razas+"'");
		    break;
		case "Caballo":
		     rs= ob.ConsultaBD("Select * from Caballos where Raza='"+Busqueda.razas+"'");
		    break;
		case "Borrego":
		     rs= ob.ConsultaBD("Select * from Borregos where Raza='"+Busqueda.razas+"'");
		    break;
		case "Cerdo":
		     rs= ob.ConsultaBD("Select * from Cerdos where Raza='"+Busqueda.razas+"'");
		    break;
		case "Pollo":
		     rs= ob.ConsultaBD("Select * from Pollos where Raza='"+Busqueda.razas+"'");
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
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
}
