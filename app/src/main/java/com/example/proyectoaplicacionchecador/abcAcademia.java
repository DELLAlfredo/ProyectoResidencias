package com.example.proyectoaplicacionchecador;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;

public class abcAcademia extends AppCompatActivity {
    EditText etIDacademia,etCodigo;
    Spinner spinerAcademia;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_academia);

        etIDacademia = findViewById(R.id.etIDacademia);
        etCodigo = findViewById(R.id.etCodigo);
        spinerAcademia = findViewById(R.id.spinerAcademia);
        btnAceptar = findViewById(R.id.btnAceptar);

        String[] crud = {"Añadir","Actualizar","Buscar","Eliminar"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        spinerAcademia.setAdapter(AdapterCrud);
        
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(spinerAcademia.getSelectedItem().toString()) {
                    case "Añadir":
                        if (!etIDacademia.getText().toString().isEmpty() &&
                                !etCodigo.getText().toString().isEmpty()) {
                            add(Integer.parseInt(etIDacademia.getText().toString()), etCodigo.getText().toString(),spinerAcademia.getSelectedItem().toString());
                        }else{
                            Toast.makeText(abcAcademia.this, "Debes llenar los demas campos", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Actualizar":
                        if (!etIDacademia.getText().toString().isEmpty() &&
                                !etCodigo.getText().toString().isEmpty()) {
                            edit(Integer.parseInt(etIDacademia.getText().toString()), etCodigo.getText().toString(),spinerAcademia.getSelectedItem().toString());
                        }else{
                            Toast.makeText(abcAcademia.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Eliminar":
                        if(!etIDacademia.getText().toString().isEmpty()){
                            delete(Integer.parseInt(etIDacademia.getText().toString()), spinerAcademia.getSelectedItem().toString());
                        }
                        break;
                    case "Buscar":
                        if(!etIDacademia.getText().toString().isEmpty()){
                            search(Integer.parseInt(etIDacademia.getText().toString()), spinerAcademia.getSelectedItem().toString());
                        }
                        break;

                }

            }
                
            
        });
    }

    private void search(int code, String spinerAcademia) {
        if(spinerAcademia.equals("Buscar")) {
            if (code != 0) {
                DbHelper admin = new DbHelper(abcAcademia.this, "POS", null, 2);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor cursor = db.rawQuery("select id_academia, codigo from t_academia where id_academia=" + code, null);
                if (cursor.moveToFirst()) {
                    etIDacademia.setText(cursor.getString(0));
                    etCodigo.setText(cursor.getString(1));
                    db.close();

                } else {
                    Toast.makeText(abcAcademia.this, "El codigo que desea buscar no existe! ", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(abcAcademia.this, "Escribe un codigo valido, no puede ser zero", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete(int code, String spinerAcademia) {
        if (spinerAcademia.equals("Eliminar")) {
            DbHelper admin = new DbHelper(abcAcademia.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();
            int result = db.delete("t_academia", "id_academia=" + code, null);

            admin.close();
            etIDacademia.setText("");
            etCodigo.setText("");

            if (result >= 1) {
                Toast.makeText(abcAcademia.this, "Se elimino al usuario", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(abcAcademia.this, "El usuario que desea eliminar, no existe!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void edit(int code, String codigo, String spinerAcademia) {

        if (spinerAcademia.equals("Actualizar")) {
            DbHelper admin = new DbHelper(abcAcademia.this, "POS", null, 2);
            SQLiteDatabase db = admin.getWritableDatabase();

            ContentValues registry = new ContentValues();

            registry.put("id_academia", code);
            registry.put("codigo", codigo);


            int result = db.update("t_academia", registry,"id_academia ="+code, null);
            if (result != 0){
                Toast.makeText(abcAcademia.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(abcAcademia.this, "No se puedo actualizar el registro, verifique por favor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void add(int code, String codigo, String spinerAcademia) {
        if (spinerAcademia.equals("Añadir")) {

            if (code != 0) {
                DbHelper admin = new DbHelper(abcAcademia.this, "POS", null, 2);
                SQLiteDatabase db = admin.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT id_academia,codigo from t_academia where id_academia ="+code, null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(abcAcademia.this, "Codigo ya existente \n elija otro codigo", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues registro = new ContentValues();
                    registro.put("id_academia", code);
                    registro.put("codigo", codigo);


                    db.insert("t_academia", null, registro);
                    db.close();
                    etIDacademia.setText("");
                    etCodigo.setText("");
                    Toast.makeText(abcAcademia.this, "Se guardo el registro", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(abcAcademia.this, "No se pudo añadir, llena los campos", Toast.LENGTH_SHORT).show();
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
            case R.id.edNOMBRE:
                Intent inten =  new Intent(abcAcademia.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(abcAcademia.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(abcAcademia.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(abcAcademia.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(abcAcademia.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases =  new Intent(abcAcademia.this, chequeoClases.class);
                startActivity(clases);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}