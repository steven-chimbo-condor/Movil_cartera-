package com.example.prototipo;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.prototipo.Utilidades.utilidades;

import java.util.ArrayList;

public class Ahorros extends AppCompatActivity {

    private ListView lista_cuenta;
    ArrayList<String> lista_informacion;
    ArrayList<Modelo> lista_modelo;

    ConexionSQLiteHelper conexion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);

        //lista_cuenta= (ListView) findViewById(R.id.list_cuentas);

        conexion = new ConexionSQLiteHelper(this,"bd_cuenta",null,1);

        //creo un adapter para enviar la list ade indormacion
        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista_informacion);
        //envio a lista_cuenta el adaptador
        lista_cuenta.setAdapter(adapter);

        //metodo para consultar las cuentas
        consultaListaCuenta();
    }

    private void consultaListaCuenta() {
        SQLiteDatabase database=conexion.getReadableDatabase();
        Modelo modelo= null;
        lista_modelo= new ArrayList<Modelo>();
        //consulta=select * from cuenta
        Cursor cursor=database.rawQuery("SELECT * FROM "+ utilidades.TABLA_CUENTA,null);

        while (cursor.moveToNext()){
            modelo = new Modelo();

            modelo.setNombre_cuenta(cursor.getString(0));
            modelo.setValor_cuenta(cursor.getInt(0));

            lista_modelo.add(modelo);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        lista_informacion= new ArrayList<String>();

        //creo un for para recorrer todos los datos
        for(int i=0;i<lista_modelo.size();i++){
            lista_informacion.add(lista_modelo.get(i).getNombre_cuenta());

        }


    }


}
