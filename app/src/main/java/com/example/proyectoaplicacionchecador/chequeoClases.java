package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyCallback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.proyectoaplicacionchecador.db.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class chequeoClases extends AppCompatActivity {

    DbHelper dbHelper,dbHelpere;
    Spinner spinnerAula;
    Spinner spinnerMaestro;
    Button btnguardarclase;
    Spinner  SpAula, SpHORA,SpDocente,SpAction;
    EditText etclases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chequeo_clases);

        btnguardarclase = findViewById(R.id.btnguardarclase);
        SpAula = findViewById(R.id.SpAula);
        SpHORA = findViewById(R.id.SpHORA);
        SpDocente = findViewById(R.id.SpDocente);
        SpAction = findViewById(R.id.SpAction);
        etclases = findViewById(R.id.etclases);

        ////////////////////////Aulas////////////////////////////////////////////////////////
        dbHelper = new DbHelper(this);
        spinnerAula = findViewById(R.id.SpAula);
        DbHelper dbHelper = new DbHelper(this);

        Cursor cursor = dbHelper.getSpinnerData();
        String[] ids = {"_id"};
        String[] nombres = {"nombre"};
        int[] toViews = {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor,nombres, toViews, 0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerAula = findViewById(R.id.SpAula);
        spinnerAula.setAdapter(adapter);
/////////////////////////////////////////////////////////////////
        String[] horas = {"7AM-8AM","8AM-9AM","9AM-10AM","10AM-11AM","11AM-12AM","12AM-1PM","1PM-2PM","2PM-3PM"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        SpHORA.setAdapter(Adapter);

        ///////////////////Maestros///////////////////////////////////////////////////

    ///////////////////Maestros///////////////////////////////////////////////////

        dbHelpere = new DbHelper(this);
        spinnerMaestro = findViewById(R.id.SpDocente);
        DbHelper dbHelpere = new DbHelper(this);

        Cursor cursore = dbHelpere.getSpinnerDatas("maestro");
        String[] nombres1 = {"nombre"};
        int[] toViewse = {android.R.id.text1};
        SimpleCursorAdapter adaptere = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursore,nombres1, toViewse, 0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerMaestro = findViewById(R.id.SpDocente);
        spinnerMaestro.setAdapter(adaptere);

        //crud
        String[] crud = {"IMPARTIDA","NO IMPARTIDA","CLASE INCOMPLETA","SUSPENCION"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        SpAction.setAdapter(AdapterCrud);
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