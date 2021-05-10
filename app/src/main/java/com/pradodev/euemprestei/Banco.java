package com.pradodev.euemprestei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME = "AppEuEmprestei";


    public Banco (Context context){
        super(context,NOME,null,VERSAO);

    };
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS itens(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome_pessoa TEXT ," +
                "nome_item TEXT ," +
                "categoria TEXT ," +
                "ano INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}
