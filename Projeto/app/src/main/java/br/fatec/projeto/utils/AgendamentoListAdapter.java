package br.fatec.projeto.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.fatec.projeto.R;
import br.fatec.projeto.model.Agendamento;

public class AgendamentoListAdapter extends ArrayAdapter<Agendamento> {
    public AgendamentoListAdapter(Context context, ArrayList<Agendamento> agendamentos) {
        super(context, 0, agendamentos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Agendamento agendamento = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.agendamento_list_item, parent, false);
        }

        // Lookup view for data population
        TextView txtData = (TextView) convertView.findViewById(R.id.txt_data);
        TextView txtTransporte = (TextView) convertView.findViewById(R.id.txt_transporte);
        TextView txtGravatinha = (TextView) convertView.findViewById(R.id.txt_gravatinha);
        TextView txtTreinamento = (TextView) convertView.findViewById(R.id.txt_treinamento);
        TextView txtServicos = (TextView) convertView.findViewById(R.id.txt_servicos);

        // Populate the data into the template view using the data object
        if (agendamento != null) {
            txtData.setText(agendamento.getData());
            txtTransporte.setText(agendamento.getTransporte());
            txtGravatinha.setText(agendamento.getGravatinha() > 0 ? "Gravatinha em dobro" : "");
            txtTreinamento.setText(agendamento.getTreinamento() > 0 ? "Treinamento rápido" : "");
            String servicos = "";

            if (agendamento.servicos != null) {
                for (int i = 0; i < agendamento.servicos.size(); i++) {
                    servicos += agendamento.servicos.get(i).getDescricao();

                    if (i < agendamento.servicos.size() - 1)
                        servicos += ", ";
                }
            }

            txtServicos.setText(servicos);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
