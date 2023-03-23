package com.example.proyectoaplicacionchecador.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
/*
public class DbMaestros extends DbHelper{

    Context context;

    public DbMaestros(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long InsertarMaestro(String nombre, String apellidos, String academia){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellidos", apellidos);
            values.put("academia", academia);
            id = db.insert(TABLE_MAESTROS, null, values);
        }catch(Exception ex) {
            ex.toString();
        }

        return id;
    }

}
*/
