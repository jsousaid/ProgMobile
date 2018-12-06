package br.fatec.projeto.model;

public class ServicoAgendamento {
    private int id;
    private int servicoId;
    private int agendamentoId;

    public ServicoAgendamento(){

    }

    public ServicoAgendamento(int servicoId, int agendamentoId){
        this.id = 0;
        this.servicoId = servicoId;
        this.agendamentoId = agendamentoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(int agendamentoId) {
        this.agendamentoId = agendamentoId;
    }
}
