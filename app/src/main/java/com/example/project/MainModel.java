package com.example.project;

public class MainModel {
    int CantidadAct, CantidadDes;
    String nombre, tulr;

    MainModel(){

    }
    public MainModel(int cantidadAct, int cantidadDes, String nombre, String tulr) {
        CantidadAct = cantidadAct;
        CantidadDes = cantidadDes;
        this.nombre = nombre;
        this.tulr = tulr;
    }

    public int getCantidadAct() {
        return CantidadAct;
    }

    public void setCantidadAct(int cantidadAct) {
        CantidadAct = cantidadAct;
    }

    public int getCantidadDes() {
        return CantidadDes;
    }

    public void setCantidadDes(int cantidadDes) {
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
