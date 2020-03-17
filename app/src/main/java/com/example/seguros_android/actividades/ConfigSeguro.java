package com.example.seguros_android.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;

public class ConfigSeguro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_seguro);
    }

    public void cargarConfigurarSeguro(View v) {
        Intent intent = new Intent(ConfigSeguro.this, FormularioCrearSeguro.class);
        startActivity(intent);
    }
}
