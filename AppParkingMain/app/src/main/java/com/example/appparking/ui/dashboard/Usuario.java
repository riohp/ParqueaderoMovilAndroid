package com.example.appparking.ui.dashboard;

import java.util.List;

public class Usuario {
    String identificacion;
    String nombres;
    String email;
    String estado;
    String rol;


    public Usuario(String identificacion, String nombres, String email, String estado, String rol) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.email = email;
        this.estado = estado;
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
