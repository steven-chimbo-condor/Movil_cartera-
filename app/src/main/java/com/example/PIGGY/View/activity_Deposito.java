package com.example.PIGGY.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PIGGY.SQLite.ConexionSQLiteHelper;
import com.example.PIGGY.Modelo.Modelo;
import com.example.PIGGY.R;
import com.example.PIGGY.SQLite.utilidades;

public class activity_Deposito extends AppCompatActivity {

    //declaro todas las variables a uutilizar
    private ImageView regresar;
    private TextView txt_nombre;
    private TextView txt_dinero;
    private  Dialog dialogo;

    private TextView Deposito;
    private TextView Valor_retiro;
    private Button guardar;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);
        //es la coneccion a la base de datos tienen el nombre de la vase y la vercion
        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);
        //inicializo las variables incertadas con su id
        regresar = (ImageView) findViewById(R.id.regresar);
        txt_nombre = findViewById(R.id.nombre_deposito);
        txt_dinero = findViewById(R.id.saldo_dinero);
        Deposito = (TextView) findViewById(R.id.Deposito);
        Valor_retiro = (TextView) findViewById(R.id.Valor_retiro);
        guardar = (Button) findViewById(R.id.button_guardar);
        dialogo = new Dialog(this);

        //metodo para obtener los datos de las listView utilizando serializable
        Bundle objetoEnviado=getIntent().getExtras();
        Modelo user=null;
        // verifico si hay algun dato enviado me obtenga y me muestre
        if(objetoEnviado!=null){
            user= (Modelo) objetoEnviado.getSerializable("cuenta");
            //le evio los datos obtenidos
            txt_nombre.setText(user.getNombre_cuenta());
            txt_dinero.setText(user.getValor_cuenta());
        }
        //metodo para regresar a la otra activity
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_Deposito.this, activity_listview_cuentas.class);
                startActivity(intent);

            }
        });
        //metodo de verificacion para realizar depositos y retiro
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verificador que si el campo deposito esta lleno  quiere decir que
                // el campo retiro esta vacio  y me realiza el metodo desposito

                if(Deposito.getText().toString().isEmpty()){
                    //verificador que si el campo deposito esta vacio quiere decir
                    // que el campo retiro esta lleno y me realiza el metodo retiro
                    if (Valor_retiro.getText().toString().isEmpty()){

                    }else{
                        Retiro_cuenta();
                        Valor_retiro.setText(null);
                    }

                }else{
                    Deposito_cuenta();
                    Guardar();
                    Deposito.setText(null);
                }
            }
        });

    }
    // metodo de deporito es una suma sensilla en dodne obtenes el valor guardo y los sumamos con el nuevo valor de deposito

    public void Deposito_cuenta(){
        String valor1=txt_dinero.getText().toString();
        String valor2= Deposito.getText().toString() ;

        //parceo los dos numeros
        int numero1=Integer.parseInt(valor1);
        int numero2=Integer.parseInt(valor2);

        int suma=numero1+numero2;

        String resultado=String.valueOf(suma);

        txt_dinero.setText(resultado);
        Toast.makeText(getApplicationContext(),"Sea depositado nuevo valor",Toast.LENGTH_SHORT).show();

    }
    //metodo de retiro es una resta en donde el valor guardado lo restamos
    //y verificamos que que en valor guardodo de ser mayor al monto de retiro
    public void Retiro_cuenta(){
        String valor1=txt_dinero.getText().toString();
        String valor2= Valor_retiro.getText().toString() ;
        int resta = 0;

        //parceo los dos numeros
        int numero1=Integer.parseInt(valor1);
        int numero2=Integer.parseInt(valor2);
        //creamos el controlador con if y preguntamos
        if(numero1>numero2){
            resta=numero1-numero2;
            Toast.makeText(getApplicationContext(),"Se retiro dinero "+Valor_retiro,Toast.LENGTH_SHORT).show();
            String resultado=String.valueOf(resta);
            txt_dinero.setText(resultado);
        }else{
            if(numero1<=numero2){
                Toast.makeText(getApplicationContext(),"fondos insuficientes",Toast.LENGTH_SHORT).show();
            }
        }



    }
    //metodo de actualizar al momento de realizar el deposito o el retiro este metodo actualiza el valor del deposito
    public void Guardar(){
        //llamo a la base de datos y la sobre escribo
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={txt_dinero.getText().toString()};
        //el contenValues nos permite obtener el valor guardado
        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_DINERO,txt_dinero.getText().toString());
        //realizo la consula donde se va actualizar el valor dinero
        db.update(utilidades.TABLA_NOMBRE,values,utilidades.CAMPO_NOMBRE+"=?",parametros);
        Toast.makeText(getApplicationContext(),"transacción exitosa ",Toast.LENGTH_LONG).show();
        //cierro la base de datos
        db.close();
    }
    //metodo para llamar la ventana flotante de saber mas
    public void ShowPupop(View view){
        //decaloro la variable
        TextView cerrar;
        //a la variale dialogo y le incorporo el layout
        dialogo.setContentView(R.layout.activity_ayuda_retiro);
        cerrar = (TextView) dialogo.findViewById(R.id.Cerrar);
        //creo el metodo para cerrar la vista flotante al momento de precionar en la vista
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();

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
