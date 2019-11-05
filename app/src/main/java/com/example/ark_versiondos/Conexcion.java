package com.example.ark_versiondos;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexcion {
    public ResultSet ConsultaBD(String query){
        ResultSet rs=null;
        try {
            Statement st = this.conexionBd().createStatement();
            rs = st.executeQuery(query);

        }catch (Exception e){

        }
        return rs;
    }
    public Connection conexionBd(){
        Connection conexion=null;
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://sql5045.site4now.net:1433;databaseName=" +
                    "DB_A4E6C9_ProyectArk;user=DB_A4E6C9_ProyectArk_admin;" +
                    "password=Joaquin4562#;integratedSecurity=true;");
        }catch (Exception e){
        }
        return conexion;
    }
}
