package com.example.PIGGY.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prototipo.R;

public class activity_inicio extends AppCompatActivity {
    Button iniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        iniciar = (Button) findViewById(R.id.inicio);

        //envio la accion al boton
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent intent= new Intent(activity_inicio.this, activity_Home.class);
                 startActivity(intent);
                 overridePendingTransition(R.animator.izquierda,R.animator.derecha);

            }
        });

    }


}
