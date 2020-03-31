package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Cuenta extends AppCompatActivity {
    ImageView crear;
    ImageView cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        crear= (ImageView) findViewById(R.id.crear);
        cuenta=(ImageView) findViewById(R.id.cuenta);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Cuenta.this,Banco.class);
                startActivity(intent);

            }
        });
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Cuenta.this,Ahorros.class);
                startActivity(intent);

            }
        });

    }

}
