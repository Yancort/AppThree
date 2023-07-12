package com.example.threeapp;
import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String fecha;
    private String correo;

    public Item(int id, String nombre, String apellido, String fecha, String correo ){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return nombre;
    }
    public String getLastName() {
        return apellido;
    }
    public String getFecha() {
        return fecha;
    }
    public String getCorreo() {
        return correo;
    }

}
