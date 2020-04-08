package com.example.prototipo.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo.R;
import com.example.prototipo.SQLite.ConexionSQLiteHelper;

public class activity_Home extends AppCompatActivity {
    ImageView crear;
    ImageView cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);

        crear= (ImageView) findViewById(R.id.crear);
        cuenta=(ImageView) findViewById(R.id.cuenta);

        //creo n onClick para cambiar de activity
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity_Home.this, activity_Registro_cuenta.class);
                startActivity(intent);

            }
        });
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo= new Intent(activity_Home.this, activity_listview_cuentas.class);
                startActivity(nuevo);

            }
        });

    }

}
