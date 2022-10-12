package com.example.project;

public class MainModel {
    String CantidadAct;
    String CantidadDes;
    String nombre, tulr;

    MainModel(){

    }
    public MainModel(String cantidadAct, String cantidadDes, String nombre, String tulr) {
        this.CantidadAct = cantidadAct;
        this.CantidadDes = cantidadDes;
        this.nombre = nombre;
        this.tulr = tulr;
    }

    public String getCantidadAct() {
        return CantidadAct;
    }

    public void setCantidadAct(String cantidadAct) {
        CantidadAct = cantidadAct;
    }

    public String getCantidadDes() {
        return CantidadDes;
    }

    public void setCantidadDes(String cantidadDes) {
        CantidadDes = cantidadDes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTulr() {
        return tulr;
    }

    public void setTulr(String tulr) {
        this.tulr = tulr;
    }
}
