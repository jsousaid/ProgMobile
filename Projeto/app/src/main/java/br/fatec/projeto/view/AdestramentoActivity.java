package br.fatec.projeto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.fatec.projeto.R;

public class AdestramentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adestramento);

        // Colocar setinha pra voltar na tela servicos
        getSupportActionBar().setTitle("Adestramento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
