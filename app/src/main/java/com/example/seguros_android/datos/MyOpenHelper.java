package com.example.seguros_android.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
            "  `DNI` varchar(9) NOT NULL," +
            "  `nombre` varchar(30) NOT NULL," +
            "  `apellidos` varchar(30) NOT NULL," +
            "  `telefono` varchar(11) NOT NULL," +
            "  `es_admin` tinyint(1) NOT NULL," +
            "  `activo` tinyint(1) NOT NULL," +
            "  `password` varchar(255) NOT NULL" +
            ")";

    private static final String CLIENTES_TABLE_CREATE = "CREATE TABLE `clientes` (" +
            "  `DNI` varchar(9) NOT NULL,"+
            "  `nombre` varchar(20) NOT NULL,"+
            "  `apellidos` varchar(50) NOT NULL,"+
            "  `telefono` varchar(15) NOT NULL"+
            ")";

    private static final String POLIZA_TABLE_CREATE = "CREATE TABLE `poliza` (" +
            "  `id_poliza` int(11) NOT NULL," +
            "  `id_seguro` int(11) NOT NULL," +
            "  `comentario` varchar(255) NOT NULL" +
            ")";

    private static final String SEGURO_TABLE_CREATE = "CREATE TABLE `seguros` (" +
            "  `id_seguro` int(11) NOT NULL," +
            "  `nombre` varchar(50) NOT NULL," +
            "  `descripcion` varchar(255) NOT NULL," +
            "  `precio` double NOT NULL" +
            ")";

    private static final String UNION_TABLE_CREATE = "CREATE TABLE `union-seguro-vendedor` (" +
            "  `id_union` int(11) NOT NULL," +
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
}
