package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class abcAulas extends AppCompatActivity {
    Button btnAceptar;
    EditText etnombre;
    Spinner spAccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_aulas);
        spAccion = (Spinner)findViewById(R.id.spAccion);
        String[] lenguajes = {"Seleccione","Alta","Baja","Cambio"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenguajes);
        spAccion.setAdapter(Adapter);
    }
}