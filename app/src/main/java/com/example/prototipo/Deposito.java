package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Deposito  extends AppCompatActivity {
    private Ahorros item;
    private ImageView regresar;
    private TextView txt_nombre;
    private TextView txt_dinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        regresar= (ImageView) findViewById(R.id.regresar);
        txt_nombre= findViewById(R.id.nombre_deposito);
        txt_dinero = findViewById(R.id.saldo_dinero);
        // item =(Ahorros)getIntent().

        ConexionSQLiteHelper db = new ConexionSQLiteHelper(getApplicationContext(), null, null, 1);

        txt_nombre.getText().toString();

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Deposito.this,Ahorros.class);
                startActivity(intent);

            }
        });


    }
}
