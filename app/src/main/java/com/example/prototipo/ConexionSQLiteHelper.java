package com.example.prototipo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.prototipo.Utilidades.utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,
                                int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase Database) {
        Database.execSQL(utilidades.Crear_Cuenta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int vercionAntigua, int vercionNueva) {



    }
}
