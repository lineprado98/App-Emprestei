package com.pradodev.euemprestei;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    //tODOS OS METODOS QUE TERAO INTERAÇÃO COM O BANCO FICAM AQUI
    //TEREMOS METODO CRUD

    public static void inserir(Item item, Context context){
        //coluna-bd/campo do obj
        ContentValues valores = new ContentValues();
        valores.put("nome_pessoa",item.nome);
        valores.put("nome_item",item.item);
        valores.put("categoria",item.categoria);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.insert("itens",null ,valores);
    }

    public static void editar(Item item, Context context){
        //chaves  nome das colunas
        ContentValues valores = new ContentValues();
        valores.put("nome",item.nome);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.update("itens",valores ,"id = "+item.id, null);

    }


    public static List<Item> getItens(Context context){
        List<Item> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM itens ORDER BY nome_pessoa",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Item item  = new Item();
                item.id = cursor.getInt(0);// coluna no caso coluna zero é o id
                item.nome = cursor.getString(1);
                item.item = cursor.getString(2);
                item.categoria = cursor.getString(3);


                lista.add(item);
            }while(cursor.moveToNext()); //se xistir um prox ele retorna true
        }

        return lista;
    }

    public static Item getItemById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();//permissão de leitura
        Cursor cursor = db.rawQuery("SELECT * FROM itens WHERE id="+id,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Item item  = new Item();
            item.id = cursor.getInt(0);// coluna no caso coluna zero é o id
            item.nome = cursor.getString(1);

            return item;
        }else{
            return null;
        }
    }



}
