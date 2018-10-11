package br.fatec.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HospedagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedagem);

        // Colocar setinha pra voltar na tela sobre
        getSupportActionBar().setTitle("Hospegaem");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
