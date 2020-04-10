package com.example.PIGGY.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.PIGGY.R;

public class activity_inicio extends AppCompatActivity {

    private Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        iniciar = (Button) findViewById(R.id.inicio);

        //envio la accion al boton y que me cambie de activity
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent intent= new Intent(activity_inicio.this, activity_Home.class);
                 startActivity(intent);
                 //le incorporo una animacion para que se rialize de izquierda a derecha
                 overridePendingTransition(R.animator.izquierda,R.animator.derecha);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad est� a punto de hacerse visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        //  Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad est� a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora est� "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad est� a punto de ser destruida.
    }


}
