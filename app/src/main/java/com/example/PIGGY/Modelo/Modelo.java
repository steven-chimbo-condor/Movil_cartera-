package com.example.PIGGY.Modelo;

import java.io.Serializable;

public class Modelo implements Serializable {

    private String nombre_cuenta;
    private String valor_cuenta;

    public Modelo(String nombre_cuenta, String valor_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
        this.valor_cuenta = valor_cuenta;
    }

    public Modelo() {

    }


    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public String getValor_cuenta() {
        return valor_cuenta;
    }

    public void setValor_cuenta(String valor_cuenta) {
        this.valor_cuenta = valor_cuenta;
    }
}
