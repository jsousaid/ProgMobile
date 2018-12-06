package br.fatec.projeto.view;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.fatec.projeto.R;
import br.fatec.projeto.controller.AgendamentoDAO;
import br.fatec.projeto.controller.DatabaseHelper;
import br.fatec.projeto.controller.ServicoAgendamentoDAO;
import br.fatec.projeto.controller.ServicoDAO;
import br.fatec.projeto.model.Agendamento;
import br.fatec.projeto.model.Servico;
import br.fatec.projeto.model.ServicoAgendamento;
import br.fatec.projeto.utils.ServicoAdapter;

public class AgendamentoActivity extends AppCompatActivity {

    private List<Servico> servicosSelecionados = new ArrayList<>();
    private RecyclerView recyclerServicos;
    private TextView txtTotal;
    private double valorTotal = 0;
    private AgendamentoDAO dao;
    private DatabaseHelper dh;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        dh = new DatabaseHelper(this);
        dao = new AgendamentoDAO();

        ServicoDAO servicoDao = new ServicoDAO();

        // Colocar setinha pra voltar na tela de menu
        getSupportActionBar().setTitle("Agendamento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Servico> servicosBd = servicoDao.listar(dh);

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

                for (int i = 0; i < servicosBd.size(); i++) {
                    Servico servico = (Servico) servicosBd.toArray()[i];

                    if (servico.getDescricao().equals(value)) {
                        servicosSelecionados.add(servico);
                        break;
                    }
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
                Agendamento agendamento = new Agendamento();

                RadioGroup transporte = (RadioGroup) findViewById(R.id.rdg_transporte);

                switch (transporte.getCheckedRadioButtonId()) {
                    case R.id.rdb_busque:
                        agendamento.setTransporte("Busque");
                        break;
                    case R.id.rdb_leve:
                        agendamento.setTransporte("Levarei");
                        break;
                }

                CheckBox gravatinhaChk = (CheckBox) findViewById(R.id.ckb_gravatinha);
                if (gravatinhaChk.isChecked())
                    agendamento.setGravatinha(1);
                else
                    agendamento.setGravatinha(0);

                CheckBox treinamentoChk = (CheckBox) findViewById(R.id.ckb_treinamento);
                if (treinamentoChk.isChecked())
                    agendamento.setTreinamento(1);
                else
                    agendamento.setTreinamento(0);

                EditText editData = (EditText) findViewById(R.id.edt_data);
                agendamento.setData(editData.getText().toString());

                try {
                    long agendamentoId = dao.inserir(dh, agendamento);

                    for (int i = 0; i < servicosSelecionados.size(); i++) {
                        ServicoAgendamento servAgen = new ServicoAgendamento(((Servico) servicosSelecionados.toArray()[i]).getId(), (int) agendamentoId);
                        ServicoAgendamentoDAO servAgenDao = new ServicoAgendamentoDAO();
                        servAgenDao.inserir(dh, servAgen);
                    }

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
                } catch (Exception e) {
                    AlertDialog.Builder agendamentoDialog = new AlertDialog.Builder(AgendamentoActivity.this);
                    agendamentoDialog.setTitle("Erro");
                    agendamentoDialog.setMessage(e.getMessage());
                    agendamentoDialog.setPositiveButton("OK", null);
                    agendamentoDialog.show();
                }
            }
        });

        List<String> servicosSelect = new ArrayList<String>();

        if (servicosBd != null) {
            List<Servico> servicos = new ArrayList(servicosBd);

            for (int i = 0; i < servicos.size(); i++) {
                servicosSelect.add(servicos.toArray()[i].toString());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, servicosSelect);

        Spinner servicosSpinner = (Spinner) findViewById(R.id.spn_servicos);
        servicosSpinner.setAdapter(adapter);
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
