package jvsouza.com.juntando.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jvsouza.com.juntando.model.Tarefas;

public class TarefaDAO {

    private Banco dbHelper;
    private SQLiteDatabase database;

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public void fechar() {
        dbHelper.close();
    }

    public TarefaDAO(Context context) {
        dbHelper = new Banco(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }




    public boolean salvar(Tarefas tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        cv.put("descricao", tarefa.getDescricaoTarefa());
        cv.put("dificuldade", tarefa.getDificuldadeTarefa());
        cv.put("checado", tarefa.getChecado());
        cv.put("dia", tarefa.getDia());
        cv.put("mes", tarefa.getMes());
        cv.put("hora", tarefa.getHora());
        cv.put("minuto", tarefa.getMinuto());
        cv.put("desafio", tarefa.getDesafio());
        cv.put("hcria", tarefa.getHcria());
        cv.put("mcria", tarefa.getMcria());



        try{
            escreve.insert(Banco.TABLE_TAREFA,null, cv);
            Log.i("INFO","Tarefa salva com sucesso ");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar tarefa "+ e.getMessage());
            return false;
        }

        return true;
    }


    public boolean atualizar(Tarefas tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        cv.put("descricao", tarefa.getDescricaoTarefa());
        cv.put("dificuldade", tarefa.getDificuldadeTarefa());
        cv.put("checado", tarefa.getChecado());
        cv.put("dia", tarefa.getDia());
        cv.put("mes", tarefa.getMes());
        cv.put("hora", tarefa.getHora());
        cv.put("minuto", tarefa.getMinuto());
        cv.put("desafio", tarefa.getDesafio());
        cv.put("hcria", tarefa.getHcria());
        cv.put("mcria", tarefa.getMcria());


        try{
            String[] args = { tarefa.getId().toString() };
            escreve.update(Banco.TABLE_TAREFA, cv, "id=?", args);
            Log.i("INFO","Tarefa atualizada com sucesso ");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar tarefa "+ e.getMessage());
            return false;
        }

        return true;
    }


    public boolean deletar(Tarefas tarefa) {

        try{
            String[] args = { tarefa.getId().toString() };
            escreve.delete(Banco.TABLE_TAREFA, "id = ?", args);
            Log.i("INFO","Tarefa removida com sucesso ");
        }catch(Exception e){
            Log.i("INFO","Erro ao excluir tarefa "+ e.getMessage());
            return false;
        }

        return true;


    }


    public List<Tarefas> listar() {

        List<Tarefas> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + Banco.TABLE_TAREFA + " ;";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){
            Tarefas tarefa = new Tarefas();

            Long id = c.getLong( c.getColumnIndex("id"));
            String nomeTarefa = c.getString( c.getColumnIndex("nome"));
            String descricaoTarefa = c.getString( c.getColumnIndex("descricao"));
            String dificuldadeTarefa = c.getString( c.getColumnIndex("dificuldade"));
            int checado = c.getInt(c.getColumnIndex("checado"));
            int dia = c.getInt(c.getColumnIndex("dia"));
            int mes = c.getInt(c.getColumnIndex("mes"));
            int hora = c.getInt(c.getColumnIndex("hora"));
            int minuto = c.getInt(c.getColumnIndex("minuto"));
            int desafio = c.getInt(c.getColumnIndex("desafio"));
            int hcria = c.getInt(c.getColumnIndex("hcria"));
            int mcria = c.getInt(c.getColumnIndex("mcria"));




            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);
            tarefa.setDescricaoTarefa(descricaoTarefa);
            tarefa.setDificuldadeTarefa(dificuldadeTarefa);
            tarefa.setChecado(checado);
            tarefa.setDia(dia);
            tarefa.setMes(mes);
            tarefa.setHora(hora);
            tarefa.setMinuto(minuto);
            tarefa.setDesafio(desafio);
            tarefa.setHcria(hcria);
            tarefa.setMcria(mcria);



            tarefas.add(tarefa);
        }

        return  tarefas;

    }

    public int getID() {
        return getColumnValue(Banco.COLUMN_ID);
    }



    @SuppressLint("Range")
    private int getColumnValue(String columnName) {
        int value = 0;
        Cursor cursor = database.query(Banco.TABLE_TAREFA,
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

        database.update(Banco.TABLE_TAREFA, valores, null, null);
    }

    public boolean verificarNomeExistente(String nomeTarefa) {
        String sql = "SELECT * FROM " + Banco.TABLE_TAREFA + " WHERE nome = ?";
        Cursor cursor = le.rawQuery(sql, new String[]{nomeTarefa});

        // Verifique se há pelo menos uma linha com o mesmo nome
        boolean nomeExistente = cursor.getCount() > 0;

        cursor.close();
        return nomeExistente;
    }








}