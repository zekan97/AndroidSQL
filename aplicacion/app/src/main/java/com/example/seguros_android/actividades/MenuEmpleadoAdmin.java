package com.example.seguros_android.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;

public class MenuEmpleadoAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_empleado_admin);
    }

    public void cargarSeguros(View v) {
        Intent intent = new Intent(MenuEmpleadoAdmin.this, MenuEmpleadoNoAdmin.class);
        startActivity(intent);
    }

    public void cargarConfigurarAdmin(View v) {
        Intent intent = new Intent(MenuEmpleadoAdmin.this, AdministrarEmpleados.class);
        startActivity(intent);
    }

    public void cargarConfigurarSeguro(View v) {
        Intent intent = new Intent(MenuEmpleadoAdmin.this, AdministrarSeguro.class);
        startActivity(intent);
    }

    public void cargarPantallasClientes(View v) {
        Intent intent = new Intent(MenuEmpleadoAdmin.this, MenuEmpleadoNoAdmin.class);
        startActivity(intent);
    }
}
