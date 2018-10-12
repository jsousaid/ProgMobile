package br.fatec.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ServicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        // Colocar setinha pra voltar na tela de menu
        getSupportActionBar().setTitle("Serviços");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Criar ações dos botões
        Button banhoTosaButton = (Button) findViewById(R.id.btn_banho_tosa);
        banhoTosaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent banhoTosaIntent = new Intent(getApplicationContext(), BanhoTosaActivity.class);
                startActivity(banhoTosaIntent);
            }
        });

        Button servMedicosButton = (Button) findViewById(R.id.btn_serv_medicos);
        servMedicosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent servMedicosIntent = new Intent(getApplicationContext(), ServicosMedicosActivity.class);
                startActivity(servMedicosIntent);
            }
        });

        Button adestramentoButton = (Button) findViewById(R.id.btn_adestramento);
        adestramentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adestramentoIntent = new Intent(getApplicationContext(), AdestramentoActivity.class);
                startActivity(adestramentoIntent);
            }
        });

        Button hospedagemButton = (Button) findViewById(R.id.btn_hospedagem);
        hospedagemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hospedagemIntent = new Intent(getApplicationContext(), HospedagemActivity.class);
                startActivity(hospedagemIntent);
            }
        });
    }
}
