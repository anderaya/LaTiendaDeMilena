package com.app.latiendademilena;

public class producto {

    private  String nombre;
    private  String detalles;
    private  Double precio;
    private  Double unidades;

    public producto(String nombre, String detalles, Double precio, Double unidades) {
        this.nombre = nombre;
        this.detalles = detalles;
        this.precio = precio;
        this.unidades = unidades;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getUnidades() {
        return unidades;
    }

    public void setUnidades(Double unidades) {
        this.unidades = unidades;
    }
}


