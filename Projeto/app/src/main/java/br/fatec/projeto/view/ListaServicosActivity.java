package br.fatec.projeto.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.R;
import br.fatec.projeto.controller.DatabaseHelper;
import br.fatec.projeto.controller.ServicoDAO;
import br.fatec.projeto.model.Servico;
import br.fatec.projeto.utils.ServicoAdapter;
import br.fatec.projeto.utils.ServicoListAdapter;

public class ListaServicosActivity extends AppCompatActivity {

    private ArrayList<Servico> servicos = new ArrayList<Servico>();
    private ListView listServicos;
    private Button cadastrarButton;
    private DatabaseHelper dh;
    private ServicoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servicos);

        getSupportActionBar().setTitle("Serviços Cadastrados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dh = new DatabaseHelper(this);
        dao = new ServicoDAO();

        listServicos = (ListView) findViewById(R.id.rcv_servicos);
        setLista();
        listServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(ListaServicosActivity.this);
                dlg.setTitle("Remover");
                dlg.setMessage("Deseja realmente remover esse serviço?");
                dlg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Servico item = (Servico) servicos.toArray()[position];
                            dao.deletar(dh, item.getId());
                            setLista();
                        } catch (Exception e) {
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
                Intent cadastrarIntent = new Intent(getApplicationContext(), CadastrarServicoActivivty.class);
                startActivity(cadastrarIntent);
            }
        });
    }

    private void setLista() {
        servicos = (ArrayList<Servico>) dao.listar(dh);

        if(servicos == null)
            servicos = new ArrayList<Servico>();

            listServicos.setAdapter(new ServicoListAdapter(getApplicationContext(), servicos));
    }
}
