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
    Button btnAceptar,btnregresar;

    EditText etnombre;
    int joto = 0;
    Spinner spAccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_aulas);
        spAccion = (Spinner)findViewById(R.id.spAccion);
        String[] lenguajes = {"Seleccione","Alta","Baja","Cambio"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenguajes);
        spAccion.setAdapter(Adapter);
        btnregresar.findViewById(R.id.btnregresar);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(),Inicio_de_sesion.class);
                startActivity(reg);
            }
        });

    }
    public void onCreate(SQLiteDatabase db) {
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}