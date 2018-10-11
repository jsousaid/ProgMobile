package br.fatec.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BanhoTosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banho_tosa);

        // Colocar setinha pra voltar na tela sobre
        getSupportActionBar().setTitle("Banho e Tosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
