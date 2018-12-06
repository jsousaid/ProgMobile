package br.fatec.projeto.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.model.Agendamento;
import br.fatec.projeto.model.ServicoAgendamento;

public class ServicoAgendamentoDAO implements IDatabaseHelper {

    private SQLiteDatabase db;

    public long inserir(DatabaseHelper db, Object obj) {
        //Transformar o OBJ em um objeto Categoria
        ServicoAgendamento servicoAgendamento = (ServicoAgendamento) obj;

        //Habilitar o modo ESCRITA
        this.db = db.getWritableDatabase();

        //Especificar os valores para INSERÇÃO
        ContentValues val = new ContentValues();
        val.put(IDatabaseHelper.COL_ID_SERVICO, servicoAgendamento.getServicoId());
        val.put(IDatabaseHelper.COL_ID_AGENDAMENTO, servicoAgendamento.getAgendamentoId());

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
                IDatabaseHelper.TABLE_SERVICO_AGENDAMENTO,      // nome da tabela
                where,                              // where
                whereArgs                           // argumento
        );
    }

    public List<ServicoAgendamento> listar(DatabaseHelper db, int agendamentoId) {
        String SQL = "SELECT * FROM servico_agendamento ORDER BY id";
        String where[] = null;

        this.db = db.getReadableDatabase();

        //Executar a operação de SELEÇÃO
        Cursor c = this.db.rawQuery(SQL, where);

        if (c.moveToFirst()) {
            List<ServicoAgendamento> res = new ArrayList<>();
            do {
                ServicoAgendamento cat = new ServicoAgendamento();
                //if (c.getInt(2) == agendamentoId) {
                    cat.setId(c.getInt(0));
                    cat.setServicoId(c.getInt(1));
                    cat.setAgendamentoId(c.getInt(2));
                    res.add(cat);
              //  }
            } while (c.moveToNext());
            return res;
        }
        return null;
    }
}