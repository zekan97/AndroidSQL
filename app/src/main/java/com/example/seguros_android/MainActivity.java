package com.example.seguros_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.seguros_android.datos.MyOpenHelper;

public class MainActivity extends AppCompatActivity {
    EditText usuario, pass;
    SQLiteDatabase db;
    String usuarioStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.dniUser);
        usuarioStr = usuario.getText().toString();

        pass = (EditText) findViewById(R.id.passUser);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            insertar();
        }
    }

    public void onClick(View view) {
        loginOk();
        Cursor c = db.rawQuery("SELECT * FROM vendedores WHERE TRIM(DNI) = '"+usuarioStr.trim()+"'", null);
        if(c.getColumnCount() > 0) {
            loginOk();
        }
    }

    private void loginOk() {
        Intent intent = new Intent(MainActivity.this, Seguro_menu_admin.class);
        startActivity(intent);
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
