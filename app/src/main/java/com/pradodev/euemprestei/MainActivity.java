package com.pradodev.euemprestei;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvItens;
    private ArrayAdapter adapter;
    private List<Item> listaItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nomeIntent = new Intent(MainActivity.this, Formulario.class);
                nomeIntent.putExtra("acao","novo");
                startActivity(nomeIntent);
            }
        });

        lvItens = findViewById(R.id.lvItens);
        carregarItens();
    }

    private void carregarItens(){
        listaItens = ItemDAO.getItens(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaItens);
        lvItens.setAdapter(adapter);
    }



    @Override
    protected void onRestart() {
        carregarItens();
        super.onRestart();
    }
}