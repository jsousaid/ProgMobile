package br.fatec.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdestramentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adestramento);

        // Colocar setinha pra voltar na tela sobre
        getSupportActionBar().setTitle("Adestramento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
