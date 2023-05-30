package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class chequeoClases extends AppCompatActivity {

    DbHelper dbHelperMaestro,dbHelperAulas,dbHelper,dbHelperMaestroApellido;

    Button btnguardarclase;
    Spinner  SpAula, SpHORA,SpDocente,SpAction;


    private List<String> AulaValues;
    private List<String> DocenteValues, DocenteApellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chequeo_clases);

        AulaValues = new ArrayList<>();
        DocenteValues = new ArrayList<>();

        btnguardarclase = findViewById(R.id.btnguardarclase);
        SpAula = findViewById(R.id.SpAula);
        SpHORA = findViewById(R.id.SpHORA);
        SpDocente = findViewById(R.id.SpDocente);
        SpAction = findViewById(R.id.SpAction);









        ////////////////////////Maestros////////////////////////////////////////////////////////
        dbHelperMaestro = new DbHelper(this);
        dbHelperAulas = new DbHelper(this);


        Cursor cursorMaestro = dbHelperMaestro.DatosMaestro("holds");

        if(cursorMaestro.moveToFirst()){
            do{
                String nombre = cursorMaestro.getString(0); // Valor de la primera columna
                String apellido = cursorMaestro.getString(1); // Valor de la segunda columna
                String valorConcatenado = nombre + " " + apellido; // Concatenación de los valores

                DocenteValues.add(valorConcatenado);

            }while(cursorMaestro.moveToNext());
        }
        SpDocente.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, DocenteValues));



////////////////////////Aula////////////////////////////////////////////////////////

        Cursor cursorAula = dbHelperAulas.DatasAula();
        if(cursorAula.moveToFirst()){
            do{
                AulaValues.add(cursorAula.getString(0));

            }while(cursorAula.moveToNext());
        }

        SpAula.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AulaValues));


//////////////////////////horas///////////////////////////////////////
        String[] horas = {"7AM-8AM","8AM-9AM","9AM-10AM","10AM-11AM","11AM-12AM","12AM-1PM","1PM-2PM","2PM-3PM"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        SpHORA.setAdapter(Adapter);


        ///////////////////////////////Acciones//////////////////////////////////////////////////////////////////
        String[] crud = {"IMPARTIDA","NO IMPARTIDA","CLASE INCOMPLETA","SUSPENCION"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        SpAction.setAdapter(AdapterCrud);

 //////////////////////////////////////////////////////////////////////////////////////
        /*
        SpAula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String aulaValue = parent.getItemAtPosition(position).toString();
                AulaValues.add(aulaValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SpDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String docenteValue = parent.getItemAtPosition(position).toString();
               DocenteValues.add(docenteValue);

            }    @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        */

        btnguardarclase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(chequeoClases.this, "Se guardo Correcta Mente", Toast.LENGTH_SHORT).show();
                // Obtener los valores seleccionados de los spinners y el texto ingresado en el EditText
                String aula = SpAula.getSelectedItem().toString();
                String hora = SpHORA.getSelectedItem().toString();
                String docente = SpDocente.getSelectedItem().toString();
                String accion = SpAction.getSelectedItem().toString();

                // Verificar si los valores existen en los arrays guardados
                if (!AulaValues.contains(aula) || !DocenteValues.contains(docente)) {
                    // Mostrar mensaje de error
                    Toast.makeText(chequeoClases.this, "Por favor, seleccione un valor válido para Aula y Docente", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(aula) || TextUtils.isEmpty(hora) || TextUtils.isEmpty(docente) || TextUtils.isEmpty(accion)) {
                    // Mostrar mensaje de error
                    Toast.makeText(chequeoClases.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insertar los datos en la tabla "ChequeoClases"
                dbHelperMaestro.insertarChequeoClases(aula, hora, docente, accion);

                // Limpiar los campos después de guardar
                SpAula.setSelection(0);
                SpHORA.setSelection(0);
                SpDocente.setSelection(0);
                SpAction.setSelection(0);



            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
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
                Intent intent =  new Intent(chequeoClases.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.edNOMBRE:
                Intent inten =  new Intent(chequeoClases.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(chequeoClases.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(chequeoClases.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(chequeoClases.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(chequeoClases.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases =  new Intent(chequeoClases.this, chequeoClases.class);
                startActivity(clases);

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}