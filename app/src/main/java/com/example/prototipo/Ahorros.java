package com.example.prototipo;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo.Utilidades.utilidades;
import com.example.prototipo.entidades.Modelo;

import java.util.ArrayList;

public class Ahorros extends AppCompatActivity {

    ListView lv ;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);
        lv = (ListView) findViewById(R.id.lista);
        //conecto a la base de datos
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(getApplicationContext(), null, null, 1);
        //a lista incorporo en metodo llenar lista
        lista = db.llenar_lv();
        //creo un adapter para que se muestre los datos
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adaptador);
    }
}
