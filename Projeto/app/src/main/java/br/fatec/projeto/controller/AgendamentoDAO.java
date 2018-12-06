package br.fatec.projeto.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.model.Agendamento;

public class AgendamentoDAO implements IDatabaseHelper {

    private SQLiteDatabase db;

    public long inserir(DatabaseHelper db, Object obj) {
        //Transformar o OBJ em um objeto Categoria
        Agendamento agendamento = (Agendamento) obj;

        //Habilitar o modo ESCRITA
        this.db = db.getWritableDatabase();

        //Especificar os valores para INSERÇÃO
        ContentValues val = new ContentValues();
        val.put(IDatabaseHelper.COL_TRANSPORTE, agendamento.getTransporte());
        val.put(IDatabaseHelper.COL_GRAVATINHA, agendamento.getGravatinha());
        val.put(IDatabaseHelper.COL_TREINAMENTO, agendamento.getTreinamento());
        val.put(IDatabaseHelper.COL_DATA, agendamento.getData());

        //Executar a operação de INSERÇÃO
        long id = this.db.insert(
                IDatabaseHelper.TABLE_AGENDAMENTO,
                null,
                val
        );

        return id;
    }

    public void deletar(DatabaseHelper db, int id) {
        this.db = db.getWritableDatabase();
        String where = IDatabaseHelper.COL_ID + "= ?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        this.db.delete(
                IDatabaseHelper.TABLE_AGENDAMENTO,      // nome da tabela
                where,                              // where
                whereArgs                           // argumento
        );
    }

    public List<Agendamento> listar(DatabaseHelper db) {
        String SQL = "SELECT * FROM agendamento ORDER BY id";
        String where[] = null;

        this.db = db.getReadableDatabase();

        //Executar a operação de SELEÇÃO
        Cursor c = this.db.rawQuery(SQL, where);

        if (c.moveToFirst()) {
            List<Agendamento> res = new ArrayList<>();
            do {
                Agendamento cat = new Agendamento();
                cat.setId(c.getInt(0));
                cat.setTransporte(c.getString(1));
                cat.setGravatinha(c.getInt(2));
                cat.setTreinamento(c.getInt(3));
                cat.setData(c.getString(4));
                res.add(cat);
            } while (c.moveToNext());
            return res;
        }
        return null;
    }
}
