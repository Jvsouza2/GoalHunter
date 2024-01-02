package jvsouza.com.juntando.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.adapter.Adapter;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.MyGifView;
import jvsouza.com.juntando.helper.ProgressoDAO;
import jvsouza.com.juntando.helper.RecyclerItemClickListener;
import jvsouza.com.juntando.helper.TarefaDAO;
import jvsouza.com.juntando.model.Tarefas;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements Adapter.OnCheckboxClickListener{

    private RecyclerView recyclerView;
    private ImageButton botao;

    private List<Tarefas> listaTarefa = new ArrayList<>();

    private TarefaDAO tarefaDAO;
    private  Adapter adapter;

    private Tarefas tarefaSelecionada;
    private TextView mainProgresso, mainLevel, mainMaximo;
    private MyGifView gifView;
    private View view;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaração de variáveis com base em seus IDs no layout

        botao = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        mainLevel = findViewById(R.id.mainLevel);
        mainProgresso = findViewById(R.id.mainProgresso);
        mainMaximo = findViewById(R.id.mainMaximo);
        gifView = findViewById(R.id.gifView);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        view = findViewById(R.id.view);
        View backgroundView = findViewById(R.id.ConstraintView);
        int newColor = getResources().getColor(R.color.light);
        backgroundView.setBackgroundColor(newColor);


        // Abre a Data Access Object (Objeto de acesso de dados) relacionado ao progresso do usuário
        ProgressoDAO progressoDAO = new ProgressoDAO(getApplicationContext());
        progressoDAO.abrir();




        // Define alguns itens do layout como transparentes (Podem ser exibidos depois)
        mainProgresso.setText(String.valueOf(progressoDAO.getContador()));
        mainProgresso.setAlpha(0f);
        mainLevel.setText(String.valueOf(progressoDAO.getLevel()));
        mainMaximo.setText(String.valueOf(progressoDAO.getMaximo()));
        mainMaximo.setAlpha(0f);
        mainLevel.setAlpha(0f);
        gifView.setAlpha(0.0f);



        tarefaDAO = new TarefaDAO(this);

        // Obtém a instância da ActionBar
        ActionBar actionBar = getSupportActionBar();
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Switch que controla a ActionBar para trocar de tela
                switch (item.getItemId()) {
                    case R.id.home:



                        Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentHome);
                        return true;


                    case R.id.person:




                        Intent intentConquistas = new Intent(getApplicationContext(), Progresso.class);
                        startActivity(intentConquistas);
                        return true;

                    case R.id.recompensas:



                        Intent intentRecompensa = new Intent(getApplicationContext(), Recompensa.class);
                        startActivity(intentRecompensa);
                        return true;

                    default:
                        return false;
                }
            }
        });
        Banco banco = new Banco(getApplicationContext());




        try {

            // Cria uma variável com o número relacionado a recompensas de background
            int botaoRes = banco.getRes();

            // Sequencia de if e else if que verifica e altera o tema quando o usuário clica em uma recompensa
            if (botaoRes == 2) {
                int newColorA = getResources().getColor(R.color.ColorA);
                backgroundView.setBackgroundColor(newColorA);
                botao.setBackgroundColor(getResources().getColor(R.color.ColorB));
                botao.setImageResource(R.drawable.addbranco);

                int Branco = getResources().getColor(R.color.white);
                view.setBackgroundColor(Branco);


                // Verifica se a ActionBar não é nula
                if (actionBar != null) {
                    // Define a cor de fundo da ActionBar
                    int minhaCor = getResources().getColor(R.color.ColorB);
                    actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                    actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                }

                Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);

                // Altera a cor da decoração de dividir itens na lista de tarefas
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(dividerDrawable);
                recyclerView.addItemDecoration(itemDecoration);

                // Define um adapter para o recyclerView (Lista de tarefas)
                adapter = new Adapter(getApplicationContext(), listaTarefa, recyclerView);
                recyclerView.setAdapter(adapter);
                mainLevel.setTextColor(Color.parseColor("#FFFFFF"));

                // Altera cor da ActionBar
                int minhaCorMarcado = getResources().getColor(R.color.ColorB);
                int minhaCorDesmarcado = getResources().getColor(R.color.white);

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
                bottomNavigationView.setItemTextColor(colorStateList);
                bottomNavigationView.setItemIconTintList(colorStateList);


            } else if (botaoRes == 4) {
                // Define o gif da Matrix para aparecer (Retira sua transparência)
                gifView.setAlpha(1f);
                backgroundView.setBackgroundResource(R.drawable.matrix);

                // Verifica se a ActionBar não é nula
                if (actionBar != null) {
                    // Define a cor de fundo da ActionBar
                    int minhaCor = getResources().getColor(R.color.ColorD);
                    actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                    actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                }


                Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);


                DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(dividerDrawable);
                recyclerView.addItemDecoration(itemDecoration);


                adapter = new Adapter(getApplicationContext(), listaTarefa);
                recyclerView.setAdapter(adapter);
                mainLevel.setTextColor(Color.parseColor("#FFFFFF"));
                botao.setBackgroundColor(getResources().getColor(R.color.ColorC));
                botao.setImageResource(R.drawable.addbranco);

                int Verde = getResources().getColor(R.color.ColorD);
                bottomNavigationView.setBackgroundColor(Verde);
                bottomNavigationView.setAlpha(1f);




                int minhaCorMarcado = getResources().getColor(R.color.ColorC);
                int minhaCorDesmarcado = getResources().getColor(R.color.white);


                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                };

                int[] colors = new int[] {
                        minhaCorMarcado,
                        minhaCorDesmarcado
                };

                ColorStateList colorStateList = new ColorStateList(states, colors);
                bottomNavigationView.setItemTextColor(colorStateList);
                bottomNavigationView.setItemIconTintList(colorStateList);
                view.setBackgroundResource(R.color.white);


            }else if(botaoRes == 6){
                gifView.setAlpha(0f);

                try {
                    // Inicia a animação do background para mudar as cores com base em uma lista de cores (gradient_list)
                    backgroundView.setBackgroundResource(R.drawable.gradient_list);
                    AnimationDrawable animationDrawable = (AnimationDrawable) backgroundView.getBackground();
                    animationDrawable.setEnterFadeDuration(2500);
                    animationDrawable.setExitFadeDuration(5000);
                    animationDrawable.start();
                }catch (Exception e){

                }






                // Verifica se a ActionBar não é nula
                if (actionBar != null) {




                    actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));


                    // Cores do gradiente
                    int[] colors = {Color.parseColor("#a8dadc"), Color.parseColor("#e63946"), Color.parseColor("#1d3557")};


                    GradientDrawable gradientDrawable = new GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM, // Define a orientação do gradiente
                            colors // Array de cores do gradiente
                    );

                    // Ajuste o canto arredondado
                    gradientDrawable.setCornerRadius(0);


                    StateListDrawable stateListDrawable = new StateListDrawable();
                    stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable);
                    stateListDrawable.addState(new int[]{}, gradientDrawable);

                    // LayerDrawable para adicionar uma borda à ActionBar
                    Drawable[] layers = new Drawable[2];
                    layers[0] = stateListDrawable;

                    LayerDrawable layerDrawable = new LayerDrawable(layers);

                    // Plano de fundo personalizado da ActionBar
                    getSupportActionBar().setBackgroundDrawable(layerDrawable);








                }

                Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);

                // Use o DividerItemDecoration padrão e aplica o Drawable criado
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                itemDecoration.setDrawable(dividerDrawable);
                recyclerView.addItemDecoration(itemDecoration);


                adapter = new Adapter(getApplicationContext(), listaTarefa);
                recyclerView.setAdapter(adapter);
                mainLevel.setTextColor(Color.parseColor("#FFFFFF"));
                botao.setBackgroundColor(Color.TRANSPARENT);
                botao.setImageResource(R.drawable.addbranco);


                int minhaCorMarcado = getResources().getColor(R.color.Colorg);
                int minhaCorDesmarcado = getResources().getColor(R.color.Colorf);


                int[][] states = new int[][] {
                        new int[] { android.R.attr.state_checked },
                        new int[] { -android.R.attr.state_checked }
                };

                int[] colors = new int[] {
                        minhaCorMarcado,
                        minhaCorDesmarcado
                };

                ColorStateList colorStateList = new ColorStateList(states, colors);
                bottomNavigationView.setItemTextColor(colorStateList);
                bottomNavigationView.setItemIconTintList(colorStateList);
            }else {
                // Se nenhum if foi acionado, o padrão de cores é aplicado (Tema padrão branco)
                int light = getResources().getColor(R.color.light);
                backgroundView.setBackgroundColor(light);
                int Branco = getResources().getColor(R.color.white);
                int Preto = getResources().getColor(R.color.black);
                bottomNavigationView.setBackgroundColor(Branco);
                view.setBackgroundColor(Preto);

            }



        } catch (Resources.NotFoundException e) {
            throw new RuntimeException(e);
        }
        try {


            // Variável que captura o botão relacionado as recompensas de botões
            int botaoAtual = banco.getNumero();
            //Verifica se o usuário clicou em um botão e altera de acordo
            if (botaoAtual == 0){
                botao.setImageResource(R.drawable.add);
            }
            if (botaoAtual == 1){
                botao.setImageResource(R.drawable.a);
            }else if(botaoAtual == 3){
                botao.setImageResource(R.drawable.b);
            }else if(botaoAtual == 5){
                botao.setImageResource(R.drawable.c);
            }else if(botaoAtual == 7){
                botao.setImageResource(R.drawable.d);
            }else if(botaoAtual == 9){
                botao.setImageResource(R.drawable.e);
            }

            Log.i("Sucesso", "Sucesso ao exibir botão");

        } catch (Exception e) {
            Log.i("Erro", "Erro ao exibir botão");
        }


        //evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {


                            @Override
                            public void onLongItemClick(View view, int position) {

                                //Recupera a tarefa para deletar
                                tarefaSelecionada = listaTarefa.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                //Configurar titulo e mensagem
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                        if (tarefaDAO.deletar(tarefaSelecionada)){
                                            carregarListaTarefas();


                                            Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show();

                                        }else{

                                            Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                dialog.setNegativeButton("Não", null);

                                //Exibe o alerta
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Evento de clique do botão que envia para o formulário
                Intent intent = new Intent(MainActivity.this, Formulario.class);

                startActivity(intent);

            }
        });


    // Fecha o banco de dados e seus DAOs
    progressoDAO.fechar();
    banco.close();
    tarefaDAO.fechar();




    }


    public void carregarListaTarefas(){





        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefa = tarefaDAO.listar();

        adapter = new Adapter(getApplicationContext(), listaTarefa, recyclerView);
        adapter.setOnCheckboxClickListener(this);
        //Configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnCheckboxClickListener(this);

        tarefaDAO.fechar();




    }

    @Override
    protected void onStart() {
        // Chama o método de carregar tarefas quando inicia o APP
        carregarListaTarefas();
        super.onStart();

    }

    @Override
    public void onCheckboxClick(int position, boolean isChecked) {
        // Atualiza a lista de tarefas com o novo estado do checkbox
        Tarefas tarefa = listaTarefa.get(position);
        tarefa.setChecado(isChecked ? 1 : 0);

        if (tarefa.getDesafio() == 1 || tarefa.getDesafio() == 2){
            showDeleteDesafio(position);
            tarefa.setChecado(1);
        }else{
            showDeleteDialog(position);
            tarefa.setChecado(1);
        }


        // Atualiza o banco de dados com o novo estado da tarefa
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        tarefaDAO.atualizar(tarefa);
        // Notifica o adaptador que houve mudanças na lista de tarefas
        adapter.notifyDataSetChanged();
        tarefaDAO.fechar();
    }



    // Método para apagar tarefas
    private void showDeleteDialog(int position) {
        tarefaSelecionada = listaTarefa.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Excluir Item");
        builder.setMessage("Parabéns! Você concluiu uma tarefa. Deseja excluir esta tarefa ?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                if (tarefaDAO.deletar(tarefaSelecionada)){
                    carregarListaTarefas();

                    Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                }
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }

        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }


        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        tarefaDAO.fechar();
    }

    // Método para apagar tarefas que possuem desafio ativo
    private void showDeleteDesafio(int position) {
        tarefaSelecionada = listaTarefa.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Excluir Item");
        builder.setMessage("Parabéns! Você concluiu um desafio. Deseja excluir esta tarefa ?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                if (tarefaDAO.deletar(tarefaSelecionada)){
                    carregarListaTarefas();

                    Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                }
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }

        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }


        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        tarefaDAO.fechar();
    }











}