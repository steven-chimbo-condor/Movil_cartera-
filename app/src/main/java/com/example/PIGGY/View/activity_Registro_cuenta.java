package com.example.PIGGY.View;

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

import com.example.PIGGY.SQLite.ConexionSQLiteHelper;
import com.example.PIGGY.R;
import com.example.PIGGY.SQLite.utilidades;

public class activity_Registro_cuenta extends AppCompatActivity {
    // de claro las valriables
    private ImageView regresar;
    private Dialog dialogo;
    private EditText nombre_cuenta;
    private EditText dinero;
    private TextView cerrar;
    private Button crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);
        //  inicializo todas las variables con su id
        regresar= (ImageView) findViewById(R.id.regresar);
        crear=(Button) findViewById(R.id.b_crear);
        nombre_cuenta= (EditText) findViewById(R.id.nombre);
        dinero= (EditText) findViewById(R.id.numero);
        dialogo = new Dialog(this);

        //metodo para regresar de activiadad
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_Registro_cuenta.this, activity_Home.class);
                startActivity(intent);

            }
        });
        //metodo de regiatrar la cuenta aplicand un validador
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //valido los parametro del a ingresar y verifico si estan llenos o no
                if(nombre_cuenta.getText().toString().isEmpty()&& dinero.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Campos obligatorios ",Toast.LENGTH_SHORT).show();
                }else{
                    if(dinero.getText().toString().isEmpty()){

                        Toast.makeText(getApplicationContext(),"debe ingresar la cantidad de apertura de su cuenta ",Toast.LENGTH_SHORT).show();
                    }else{
                        //llamo al metodo registrar
                        registrarCuentas();
                        //limpio los parametros
                        nombre_cuenta.setText(null);
                        dinero.setText(null);

                    }

                }

            }
        });
    }
    //metodo para que me muestre una ventana flotante
    public void ShowPupop(View view){
        //incorporo los layout de dialogo y de cerrar
        dialogo.setContentView(R.layout.ayuda);
        cerrar = (TextView) dialogo.findViewById(R.id.Cerrar);
        //creo al momento de dar click me cierra la ventana flotante
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();

    }
    private void registrarCuentas() {
        //es donde se guarda la base de datos SQLite
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_cuenta",null,1);
        // escribir en la base de datos
        SQLiteDatabase db=conn.getWritableDatabase();
        //envio los parametros a guardar
        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE,nombre_cuenta.getText().toString());
        values.put(utilidades.CAMPO_DINERO,dinero.getText().toString());


        Long idResultante=db.insert(utilidades.TABLA_NOMBRE,utilidades.CAMPO_NOMBRE,values);

        Toast.makeText(getApplicationContext(),"Cuenta Registrada: "+idResultante,Toast.LENGTH_SHORT).show();
        //cierro la base de datos
        db.close();
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
