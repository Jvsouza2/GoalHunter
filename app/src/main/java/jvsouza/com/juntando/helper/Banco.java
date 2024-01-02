package jvsouza.com.juntando.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {



    private static final String DATABASE = "bancodados";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TAREFA = "TABLE_TAREFA";
    public static final String TABLE_CONQUISTA = "TABLE_CONQUISTA";
    public static final String TABLE_PROGRESSO = "TABLE_PROGRESSO";
    public static final String TABLE_BOTAO = "TABLE_BOTAO";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_DIFICULDADE = "dificuldade";
    public static final String COLUMN_CHECK = "checado";
    // Botões
    public static final String COLUMN_NUM = "COLUMN_NUM";
    public static final String COLUMN_RES = "COLUMN_RES";

    public static final String NOME_CONQUISTA = "NOME_CONQUISTA";
    public static final String LEVEL_CONQUISTA = "LEVEL_CONQUISTA";
    public static final String DESCRICAO_CONQUISTA = "DESCRICAO_CONQUISTA";
    public static final String COLUMN_CONTADOR = "COLUMN_CONTADOR";
    public static final String LEVEL_USUARIO = "LEVEL_USUARIO";
    public static final String COLUMN_MAXIMO = "COLUMN_MAXIMO";
    public static final String COLUMN_DIA = "dia";
    public static final String COLUMN_MES = "mes";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_MINUTO = "minuto";
    public static final String COLUMN_DESAFIO = "desafio";
    public static final String COLUMN_HCRIA = "hcria";
    public static final String COLUMN_MCRIA = "mcria";


    // Comando SQL para criar a tabela
    private static final String CREATE_TABLE_TAREFA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TAREFA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT, " +
                    COLUMN_DESCRICAO + " TEXT, " +
                    COLUMN_DIFICULDADE + " TEXT, " +
                    COLUMN_DIA + " INTEGER , " +
                    COLUMN_MES + " INTEGER , " +
                    COLUMN_HORA + " INTEGER , " +
                    COLUMN_MINUTO + " INTEGER , " +
                    COLUMN_DESAFIO + " INTEGER DEFAULT 0, " +
                    COLUMN_HCRIA + " INTEGER DEFAULT 0, " +
                    COLUMN_MCRIA + " INTEGER DEFAULT 0, " +
                    COLUMN_CHECK + " INTEGER DEFAULT 0)";

    private static final String CREATE_TABLE_CONQUISTA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CONQUISTA + " (" +
                    NOME_CONQUISTA + " TEXT, " +
                    LEVEL_CONQUISTA + " INTEGER, " +
                    DESCRICAO_CONQUISTA + " TEXT)";

    private static final String CREATE_TABLE_PROGRESSO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PROGRESSO + " (" +
                    COLUMN_CONTADOR + " INTEGER DEFAULT 0, " +
                    LEVEL_USUARIO + " INTEGER DEFAULT 0, " +
                    COLUMN_MAXIMO + " INTEGER)";

    private static final String CREATE_TABLE_BOTAO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOTAO + " (" +
                    COLUMN_NUM + " INTEGER DEFAULT -1, " +
                    COLUMN_RES + " INTEGER DEFAULT -1)";



    public Banco(@Nullable Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TAREFA);
        db.execSQL(CREATE_TABLE_CONQUISTA);
        db.execSQL(CREATE_TABLE_PROGRESSO);
        db.execSQL(CREATE_TABLE_BOTAO);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREFA);
    }


    ////////////////////////////// METODOS DOS BOTÕES //////////////////////////////////////////////////



    // Método para inserir um valor na coluna COLUMN_NUM
    public void inserirNumero(int numero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM, numero);

        // Insere o valor na tabela
        db.insert(TABLE_BOTAO, null, values);

        db.close();
    }

    // Método para obter valores da coluna COLUMN_NUM
    public int getNumero() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_NUM};

        Cursor cursor = db.query(
                TABLE_BOTAO,     // Tabela
                projection,     // Colunas a serem retornadas
                null,           // Cláusula WHERE
                null,           // Argumentos da cláusula WHERE
                null,           // GROUP BY
                null,           // HAVING
                null            // ORDER BY
        );

        int numero = -1;
        if (cursor != null && cursor.moveToFirst()) {
            numero = cursor.getInt(cursor.getColumnIndex(COLUMN_NUM));
            cursor.close();
        }
        db.close();
        return numero;
    }

    // Método para atualizar o número na coluna COLUMN_NUM
    public void atualizarNumero(int novoNumero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM, novoNumero);

        // Atualiza o valor na tabela
        db.update(TABLE_BOTAO, values, null, null);

        db.close();
    }

    // Método para inserir um valor na coluna COLUMN_RES
    public void inserirRes(int numero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RES, numero);

        // Insere o valor na tabela
        db.insert(TABLE_BOTAO, null, values);

        db.close();
    }

    // Método para obter valores da coluna COLUMN_RES
    public int getRes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_RES};

        Cursor cursor = db.query(
                TABLE_BOTAO,     // Tabela
                projection,     // Colunas a serem retornadas
                null,           // Cláusula WHERE
                null,           // Argumentos da cláusula WHERE
                null,           // GROUP BY
                null,           // HAVING
                null            // ORDER BY
        );

        int numero = -1;
        if (cursor != null && cursor.moveToFirst()) {
            numero = cursor.getInt(cursor.getColumnIndex(COLUMN_RES));
            cursor.close();
        }
        db.close();
        return numero;

    }

    // Método para atualizar o número na coluna COLUMN_RES
    public void atualizarRes(int novoNumero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RES, novoNumero);

        // Atualiza o valor na tabela
        db.update(TABLE_BOTAO, values, null, null);

        db.close();
    }



    //////////////////////////////////////// FIM DOS METODOS DOS BOTÕES ////////////////////////////////////////////////////////////////

}