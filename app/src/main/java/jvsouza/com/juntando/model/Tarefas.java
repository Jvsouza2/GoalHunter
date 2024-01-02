package jvsouza.com.juntando.model;

import java.io.Serializable;

import jvsouza.com.juntando.helper.TarefaDAO;

public class Tarefas implements Serializable {


    private Long id;
    private String nomeTarefa;
    private String descricaoTarefa;
    private String dificuldadeTarefa;
    private int checado, dia, hora, mes, minuto, desafio, hcria, mcria;
    private boolean ret;


    public String getDificuldadeTarefa() {
        return dificuldadeTarefa;
    }

    public void setDificuldadeTarefa(String dificuldadeTarefa) {
        this.dificuldadeTarefa = dificuldadeTarefa;
    }

    public int getDesafio() {
        return desafio;
    }

    public int getHcria() {
        return hcria;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public void setHcria(int hcria) {
        this.hcria = hcria;
    }



    public int getMcria() {
        return mcria;
    }

    public void setMcria(int mcria) {
        this.mcria = mcria;
    }

    public void setDesafio(int desafio) {
        this.desafio = desafio;
    }

    public int getDia() {
        return dia;
    }



    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public int getChecado() {
        return checado;
    }

    public void setChecado(int checado) {
        this.checado = checado;
    }
}


