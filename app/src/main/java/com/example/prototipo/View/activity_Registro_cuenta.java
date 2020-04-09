package com.example.prototipo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototipo.R;
import com.example.prototipo.SQLite.ConexionSQLiteHelper;
import com.example.prototipo.SQLite.utilidades;

public class activity_Registro_cuenta extends AppCompatActivity {
    ImageView regresar;
    Dialog daialogo;
    private EditText nombre_cuenta;
    private EditText dinero;
    private Button crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        regresar= (ImageView) findViewById(R.id.regresar);
        crear=(Button) findViewById(R.id.b_crear);
        nombre_cuenta= (EditText) findViewById(R.id.nombre);
        dinero= (EditText) findViewById(R.id.numero);
        daialogo = new Dialog(this);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_Registro_cuenta.this, activity_Home.class);
                startActivity(intent);

            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre_cuenta.getText().toString().isEmpty()&& dinero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Campos obligatorios ",Toast.LENGTH_SHORT).show();
                }else{
                    registrarCuentas();
                    nombre_cuenta.setText(null);
                    dinero.setText(null);
                }

            }
        });
    }
    //metodo para crea venta de ayuda
    public void ShowPupop(View view){
        TextView cerrar;
        TextView ayuda;
        daialogo.setContentView(R.layout.ayuda);
        cerrar = (TextView) daialogo.findViewById(R.id.Cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daialogo.dismiss();
            }
        });
        daialogo.show();

    }
    private void registrarCuentas() {
        //es donde se guarda la base de datos SQLite
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_cuenta",null,1);
        // escribir en la base de datos
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE,nombre_cuenta.getText().toString());
        values.put(utilidades.CAMPO_DINERO,dinero.getText().toString());//cambiar a decimales


        Long idResultante=db.insert(utilidades.TABLA_NOMBRE,utilidades.CAMPO_NOMBRE,values);

        Toast.makeText(getApplicationContext(),"Cuenta Registrada: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}
