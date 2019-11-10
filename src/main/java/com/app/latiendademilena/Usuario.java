package com.app.latiendademilena;

public class Usuario {

    public Usuario() {

    }

    public Usuario(String correo,String longitud,String latitud,String tipouser) {
        this.usuario = usuario;
        this.correo = correo;
        this.longitud=longitud;
        this.latitud=latitud;
        this.tipouser=tipouser;

    }

    private String usuario;
    private String contraseña;
    private String correo;
    private String longitud;
    private String latitud;
    private String tipouser;


    public String getTipouser() {
        return tipouser;
    }

    public void setTipouser(String tipoUser) {
        this.tipouser = tipoUser;
    }

    private String tipoUser;

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreo() {
        return correo;
    }




}
