package br.fatec.projeto.model;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.controller.DatabaseHelper;
import br.fatec.projeto.controller.ServicoDAO;

public class Servico extends Object {

    private int id;
    private String descricao;
    private double preco;

    public Servico() {
        id = 0;
        descricao = "";
        preco = 0;
    }

    public Servico(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return this.descricao;
    }
}
