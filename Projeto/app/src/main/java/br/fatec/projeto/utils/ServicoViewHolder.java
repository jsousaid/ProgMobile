package br.fatec.projeto.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.fatec.projeto.R;

public class ServicoViewHolder extends RecyclerView.ViewHolder {

    final TextView txtDescricao;
    final TextView txtPreco;

    public ServicoViewHolder(View view) {
        super(view);

        txtDescricao = (TextView) view.findViewById(R.id.txt_servico);
        txtPreco = (TextView) view.findViewById(R.id.txt_preco);
    }
}
