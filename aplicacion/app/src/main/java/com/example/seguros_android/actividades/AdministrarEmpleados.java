package com.example.seguros_android.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;

public class AdministrarEmpleados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_empleados);
    }

    public void cargarConfigurarSeguro(View v) {
        Intent intent = new Intent(AdministrarEmpleados.this, FormularioCrearEmpleado.class);
        startActivity(intent);
    }

    public void borrarEmpleado(View v) {
        Intent intent = new Intent(AdministrarEmpleados.this, FormularioEliminarEmpleados.class);
        startActivity(intent);
    }
}
