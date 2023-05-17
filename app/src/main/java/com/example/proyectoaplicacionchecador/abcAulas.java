package com.example.proyectoaplicacionchecador;

import static android.graphics.Insets.add;

import static androidx.core.content.SharedPreferencesKt.edit;

import static java.nio.file.Files.delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;

public class abcAulas extends AppCompatActivity {
    EditText edNombreAula,edIDAula;
    Spinner spinerAula;
    Button btnAceptarAu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_aulas);

        edNombreAula = findViewById(R.id.edNombreAula);
        edIDAula = findViewById(R.id.edIDAula);
        spinerAula =findViewById(R.id.spinerAula);
        btnAceptarAu = findViewById(R.id.btnAceptarAu);


        String[] crud = {"Añadir","Actualizar","Buscar","Eliminar"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        spinerAula.setAdapter(AdapterCrud);


        btnAceptarAu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch(spinerAula.getSelectedItem().toString()) {
                        case "Añadir":
                            if (!edIDAula.getText().toString().isEmpty() &&
                                    !edNombreAula.getText().toString().isEmpty()) {
                                add(Integer.parseInt(edIDAula.getText().toString()), edNombreAula.getText().toString(),spinerAula.getSelectedItem().toString());
                            }else{
                                Toast.makeText(abcAulas.this, "Debes llenar los demas campos", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Actualizar":
                            if (!edIDAula.getText().toString().isEmpty() &&
                                    !edNombreAula.getText().toString().isEmpty()) {
                                edit(Integer.parseInt(edIDAula.getText().toString()), edNombreAula.getText().toString(),spinerAula.getSelectedItem().toString());
                            }else{
                                Toast.makeText(abcAulas.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Eliminar":
                            if(!edIDAula.getText().toString().isEmpty()){
                                delete(Integer.parseInt(edIDAula.getText().toString()), spinerAula.getSelectedItem().toString());
                            }
                            break;
                        case "Buscar":
                            if(!edIDAula.getText().toString().isEmpty()){
                                search(Integer.parseInt(edIDAula.getText().toString()), spinerAula.getSelectedItem().toString());
                            }
                            break;

                    }

                }

        });


    }

    private void search(int code, String spinerAula) {
        if(spinerAula.equals("Buscar")) {
            if (code != 0) {
                DbHelper admin = new DbHelper(abcAulas.this, "POS", null, 2);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor cursor = db.rawQuery("select id_aula, nombre from t_aula where id_aula=" + code, null);
                if (cursor.moveToFirst()) {
                    edIDAula.setText(cursor.getString(0));
                    edNombreAula.setText(cursor.getString(1));
                    db.close();

                } else {
                    Toast.makeText(abcAulas.this, "El codigo que desea buscar no existe! ", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(abcAulas.this, "Escribe un codigo valido, no puede ser zero", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete(int code, String spinerAula) {
        if (spinerAula.equals("Eliminar")) {
            DbHelper admin = new DbHelper(abcAulas.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();
            int result = db.delete("t_aula", "id_aula=" + code, null);

            admin.close();
            edIDAula.setText("");
            edNombreAula.setText("");

            if (result >= 1) {
                Toast.makeText(abcAulas.this, "Se elimino al usuario", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(abcAulas.this, "El usuario que desea eliminar, no existe!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void edit(int code, String nombre, String spinerAula) {
        if (spinerAula.equals("Actualizar")) {
            DbHelper admin = new DbHelper(abcAulas.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registry = new ContentValues();

            registry.put("id_aula", code);
            registry.put("nombre", nombre);


            int result = db.update("t_aula", registry,"id_aula ="+code, null);
            if (result != 0){
                Toast.makeText(abcAulas.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(abcAulas.this, "No se puedo actualizar el registro, verifique por favor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void add(int code, String nombre, String spinerAula) {
        if (spinerAula.equals("Añadir")) {

            if (code != 0) {
                DbHelper admin = new DbHelper(abcAulas.this, "POS", null, 2);
                SQLiteDatabase db = admin.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT id_aula,nombre from t_aula where id_aula ="+code, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(abcAulas.this, "Codigo ya existente \n elija otro codigo", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues registro = new ContentValues();
                    registro.put("id_aula", code);
                    registro.put("nombre", nombre);


                    db.insert("t_aula", null, registro);
                    db.close();
                    edIDAula.setText("");
                    edNombreAula.setText("");
                    Toast.makeText(abcAulas.this, "Se guardo el registro", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(abcAulas.this, "No se pudo añadir, llena los campos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spAcademia:
                Intent intent =  new Intent(abcAulas.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(abcAulas.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.edNOMBRE:
                Intent carrera =  new Intent(abcAulas.this, abcCarrera.class);
                startActivity(carrera);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(abcAulas.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(abcAulas.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases =  new Intent(abcAulas.this, chequeoClases.class);
                startActivity(clases);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}