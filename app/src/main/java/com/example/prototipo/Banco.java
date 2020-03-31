package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Banco extends AppCompatActivity {
    ImageView regresar;
    Dialog daialogo;
    private EditText nombre;
    private EditText numero;
    private Button crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);
        regresar= (ImageView) findViewById(R.id.regresar);
        crear=(Button) findViewById(R.id.b_crear);
        nombre= (EditText) findViewById(R.id.nombre);
        numero= (EditText) findViewById(R.id.numero);
        daialogo = new Dialog(this);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Banco.this,Cuenta.class);
                startActivity(intent);

            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarDatos();
                nombre.setText(null);
                numero.setText(null);
            }
        });


    }



    //metodo para crea venta de ayuda
    public void ShowPupop(View view){
        TextView cerrar;
        TextView ayuda;
        daialogo.setContentView(R.layout.ayuda);
        cerrar = (TextView) daialogo.findViewById(R.id.ayuda);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daialogo.dismiss();
            }
        });
        daialogo.show();

    }


    public void GuardarDatos(){
        String nom_cuenta=nombre.getText().toString();
        String num_cantidad=numero.getText().toString();

        //creo el documento dodne se va a guardar los datos
        SharedPreferences preferencias=getSharedPreferences("cuentas", Context.MODE_PRIVATE);
        //asignamos los datos en el xml cuentas
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(nom_cuenta,num_cantidad);


        editor.commit();

        Toast.makeText(this,"Datos guarados",Toast.LENGTH_SHORT).show();


    }
}
