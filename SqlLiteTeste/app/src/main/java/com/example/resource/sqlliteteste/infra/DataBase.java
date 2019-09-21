package com.example.resource.sqlliteteste.infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class DataBase extends SQLiteOpenHelper implements IDataBase {
    @RequiresApi(api = Build.VERSION_CODES.P)
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS CLIENTES");

        db.execSQL("CREATE TABLE CLIENTES(\n" +
                "\tID INT NOT NULL, \n" +
                "\tCPF VARCHAR(11) NOT NULL, \n" +
                "\tNOME VARCHAR(50) NOT NULL, \n" +
                "\tDATA_NASC VARCHAR(8) NOT NULL,\n" +
                "\tPRIMARY KEY (ID)\n" +
                ");");
    }


    @Override
    public void inserirClientes(Integer id, String nome, String cpf, String data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("NOME", nome);
        cv.put("CPF", cpf);
        cv.put("DATA_NASC", data);
        db.insert("CLIENTES", "ID", cv);
    }

    @Override
    public Cursor listarTodos() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT ID, NOME, CPF, DATA_NASC FROM CLIENTES ORDER BY ID", null);
    }

    @Override
    public void atualizarCliente(Integer id, String nome, String cpf, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NOME", nome);
        cv.put("CPF", cpf);
        cv.put("DATA_NASC", data);
        db.update("CLIENTES", cv, "ID=?", new String[]{id.toString()});
    }

    @Override
    public void deleteCliente(String cpf) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("CLIENTES", "CPF=?", new String[]{cpf});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
