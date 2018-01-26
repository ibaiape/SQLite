package com.example.dm2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelperLibros extends SQLiteOpenHelper {

    public SQLHelperLibros(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT)");
        for (int i =1; i<=5; i++){
            db.execSQL("INSERT INTO libros (titulo, autor) VALUES ('titulo"+i+"','autor"+i+"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("INSERT INTO libros (titulo, autor) VALUES ('titulo"+i+"','autor"+i+"')");
    }

}
