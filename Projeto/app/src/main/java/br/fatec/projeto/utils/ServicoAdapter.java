package br.fatec.projeto.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import br.fatec.projeto.R;
import br.fatec.projeto.model.Servico;

public class ServicoAdapter extends RecyclerView.Adapter {

    private final List<Servico> lista;
    private Context ctx;

    public ServicoAdapter(List<Servico> lista, Context ctx) {
        this.lista = lista;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Carregar o layout personalizado
        View view = LayoutInflater.from(ctx)
                .inflate(R.layout.item_recycler,
                        viewGroup, false);

        return new ServicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ServicoViewHolder vh = (ServicoViewHolder) viewHolder;
        Servico servico = lista.get(i);

        vh.txtDescricao.setText(servico.getDescricao());
        vh.txtPreco.setText(String.format("%.2f", servico.getPreco()));
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }

}
