package com.pradodev.euemprestei;

public class Item {

    public int  id ;
    public String nome ;
    public String item;
    public String categoria;

    //create no banco
    public  Item(){

    }
    public Item(String nome,String item, String categoria){
        this.nome = nome;
        this.item = item;
        this.categoria = categoria;


    }

    //busca no banco
    public Item(int id , String nome,String item, String categoria){
        this.nome= nome;
        this.item = item;
        this.categoria = categoria;
        this.id= id;
    }



    @Override
    public String toString() {
        return  id +"-"+nome+"\nItem:"+item+"\nCategoria"+categoria;
    }
}
