package com.example.prototipo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototipo.Modelo.Modelo;
import com.example.prototipo.R;
import com.example.prototipo.SQLite.ConexionSQLiteHelper;
import com.example.prototipo.SQLite.utilidades;

public class activity_Deposito extends AppCompatActivity {

    private ImageView regresar;
    private TextView txt_nombre;
    private TextView txt_dinero;
    Dialog daialogo;

    private TextView Deposito;
    private TextView Valor_retiro;
    private Button guardar;

    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);

        regresar = (ImageView) findViewById(R.id.regresar);
        txt_nombre = findViewById(R.id.nombre_deposito);
        txt_dinero = findViewById(R.id.saldo_dinero);
        Deposito = (TextView) findViewById(R.id.Deposito);
        Valor_retiro = (TextView) findViewById(R.id.Valor_retiro);
        guardar = (Button) findViewById(R.id.button_guardar);
        daialogo = new Dialog(this);

        Bundle objetoEnviado=getIntent().getExtras();
        Modelo user=null;

        if(objetoEnviado!=null){
            user= (Modelo) objetoEnviado.getSerializable("cuenta");
            txt_nombre.setText(user.getNombre_cuenta().toString());
            txt_dinero.setText(user.getValor_cuenta().toString());
        }
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_Deposito.this, activity_listview_cuentas.class);
                startActivity(intent);

            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resta();
                Valor_retiro.setText(null);
            }
        });

    }

    public void Suma(){
        String valor1=txt_dinero.getText().toString();
        String valor2= Deposito.getText().toString() ;

        //parceo los dos numeros
        int numero1=Integer.parseInt(valor1);
        int numero2=Integer.parseInt(valor2);

        int suma=numero1+numero2;

        String resultado=String.valueOf(suma);

        txt_dinero.setText(resultado);
        //Toast.makeText(getApplicationContext(),"Sea depositado nuevo valor",Toast.LENGTH_SHORT).show();

    }
    public void Resta(){
        String valor1=txt_dinero.getText().toString();
        String valor2= Valor_retiro.getText().toString() ;
        int resta = 0;

        //parceo los dos numeros
        int numero1=Integer.parseInt(valor1);
        int numero2=Integer.parseInt(valor2);
        if(numero1>numero2){
            resta=numero1-numero2;
            Toast.makeText(getApplicationContext(),"Se retiro dinero ",Toast.LENGTH_SHORT).show();
            String resultado=String.valueOf(resta);
            txt_dinero.setText(resultado);
        }else{
            if(numero1<=numero2){
                Toast.makeText(getApplicationContext(),"fondos insuficientes",Toast.LENGTH_SHORT).show();
            }
        }



    }

    public void Guardar(){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={txt_nombre.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(utilidades.CAMPO_DINERO,txt_dinero.getText().toString());
        db.update(utilidades.TABLA_NOMBRE,values,utilidades.CAMPO_DINERO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se depositado corectamente ",Toast.LENGTH_LONG).show();
        db.close();
    }
    //metodo para crea venta de ayuda
    public void ShowPupop(View view){
        TextView cerrar;
        daialogo.setContentView(R.layout.activity_ayuda_retiro);
        cerrar = (TextView) daialogo.findViewById(R.id.Cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daialogo.dismiss();
            }
        });
        daialogo.show();

    }

}
