package com.example.seguros_android.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class MyOpenHelper extends SQLiteOpenHelper {

    //Nombre de la base de datos
    private static final String DB_NAME = "seguros.sqlite";
    //Version de la base de datos
    private static final int DB_VERSION = 1;

    //El constructor de la base de tados
    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Tabla de vendedores
    private static final String VENDEDORES_TABLE_CREATE = "CREATE TABLE `vendedores` (" +
            "  `DNI` varchar(9) NOT NULL PRIMARY KEY," +
            "  `nombre` varchar(30) NOT NULL," +
            "  `apellidos` varchar(30) NOT NULL," +
            "  `telefono` varchar(11)," +
            "  `es_admin` tinyint(1) NOT NULL," +
            "  `activo` tinyint(1) NOT NULL," +
            "  `password` varchar(255) NOT NULL" +
            ")";

    private static final String CLIENTES_TABLE_CREATE = "CREATE TABLE `clientes` (" +
            "  `DNI` varchar(9) NOT NULL PRIMARY KEY,"+
            "  `nombre` varchar(20) NOT NULL,"+
            "  `apellidos` varchar(50),"+
            "  `activo` tinyint(1) NOT NULL," +
            "  `telefono` varchar(11) NOT NULL"+
            ")";

    private static final String POLIZA_TABLE_CREATE = "CREATE TABLE `poliza` (" +
            "  `id_poliza` INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  `id_seguro` int(11) NOT NULL," +
            "  `comentario` varchar(255)" +
            ")";

    private static final String SEGURO_TABLE_CREATE = "CREATE TABLE `seguros` (" +
            "  `id_seguro` INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  `nombre` varchar(50) NOT NULL," +
            "  `descripcion` varchar(255) NOT NULL," +
            "  `precio` double NOT NULL" +
            ")";

    private static final String UNION_TABLE_CREATE = "CREATE TABLE `union_seguro_vendedor` (" +
            "  `id_union` INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  `DNI_cliente` varchar(9) NOT NULL," +
            "  `DNI_vendedor` varchar(9) NOT NULL," +
            "  `id_poliza` int(11) NOT NULL" +
            ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VENDEDORES_TABLE_CREATE);
        db.execSQL(CLIENTES_TABLE_CREATE);
        db.execSQL(POLIZA_TABLE_CREATE);
        db.execSQL(SEGURO_TABLE_CREATE);
        db.execSQL(UNION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        String TABLE_NAME = "seguros";
        // Select All Query
        String selectQuery = "SELECT nombre FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
}
