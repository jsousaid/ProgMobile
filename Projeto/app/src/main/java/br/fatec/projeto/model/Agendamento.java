package br.fatec.projeto.model;

import java.util.List;

public class Agendamento {
    private int id;
    private String transporte;
    private int gravatinha;
    private int treinamento;
    private String data;
    public List<Servico> servicos;

    public Agendamento() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public int getGravatinha() {
        return gravatinha;
    }

    public void setGravatinha(int gravatinha) {
        this.gravatinha = gravatinha;
    }

    public int getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(int treinamento) {
        this.treinamento = treinamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
