package com.example.project;


public class donacionGetter {
    String Name;
    Integer Cant;

    public String getName() {
        return Name;
    }

    public Integer getCant() {
        return Cant;
    }

    public donacionGetter(String name, Integer cant) {
        this.Name = name;
        this.Cant = cant;
    }

}
