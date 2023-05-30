package com.example.proyectoaplicacionchecador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ReporteSemanal extends AppCompatActivity {
    Spinner SpAction, SpHORA;
    TableLayout tabla;
    Button btnreporte;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_semanal);
        SpAction = findViewById(R.id.Spaction);
        //SpHORA = findViewById(R.id.SpHora);
        tabla = findViewById(R.id.tabla);
       // btnreporte = findViewById(R.id.btnreporte);

      /*  String[] horas = {"7AM-8AM", "8AM-9AM", "9AM-10AM", "10AM-11AM", "11AM-12AM", "12AM-1PM", "1PM-2PM", "2PM-3PM"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        SpHORA.setAdapter(Adapter);*/


        ///////////////////////////////Acciones//////////////////////////////////////////////////////////////////
        String[] crud = {"IMPARTIDA", "NO IMPARTIDA", "CLASE INCOMPLETA", "SUSPENCION"};
        ArrayAdapter<String> AdapterCrud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crud);
        SpAction.setAdapter(AdapterCrud);

        SpAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAction = parent.getItemAtPosition(position).toString();
                llenaCampos(selectedAction);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        TableLayout tabla = findViewById(R.id.tabla);
        dbHelper = new DbHelper(this);

        // Obtener una instancia de la base de datos
        SQLiteDatabase db = new DbHelper(this).getReadableDatabase();

        // Definir las columnas que deseas obtener de la tabla chequeo_clases
        String[] columnas = {"nombreAula", "nombreDocente", "Accion"};

        // Realizar la consulta a la base de datos
        Cursor reporte = db.query("chequeo_clases", columnas, null, null, null, null, null);

        // Iterar sobre el cursor para obtener los datos y mostrarlos en la tabla
        if (reporte.moveToFirst()) {
            do {
                // Obtener los valores de las columnas
                int nomAula = reporte.getColumnIndex("nombreAula");
                String nombreAula = reporte.getString(nomAula);
                int nomDocente = reporte.getColumnIndex("nombreDocente");
                String nombreDocente = reporte.getString(nomDocente);
                int nomAccion = reporte.getColumnIndex("Accion");
                String accion = reporte.getString(nomAccion);

                // Crear una nueva fila en la tabla
                TableRow fila = new TableRow(this);

                // Crear los TextViews para mostrar los datos en la fila
                TextView txtNombreAula = new TextView(this);
                txtNombreAula.setText(nombreAula);
                TextView txtNombreDocente = new TextView(this);
                txtNombreDocente.setText(nombreDocente);
                TextView txtAccion = new TextView(this);
                txtAccion.setText(accion);

                // Agregar los TextViews a la fila
                fila.addView(txtNombreAula);
                fila.addView(txtNombreDocente);
                fila.addView(txtAccion);

                // Agregar la fila a la tabla
                tabla.addView(fila);

            } while (reporte.moveToNext());
        }

        // Cerrar el cursor y la conexión a la base de datos
        reporte.close();
        db.close();

    }
    private void llenaCampos(String action) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT nombreAula, nombreDocente, Accion FROM chequeo_clases WHERE Accion = ?";
        String[] selectionArgs = { action };
        Cursor llena = db.rawQuery(query, selectionArgs);

        // Limpiar la tabla antes de agregar los nuevos datos
        tabla.removeAllViews();

        // Crear la fila de encabezados
        TableRow headerRow = new TableRow(this);
        String[] headers = { "Aula", "Nombre de Docente", "Acción" };
        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setPadding(5, 5, 5, 5);
            textView.setBackgroundResource(R.color.purple_200);
            textView.setTypeface(null,Typeface.BOLD);
            headerRow.addView(textView);
        }
        tabla.addView(headerRow);

        // Agregar las filas con los datos
        while (llena.moveToNext()) {
            TableRow dataRow = new TableRow(this);
            List<String> rowData = new ArrayList<>();
            int aulanom = llena.getColumnIndex("nombreAula");
            rowData.add(llena.getString(aulanom));
            int docentenom = llena.getColumnIndex("nombreDocente");
            rowData.add(llena.getString(docentenom));
            int accionnom = llena.getColumnIndex("Accion");
            rowData.add(llena.getString(accionnom));

            for (String data : rowData) {
                TextView textView = new TextView(this);
                textView.setText(data);
                textView.setPadding(5, 5, 5, 5);
                textView.setBackgroundResource(R.color.teal_200);
                dataRow.addView(textView);
            }
            tabla.addView(dataRow);
        }

        llena.close();
        db.close();
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcions_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spAcademia:
                Intent intent = new Intent(ReporteSemanal.this, abcAcademia.class);
                startActivity(intent);

                return true;
            case R.id.edNOMBRE:
                Intent inten = new Intent(ReporteSemanal.this, abcCarrera.class);
                startActivity(inten);

                return true;
            case R.id.reporte_semanal:
                Intent inte = new Intent(ReporteSemanal.this, ReporteSemanal.class);
                startActivity(inte);

                return true;
            case R.id.campoaula:
                Intent aula = new Intent(ReporteSemanal.this, abcAulas.class);
                startActivity(aula);

                return true;
            case R.id.Maestro:
                Intent maestro = new Intent(ReporteSemanal.this, abcMaestros.class);
                startActivity(maestro);
                return true;
            case R.id.Salir:
                Intent salir = new Intent(ReporteSemanal.this, Inicio_de_sesion.class);
                startActivity(salir);

                return true;
            case R.id.chequeoClases:
                Intent clases = new Intent(ReporteSemanal.this, chequeoClases.class);
                startActivity(clases);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}