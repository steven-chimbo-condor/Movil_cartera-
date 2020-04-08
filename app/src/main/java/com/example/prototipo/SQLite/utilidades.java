package com.example.prototipo.SQLite;

public class utilidades {

    //constante campos tabla cuenta
    public static  final String TABLA_NOMBRE="cuenta";
    public static  final String CAMPO_NOMBRE="nombre";
    public static  final String CAMPO_DINERO="dinero";
    //creas la tabla
    public static  final String Crear_Cuenta="CREATE TABLE "+TABLA_NOMBRE+" ("+CAMPO_NOMBRE+" TEXT, "+CAMPO_DINERO+" TEXT)";
}
