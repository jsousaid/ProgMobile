package br.fatec.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AgendamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        // Colocar setinha pra voltar na tela de menu
        getSupportActionBar().setTitle("Agendamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
