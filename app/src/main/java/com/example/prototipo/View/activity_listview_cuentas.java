package com.example.prototipo.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo.Modelo.Modelo;
import com.example.prototipo.R;
import com.example.prototipo.SQLite.ConexionSQLiteHelper;
import com.example.prototipo.SQLite.utilidades;

import java.util.ArrayList;

public class activity_listview_cuentas extends AppCompatActivity {

    ListView listViewPersonas;//lista creada
    ArrayList<String> listaInformacion;
    ArrayList<Modelo> listaCuenta;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_cuentas);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);

        listViewPersonas= (ListView) findViewById(R.id.lista);

        consultarListaCuentas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        //metodo que cuando doy click en un item me muestre la otra activity
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Nombre: "+listaCuenta.get(pos).getNombre_cuenta()+"\n";
                informacion+="Dinero: "+listaCuenta.get(pos).getValor_cuenta()+"\n";


                //Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                Modelo user=listaCuenta.get(pos);

                Intent intent=new Intent(activity_listview_cuentas.this, activity_Deposito.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("cuenta",user);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    private void consultarListaCuentas() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Modelo cuenta=null;
        listaCuenta=new ArrayList<Modelo>();
        //select * from cuenta
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utilidades.TABLA_NOMBRE,null);

        while (cursor.moveToNext()){
            cuenta=new Modelo();
            cuenta.setNombre_cuenta(cursor.getString(0));
            cuenta.setValor_cuenta(cursor.getString(1));


            listaCuenta.add(cuenta);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for (int i=0; i<listaCuenta.size();i++){
            listaInformacion.add(listaCuenta.get(i).getNombre_cuenta());
        }

    }

}

