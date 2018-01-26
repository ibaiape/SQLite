package com.example.dm2.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ejercicio2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);
        mostrarLibros();
    }

    public void mostrarLibros(){
        TextView txtEjer2 = (TextView)findViewById(R.id.txtEjer2);
        SQLHelperLibros usdbh =
                new SQLHelperLibros(this, "DBLibros", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if (db != null){
            Cursor c = db.rawQuery("SELECT id, titulo, autor FROM libros", null);
            txtEjer2.setText("");
            if (c.moveToFirst()){
                do {
                    txtEjer2.append (c.getString(0) +".  "+c.getString(1)+" - "+c.getString(2)+"\n" );
                }while (c.moveToNext());
            }
        }
    }

    public void insertarLibro(View v){
        SQLHelperLibros usdbh =
                new SQLHelperLibros(this, "DBLibros", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO libros (titulo, autor) VALUES ('"+((TextView)findViewById(R.id.txtTitulo)).getText().toString()+"','"+((TextView)findViewById(R.id.txtAutor)).getText().toString()+"')");
        }
        mostrarLibros();
    }

    public void modificarLibro(View v){
        SQLHelperLibros usdbh =
                new SQLHelperLibros(this, "DBLibros", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db!=null){
            db.execSQL("UPDATE libros SET titulo = '"+((TextView)findViewById(R.id.txtTitulo)).getText().toString()+"', autor = '"+((TextView)findViewById(R.id.txtAutor)).getText().toString()+"' WHERE id = "+((TextView)findViewById(R.id.txtId)).getText().toString());
        }
        mostrarLibros();
    }

    public void borrarLibro(View v){
        SQLHelperLibros usdbh =
                new SQLHelperLibros(this, "DBLibros", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM libros WHERE id = "+((TextView)findViewById(R.id.txtId)).getText().toString());
        }
        mostrarLibros();
    }

    public void buscarLibros(View v){
        TextView txtId = (TextView)findViewById(R.id.txtId);
        TextView txtTit = (TextView)findViewById(R.id.txtTitulo);
        TextView txtAut = (TextView)findViewById(R.id.txtAutor);
        TextView txtEjer2 = (TextView)findViewById(R.id.txtEjer2);
        if(txtId.getText().length()==0 && txtTit.getText().length()==0 && txtAut.getText().length()==0)
            mostrarLibros();
        else{
            String sql = "SELECT id, titulo, autor FROM libros WHERE";
            Boolean filtrado = false;
            if(txtTit.getText().length()!=0){
                sql += " titulo like '%"+txtTit.getText().toString()+"%'";
                filtrado = true;
            }
            if(txtAut.getText().length()!=0){
                if(filtrado)
                    sql+= " and";
                sql += " autor like '%"+txtAut.getText().toString()+"%'";
                filtrado = true;
            }
            if(txtId.getText().length()!=0){
                if(filtrado)
                    sql+= " and";
                sql += " id = "+txtId.getText().toString();
                filtrado = true;
            }
            SQLHelperLibros usdbh =
                    new SQLHelperLibros(this, "DBLibros", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            if (db != null) {
                Cursor c = db.rawQuery(sql, null);
                txtEjer2.setText("");
                if (c.moveToFirst()) {
                    do {
                        txtEjer2.append(c.getString(0) + ".  " + c.getString(1) + " - " + c.getString(2) + "\n");
                    } while (c.moveToNext());
                }
            }
        }
    }

    public void listarLibros(View v){
        mostrarLibros();
    }

}
