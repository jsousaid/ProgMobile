package br.fatec.projeto.controller;

public interface IDatabaseHelper {

    //Versão da base de dados
    public static final int DATABASE_VERSION = 1;

    //Nome da base de dados
    public static final String DATABASE_NAME="petshop.db";

    //Nomes das tabelas
    public static final String TABLE_SERVICO="servico";
    public static final String TABLE_AGENDAMENTO="agendamento";
    public static final String TABLE_SERVICO_AGENDAMENTO="servico_agendamento";

    //Nomes das colunas (comuns)
    public static final String COL_ID="id";

    // Tabela servico = colunas específicas
    public static final String COL_DESCRICAO="descricao";
    public static final String COL_PRECO="preco";

    // Tabela agendamento = colunas específicas
    public static final String COL_TRANSPORTE="transporte";
    public static final String COL_GRAVATINHA="gravatinha";
    public static final String COL_TREINAMENTO="treinamento";
    public static final String COL_DATA="data";

    //Tabela servico agendamento = colunas especificas
    public static final String COL_ID_SERVICO="id_servico";
    public static final String COL_ID_AGENDAMENTO="id_agendamento";

    //
    // INSTRUÇÕES para CRIAÇÃO DAS TABELAS
    //
    public static final String CREATE_TABLE_SERVICO =
            "CREATE TABLE " + TABLE_SERVICO + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_DESCRICAO + " TEXT, " +
                    COL_PRECO + " REAL " + ")";

    public static final String CREATE_TABLE_AGENDAMENTO=
            "CREATE TABLE " + TABLE_AGENDAMENTO + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TRANSPORTE + " TEXT, " +
                    COL_GRAVATINHA + " INTEGER, " +
                    COL_TREINAMENTO + " INTEGER, " +
                    COL_DATA + " TEXT " + ")";

    public static final String CREATE_TABLE_SERVICO_AGENDAMENTO=
            "CREATE TABLE " + TABLE_SERVICO_AGENDAMENTO + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ID_SERVICO + " INTEGER, " +
                    COL_ID_AGENDAMENTO + " INTEGER " + ")";
}
