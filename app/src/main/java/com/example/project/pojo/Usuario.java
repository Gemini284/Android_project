package com.example.project.pojo;

// Nuestra clase objeto de nuestra base da datos que tenemos en firebase

public class Usuario {
    private String email;
    private String nombre;

    public Usuario() {
    }

    public Usuario(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }


    // Getters y setters para llamarlos desde el adaptador
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
