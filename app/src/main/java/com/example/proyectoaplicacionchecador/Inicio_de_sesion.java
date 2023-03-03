package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Inicio_de_sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);
    }

    private void camposVacios(){
        Toast.makeText(Inicio_de_sesion.this, "Campos vacios", Toast.LENGTH_SHORT).show();
    }
}