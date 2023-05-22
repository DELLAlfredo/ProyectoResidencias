package com.example.proyectoaplicacionchecador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectoaplicacionchecador.abcMaestros;
import com.example.proyectoaplicacionchecador.chequeoClases;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "POS";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_MAESTROS = "t_maestros";

    public static final String TABLE_AULA = "t_aula";
    public static final String TABLE_CARRERA = "t_carrera";
    public static final String TABLE_ACADEMIA = "t_academia";
    public static final String TABLE_USER = "t_user";
    public static  final  String TABLE_ChequeoClases ="ChequeoClases";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(@Nullable Context abcMaestros, @Nullable String POS, @Nullable SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(abcMaestros, POS, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAESTROS + "("+
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
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
                "usuario TEXT NOT NULL,"+ "contraseña TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AULA + "("+
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ChequeoClases + "("+
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "nombreAula TEXT NOT NULL,"
                +"nombreDocente TEXT NOT NULL,"
                +"Hora TEXT NOT NULL,"+"" +
                "Accion TEXT NOT NULL)");
    }
/////////////////Revisa si ya existe un campo///////////////
   /* public boolean checkClasesExists(String clases) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM ChequeoClases WHERE clases = ?";
        Cursor cursor = db.rawQuery(query, new String[]{clases});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }*/

    public Cursor getSpinnerData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"_id","nombre"}; // Cambia "nombre_aula" al nombre real del campo que deseas mostrar en el Spinner
        String tableName = "t_aula"; // Cambia "tabla_aulas" al nombre real de la tabla que deseas consultar
        return db.query(tableName, projection, null, null, null, null, null);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MAESTROS);
        onCreate(sqLiteDatabase);
    }

    public Cursor getSpinnerDatas(String maestro) {
        SQLiteDatabase DB = getReadableDatabase();
        String[] projection = {"_id","nombre"}; // Cambia "nombre_aula" al nombre real del campo que deseas mostrar en el Spinner
        String tableName = "t_maestros"; // Cambia "tabla_aulas" al nombre real de la tabla que deseas consultar
        return DB.query(tableName, projection, null, null, null, null, null);

    }

    public void insertarChequeoClases(String aula, String hora, String docente, String accion, String clases) {
        SQLiteDatabase db = getWritableDatabase();

        // Crear un objeto ContentValues y agregar los valores a insertar
        ContentValues values = new ContentValues();
        values.put("nombreAula", aula);
        values.put("Hora", hora);
        values.put("nombreDocente", docente);
        values.put("Accion", accion);
        values.put("_id", clases);

        // Insertar los valores en la tabla "ChequeoClases"
        db.insert("ChequeoClases", null, values);

        // Cerrar la base de datos
        db.close();
    }
}