package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.TelephonyCallback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.proyectoaplicacionchecador.db.DbHelper;

import java.util.ArrayList;

public class chequeoClases extends AppCompatActivity {
    private DbHelper dbHelper;
     Spinner spinnerAula;
    Button btnguardarclase;
    Spinner  SpAula, SpHORA,SpDocente,SpAction;
    EditText etclases;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chequeo_clases);

        btnguardarclase = findViewById(R.id.btnguardarclase);
        SpAula = findViewById(R.id.SpAula);
        SpHORA = findViewById(R.id.SpHORA);
        SpDocente = findViewById(R.id.SpDocente);
        SpAction = findViewById(R.id.SpAction);
        etclases = findViewById(R.id.etclases);




    }

        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spAcademia:
                Intent intent =  new Intent(chequeoClases.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.edNOMBRE:
                Intent inten =  new Intent(chequeoClases.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(chequeoClases.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(chequeoClases.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(chequeoClases.this, abcMaestros.class);
                startActivity(maestro);

                return true;
            case R.id.Salir:
                Intent salir =  new Intent(chequeoClases.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases =  new Intent(chequeoClases.this, chequeoClases.class);
                startActivity(clases);

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}