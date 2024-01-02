package jvsouza.com.juntando.helper;

import static jvsouza.com.juntando.helper.Banco.NOME_CONQUISTA;
import static jvsouza.com.juntando.helper.Banco.TABLE_CONQUISTA;
import static jvsouza.com.juntando.helper.Banco.TABLE_TAREFA;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jvsouza.com.juntando.model.Conquistas;
import jvsouza.com.juntando.model.Tarefas;

public class ConquistaDAO {

    private Banco dbHelper;
    private SQLiteDatabase database;

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public void fechar() {
        dbHelper.close();
    }

    public ConquistaDAO(Context context) {
        dbHelper = new Banco(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }



    public boolean salvar(Conquistas conquistas) {

        ContentValues cv = new ContentValues();
        cv.put("NOME_CONQUISTA", conquistas.getNomeConquista());
        cv.put("DESCRICAO_CONQUISTA", conquistas.getDescricaoConquista());
        cv.put("LEVEL_CONQUISTA", conquistas.getNivelConquista());


        try{
            escreve.insert(TABLE_CONQUISTA,null, cv);
            Log.i("INFO","Conquista salva com sucesso ");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar a Conquista "+ e.getMessage());
            return false;
        }

        return true;
    }



    public List<Conquistas> listar() {

        List<Conquistas> conquistas = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_CONQUISTA + " ;";
        Cursor c = le.rawQuery(sql, null);

        while(c.moveToNext()){
            Conquistas conquista = new Conquistas();

            //Long id = c.getLong( c.getColumnIndex("COLUMN_ID"));
            String nomeConquista = c.getString( c.getColumnIndex("NOME_CONQUISTA"));
            String descricaoConquista = c.getString( c.getColumnIndex("DESCRICAO_CONQUISTA"));
            int levelConquista = c.getInt( c.getColumnIndex("LEVEL_CONQUISTA"));


            //conquista.setIdConquista(id);
            conquista.setNomeConquista(nomeConquista);
            conquista.setDescricaoConquista(descricaoConquista);
            conquista.setNivelConquista(levelConquista);


            conquistas.add(conquista);
        }

        return  conquistas;

    }


    public boolean conquistaExiste(String nomeConquista) {
        Cursor cursor = le.query(TABLE_CONQUISTA,
                null, NOME_CONQUISTA + " = ?", new String[]{nomeConquista},
                null, null, null);

        boolean existe = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }

        return existe;
    }






}
