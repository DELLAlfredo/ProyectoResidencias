package com.example.proyectoaplicacionchecador.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectoaplicacionchecador.abcMaestros;
import com.example.proyectoaplicacionchecador.chequeoClases;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_MAESTROS = "t_maestros";

    public static final String TABLE_AULA = "t_aula";
    public static final String TABLE_CARRERA = "t_carrera";
    public static final String TABLE_ACADEMIA = "t_academia";
    public static final String TABLE_USER = "t_user";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(@Nullable Context abcMaestros, @Nullable String POS, @Nullable SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(abcMaestros, POS, factory, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAESTROS + "("+
                "id_maestro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "academia TEXT NOT NULL)");

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

    public Cursor getSpinnerData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"nombre"}; // Cambia "nombre_aula" al nombre real del campo que deseas mostrar en el Spinner
        String tableName = "t_aula"; // Cambia "tabla_aulas" al nombre real de la tabla que deseas consultar
        return db.query(tableName, projection, null, null, null, null, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAESTROS);
        onCreate(sqLiteDatabase);
    }



}
