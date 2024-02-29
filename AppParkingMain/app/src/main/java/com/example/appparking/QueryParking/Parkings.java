package com.example.appparking.QueryParking;

public class Parkings {

    String nombre;
    String direccion;

    String idparking;

    public Parkings(String nombre, String direccion, String idparking) {
        this.idparking = idparking;
        this.nombre = nombre;
        this.direccion = direccion;
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

    public String getIdparking() {
        return idparking;
    }

    public void setIdparking(String idparking) {
        this.idparking = idparking;
    }


}
