package com.example.proyectoaplicacionchecador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "db_checador";
    public static final String TABLE_MAESTROS = "t_maestros";



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAESTROS + "("+
                "id_maestro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "academia TEXT NOT NULL)");

        sqLiteDatabase.execSQL("create table carreras(id int primary key, nombre text)");

        sqLiteDatabase.execSQL("create table carreras(id int primary key, codigo text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAESTROS);
        onCreate(sqLiteDatabase);
    }

}
