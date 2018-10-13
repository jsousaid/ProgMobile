package br.fatec.projeto;

import java.util.ArrayList;
import java.util.List;

public class Servico {
    private String descricao;
    private double preco;

    public Servico() {
    }

    public Servico(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public static List<Servico> listar() {
        List<Servico> servicos = new ArrayList<>();

        servicos.add(new Servico("Adestramento", 32.5));
        servicos.add(new Servico("Banho e Tosa", 25));
        servicos.add(new Servico("Consulta Veterinária", 48.90));
        servicos.add(new Servico("Hospedagem", 28.15));

        return servicos;
    }
}
