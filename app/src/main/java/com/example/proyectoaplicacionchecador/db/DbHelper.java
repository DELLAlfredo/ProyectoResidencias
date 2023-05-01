package com.example.proyectoaplicacionchecador.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectoaplicacionchecador.abcMaestros;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "db_checador";
    public static final String TABLE_MAESTROS = "t_maestros";

    public static final String TABLE_AULA = "t_aula";
    public static final String TABLE_CARRERA = "t_carrera";
    public static final String TABLE_ACADEMIA = "t_academia";
    public static final String TABLE_USER = "t_user";




    public DbHelper(abcMaestros abcMaestros, String pos, @Nullable Context context, int i) {
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

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CARRERA + "("+
                "id_carrera INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACADEMIA + "("+
                "id_academia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "codigo TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + "("+
                "id_user INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "usuario TEXT NOT NULL,"+
                "contraseña TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AULA + "("+
                "id_aula INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAESTROS);
        onCreate(sqLiteDatabase);
    }

}
