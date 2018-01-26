package com.example.dm2.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Ejercicio1 extends AppCompatActivity {

    private static final String uri = "content://com.example.dm2.sqlite/agenda";
    private static final Uri CONTENT_URI = Uri.parse(uri);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);
        mostrarAgenda();
    }

    public void mostrarAgenda(){
        TextView txtEjer1 = (TextView)findViewById(R.id.txtEjer1);
        SQLHelperAgenda usdbh =
                new SQLHelperAgenda(this, "DBAgenda", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if (db != null){
            Cursor c = db.rawQuery("SELECT nombre, numero FROM agenda", null);
            txtEjer1.setText("");
            if (c.moveToFirst()){
                do {
                    txtEjer1.append (c.getString(0) +" - "+c.getString(1)+"\n" );
                }while (c.moveToNext());
            }
        }
    }

}



