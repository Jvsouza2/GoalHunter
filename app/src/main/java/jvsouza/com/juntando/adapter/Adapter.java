package jvsouza.com.juntando.adapter;







import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import jvsouza.com.juntando.R;
import jvsouza.com.juntando.activity.Formulario;
import jvsouza.com.juntando.activity.MainActivity;
import jvsouza.com.juntando.activity.Progresso;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.ProgressoDAO;
import jvsouza.com.juntando.helper.TarefaDAO;
import jvsouza.com.juntando.model.Tarefas;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;

    public List<Tarefas> listaTarefas;

    private RecyclerView recyclerView;





    public Adapter(List<Tarefas> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    public Adapter(Context context, List<Tarefas> listaTarefas, RecyclerView recyclerView) {
        this.context = context;
        this.listaTarefas = listaTarefas;
        this.recyclerView = recyclerView;
    }

    public Adapter(Context context, List<Tarefas> listaTarefas) {
        this.context = context;
        this.listaTarefas = listaTarefas;

    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTarefas = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tarefas, parent, false);

        return new MyViewHolder(itemTarefas);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Tarefas tarefa = listaTarefas.get(position);
        ProgressoDAO progressoDAO = new ProgressoDAO(context);
        TarefaDAO tarefaDAO  = new TarefaDAO(context);






        holder.nome.setText(tarefa.getNomeTarefa());
        holder.descricao.setText(tarefa.getDescricaoTarefa());
        holder.dificuldade.setText(tarefa.getDificuldadeTarefa());
        holder.dia.setText(String.valueOf(tarefa.getDia()));
        holder.hora.setText(String.valueOf(tarefa.getHora()));
        holder.mes.setText(String.valueOf(tarefa.getMes() + 1));
        holder.minuto.setText(String.valueOf(tarefa.getMinuto()));


        // Calcular o tempo decorrido desde a criação da tarefa em minutos
        Calendar calendar = Calendar.getInstance();

        int horaAtual = calendar.get(Calendar.HOUR_OF_DAY); // Hora atual (0-23)
        int minutoAtual = calendar.get(Calendar.MINUTE); // Minutos atuais (0-59)

        int horaCria = tarefa.getHcria(); // Hora em que a tarefa foi criada
        int minutoCria = tarefa.getMcria(); // Minutos em que a tarefa foi criada

        // Calcular o tempo decorrido desde a criação da tarefa em minutos
        int minutosDecorridos = (horaAtual - horaCria) * 60 + (minutoAtual - minutoCria);

        if (tarefa.getDesafio() == 1){
            holder.desafio.setText("15 minutos tempo limite");

        }else if (tarefa.getDesafio() == 2){
            holder.desafio.setText("3 horas tempo limite");

        }else{
            holder.desafio.setAlpha(0f);
        }

        // Mudar cor do texto do recycler view
        Banco banco = new Banco(context);
        int botaoAtual = banco.getRes();


            if(botaoAtual == 2) {

                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);
                holder.nome.setTextColor(minhaCor);
                holder.descricao.setTextColor(minhaCor);
                holder.dificuldade.setTextColor(minhaCor);
                holder.dia.setTextColor(minhaCor);
                holder.mes.setTextColor(minhaCor);
                holder.hora.setTextColor(minhaCor);
                holder.minuto.setTextColor(minhaCor);
                holder.prazo.setTextColor(minhaCor);
                holder.ponto.setTextColor(minhaCor);
                holder.barra.setTextColor(minhaCor);
                holder.botaosobe.setImageResource(R.drawable.sobe);
                holder.botaodesce.setImageResource(R.drawable.desce);



                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorB);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.white);

                // Crie um ColorStateList para definir as cores em diferentes estados
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                };

                int[] colors = new int[] {
                        minhaCorMarcado,
                        minhaCorDesmarcado
                };

                ColorStateList colorStateList = new ColorStateList(states, colors);

                // Defina a ColorStateList como a cor do CheckBox
                CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);


            }else if (botaoAtual == 4) {

                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);
                holder.nome.setTextColor(minhaCor);
                holder.descricao.setTextColor(minhaCor);
                holder.dificuldade.setTextColor(minhaCor);
                holder.dia.setTextColor(minhaCor);
                holder.mes.setTextColor(minhaCor);
                holder.hora.setTextColor(minhaCor);
                holder.minuto.setTextColor(minhaCor);
                holder.prazo.setTextColor(minhaCor);
                holder.ponto.setTextColor(minhaCor);
                holder.barra.setTextColor(minhaCor);
                holder.botaosobe.setImageResource(R.drawable.sobe);
                holder.botaodesce.setImageResource(R.drawable.desce);


                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorC);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.white);

                // Crie um ColorStateList para definir as cores em diferentes estados
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                };

                int[] colors = new int[] {
                        minhaCorMarcado,
                        minhaCorDesmarcado
                };

                ColorStateList colorStateList = new ColorStateList(states, colors);

                // Defina a ColorStateList como a cor do CheckBox
                CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);



            }else if(botaoAtual == 6){



                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);
                holder.nome.setTextColor(minhaCor);
                holder.descricao.setTextColor(minhaCor);
                holder.dificuldade.setTextColor(minhaCor);
                holder.dia.setTextColor(minhaCor);
                holder.mes.setTextColor(minhaCor);
                holder.hora.setTextColor(minhaCor);
                holder.minuto.setTextColor(minhaCor);
                holder.prazo.setTextColor(minhaCor);
                holder.ponto.setTextColor(minhaCor);
                holder.barra.setTextColor(minhaCor);
                holder.botaosobe.setImageResource(R.drawable.sobe);
                holder.botaodesce.setImageResource(R.drawable.desce);


                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.Colorf);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.Colorg);

                // Crie um ColorStateList para definir as cores em diferentes estados
                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                };

                int[] colors = new int[] {
                        minhaCorMarcado,
                        minhaCorDesmarcado
                };

                ColorStateList colorStateList = new ColorStateList(states, colors);

                // Defina a ColorStateList como a cor do CheckBox
                CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);
            }




        // Define o estado do checkbox com base na propriedade "checado" da tarefa
        holder.checkBox.setChecked(tarefa.getChecado() == 1);

        // Verifica o estado da tarefa e atualiza a aparência do item
        if (tarefa.getChecado() == 1) {
            holder.nome.setPaintFlags(holder.nome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.descricao.setPaintFlags(holder.descricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dificuldade.setPaintFlags(holder.dificuldade.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dia.setPaintFlags(holder.dia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mes.setPaintFlags(holder.mes.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.hora.setPaintFlags(holder.hora.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.minuto.setPaintFlags(holder.minuto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.checkBox.setEnabled(false); // Desabilita o clique no checkbox

                //if (botaoAtual == 2 || botaoAtual == 4 || botaoAtual == 6){

                    int corVerde = ContextCompat.getColor(context, R.color.ColorD); // Suponha que você tenha uma cor vermelha definida em seus recursos (colors.xml)
                    holder.nome.setTextColor(corVerde);
                    holder.prazo.setTextColor(corVerde);
                    holder.descricao.setTextColor(corVerde);
                    holder.dificuldade.setTextColor(corVerde);
                    holder.dia.setTextColor(corVerde);
                    holder.mes.setTextColor(corVerde);
                    holder.hora.setTextColor(corVerde);
                    holder.minuto.setTextColor(corVerde);

              //  }




            int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorD);
            int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.light);

            // Crie um ColorStateList para definir as cores em diferentes estados
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_checked },
                    new int[] { -android.R.attr.state_checked }
            };

            int[] colors = new int[] {
                    minhaCorMarcado,
                    minhaCorDesmarcado
            };

            ColorStateList colorStateList = new ColorStateList(states, colors);

            // Defina a ColorStateList como a cor do CheckBox
            CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);
        } else {
            holder.nome.setPaintFlags(holder.nome.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.descricao.setPaintFlags(holder.descricao.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.dificuldade.setPaintFlags(holder.dificuldade.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.checkBox.setEnabled(true); // Habilita o clique no checkbox



        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tarefa.getChecado() != 1) {
                    editarTarefa(position);
                }

            }
        });




        int contador;
        String dif = holder.dificuldade.getText().toString();

        if (dif.equals("Fácil")){
            contador = 2;
        } else if (dif.equals("Médio")) {
            contador = 5;
        }else{
            contador = 10;
        }





        if (minutosDecorridos < 1) {

            holder.checkBox.setEnabled(false); // disable checkbox


            // Criar um Handler com um atraso de um minuto (60 segundos)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Após um minuto, habilitar o checkbox
                    holder.checkBox.setEnabled(true);
                }
            }, 60 * 1000); // 60 segundos (convertidos para milissegundos)

        }else{

            if (tarefa.getDesafio() == 2 && minutosDecorridos > 180 && tarefa.getChecado() == 0) {

                holder.checkBox.setEnabled(false); // Desabilita a caixa de seleção

                progressoDAO.abrir();
                int premio = contador;
                if (progressoDAO.getContador() <= 1 && progressoDAO.getLevel() == 0) {
                    Toast.makeText(context, "Você não tem pontos para perder", Toast.LENGTH_SHORT).show();
                } else {

                    progressoDAO.setContador(progressoDAO.getContador() - premio);
                    if (progressoDAO.getContador() < 0) {
                        progressoDAO.setContador(1);
                        int lvl = progressoDAO.getLevel();
                        progressoDAO.setLevel(lvl -1);
                    }

                    Toast.makeText(context, "Infelizmente você não foi capaz de completar o desafio, a tarefa será apagada", Toast.LENGTH_SHORT).show();
                    holder.checkBox.setClickable(false);
                    holder.itemView.setOnClickListener(null); // Desativa o evento de clique
                }
                deleteTarefa(position);

                progressoDAO.fechar();




            } else if (tarefa.getDesafio() == 1 && minutosDecorridos > 15 && tarefa.getChecado() == 0) {
                holder.checkBox.setEnabled(false); // Desabilita a caixa de seleção


                progressoDAO.abrir();
                int lvl = progressoDAO.getLevel();
                if (lvl >= 1) {
                    progressoDAO.setLevel(lvl - 1);
                    Toast.makeText(context, "Infelizmente você não foi capaz de completar o desafio, a tarefa será apagada", Toast.LENGTH_SHORT).show();
                    holder.checkBox.setClickable(false);
                    holder.itemView.setOnClickListener(null); // Desativa o evento de clique
                } else {
                    Toast.makeText(context, "Sem nível suficiente", Toast.LENGTH_SHORT).show();
                }
                deleteTarefa(position);


                progressoDAO.fechar();


            }else {
                holder.checkBox.setEnabled(true); // disable checkbox


            }
        }





        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Hora atual
                Calendar calendar = Calendar.getInstance();

                int horaAtual = calendar.get(Calendar.HOUR_OF_DAY); // Hora atual (0-23)
                int minutoAtual = calendar.get(Calendar.MINUTE); // Minutos atuais (0-59)

                int horaTarefa = tarefa.getHora(); // Hora em que a tarefa foi criada
                int minutoTarefa = tarefa.getMinuto(); // Minutos em que a tarefa foi criada

                // Calcular o tempo decorrido desde a criação da tarefa em minutos
                int minutosDecorridos = (horaAtual - horaTarefa) * 60 + (minutoAtual - minutoTarefa);
                tarefa.setChecado(1);

                if (minutosDecorridos >= 0) {
                    if (tarefa.getDesafio() == 2 && minutosDecorridos <= 180 && tarefa.getChecado() == 1) {
                        progressoDAO.abrir();
                        int premio = contador;


                        progressoDAO.setContador(progressoDAO.getContador() + premio);
                        progressoDAO.fechar();
                        Toast.makeText(context, "Parabéns, você completou o desafio", Toast.LENGTH_SHORT).show();
                    } else if (tarefa.getDesafio() == 1 && minutosDecorridos <= 15 && tarefa.getChecado() == 1) {
                        progressoDAO.abrir();

                        int lvl = progressoDAO.getLevel();

                        progressoDAO.setLevel(lvl + 1);

                        progressoDAO.fechar();
                        Toast.makeText(context, "Parabéns, você completou o desafio", Toast.LENGTH_SHORT).show();
                    }
                }



                int contador;
                tarefa.setChecado(holder.checkBox.isChecked() ? 1 : 0);
                TarefaDAO tarefaDAO = new TarefaDAO(context);
                tarefaDAO.atualizar(tarefa);

                // Notifica a atividade principal sobre a mudança no estado do checkbox
                if (mOnCheckboxClickListener != null) {
                    mOnCheckboxClickListener.onCheckboxClick(position, holder.checkBox.isChecked());
                }

                boolean isChecked = holder.checkBox.isChecked();
                String dif = holder.dificuldade.getText().toString();
                if (isChecked) {
                    holder.nome.setPaintFlags(holder.nome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.descricao.setPaintFlags(holder.descricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.dificuldade.setPaintFlags(holder.dificuldade.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.dia.setPaintFlags(holder.dia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.mes.setPaintFlags(holder.mes.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.hora.setPaintFlags(holder.hora.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.minuto.setPaintFlags(holder.minuto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.desafio.setPaintFlags(holder.minuto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.checkBox.setEnabled(false); // disable checkbox

                    int corVerde = ContextCompat.getColor(context, R.color.ColorD); // Suponha que você tenha uma cor vermelha definida em seus recursos (colors.xml)
                    holder.nome.setTextColor(corVerde);
                    holder.prazo.setTextColor(corVerde);
                    holder.descricao.setTextColor(corVerde);
                    holder.dificuldade.setTextColor(corVerde);
                    holder.dia.setTextColor(corVerde);
                    holder.mes.setTextColor(corVerde);
                    holder.hora.setTextColor(corVerde);
                    holder.minuto.setTextColor(corVerde);
                    holder.desafio.setTextColor(corVerde);
                    tarefa.setChecado(1);

                    int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorD);
                    int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.light);

                    // Crie um ColorStateList para definir as cores em diferentes estados
                    int[][] states = new int[][] {
                            new int[] { android.R.attr.state_checked },
                            new int[] { -android.R.attr.state_checked }
                    };

                    int[] colors = new int[] {
                            minhaCorMarcado,
                            minhaCorDesmarcado
                    };

                    ColorStateList colorStateList = new ColorStateList(states, colors);

                    // Defina a ColorStateList como a cor do CheckBox
                    CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);





                    if (dif.equals("Fácil")){
                        contador = 2;
                    } else if (dif.equals("Médio")) {
                        contador = 5;
                    }else{
                        contador = 10;
                    }



                    progressoDAO.abrir();
                    int level = progressoDAO.getLevel();
                    int maximo = progressoDAO.getMaximo();



                    int lvl = level;
                    int max = maximo;


                    int cc = progressoDAO.getContador();
                    if (cc == 0){
                        progressoDAO.inserirDados(contador, 0, 25);
                    }else if (cc == 1){
                        progressoDAO.setContador(contador);
                    }else if (cc < 0){
                        progressoDAO.setContador(Math.abs(cc));
                    }else if (contador >= maximo) {
                        progressoDAO.setContador(maximo - contador);

                        max +=10;
                        progressoDAO.setMaximo(max);

                        lvl++;
                        progressoDAO.setLevel(lvl);


                    }else{
                        if (tarefa.getDesafio() == 1){

                        }else{
                            progressoDAO.setContador(cc + contador);
                        }

                    }





                } else {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editarTarefa(position);
                        }
                    });
                }





            }


        });



        int hora = calendar.get(Calendar.HOUR_OF_DAY); // Hora do dia (0-23)
        int minuto = calendar.get(Calendar.MINUTE);    // Minutos (0-59)
        int dia = calendar.get(Calendar.DAY_OF_MONTH);  // Dia do mês (1-31)
        int mes = calendar.get(Calendar.MONTH);// Mês (0-11)
        //hora -= 3; // Para uso na máquina virtual

        // Prazo do usuário
        int horaTarefa = tarefa.getHora();
        int minutoTarefa = tarefa.getMinuto();
        int diaTarefa = tarefa.getDia();
        int mesTarefa = tarefa.getMes();
        //int horaTarefaAtual = horaTarefa - 3; // Para uso na máquina virtual



        // Comparar os valores
        if (horaTarefa == hora && minutoTarefa == minuto && diaTarefa == dia && mesTarefa == mes) {
            Toast.makeText(context, "Infelizmente você não foi capaz de completar o desafio, a tarefa será apagada", Toast.LENGTH_SHORT).show();
            deleteTarefa(position);

            // Define a cor vermelha para o texto com estilo "strike through"
            int corVermelha = ContextCompat.getColor(context, R.color.red); // Suponha que você tenha uma cor vermelha definida em seus recursos (colors.xml)
            holder.nome.setTextColor(corVermelha);
            holder.prazo.setTextColor(corVermelha);
            holder.descricao.setTextColor(corVermelha);
            holder.dificuldade.setTextColor(corVermelha);
            holder.dia.setTextColor(corVermelha);
            holder.mes.setTextColor(corVermelha);
            holder.hora.setTextColor(corVermelha);
            holder.minuto.setTextColor(corVermelha);
            tarefa.setChecado(1);

            holder.nome.setPaintFlags(holder.nome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.descricao.setPaintFlags(holder.descricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dificuldade.setPaintFlags(holder.dificuldade.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dia.setPaintFlags(holder.dia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mes.setPaintFlags(holder.mes.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.hora.setPaintFlags(holder.hora.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.minuto.setPaintFlags(holder.minuto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.checkBox.setEnabled(false); // Desabilita a caixa de seleção
            holder.itemView.setOnClickListener(null); // Desativa o evento de clique
            holder.botaodesce.setOnClickListener(null);
            holder.botaosobe.setOnClickListener(null);

            int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.light);
            int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.red);

            // Crie um ColorStateList para definir as cores em diferentes estados
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_checked },
                    new int[] { -android.R.attr.state_checked }
            };

            int[] colors = new int[] {
                    minhaCorMarcado,
                    minhaCorDesmarcado
            };

            ColorStateList colorStateList = new ColorStateList(states, colors);

            // Defina a ColorStateList como a cor do CheckBox
            CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);





            progressoDAO.abrir();

            int cc = progressoDAO.getContador();
            int lvl = progressoDAO.getLevel();
            int max = progressoDAO.getMaximo();
            if (cc <= 0){
                progressoDAO.setLevel(lvl - 1);
                progressoDAO.setMaximo(max - 10);
                progressoDAO.setContador(1);
            }else{
                progressoDAO.setContador(cc - contador);
            }


        } else if (mesTarefa < mes || (mesTarefa == mes && diaTarefa < dia) || (mesTarefa == mes && diaTarefa == dia && horaTarefa < hora) || (mesTarefa == mes && diaTarefa == dia && horaTarefa == hora && minutoTarefa < minuto)) {
            Toast.makeText(context, "Infelizmente você não foi capaz de completar o desafio, a tarefa será apagada", Toast.LENGTH_SHORT).show();
            deleteTarefa(position);


            // Define a cor vermelha para o texto com estilo "strike through"
            int corVermelha = ContextCompat.getColor(context, R.color.red); // Suponha que você tenha uma cor vermelha definida em seus recursos (colors.xml)
            holder.nome.setTextColor(corVermelha);
            holder.prazo.setTextColor(corVermelha);
            holder.descricao.setTextColor(corVermelha);
            holder.dificuldade.setTextColor(corVermelha);
            holder.dia.setTextColor(corVermelha);
            holder.mes.setTextColor(corVermelha);
            holder.hora.setTextColor(corVermelha);
            holder.minuto.setTextColor(corVermelha);
            tarefa.setChecado(1);

            holder.nome.setPaintFlags(holder.nome.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.descricao.setPaintFlags(holder.descricao.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dificuldade.setPaintFlags(holder.dificuldade.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.dia.setPaintFlags(holder.dia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mes.setPaintFlags(holder.mes.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.hora.setPaintFlags(holder.hora.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.minuto.setPaintFlags(holder.minuto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.checkBox.setEnabled(false); // Desabilita a caixa de seleção
            holder.itemView.setOnClickListener(null); // Desativa o evento de clique
            holder.botaodesce.setOnClickListener(null);
            holder.botaosobe.setOnClickListener(null);

            int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.light);
            int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.red);

            // Crie um ColorStateList para definir as cores em diferentes estados
            int[][] states = new int[][] {
                    new int[] { android.R.attr.state_checked },
                    new int[] { -android.R.attr.state_checked }
            };

            int[] colors = new int[] {
                    minhaCorMarcado,
                    minhaCorDesmarcado
            };

            ColorStateList colorStateList = new ColorStateList(states, colors);

            // Defina a ColorStateList como a cor do CheckBox
            CompoundButtonCompat.setButtonTintList(holder.checkBox, colorStateList);





            progressoDAO.abrir();

            int cc = progressoDAO.getContador();
            int lvl = progressoDAO.getLevel();
            int max = progressoDAO.getMaximo();
            if (cc <= 0){
                progressoDAO.setLevel(lvl - 1);
                progressoDAO.setMaximo(max - 10);
                progressoDAO.setContador(1);
            }else{
                progressoDAO.setContador(cc - contador);
            }




        } else {
            if (hora == (horaTarefa - 1) || minuto == (minutoTarefa - 30)) {
                Toast.makeText(context, "Cuidado! O prazo está acabando...", Toast.LENGTH_SHORT).show();
            }

        }






        holder.botaosobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifica se o item pode ser movido para cima
                if (tarefa.getChecado() == 1){
                    Toast.makeText(context, "Não é possível mover um item já concluido", Toast.LENGTH_SHORT).show();
                }else if (position > 0) {
                    // Troca as posições do item atual com o item acima dele
                    Collections.swap(listaTarefas, position, position - 1);
                    // Notifica o adaptador sobre a mudança na posição dos itens
                    notifyItemMoved(position, position - 1);

                    // Notifica os itens afetados para atualização
                    notifyItemChanged(position);
                    notifyItemChanged(position - 1);
                }
            }
        });

        holder.botaodesce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifica se o item pode ser movido para baixo
                if (tarefa.getChecado() == 1){
                    Toast.makeText(context, "Não é possível mover um item já concluido", Toast.LENGTH_SHORT).show();
                }else if (position < listaTarefas.size() - 1) {
                    // Troca as posições do item atual com o item abaixo dele
                    Collections.swap(listaTarefas, position, position + 1);

                    // Notifica o adaptador sobre a mudança na posição dos itens
                    notifyItemMoved(position, position + 1);

                    // Notifica os itens afetados para atualização
                    notifyItemChanged(position);
                    notifyItemChanged(position + 1);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nome;
        public TextView descricao;
        public TextView dificuldade;
        public TextView dia;
        public TextView minuto;
        public TextView mes;
        public TextView hora;
        public TextView prazo;
        public TextView barra;
        public TextView ponto;
        public TextView desafio;
        public CheckBox checkBox;
        public ImageButton botaosobe, botaodesce;

        private ProgressoDAO progressoDAO;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            nome = itemView.findViewById(R.id.textTarefa);
            descricao = itemView.findViewById(R.id.textDesc);
            dificuldade = itemView.findViewById(R.id.textDif);
            checkBox = itemView.findViewById(R.id.checkBox1);
            dia = itemView.findViewById(R.id.textDay);
            mes = itemView.findViewById(R.id.textMonth);
            hora = itemView.findViewById(R.id.textHour);
            minuto = itemView.findViewById(R.id.textMinute);
            progressoDAO = new ProgressoDAO(context);
            prazo = itemView.findViewById(R.id.textPrazo);
            botaosobe = itemView.findViewById(R.id.botaoSobe);
            botaodesce = itemView.findViewById(R.id.botaoDesce);
            ponto = itemView.findViewById(R.id.textPonto);
            barra = itemView.findViewById(R.id.textBarra);
            desafio = itemView.findViewById(R.id.textView5);





        }
    }

    public interface OnCheckboxClickListener {
        void onCheckboxClick(int position, boolean isChecked);
    }

    private OnCheckboxClickListener mOnCheckboxClickListener;

    public void setOnCheckboxClickListener(OnCheckboxClickListener listener) {
        this.mOnCheckboxClickListener = listener;
    }



    public void editarTarefa(int position) {
        Tarefas tarefaSelecionada = listaTarefas.get(position);

        // Abrir a guia do formulário para edição
        Intent intent = new Intent(context, Formulario.class);
        intent.putExtra("tarefaSelecionada", tarefaSelecionada);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Adiciona a flag necessária
        context.startActivity(intent);
    }

    public void deleteTarefa(final int position) {
        // Posterga a execução da operação para o segmento da interface do usuário
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                Tarefas tarefaSelecionada = listaTarefas.get(position);
                TarefaDAO tarefaDAO = new TarefaDAO(context);
                tarefaDAO.deletar(tarefaSelecionada);
                // Notifique o adaptador sobre a remoção do item
                listaTarefas.remove(position);
                notifyItemRemoved(position);
            }
        });
    }
















}