package com.example.prototipo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prototipo.Modelo.Modelo;
import com.example.prototipo.R;

public class activity_Deposito extends AppCompatActivity {

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

        Bundle objetoEnviado=getIntent().getExtras();
        Modelo user=null;

        if(objetoEnviado!=null){
            user= (Modelo) objetoEnviado.getSerializable("cuenta");
            txt_nombre.setText(user.getNombre_cuenta().toString());
            txt_dinero.setText(user.getValor_cuenta().toString());
        }
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_Deposito.this, activity_listview_cuentas.class);
                startActivity(intent);

            }
        });

    }
    public void SUMA(){

    }
}
