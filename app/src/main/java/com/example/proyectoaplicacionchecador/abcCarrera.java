package com.example.proyectoaplicacionchecador;

import static android.graphics.Insets.add;

import static androidx.core.content.SharedPreferencesKt.edit;

import static java.nio.file.Files.delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.ContentValues;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;
import com.example.proyectoaplicacionchecador.db.dbCarrera;

public class abcCarrera extends AppCompatActivity {

    private EditText edNOMBRE,edID;
    private Spinner sPOpciones;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_carrera);

        edID = findViewById(R.id.edID);
        edNOMBRE = findViewById(R.id.edNOMBRE);
        sPOpciones = findViewById(R.id.sPOpciones);
        btnGuardar = findViewById(R.id.btnGuardar);


        String[] crud = {"Añadir","Actualizar","Buscar","Eliminar"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        sPOpciones.setAdapter(AdapterCrud);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(sPOpciones.getSelectedItem().toString()) {
                    case "Añadir":
                        if (!edID.getText().toString().isEmpty() &&
                                !edNOMBRE.getText().toString().isEmpty()) {
                            add(Integer.parseInt(edID.getText().toString()), edNOMBRE.getText().toString(),sPOpciones.getSelectedItem().toString());
                        }else{
                            Toast.makeText(abcCarrera.this, "Debes llenar los demas campos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Actualizar":
                        if (!edID.getText().toString().isEmpty() &&
                                !edNOMBRE.getText().toString().isEmpty()) {
                            edit(Integer.parseInt(edID.getText().toString()), edNOMBRE.getText().toString(),sPOpciones.getSelectedItem().toString());
                        }else{
                            Toast.makeText(abcCarrera.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Eliminar":
                        if(!edID.getText().toString().isEmpty()){
                            delete(Integer.parseInt(edID.getText().toString()), sPOpciones.getSelectedItem().toString());
                        }
                        break;
                    case "Buscar":
                        if(!edID.getText().toString().isEmpty()){
                            search(Integer.parseInt(edID.getText().toString()), sPOpciones.getSelectedItem().toString());
                        }
                        break;

                }

            }

        });
    }

    private void search(int code, String sPOpciones) {
        if(sPOpciones.equals("Buscar")) {
            if (code != 0) {
                DbHelper admin = new DbHelper(abcCarrera.this, "POS", null, 2);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor cursor = db.rawQuery("select id_carrera, nombre from t_carrera where id_carrera=" + code, null);
                if (cursor.moveToFirst()) {
                    edID.setText(cursor.getString(0));
                    edNOMBRE.setText(cursor.getString(1));
                    db.close();

                } else {
                    Toast.makeText(abcCarrera.this, "El codigo que desea buscar no existe! ", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(abcCarrera.this, "Escribe un codigo valido, no puede ser zero", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete(int code, String sPOpciones) {
        if (sPOpciones.equals("Eliminar")) {
            DbHelper admin = new DbHelper(abcCarrera.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();
            int result = db.delete("t_carrera", "id_carrera=" + code, null);

            admin.close();
            edID.setText("");
            edNOMBRE.setText("");

            if (result >= 1) {
                Toast.makeText(abcCarrera.this, "Se elimino al usuario", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(abcCarrera.this, "El usuario que desea eliminar, no existe!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void edit(int code, String nombre, String sPOpciones) {
        if (sPOpciones.equals("Actualizar")) {
            DbHelper admin = new DbHelper(abcCarrera.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registry = new ContentValues();

            registry.put("id_carrera", code);
            registry.put("nombre", nombre);


            int result = db.update("t_carrera", registry,"id_carrera ="+code, null);
            if (result != 0){
                Toast.makeText(abcCarrera.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(abcCarrera.this, "No se puedo actualizar el registro, verifique por favor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void add(int code, String nombre, String sPOpciones) {
        if (sPOpciones.equals("Añadir")) {

            if (code != 0) {
                DbHelper admin = new DbHelper(abcCarrera.this, "POS", null, 2);
                SQLiteDatabase db = admin.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT id_carrera,nombre from t_carrera where id_carrera ="+code, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(abcCarrera.this, "Codigo ya existente \n elija otro codigo", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues registro = new ContentValues();
                    registro.put("id_carrera", code);
                    registro.put("nombre", nombre);


                    db.insert("t_carrera", null, registro);
                    db.close();
                    edID.setText("");
                    edNOMBRE.setText("");
                    Toast.makeText(abcCarrera.this, "Se guardo el registro", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(abcCarrera.this, "No se pudo añadir, llena los campos", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spAcademia:
                Intent intent =  new Intent(abcCarrera.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(abcCarrera.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(abcCarrera.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(abcCarrera.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(abcCarrera.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}