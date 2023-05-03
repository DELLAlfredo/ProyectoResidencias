package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.proyectoaplicacionchecador.db.DbMaestros;

public class Inicio_de_sesion extends AppCompatActivity {
    Button btnInicarSesion;
    EditText EdUsuario, EdContraseña;
     String password = "123";
     String user = "root";
    private Object backgroundTint;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);

    btnInicarSesion     = findViewById(R.id.btnInicarSesion);
    EdUsuario           = findViewById(R.id.EdUsuario);
    EdContraseña           = findViewById(R.id.EdContraseña);


    btnInicarSesion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String frase = EdUsuario.getText().toString();
            String pass = EdContraseña.getText().toString();
            if(EdUsuario.getText().toString().equalsIgnoreCase(user)&& EdContraseña.getText().toString().equalsIgnoreCase(password)){
                Toast.makeText(getApplicationContext(),"Ha iniciado sesión correctamente",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Inicio_de_sesion.this, abcMaestros.class);  //Creamos el intent y le indicamos desde donde vamos (this) y a donde vamos (OtraActividad.class)
               startActivity(intent);  //Abrimos la otra actividad
            }else{
                EdUsuario.setError("Ingresa tu nombre de usuario correcto");
                EdContraseña.setError("Ingresa la contraseña correcta");
                Toast.makeText(getApplicationContext(),"User o contraseña incorrectos",Toast.LENGTH_SHORT).show();

                camposVacios();
            }
        }
    });



    }

    private void camposVacios(){
        Toast.makeText(Inicio_de_sesion.this, "Campos vacios", Toast.LENGTH_SHORT).show();
    }
}