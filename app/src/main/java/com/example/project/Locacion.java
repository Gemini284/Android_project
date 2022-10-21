package com.example.project;

public class Locacion {
    public Double lat;
    public Double lng;
    public String name;

    public Locacion(){
    }

    public Locacion(Double lat, Double lng, String name){
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public Double getLat(){
        return lat;
    }
    public Double getLng(){
        return lng;
    }
    public String getName(){
        return name;
    }

    public void setLat(Double lat){
        this.lat = lat;
    }
    public void setLng(Double lng){
        this.lng = lng;
    }
    public void setName(String name){
        this.name = name;
    }
}
