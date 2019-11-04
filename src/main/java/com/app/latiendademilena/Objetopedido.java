package com.app.latiendademilena;

public class Objetopedido {

    private  String producto1;
    private  String producto2;
    private  String producto3;
    private  String unidades1;
    private  String unidades2;
    private  String unidades3;

    private  String usuario;
    private  String latitud;
    private  String longitud;
    private  Double valor;

    public Objetopedido(String producto1, String producto2, String producto3, String unidades1, String unidades2, String unidades3, String usuario, String latitud, String longitud, Double valor) {
        this.producto1 = producto1;
        this.producto2 = producto2;
        this.producto3 = producto3;
        this.unidades1 = unidades1;
        this.unidades2 = unidades2;
        this.unidades3 = unidades3;
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.valor = valor;
    }

    public String getProducto1() {
        return producto1;
    }

    public void setProducto1(String producto1) {
        this.producto1 = producto1;
    }

    public String getProducto2() {
        return producto2;
    }

    public void setProducto2(String producto2) {
        this.producto2 = producto2;
    }

    public String getProducto3() {
        return producto3;
    }

    public void setProducto3(String producto3) {
        this.producto3 = producto3;
    }

    public String getUnidades1() {
        return unidades1;
    }

    public void setUnidades1(String unidades1) {
        this.unidades1 = unidades1;
    }

    public String getUnidades2() {
        return unidades2;
    }

    public void setUnidades2(String unidades2) {
        this.unidades2 = unidades2;
    }

    public String getUnidades3() {
        return unidades3;
    }

    public void setUnidades3(String unidades3) {
        this.unidades3 = unidades3;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
