package com.example.appparking.ui.parking;

public class Parking {

    String plazas;
    String nombre;
    String direccion;

    public Parking(String plazas, String nombre, String direccion) {
        this.plazas = plazas;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getPlazas() {
        return plazas;
    }

    public void setPlazas(String plazas) {
        this.plazas = plazas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



}
