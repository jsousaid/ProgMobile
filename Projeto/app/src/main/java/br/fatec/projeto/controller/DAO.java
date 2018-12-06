package br.fatec.projeto.controller;

import java.util.List;

public interface DAO {

    public long inserir(DatabaseHelper db, Object obj);

    public long atualizar(DatabaseHelper db, Object obj);

    public void deletar(DatabaseHelper db, int id);

    public List<Object> listar(DatabaseHelper db);

    public List<Object> pesquisarPorId(DatabaseHelper db, int id);
}

