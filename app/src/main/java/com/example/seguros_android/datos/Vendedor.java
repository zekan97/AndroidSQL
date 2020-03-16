package com.example.seguros_android.datos;

public class Vendedor {
    public String DNI, nombre, apllidos, telefono, password;
    public int es_admin, activo;

    //Contructor de vendedor
    public Vendedor(String DNI, String nombre, String apllidos, String telefono, String password, int es_admin, int activo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apllidos = apllidos;
        this.telefono = telefono;
        this.password = password;
        this.es_admin = es_admin;
        this.activo = activo;
    }

    //Getters y setters
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApllidos() {
        return apllidos;
    }

    public void setApllidos(String apllidos) {
        this.apllidos = apllidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEs_admin() {
        return es_admin;
    }

    public void setEs_admin(int es_admin) {
        this.es_admin = es_admin;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }




}
