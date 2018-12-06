package br.fatec.projeto.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.fatec.projeto.model.Servico;

public class ServicoDAO implements IDatabaseHelper {

    private SQLiteDatabase db;

    public long inserir(DatabaseHelper db, Object obj) {
        //Transformar o OBJ em um objeto Categoria
        Servico servico = (Servico) obj;

        //Habilitar o modo ESCRITA
        this.db = db.getWritableDatabase();

        //Especificar os valores para INSERÇÃO
        ContentValues val = new ContentValues();
        val.put(IDatabaseHelper.COL_DESCRICAO, servico.getDescricao());
        val.put(IDatabaseHelper.COL_PRECO, servico.getPreco());

        //Executar a operação de INSERÇÃO
        long id = this.db.insert(
                IDatabaseHelper.TABLE_SERVICO,
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
                IDatabaseHelper.TABLE_SERVICO,      // nome da tabela
                where,                              // where
                whereArgs                           // argumento
        );
    }

    public List<Servico> listar(DatabaseHelper db) {
        String SQL = "SELECT * FROM servico ORDER BY id";
        String where[] = null;

        this.db = db.getReadableDatabase();

        //Executar a operação de SELEÇÃO
        Cursor c = this.db.rawQuery(SQL,where);

        if (c.moveToFirst()){
            List<Servico> res = new ArrayList<>();
            do{
                Servico cat = new Servico();
                cat.setId(c.getInt(0));
                cat.setDescricao(c.getString(1));
                cat.setPreco(c.getDouble(2));
                res.add(cat);
            }while(c.moveToNext());
            return res;
        }
        return null;
    }

    public Servico encontrar(DatabaseHelper db, int id) {
        Servico lista = new Servico();
        String sql = "SELECT * FROM " + IDatabaseHelper.TABLE_SERVICO +
                " WHERE " + IDatabaseHelper.COL_ID + "=?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        //Definir MODO de LEITURA nas TABELAS
        this.db = db.getReadableDatabase();

        //Criar CURSOR para os resultados
        Cursor c = this.db.rawQuery(sql, whereArgs);

        // Se retornou resultados
        if (c.moveToFirst()){
            //Adicionar resultados na lista
            do{
                Servico cat = new Servico();
                cat.setId(c.getInt(c.getColumnIndex(IDatabaseHelper.COL_ID)));
                cat.setDescricao(c.getString(c.getColumnIndex(IDatabaseHelper.COL_DESCRICAO)));
                cat.setPreco(c.getDouble(c.getColumnIndex(IDatabaseHelper.COL_PRECO)));
                lista = cat;
            }while(c.moveToNext());
        }

        //Fechar o cursor
        if (c != null && !c.isClosed()){
            c.close();
        }

        return lista;
    }
}
