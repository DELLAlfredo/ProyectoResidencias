package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class abcAcademia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_academia);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edCarrera:
                Intent inten =  new Intent(abcAcademia.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(abcAcademia.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(abcAcademia.this, abcAulas.class);
                startActivity(aula);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}