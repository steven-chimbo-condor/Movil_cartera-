package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototipo.Utilidades.utilidades;

public class Banco extends AppCompatActivity {
    ImageView regresar;
    Dialog daialogo;
    private EditText nombre_cuenta;
    private EditText dinero;
    private Button crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        regresar= (ImageView) findViewById(R.id.regresar);
        crear=(Button) findViewById(R.id.b_crear);
        nombre_cuenta= (EditText) findViewById(R.id.nombre);
        dinero= (EditText) findViewById(R.id.numero);
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
                //GuardarDatos();
                registrarUsuarios();
                nombre_cuenta.setText(null);
                dinero.setText(null);
            }
        });


    }
/*
    private void registrarcuentas() {
        //creo la coneccion a la base de datos
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_cuenta",null,1);
        //puedo escribir mis datos
        SQLiteDatabase database=conexion.getWritableDatabase();
        //intertar datos en la tabla
        String insert="INSERT INTO "+utilidades.TABLA_CUENTA+"("+utilidades.CAMPO_NOMBRE+","
                +utilidades.CAMPO_DINERO+")"+"VALUES('"+nombre_cuenta.getText().toString()+"','"
                +dinero.getText().toString()+"')";

        Toast.makeText(getApplicationContext(),"Datos guarados",Toast.LENGTH_SHORT).show();

        database.execSQL(insert);

        database.close();

    }

*/
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
    private void registrarUsuarios() {
        //es donde se guarda la base de datos SQLite
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_cuenta",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE,nombre_cuenta.getText().toString());
        values.put(utilidades.CAMPO_DINERO,dinero.getText().toString());


        Long idResultante=db.insert(utilidades.TABLA_CUENTA,utilidades.CAMPO_NOMBRE,values);

        Toast.makeText(getApplicationContext(),"Cuenta Registrada: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }

/*
    public void GuardarDatos() {
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(getApplicationContext(), null, null, 1);
        String nombre = nombre_cuenta.getText().toString();
        String apellido = dinero.getText().toString();
        String mensaje = db.guardar(nombre, apellido);
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();


        //creo la coneccion a la base de datos
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_cuenta",null,1);
        //puedo escribir mis datos
        SQLiteDatabase database=conexion.getWritableDatabase();
        //agrego los datos aplicando el put
        ContentValues values= new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(utilidades.CAMPO_DINERO,dinero.getText().toString());
        //agrego todo a SQLite
        Long resultante= database.insert(utilidades.TABLA_CUENTA,utilidades.CAMPO_NOMBRE,values);
        Toast.makeText(getApplicationContext(),"Datos guarados",Toast.LENGTH_SHORT).show();

        database.close();



        String nom_cuenta=nombre.getText().toString();
        String num_cantidad=numero.getText().toString();

        //creo el documento dodne se va a guardar los datos
        SharedPreferences preferencias=getSharedPreferences("cuentas", Context.MODE_PRIVATE);
        //asignamos los datos en el xml cuentas
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("nombre",nom_cuenta);
        editor.putString("valor_cuenta",num_cantidad);


        editor.commit();

        Toast.makeText(this,"Datos guarados",Toast.LENGTH_SHORT).show();


    }
    */
}
