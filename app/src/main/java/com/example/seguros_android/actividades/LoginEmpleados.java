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

public class LoginEmpleados extends AppCompatActivity {
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
            insertar();
        }

    }

    public void onClick(View view) {
        usuarioStr = usuario.getText().toString();
        passStr = pass.getText().toString();
        if (estaActivo(usuarioStr, passStr)) {
            if (Exists(usuarioStr, passStr)) {
                if (es_administrador(usuarioStr, passStr)) {
                    Toast.makeText(view.getContext(), "Bienvenido " + usuarioStr, Toast.LENGTH_SHORT).show();
                    loginOkAdmin();
                } else {
                    Toast.makeText(view.getContext(), "Bienvenido " + usuarioStr, Toast.LENGTH_SHORT).show();
                    loginOkTrabajador();
                }
            } else
                Toast.makeText(view.getContext(), "El usuario no existe: " + usuarioStr, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Este usuario no esta activo", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Metodo que abre la nueva actividad
     * de un trabajador que no es admin
     */

    private void loginOkTrabajador() {
        Intent intent = new Intent(LoginEmpleados.this, MenuEmpleadoNoAdmin.class);
        startActivity(intent);
    }

    /**
     * Metodo que abre la nueva actividad
     * de un trabajador que es admin
     */

    private void loginOkAdmin() {
        Intent intent = new Intent(LoginEmpleados.this, MenuEmpleadoAdmin.class);
        startActivity(intent);
    }

    /**
     * Metodo que comprueba si un usuario
     * con una contraseña X existe
     * @param _id
     * @param _pass
     * @return
     */

    public boolean Exists(String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT DNI, password FROM vendedores WHERE DNI=? and password=?", args);
        boolean exists = (c.getCount() > 0);
        c.close();
        return exists;
    }

    /**
     * Metodo que comprueba si el usuario
     * es admin o no
     * @param _id
     * @param _pass
     * @return
     */

    public boolean es_administrador (String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT es_admin FROM vendedores WHERE DNI=? and password=?", args);
        c.moveToFirst();
        int valor =  c.getInt(c.getColumnIndex("es_admin"));
        c.close();
        if( valor == 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que comprueba si un empleado
     * esta en activo o no
     * @param _id
     * @param _pass
     * @return
     */
    public boolean estaActivo(String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT activo FROM vendedores WHERE DNI=? and password=?", args);
        c.moveToFirst();
        int valor =  c.getInt(c.getColumnIndex("activo"));
        c.close();
        if( valor == 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que inserta los primeros datos
     * en la base de datos
     */

    public void insertar() {
        ContentValues cv3 = new ContentValues();
        cv3.put("DNI", "33333333M");
        cv3.put("nombre", "Kiko");
        cv3.put("apellidos", "Jimenez");
        cv3.put("telefono", "674434565");
        cv3.put("es_admin", 1);
        cv3.put("activo", 1);
        cv3.put("password", "1234");
        db.insert("vendedores", null, cv3);

        ContentValues cv2 = new ContentValues();
        cv2.put("DNI", "22222222P");
        cv2.put("nombre", "Juan");
        cv2.put("apellidos", "Jimenez");
        cv2.put("telefono", "674434465");
        cv2.put("es_admin", 0);
        cv2.put("activo", 1);
        cv2.put("password", "1234");
        db.insert("vendedores", null, cv2);
        
    }
}
