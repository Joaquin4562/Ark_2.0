package com.example.ark_versiondos;

public class RegisAux {
    private String NUsuario;
    private String Correo;

    public RegisAux () {
    }

    public RegisAux (String NUsuario, String Correo) {
        this.NUsuario = NUsuario;
	this.Correo = Correo;
    }

    public String getNUsuario() {
        return NUsuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setNUsuario(String NUsuario) {
        this.NUsuario = NUsuario;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
}
