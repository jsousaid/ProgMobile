package br.fatec.projeto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.fatec.projeto.R;

public class HospedagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        // Colocar setinha pra voltar na tela serviços
        getSupportActionBar().setTitle("Hospegaem");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
