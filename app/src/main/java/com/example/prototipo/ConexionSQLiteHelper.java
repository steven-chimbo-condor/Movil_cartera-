package com.example.prototipo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.prototipo.Utilidades.utilidades;

import java.util.ArrayList;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, "Prueba", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creo la tabla con sus datos en SQLite
        db.execSQL("create table datos(nombre text, dinero text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //metodo guardar SQLite
    public String guardar(String nombre, String dinero){
        String mensaje="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",nombre);
        contenedor.put("dinero", dinero);
        try {
            database.insertOrThrow("datos",null,contenedor);
            mensaje="Datos guardados";
        }catch (SQLException e){
            mensaje="No Ingresado";
        }
        database.close();
        return mensaje;
    }
    //para buscar y actualizar datos
    public  String actualizar(String Buscar,String Nombre,String Dinero){
        String Mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",Nombre);
        contenedor.put("apellido",Dinero);
        int cantidad = database.update("datos", contenedor, "nombre='" + Buscar + "'", null);
        if(cantidad!=0){
            Mensaje="Actualizado Correctamente";
        }else{
            Mensaje="No Actualizado";
        }
        database.close();
        return Mensaje;
    }
     /*
    public String[] buscar_reg(String buscar){
        String[] datos= new String[3];
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos WHERE nombre ='"+buscar+"'";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            for(int i = 0 ; i<2;i++){
                datos[i]= registros.getString(i);

            }
            datos[2]= "Encontrado";
        }else{

            datos[2]= "No se encontro a "+buscar;
        }
        database.close();
        return datos;
    }

      */
     /*
    public String eliminar(String Nombre){
        String mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        int cantidad =database.delete("datos", "nombre='" + Nombre + "'", null);
        if (cantidad !=0){
            mensaje="Eliminado Correctamente";

        }
        else{
            mensaje = "No existe";
        }
        database.close();
        return mensaje;
    }

      */
     // metodo donde muestro los datos guardados en una listView
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM datos";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0));
            }while(registros.moveToNext());
        }
        return lista;

    }

}
