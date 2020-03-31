package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Ahorros extends AppCompatActivity {

    private ListView cantidad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);


        cargarDatos();
    }

    private void cargarDatos() {
        SharedPreferences preferencias=getSharedPreferences("cuentas", Context.MODE_PRIVATE);





    }


}
