package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class abcAulas extends AppCompatActivity {
    EditText edNombreAula,edIDAula;
    Spinner spinerAula;
    Button btnAceptarAu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_aulas);

        edNombreAula = findViewById(R.id.edNombreAula);
        edIDAula = findViewById(R.id.edIDAula);
        spinerAula =findViewById(R.id.spinerAula);
        btnAceptarAu = findViewById(R.id.btnAceptarAu);


        String[] crud = {"Añadir","Actualizar","Buscar","Eliminar"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        spinerAula.setAdapter(AdapterCrud);





    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spAcademia:
                Intent intent =  new Intent(abcAulas.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(abcAulas.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.edNOMBRE:
                Intent carrera =  new Intent(abcAulas.this, abcCarrera.class);
                startActivity(carrera);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(abcAulas.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(abcAulas.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}