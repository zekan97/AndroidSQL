package com.example.seguros_android.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;

public class Seguro_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seguro_menu);
    }

    public void cargarConfigSeguros(View v) {
        Intent intent = new Intent(Seguro_menu.this, ConfigSeguro.class);
        startActivity(intent);
    }

    public void cargarFormulario(View v) {
        Intent intent = new Intent(Seguro_menu.this, ConfigSeguro.class);
        startActivity(intent);
    }
}
