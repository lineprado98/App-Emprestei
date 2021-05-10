package com.pradodev.euemprestei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {
    //manipular objetos do elemetno da tela, primeiro criar objetos do tipo do elemento da tela
    private EditText etNomePessoa;
    private EditText etNomeItem;
    private Spinner spCategoria;
    private Button btnSalvar;
    private String acao;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etNomePessoa = findViewById(R.id.et_nome_pessoa);
        etNomeItem = findViewById(R.id.et_nome_item);
        spCategoria = findViewById(R.id.sp_categorias);

        btnSalvar = findViewById(R.id.btn_salvar);

        acao = getIntent().getStringExtra("acao");

        if(acao.equals("editar")){
            carregarFomulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Salvar();
            }
        });
    }

    // se for um editar tem que carregar formulario
    private void carregarFomulario (){
        int idItem =  getIntent().getIntExtra("idItem",0);
        item = ItemDAO.getItemById(Formulario.this,idItem);
        etNomePessoa.setText(item.nome);
        etNomeItem.setText(item.item);
        String[] arrayCategoria = getResources().getStringArray(R.array.arrayCategoria);
        for(int i=0; i<arrayCategoria.length; i++){
            if(arrayCategoria[i].equals(item.categoria)){
                spCategoria.setSelection(i);
           }
        }

    }

    private void Salvar (){
        if( spCategoria.getSelectedItemPosition()!=0){
            if(acao.equals("novo")){
                item = new Item();
            }
            item.nome = etNomePessoa.getText().toString();
            item.item = etNomeItem.getText().toString();
            item.categoria = spCategoria.getSelectedItem().toString();

            if(acao.equals("editar")){
                ItemDAO.editar(item, Formulario.this);
                finish();
            }else{
                ItemDAO.inserir(item,Formulario.this);
                etNomeItem.setText("");
                etNomePessoa.setText("");
                spCategoria.setSelection(0);

            }
        }else{
            Toast.makeText(Formulario.this,"Verifique os campos ",Toast.LENGTH_SHORT).show();
        }

    }
}  