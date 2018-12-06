package br.fatec.projeto.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseHelper {


    //CONSTRUTOR
    public DatabaseHelper(Context context){
        super(context,IDatabaseHelper.DATABASE_NAME,null,IDatabaseHelper.DATABASE_VERSION);
    }

    //
    // MÉTODO onCreate
    // Este método é chamado quando o banco de dados não
    // existe no disco, assim, a classe Helper terá que
    // criar uma nova base
    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IDatabaseHelper.CREATE_TABLE_AGENDAMENTO);
        db.execSQL(IDatabaseHelper.CREATE_TABLE_SERVICO);
        db.execSQL(IDatabaseHelper.CREATE_TABLE_SERVICO_AGENDAMENTO);
    }

    //
    // MÉTODO onUpgrade
    // Este método é chamado quando existe uma diferença de versão
    // entre o banco de dados do disco e o valor da versão especificada
    // na constante IDatabaseHelper.DATABASE_VERSION
    //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IDatabaseHelper.TABLE_AGENDAMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + IDatabaseHelper.TABLE_SERVICO);
        db.execSQL("DROP TABLE IF EXISTS " + IDatabaseHelper.TABLE_SERVICO_AGENDAMENTO);
        onCreate(db);
    }

}