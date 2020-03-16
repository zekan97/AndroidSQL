package com.example.seguros_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Seguro_menu_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seguro_menu_admin);
    }

    public void cargarSeguros(View v) {
        Intent intent = new Intent(Seguro_menu_admin.this, Seguro_menu.class);
        startActivity(intent);
    }

    public void cargarConfigurarAdmin(View v) {
        Intent intent = new Intent(Seguro_menu_admin.this, ConfigAdmin.class);
        startActivity(intent);
    }

    public void cargarConfigurarSeguro(View v) {
        Intent intent = new Intent(Seguro_menu_admin.this, ConfigSeguro.class);
        startActivity(intent);
    }

    public void cargarPantallasClientes(View v) {
        Intent intent = new Intent(Seguro_menu_admin.this, Seguro_menu.class);
        startActivity(intent);
    }
}
