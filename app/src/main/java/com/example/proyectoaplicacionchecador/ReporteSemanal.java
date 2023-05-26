package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;

public class ReporteSemanal extends AppCompatActivity {
    Spinner SpAction, SpHORA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_semanal);
        SpAction = findViewById(R.id.Spaction);
        SpHORA = findViewById(R.id.SpHora);


        String[] horas = {"7AM-8AM","8AM-9AM","9AM-10AM","10AM-11AM","11AM-12AM","12AM-1PM","1PM-2PM","2PM-3PM"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        SpHORA.setAdapter(Adapter);


        ///////////////////////////////Acciones//////////////////////////////////////////////////////////////////
        String[] crud = {"IMPARTIDA","NO IMPARTIDA","CLASE INCOMPLETA","SUSPENCION"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        SpAction.setAdapter(AdapterCrud);

        SpAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String docenteValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(ReporteSemanal.this, docenteValue, Toast.LENGTH_SHORT).show();

            }    @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SpHORA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String docenteValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(ReporteSemanal.this, docenteValue, Toast.LENGTH_SHORT).show();
                String query = "select nombreAula, nombreDocente from chequeo_clases where Hora ='" + docenteValue +"'";
                search(1, query);

            }    @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.spAcademia:
                Intent intent =  new Intent(ReporteSemanal.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.edNOMBRE:
                Intent inten =  new Intent(ReporteSemanal.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte =  new Intent(ReporteSemanal.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula =  new Intent(ReporteSemanal.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro =  new Intent(ReporteSemanal.this, abcMaestros.class);
                startActivity(maestro);
                return true;
            case R.id.Salir:
                Intent salir =  new Intent(ReporteSemanal.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases =  new Intent(ReporteSemanal.this, chequeoClases.class);
                startActivity(clases);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void search(int code, String query){
        Toast.makeText(ReporteSemanal.this, "SearchEjecutado", Toast.LENGTH_SHORT).show();
            if (code != 0) {
                DbHelper admin = new DbHelper(ReporteSemanal.this, "chequeo_clases", null, 2);
                DbHelper dbHElperReporte = new DbHelper(this);
                Cursor cursorReporte = dbHElperReporte.DatosReporte(query);
                cursorReporte.moveToFirst();
                    do{
                        String res = cursorReporte.getString(0);
                        String res2 = cursorReporte.getString(1);
                        Toast.makeText(ReporteSemanal.this, "ElementoEncontrado", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ReporteSemanal.this, res, Toast.LENGTH_SHORT).show();
                        TextView txtMaestro = findViewById(R.id.txtMaestro);
                        TextView txtAula = findViewById(R.id.txtAula);
                        txtMaestro.setText(res);
                        txtAula.setText(res2);


                    }while(cursorReporte.moveToNext());
            } else {
                Toast.makeText(ReporteSemanal.this, "Escribe un codigo valido, no puede ser zero", Toast.LENGTH_SHORT).show();
            }
    }
}