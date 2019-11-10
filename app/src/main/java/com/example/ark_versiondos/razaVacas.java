package com.example.ark_versiondos;

public class razaVacas {
    private String nombre;
    private String especialidad;
    private int fotoRaza;
    private int icon_vaca;

    public razaVacas() {
    }

    public razaVacas(String nombre, String especialidad, int fotoRaza,int icono) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.fotoRaza = fotoRaza;
        this.icon_vaca=icono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFotoRaza() {
        return fotoRaza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFotoRaza(int fotoRaza) {
        this.fotoRaza = fotoRaza;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String pais) {
        this.especialidad = pais;
    }

    public int getIcon_vaca() {
        return icon_vaca;
    }

    public void setIcon_vaca(int icon_vaca) {
        this.icon_vaca = icon_vaca;
    }
}
