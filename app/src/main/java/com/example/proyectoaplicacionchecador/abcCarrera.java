package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class abcCarrera extends AppCompatActivity {
    Button  btnGuardar;
    Spinner sPOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_carrera);


        sPOpciones = (Spinner)findViewById(R.id.sPOpciones);
        String[] lenguajes = {"Seleccione","Alta","Baja","Cambio"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenguajes);
        sPOpciones.setAdapter(Adapter);
    }
}