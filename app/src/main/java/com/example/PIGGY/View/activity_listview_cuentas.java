package com.example.PIGGY.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PIGGY.SQLite.ConexionSQLiteHelper;
import com.example.PIGGY.Modelo.Modelo;
import com.example.PIGGY.R;
import com.example.PIGGY.SQLite.utilidades;

import java.util.ArrayList;

public class activity_listview_cuentas extends AppCompatActivity {
    // decalro todas las variables a utilizar
    private ListView listViewPersonas;
    private ArrayList<String> listaInformacion;
    private ArrayList<Modelo> listaCuenta;
    private ImageView regresar;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_cuentas);
        //conecto a la base de datos
        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_cuenta",null,1);
        //inicializo las variables incertadas con su id
        listViewPersonas= (ListView) findViewById(R.id.lista);
        regresar= (ImageView) findViewById(R.id.regresar);
        //metodo para cambiar de activity
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity_listview_cuentas.this, activity_Home.class);
                startActivity(intent);

            }
        });

        //metodo de consulta
        consultarListaCuentas();
        // creo un adaptador para que me carge en la lisView los datos q le envio utilizando una lista simple

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        //metodo que cuando doy click en un item de la listView  me muestre la otra activity
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="Nombre: "+listaCuenta.get(pos).getNombre_cuenta()+"\n";
                informacion+="Dinero: "+listaCuenta.get(pos).getValor_cuenta()+"\n";
                //envio un mensaje al momento de precionar en el iten de la ListView
                //Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                Modelo user=listaCuenta.get(pos);
                //creo un intent para cambiar de activity
                Intent intent=new Intent(activity_listview_cuentas.this, activity_Deposito.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("cuenta",user);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }
    //metodo de consulta en la base de datos y me obtiene los datos

    private void consultarListaCuentas() {

        // llamo a la base de datos
        SQLiteDatabase db=conn.getReadableDatabase();

        Modelo cuenta=null;
        //cro un arraylist donde se va aguardadar los parametros qeu traigo de la base de datos
        listaCuenta=new ArrayList<Modelo>();
        //Realizo la consulta select * from cuenta c
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utilidades.TABLA_NOMBRE,null);
        while (cursor.moveToNext()){
            cuenta=new Modelo();
            cuenta.setNombre_cuenta(cursor.getString(0));
            cuenta.setValor_cuenta(cursor.getString(1));
            //le incomporo al array list los valores pbtenidos de la base de datos
            listaCuenta.add(cuenta);
        }
        obtenerLista();
    }
    //metodo donde me muestra los datos en la listView
    private void obtenerLista() {
        //declaro u array list de string donde se guarda los datos
        listaInformacion=new ArrayList<String>();
        //realixo un controlador en el cual me va a mostrar el nombre de la cuenta
        //y me carga todas las cuentas creadas
        for (int i=0; i<listaCuenta.size();i++){
            listaInformacion.add(listaCuenta.get(i).getNombre_cuenta());
        }

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

