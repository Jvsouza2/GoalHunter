package jvsouza.com.juntando.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProgressoDAO {

    private Banco dbHelper;
    private SQLiteDatabase database;

    public ProgressoDAO(Context context) {
        dbHelper = new Banco(context);
    }

    public void abrir() {
        database = dbHelper.getWritableDatabase();
    }

    public void fechar() {
        dbHelper.close();
    }

    public void inserirDados(int contador, int level, int maximo) {
        ContentValues valores = new ContentValues();
        valores.put(Banco.COLUMN_CONTADOR, contador);
        valores.put(Banco.LEVEL_USUARIO, level);
        valores.put(Banco.COLUMN_MAXIMO, maximo);

        database.insert(Banco.TABLE_PROGRESSO, null, valores);
    }


    public void inserirContador(int contador) {
        ContentValues valores = new ContentValues();
        valores.put(Banco.COLUMN_CONTADOR, contador);
        database.insert(Banco.TABLE_PROGRESSO, null, valores);

    }

    public void inserirLevel(int level) {
        ContentValues valores = new ContentValues();
        valores.put(Banco.LEVEL_USUARIO, level);
        database.insert(Banco.TABLE_PROGRESSO, null, valores);

    }

    public void inserirMaximo(int maximo) {
        ContentValues valores = new ContentValues();
        valores.put(Banco.COLUMN_MAXIMO, maximo);
        database.insert(Banco.TABLE_PROGRESSO, null, valores);

    }


    // Métodos getter e setter para as colunas
    public int getContador() {
        return getColumnValue(Banco.COLUMN_CONTADOR);
    }

    public void setContador(int contador) {
        updateColumnValue(Banco.COLUMN_CONTADOR, contador);
    }

    public int getLevel() {
        return getColumnValue(Banco.LEVEL_USUARIO);
    }

    public void setLevel(int level) {
        updateColumnValue(Banco.LEVEL_USUARIO, level);
    }

    public int getMaximo() {
        return getColumnValue(Banco.COLUMN_MAXIMO);
    }

    public void setMaximo(int maximo) {
        updateColumnValue(Banco.COLUMN_MAXIMO, maximo);
    }



    // Método auxiliar para obter o valor de uma coluna
    @SuppressLint("Range")
    private int getColumnValue(String columnName) {
        int value = 0;
        Cursor cursor = database.query(Banco.TABLE_PROGRESSO,
                new String[]{columnName}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex(columnName));
            cursor.close();
        }

        return value;
    }

    // Método auxiliar para atualizar o valor de uma coluna
    private void updateColumnValue(String columnName, int value) {
        ContentValues valores = new ContentValues();
        valores.put(columnName, value);

        database.update(Banco.TABLE_PROGRESSO, valores, null, null);
    }

}
