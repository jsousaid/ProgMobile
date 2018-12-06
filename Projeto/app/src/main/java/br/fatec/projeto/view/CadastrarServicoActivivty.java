package br.fatec.projeto.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.fatec.projeto.R;
import br.fatec.projeto.controller.DatabaseHelper;
import br.fatec.projeto.controller.ServicoDAO;
import br.fatec.projeto.model.Servico;
import br.fatec.projeto.utils.Util;

public class CadastrarServicoActivivty extends AppCompatActivity {

    private DatabaseHelper dh;
    private ServicoDAO dao;
    private Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico_activivty);

        getSupportActionBar().setTitle("Cadastrar Serviço");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dh = new DatabaseHelper(this);
        dao = new ServicoDAO();

        cadastrarButton = (Button) findViewById(R.id.btn_cadastrar);
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText descricaoEdit = (EditText) findViewById(R.id.edt_descricao);
                EditText precoEdit = (EditText) findViewById(R.id.edt_preco);

                String descricao = descricaoEdit.getText().toString();
                String preco = precoEdit.getText().toString().replace(',', '.');

                if (descricao.isEmpty() || preco.isEmpty()){
                    AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                    dlg.setTitle("Erro");
                    dlg.setMessage("Preencha os campos corretamente.");
                    dlg.setPositiveButton("OK",null);
                    dlg.show();
                }
                else {
                    try {
                        Servico servico = new Servico(descricao, Double.parseDouble(preco));
                        dao.inserir(dh, servico);
                        Intent listaIntent = new Intent(getApplicationContext(), ListaServicosActivity.class);
                        startActivity(listaIntent);
                        finish();
                    } catch (Exception e) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                        dlg.setTitle("Erro");
                        dlg.setMessage(e.getMessage());
                        dlg.setPositiveButton("OK",null);
                        dlg.show();
                    }
                }
            }
        });
    }
}
