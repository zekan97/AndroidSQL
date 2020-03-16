package com.example.seguros_android.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

public class MainActivity extends AppCompatActivity {
    EditText usuario, pass;
    SQLiteDatabase db;
    String usuarioStr, passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.dniUser);
        pass = (EditText) findViewById(R.id.passUser);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            //insertar();
        }

    }

    public void onClick(View view) {
        usuarioStr = usuario.getText().toString();
        passStr = pass.getText().toString();
        if(Exists(usuarioStr, passStr)){
            Toast.makeText(view.getContext(), "Bienvenido "+usuarioStr, Toast.LENGTH_SHORT).show();
            loginOk();
        }else
        Toast.makeText(view.getContext(), "El usuario no existe: "+usuarioStr, Toast.LENGTH_SHORT).show();
    }

    private void loginOk() {
        Intent intent = new Intent(MainActivity.this, Seguro_menu_admin.class);
        startActivity(intent);
    }

    public boolean Exists(String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT 1 FROM vendedores WHERE DNI=? and password=?", args);
        boolean exists = (c.getCount() > 0);
        c.close();
        return exists;
    }

    public void insertar() {
        ContentValues cv = new ContentValues();
        cv.put("DNI", "00468423M");
        cv.put("nombre", "Kiko");
        cv.put("apellidos", "Jimenez");
        cv.put("telefono", "674434565");
        cv.put("es_admin", 1);
        cv.put("activo", 1);
        cv.put("password", "1234");
        db.insert("vendedores", null, cv);


    }
}
