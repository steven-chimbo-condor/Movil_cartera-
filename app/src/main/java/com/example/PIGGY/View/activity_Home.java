package com.example.PIGGY.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.PIGGY.R;

public class activity_Home extends AppCompatActivity {
    //declaro todas las variables
    private ImageView crear;
    private ImageView cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //inicializo las variables incertadas con su id
        crear= (ImageView) findViewById(R.id.crear);
        cuenta=(ImageView) findViewById(R.id.cuenta);

        //creo un onClick para cambiar de activity
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity_Home.this, activity_Registro_cuenta.class);
                startActivity(intent);

            }
        });
        //creo un onClick para cambiar de activity
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo= new Intent(activity_Home.this, activity_listview_cuentas.class);
                startActivity(nuevo);

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
