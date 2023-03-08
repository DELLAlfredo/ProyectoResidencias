package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class abcAulas extends AppCompatActivity {
    Button btnAceptar;
    ImageButton btnregresar;

    EditText etnombre;
    int joto = 0;
    Spinner spAccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_aulas);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnregresar = findViewById(R.id.btnregresar);
        etnombre.findViewById(R.id.etNombre);
        spAccion = (Spinner)findViewById(R.id.spAccion);
        String[] lenguajes = {"Seleccione","Alta","Baja","Cambio"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenguajes);
        spAccion.setAdapter(Adapter);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(abcAulas.this,Inicio_de_sesion.class);
                startActivity(reg);
            }
        });

    }

}