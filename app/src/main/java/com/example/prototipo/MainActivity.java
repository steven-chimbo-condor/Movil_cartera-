package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button iniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = (Button) findViewById(R.id.inicio);

        //envio la accion al boton
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Cuenta.class);
                 startActivity(intent);
                 overridePendingTransition(R.animator.izquierda,R.animator.derecha);

            }
        });

    }


}
