package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inicio_de_sesion extends AppCompatActivity {
    Button btnInicarSesion;
    EditText EdUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);

    btnInicarSesion     = findViewById(R.id.btnInicarSesion);
    EdUsuario           = findViewById(R.id.EdUsuario);



    btnInicarSesion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!EdUsuario.getText().toString().isEmpty()){

            }else{
                camposVacios();
            }
        }
    });



    }

    private void camposVacios(){
        Toast.makeText(Inicio_de_sesion.this, "Campos vacios", Toast.LENGTH_SHORT).show();
    }
}