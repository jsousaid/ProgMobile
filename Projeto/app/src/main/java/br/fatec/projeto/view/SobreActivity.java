package br.fatec.projeto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.fatec.projeto.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        // Colocar setinha pra voltar na tela login
        getSupportActionBar().setTitle("Sobre");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
