package com.example.ark_versiondos;

public class Recuperacion {
    private String correo;
    private String contraseña;

    public Recuperacion() {
    }

    public Recuperacion(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
