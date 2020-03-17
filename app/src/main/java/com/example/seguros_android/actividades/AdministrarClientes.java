package com.example.seguros_android.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.seguros_android.R;

public class AdministrarClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_clientes);
    }

    public void irCrearCliente(View v) {
        Intent intent = new Intent(AdministrarClientes.this, FormularioCrearCliente.class);
        startActivity(intent);
    }


    public void irBorrarCliente(View v) {
        Intent intent = new Intent(AdministrarClientes.this, FormularioEliminarClientes.class);
        startActivity(intent);
    }
}
