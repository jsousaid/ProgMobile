package br.fatec.projeto.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.R;
import br.fatec.projeto.controller.AgendamentoDAO;
import br.fatec.projeto.controller.DatabaseHelper;
import br.fatec.projeto.controller.ServicoAgendamentoDAO;
import br.fatec.projeto.controller.ServicoDAO;
import br.fatec.projeto.model.Agendamento;
import br.fatec.projeto.model.Servico;
import br.fatec.projeto.model.ServicoAgendamento;
import br.fatec.projeto.utils.AgendamentoListAdapter;
import br.fatec.projeto.utils.ServicoListAdapter;

public class ListaAgendamentosActivity extends AppCompatActivity {

    private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
    private DatabaseHelper dh;
    private AgendamentoDAO dao;
    private ServicoAgendamentoDAO servAgendDao;
    private ServicoDAO servicoDao;
    private ListView listAgendamentos;
    private Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agendamentos);

        // Colocar setinha pra voltar na tela de menu
        getSupportActionBar().setTitle("Agendamentos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dh = new DatabaseHelper(this);
        dao = new AgendamentoDAO();
        servAgendDao = new ServicoAgendamentoDAO();
        servicoDao = new ServicoDAO();

        listAgendamentos = (ListView) findViewById(R.id.lst_agendamentos);
        setLista();
        listAgendamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(ListaAgendamentosActivity.this);
                dlg.setTitle("Remover");
                dlg.setMessage("Deseja realmente remover esse agendamento?");
                dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Agendamento item = (Agendamento) agendamentos.get(position);
                            List<ServicoAgendamento> servicosAgendamentos = servAgendDao.listar(dh, item.getId());
                            if (servicosAgendamentos != null) {
                                for (int i = 0; i < servicosAgendamentos.size(); i++) {
                                    servAgendDao.deletar(dh, ((ServicoAgendamento) servicosAgendamentos.toArray()[i]).getId());
                                }
                            }

                            dao.deletar(dh, item.getId());
                            setLista();
                        } catch (Exception e) {
                            AlertDialog.Builder agendamentoDialog = new AlertDialog.Builder(ListaAgendamentosActivity.this);
                            agendamentoDialog.setTitle("Erro");
                            agendamentoDialog.setMessage(e.getMessage());
                            agendamentoDialog.setPositiveButton("OK", null);
                            agendamentoDialog.show();
                        }
                    }
                });
                dlg.setNegativeButton("Cancelar", null);
                dlg.show();
            }
        });

        cadastrarButton = (Button) findViewById(R.id.btn_add);

        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarIntent = new Intent(getApplicationContext(), AgendamentoActivity.class);
                startActivity(cadastrarIntent);
            }
        });
    }

    private void setLista() {
        try {

            agendamentos = (ArrayList<Agendamento>) dao.listar(dh);

            if (agendamentos != null) {
                for (int i = 0; i < agendamentos.size(); i++) {
                    Agendamento agendamento = agendamentos.get(i);

                    List<ServicoAgendamento> servsAgen = servAgendDao.listar(dh, agendamento.getId());

                    if (servsAgen != null) {
                        for (int j = 0; j < servsAgen.size(); j++) {
                            ServicoAgendamento servAg = servsAgen.get(j);

                            agendamento.servicos.add(servicoDao.encontrar(dh, servAg.getServicoId()));
                        }
                    }
                }
            }

            if (agendamentos == null)
                agendamentos = new ArrayList<Agendamento>();

            listAgendamentos.setAdapter(new AgendamentoListAdapter(getApplicationContext(), agendamentos));
        } catch (Exception e) {
            AlertDialog.Builder agendamentoDialog = new AlertDialog.Builder(ListaAgendamentosActivity.this);
            agendamentoDialog.setTitle("Erro");
            agendamentoDialog.setMessage(e.getMessage());
            agendamentoDialog.setPositiveButton("OK", null);
            agendamentoDialog.show();
        }
    }
}
