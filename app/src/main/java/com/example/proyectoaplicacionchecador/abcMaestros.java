package com.example.proyectoaplicacionchecador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoaplicacionchecador.db.DbMaestros;

public class abcMaestros extends AppCompatActivity {
    EditText txtNombre,txtApellidos;
    Button btnGuardar;

    Spinner spAcademia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_maestros);


        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        btnGuardar = findViewById(R.id.btnGuardar);
        spAcademia = findViewById(R.id.spAcademia);

        String[] abc = {"ISIC","IIND","IGEM"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, abc);
        spAcademia.setAdapter(Adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbMaestros dbMaestros = new DbMaestros(abcMaestros.this);
                long id = dbMaestros.InsertarMaestro(txtNombre.getText().toString(),txtApellidos.getText().toString(),spAcademia.getSelectedItem().toString());

                if (id > 0){
                    limpiar();
                    Toast.makeText(abcMaestros.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(abcMaestros.this, "NO SE PUDO GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar (){
        txtNombre.setText("");
        txtApellidos.setText("");
    }
}