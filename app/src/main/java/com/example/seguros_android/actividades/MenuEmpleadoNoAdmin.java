package com.example.seguros_android.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;

public class MenuEmpleadoNoAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_empleado_no_admin);
    }

    public void cargarConfigSeguros(View v) {
        Intent intent = new Intent(MenuEmpleadoNoAdmin.this, AdministrarSeguro.class);
        startActivity(intent);
    }

    public void cargarFormulario(View v) {
        Intent intent = new Intent(MenuEmpleadoNoAdmin.this, AdministrarSeguro.class);
        startActivity(intent);
    }

    public void cargarAdministrarClientes(View v) {
        Intent intent = new Intent(MenuEmpleadoNoAdmin.this, AdministrarClientes.class);
        startActivity(intent);
    }
}
