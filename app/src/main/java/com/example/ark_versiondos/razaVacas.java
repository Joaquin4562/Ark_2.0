package com.example.ark_versiondos;

public class razaVacas {
    private String nombre;
    private String pais;
    private int fotoRaza;
    private int icon_vaca;

    public razaVacas() {
    }

    public razaVacas(String nombre, String pais, int fotoRaza,int icono) {
        this.nombre = nombre;
        this.pais = pais;
        this.fotoRaza = fotoRaza;
        this.icon_vaca=icono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return pais;
    }

    public int getFotoRaza() {
        return fotoRaza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.pais = descripcion;
    }

    public void setFotoRaza(int fotoRaza) {
        this.fotoRaza = fotoRaza;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIcon_vaca() {
        return icon_vaca;
    }

    public void setIcon_vaca(int icon_vaca) {
        this.icon_vaca = icon_vaca;
    }
}
