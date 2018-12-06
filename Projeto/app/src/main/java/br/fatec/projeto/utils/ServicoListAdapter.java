package br.fatec.projeto.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.fatec.projeto.R;
import br.fatec.projeto.model.Servico;

public class ServicoListAdapter extends ArrayAdapter<Servico> {
    public ServicoListAdapter(Context context, ArrayList<Servico> servicos) {
        super(context, 0, servicos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Servico servico = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.servico_list_item, parent, false);
        }
        // Lookup view for data population
        TextView txtDescricao = (TextView) convertView.findViewById(R.id.txt_descricao_list);
        TextView txtPreco = (TextView) convertView.findViewById(R.id.txt_preco_list);
        // Populate the data into the template view using the data object
        @Nullable
        String descricao = servico.getDescricao();
        double preco = servico.getPreco();

        if (descricao != null && preco > 0) {
            txtDescricao.setText(descricao);
            txtPreco.setText(String.format("R$ %.2f", preco));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}