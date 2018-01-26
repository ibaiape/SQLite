package com.example.dm2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelperAgenda extends SQLiteOpenHelper {

    public SQLHelperAgenda(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE agenda (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, numero TEXT)");
        for (int i =1; i<=5; i++){
            db.execSQL("INSERT INTO agenda (nombre, numero) VALUES ('usuario"+i+"','00000000"+i+"')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("INSERT INTO agenda (nombre, numero) VALUES ('usuario"+i+"','00000000"+i+"')");

    }

}
