package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class abcCarrera extends AppCompatActivity {
    Button  btnGuardar;
    ListView edCarrera;
    Spinner sPOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_carrera);

        btnGuardar = findViewById(R.id.btnGuardar);
        edCarrera = findViewById(R.id.edCarrera);


        sPOpciones = (Spinner)findViewById(R.id.sPOpciones);
        String[] lenguajes = {"Seleccione","Alta","Baja","Cambio"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenguajes);
        sPOpciones.setAdapter(Adapter);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
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