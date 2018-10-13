package br.fatec.projeto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoActivity extends AppCompatActivity {

    private List<Servico> servicosSelecionados = new ArrayList<>();
    private RecyclerView recyclerServicos;
    private TextView txtTotal;
    private double valorTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        // Colocar setinha pra voltar na tela de menu
        getSupportActionBar().setTitle("Agendamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerServicos = (RecyclerView) findViewById(R.id.rcv_servicos_agendados);
        RecyclerView.LayoutManager m = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);
        recyclerServicos.setLayoutManager(m);

        txtTotal = (TextView) findViewById(R.id.txt_total);

        Button addServicoButton = (Button) findViewById(R.id.btn_add_servico);
        addServicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner servicoSpinner = (Spinner) findViewById(R.id.spn_servicos);
                String value = (String) servicoSpinner.getSelectedItem();

                switch (value) {
                    case "Adestramento":
                        servicosSelecionados.add(new Servico("Adestramento", 32.5));
                        break;
                    case "Banho e Tosa":
                        servicosSelecionados.add(new Servico("Banho e Tosa", 25));
                        break;
                    case "Consulta Veterinária":
                        servicosSelecionados.add(new Servico("Consulta Veterinária", 48.90));
                        break;
                    case "Hospedagem":
                        servicosSelecionados.add(new Servico("Hospedagem", 28.15));
                        break;
                }

                recyclerServicos.setAdapter(new ServicoAdapter(servicosSelecionados, getApplicationContext()));

                valorTotal = 0;

                for (int i = 0; i < servicosSelecionados.size(); i++) {
                    Servico servico = servicosSelecionados.get(i);

                    valorTotal += servico.getPreco();
                }

                txtTotal.setText("Total: R$" + String.format("%.2f", valorTotal));
            }
        });

        Button finalizarButton = (Button) findViewById(R.id.btn_finaliza);
        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder agendamentoDialog = new AlertDialog.Builder(AgendamentoActivity.this);
                agendamentoDialog.setTitle("Finalizado");
                agendamentoDialog.setMessage("Seu agendamento foi finalizado com sucesso! Em breve entraremos em contato para maiores informações.");
                agendamentoDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        teste();
                    }
                });
                agendamentoDialog.show();
            }
        });
    }
    private void teste() {
        EditText editData = (EditText) findViewById(R.id.edt_data);
        String data = editData.getText().toString();
        String servicos = "";

        for (int j = 0; j < servicosSelecionados.size(); j++) {
            servicos += servicosSelecionados.get(j).getDescricao();

            if (j < servicosSelecionados.size() - 1)
                servicos += ", ";
        }

        Intent sucessoIntent = new Intent(getApplicationContext(), SucessoActivity.class);
        sucessoIntent.putExtra("data", data);
        sucessoIntent.putExtra("servicos", servicos);
        sucessoIntent.putExtra("total", String.format("%.2f", valorTotal));
        startActivity(sucessoIntent);
        finish();
    }
}
