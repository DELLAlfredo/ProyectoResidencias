package com.example.proyectoaplicacionchecador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

   /* private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "db_checador";
    public static final String TABLE_MAESTROS = "t_maestros"; */



    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version/*@Nullable Context context*/) {
      //  super(context, DATABASE_NOMBRE,null, DATABASE_VERSION);
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db /*sqLiteDatabase*/) {
       /* sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAESTROS + "("+
                "id_maestro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "academia TEXT NOT NULL)");*/
        db.execSQL("create table t_maestros (id int primary key, nombre TEXT, apellidos TEXT, academia TEXT)");
        db.execSQL("create table t_clase (id int primary key, hora TEXT, fecha TEXT, academia TEXT, id_aula int, id_carrera int, id_maestro int)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion/*SQLiteDatabase sqLiteDatabase, int i, int i1*/) {
      //  sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAESTROS);
      //  onCreate(sqLiteDatabase);


    }
}
