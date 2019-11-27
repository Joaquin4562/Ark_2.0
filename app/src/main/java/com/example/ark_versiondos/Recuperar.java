package com.example.ark_versiondos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Recuperar extends AppCompatActivity {

    String correo="arkteam.help@gmail.com", contraseña="A19r73K93", conR;//declarar correo y contraseña
    private EditText Correo;
    Button Recuperar;
    private List<Recuperacion> Usuarios = new ArrayList<>();
    Session session;
    Connection conexion;
    Boolean bol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recuperar);

        Correo= (EditText) findViewById(R.id.rccorreo);
        Recuperar = (Button) findViewById(R.id.recuperarcontra);
        Conexcion o= new Conexcion();
        conexion= o.conexionBd();
        Recuperar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Conexcion().conexionBd();
                ejecutarConsulta(conexion);
                bol = false;
                for(int i = 0; i < Usuarios.size(); i++) {
                    if(Correo.getText().toString().equals(Usuarios.get(i).getCorreo())){
                        conR = Usuarios.get(i).getContraseña();
                        MandarM();
                        Acceder_PI();
                        break;
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Correo no existente", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    public void MandarM(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        try{
            session= Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo, contraseña);
                }
            });
            if (session!=null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.setSubject("RECUPERACION DE CONTRASEÑA");
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(""+Correo.getText().toString()+""));
                message.setText("Este correo es en efecto de recuperación de contraseña\n" +
                        "Su contraseña es: \""+conR+"\"");
                Transport.send(message);
                Toast toast = Toast.makeText(getApplicationContext(),"Correo enviado con exito", Toast.LENGTH_SHORT);
                toast.show();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }

    }

    public void ejecutarConsulta(Connection con) {
        try {
            String arreglo[];
            String linea;
            String SQL = "SELECT Correo, Contraseña FROM Usuarios";
            ResultSet rs = new Conexcion().ConsultaBD(SQL);
            while (rs.next()) {
                linea = rs.getString("Correo") + "," + rs.getString("Contraseña");
                arreglo = linea.split(",");
                Usuarios.add(new Recuperacion(arreglo[0], arreglo[1]));
            }
            rs.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Acceder_PI(){
        Intent abrirInicio = new Intent(Recuperar.this, Login.class);
        startActivity(abrirInicio);
    }
}
