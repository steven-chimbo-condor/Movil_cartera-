package com.example.prototipo;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo.Utilidades.utilidades;
import com.example.prototipo.entidades.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Ahorros extends AppCompatActivity {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Modelo> listaCuenta;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);

        listViewPersonas= (ListView) findViewById(R.id.lista);

        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Nombre: "+listaCuenta.get(pos).getNombre_cuenta()+"\n";
                informacion+="Dinero: "+listaCuenta.get(pos).getValor_cuenta()+"\n";


                //Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Modelo user=listaCuenta.get(pos);

                Intent intent=new Intent(Ahorros.this,Deposito.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("cuenta",user);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Modelo usuario=null;
        listaCuenta=new ArrayList<Modelo>();
        //select * from cuenta
        Cursor cursor=db.rawQuery("SELECT * FROM "+utilidades.TABLA_CUENTA,null);

        while (cursor.moveToNext()){
            usuario=new Modelo();
            usuario.setNombre_cuenta(cursor.getString(0));
            usuario.setValor_cuenta(cursor.getString(1));


            listaCuenta.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaCuenta.size();i++){
            listaInformacion.add(listaCuenta.get(i).getNombre_cuenta()+" - "
                    +listaCuenta.get(i).getValor_cuenta());
        }

    }

}
