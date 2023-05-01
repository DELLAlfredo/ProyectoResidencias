package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;
//import com.example.proyectoaplicacionchecador.db.DbMaestros;

public class abcMaestros extends AppCompatActivity {
    EditText txtNombre,txtApellidos, txtId;
    Button btnGuardar;

    Spinner spAcademia, spCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_maestros);


        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtId = findViewById(R.id.txtId);
        btnGuardar = findViewById(R.id.btnGuardar);
        spAcademia = findViewById(R.id.spAcademia);
        spCrud = findViewById(R.id.spCrud);

        String[] abc = {"ISIC","IIND","IGEM","IINA","IIA"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, abc);
        spAcademia.setAdapter(Adapter);

        String[] crud = {"Añadir","Actualizar","Buscar","Eliminar"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        spCrud.setAdapter(AdapterCrud);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch(spCrud.getSelectedItem().toString()) {
                        case "Añadir":
                            if (!txtId.getText().toString().isEmpty() &&
                                    !txtNombre.getText().toString().isEmpty() &&
                                    !txtApellidos.getText().toString().isEmpty() &&
                                    !spAcademia.getSelectedItem().toString().isEmpty()) {
                                add(Integer.parseInt(txtId.getText().toString()), txtNombre.getText().toString(), txtApellidos.getText().toString(), spAcademia.getSelectedItem().toString(),spCrud.getSelectedItem().toString());
                            }else{
                                Toast.makeText(abcMaestros.this, "Debes llenar los demas campos", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Actualizar":
                            if (!txtId.getText().toString().isEmpty() &&
                                    !txtNombre.getText().toString().isEmpty() &&
                                    !txtApellidos.getText().toString().isEmpty() &&
                                    !spAcademia.getSelectedItem().toString().isEmpty()) {
                                edit(Integer.parseInt(txtId.getText().toString()), txtNombre.getText().toString(), txtApellidos.getText().toString(), spAcademia.getSelectedItem().toString(),spCrud.getSelectedItem().toString());
                            }else{
                                Toast.makeText(abcMaestros.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "Eliminar":
                            if(!txtId.getText().toString().isEmpty()){
                                delete(Integer.parseInt(txtId.getText().toString()), spCrud.getSelectedItem().toString());
                            }
                            break;
                        case "Buscar":
                            if(!txtId.getText().toString().isEmpty()){
                                search(Integer.parseInt(txtId.getText().toString()), spCrud.getSelectedItem().toString());
                            }
                            break;

                    }
             /*       DbMaestros dbMaestros = new DbMaestros(abcMaestros.this);
                    long id = dbMaestros.InsertarMaestro(txtNombre.getText().toString(),txtApellidos.getText().toString(),spAcademia.getSelectedItem().toString());
                    if (id > 0){
                        limpiar();
                        Toast.makeText(abcMaestros.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(abcMaestros.this, "NO SE PUDO GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                    } */
                }
        });


    }
        public void add(int code, String nombre, String apellidos, String academia, String spCrud){
            if (spCrud.equals("Añadir")) {

                if (code != 0) {
                    DbHelper admin = new DbHelper(abcMaestros.this, "POS", null, 2);
                    SQLiteDatabase db = admin.getReadableDatabase();
                    Cursor cursor = db.rawQuery("SELECT nombre,apellidos, academia from t_maestros where id =" + code, null);
                    if (cursor.moveToFirst()) {
                        Toast.makeText(abcMaestros.this, "Codigo ya existente \n elija otro codigo", Toast.LENGTH_LONG).show();
                    } else {
                        ContentValues registro = new ContentValues();
                        registro.put("id", code);
                        registro.put("nombre", nombre);
                        registro.put("apellidos", apellidos);
                        registro.put("academia", academia);

                        db.insert("t_maestros", null, registro);
                        //    db.close();
                        txtId.setText("");
                        txtApellidos.setText("");
                        txtNombre.setText("");
                        Toast.makeText(abcMaestros.this, "Se guardo el registro", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(abcMaestros.this, "No se pudo añadir, llena los campos", Toast.LENGTH_SHORT).show();
                }
            }
    }
        public void edit(int code, String nombre, String apellidos, String academia, String spCrud){
            if (spCrud.equals("Actualizar")) {
                    DbHelper admin = new DbHelper(abcMaestros.this, "POS", null, 2);
                    SQLiteDatabase db = admin.getWritableDatabase();

                        ContentValues registry = new ContentValues();

                        registry.put("id", code);
                        registry.put("nombre", nombre);
                        registry.put("apellidos", apellidos);
                        registry.put("academia", academia);

                       int result = db.update("t_maestros", registry,"id ="+code, null);
                if (result != 0){
                    Toast.makeText(abcMaestros.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(abcMaestros.this, "No se puedo actualizar el registro, verifique por favor", Toast.LENGTH_SHORT).show();
                }
            }
        }
        public  void delete(int code, String spCrud) {
            if (spCrud.equals("Eliminar")) {
                DbHelper admin = new DbHelper(abcMaestros.this, "POS", null, 2);
                SQLiteDatabase db = admin.getWritableDatabase();
                int result = db.delete("t_maestros", "id=" + code, null);

                admin.close();
                txtNombre.setText("");
                txtApellidos.setText("");
                spAcademia.getSelectedItem().toString().isEmpty();

                if (result >= 1) {
                    Toast.makeText(abcMaestros.this, "Se elimino al usuario", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(abcMaestros.this, "El usuario que desea eliminar, no existe!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    public void search(int code, String spCrud){
        if(spCrud.equals("Buscar")) {
            if (code != 0) {
                DbHelper admin = new DbHelper(abcMaestros.this, "POS", null, 2);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor cursor = db.rawQuery("select nombre, apellidos from t_maestros where id=" + code, null);
                if (cursor.moveToFirst()) {
                    txtNombre.setText(cursor.getString(0));
                    txtApellidos.setText(cursor.getString(1));
                    spAcademia.setSelection(cursor.getPosition());
                    db.close();

                } else {
                    Toast.makeText(abcMaestros.this, "El codigo que desea buscar no existe! ", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(abcMaestros.this, "Escribe un codigo valido, no puede ser zero", Toast.LENGTH_SHORT).show();
            }
        }
    }



/*            private void limpiar (){
                txtNombre.setText("");
                txtApellidos.setText("");
            }*/
        }