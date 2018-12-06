package br.fatec.projeto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.fatec.projeto.R;

public class BanhoTosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banho_tosa);

        // Colocar setinha pra voltar na tela serviços
        getSupportActionBar().setTitle("Banho e Tosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
