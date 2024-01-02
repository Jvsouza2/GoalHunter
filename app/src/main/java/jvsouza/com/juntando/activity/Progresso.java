package jvsouza.com.juntando.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.adapter.AdapterConquista;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.ConquistaDAO;
import jvsouza.com.juntando.helper.MyGifView;
import jvsouza.com.juntando.helper.ProgressoDAO;
import jvsouza.com.juntando.model.Conquistas;

public class Progresso extends AppCompatActivity {

    private TextView textXP, textLevel;
    private ProgressBar progressBar;

    private ProgressoDAO progressoDAO;
    private List<Conquistas> listaConquista = new ArrayList<>();
    private RecyclerView recyclerConquista;
    private AdapterConquista adapterConquista;
    private MyGifView gifView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);


        textXP = findViewById(R.id.textXP);
        textLevel = findViewById(R.id.textLevel);
        progressBar = findViewById(R.id.progressBar);
        progressoDAO = new ProgressoDAO(this);
        recyclerConquista = findViewById(R.id.recyclerConquista);
        gifView = findViewById(R.id.gifView);
        View view = findViewById(R.id.view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        View backgroundView = findViewById(R.id.ConstraintView3);
        int newColor = getResources().getColor(R.color.light); // Substitua pela nova cor desejada
        backgroundView.setBackgroundColor(newColor);



        // Create the adapter with the initial list of conquests
        adapterConquista = new AdapterConquista(listaConquista);
        // Set the adapter to the RecyclerView
        recyclerConquista.setAdapter(adapterConquista);
        gifView.setAlpha(0.0f);
        Banco banco = new Banco(getApplicationContext());
        try{


        if (progressoDAO.getContador() ==1) {
            progressBar.setProgress(0);
        }
        }catch (Exception e){

        }


        // Obtém a instância da ActionBar
        ActionBar actionBar = getSupportActionBar();

        bottomNavigationView.setSelectedItemId(R.id.person);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intentCasa = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentCasa);
                        return true;

                    case R.id.person:
                        Intent intentConquista = new Intent(getApplicationContext(), Progresso.class);
                        startActivity(intentConquista);
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

        // Altere a cor de background da View desejada aqui
        try{



            int botaoAtual = banco.getRes();



                if(botaoAtual == 2) {
                    int newColorA = getResources().getColor(R.color.ColorA); // Substitua pela nova cor desejada
                    backgroundView.setBackgroundColor(newColorA);


                    // Verifica se a ActionBar não é nula
                    if (actionBar != null) {
                        // Define a cor de fundo da ActionBar
                        int minhaCor = getResources().getColor(R.color.ColorB);
                        actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));


                    }
                    int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                    view.setBackgroundColor(Branco);


                    textXP.setTextColor(getResources().getColor(R.color.white));
                    textLevel.setTextColor(getResources().getColor(R.color.white));
                    progressBar.setBackgroundColor(getResources().getColor(R.color.ColorA));

                    int minhaCorProgresso = ContextCompat.getColor(this, R.color.ColorB); // Substitua pela cor desejada
                    Drawable drawable = progressBar.getProgressDrawable();


                    Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);

                    // Use o DividerItemDecoration padrão e aplique o Drawable criado
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    itemDecoration.setDrawable(dividerDrawable);
                    recyclerConquista.addItemDecoration(itemDecoration);
                    adapterConquista = new AdapterConquista(getApplicationContext(), listaConquista);
                    recyclerConquista.setAdapter(adapterConquista);

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

                }else if (botaoAtual == 4) {

                    gifView.setAlpha(1f);
                    int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                    view.setBackgroundColor(Branco);


                    // Verifica se a ActionBar não é nula
                    if (actionBar != null) {
                        // Define a cor de fundo da ActionBar
                        int minhaCor = getResources().getColor(R.color.ColorD);
                        actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));


                    }


                    textXP.setTextColor(getResources().getColor(R.color.white));
                    textLevel.setTextColor(getResources().getColor(R.color.white));
                    progressBar.setBackgroundColor(getResources().getColor(R.color.ColorD));

                    int minhaCorProgresso = ContextCompat.getColor(this, R.color.ColorC); // Substitua pela cor desejada
                    Drawable drawable = progressBar.getProgressDrawable();


                    Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);

                    // Use o DividerItemDecoration padrão e aplique o Drawable criado
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    itemDecoration.setDrawable(dividerDrawable);
                    recyclerConquista.addItemDecoration(itemDecoration);
                    adapterConquista = new AdapterConquista(getApplicationContext(), listaConquista);
                    recyclerConquista.setAdapter(adapterConquista);

                    int Verde = getResources().getColor(R.color.ColorD); // Substitua pela nova cor desejada
                    bottomNavigationView.setBackgroundColor(Verde);
                    bottomNavigationView.setAlpha(1f);




                    int minhaCorMarcado = getResources().getColor(R.color.ColorC);
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

                }else if(botaoAtual == 6){
                    gifView.setAlpha(0f);


                    try {
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
                        // Cores do gradiente que você deseja adicionar (adicionar mais cores conforme necessário)
                        int[] colors = {Color.parseColor("#a8dadc"), Color.parseColor("#e63946"), Color.parseColor("#1d3557")};

                        // Crie um Drawable GradientDrawable para o fundo da ActionBar
                        GradientDrawable gradientDrawable = new GradientDrawable(
                                GradientDrawable.Orientation.TOP_BOTTOM, // Defina a orientação do gradiente
                                colors // Array de cores do gradiente
                        );

                        // Ajuste o canto arredondado se desejar
                        gradientDrawable.setCornerRadius(0); // Valor em pixels

                        // Crie um StateListDrawable para lidar com os estados da ActionBar
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, gradientDrawable); // Quando pressionado
                        stateListDrawable.addState(new int[]{}, gradientDrawable); // Estado padrão

                        // Crie um LayerDrawable para adicionar uma borda à ActionBar (opcional)
                        Drawable[] layers = new Drawable[2];
                        layers[0] = stateListDrawable;

                        LayerDrawable layerDrawable = new LayerDrawable(layers);

                        // Defina o plano de fundo personalizado da ActionBar
                        getSupportActionBar().setBackgroundDrawable(layerDrawable);






                    }

                    textXP.setTextColor(getResources().getColor(R.color.white));
                    textLevel.setTextColor(getResources().getColor(R.color.white));
                    progressBar.setBackgroundColor(Color.TRANSPARENT);


                    int minhaCorProgresso = ContextCompat.getColor(this, R.color.white); // Substitua pela cor desejada
                    Drawable drawable = progressBar.getProgressDrawable();


                    Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_color);

                    // Use o DividerItemDecoration padrão e aplique o Drawable criado
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    itemDecoration.setDrawable(dividerDrawable);
                    recyclerConquista.addItemDecoration(itemDecoration);
                    adapterConquista = new AdapterConquista(getApplicationContext(), listaConquista);
                    recyclerConquista.setAdapter(adapterConquista);


                    int minhaCorMarcado = getResources().getColor(R.color.Colorg);
                    int minhaCorDesmarcado = getResources().getColor(R.color.Colorf);

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

                }else {
                    // Substitua pela nova cor desejada
                    backgroundView.setBackgroundColor(newColor);
                    int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                    int Preto = getResources().getColor(R.color.black); // Substitua pela nova cor desejada
                    bottomNavigationView.setBackgroundColor(Branco);
                    view.setBackgroundColor(Preto);

                }


            Log.i("MinhaTag", "Esta é uma mensagem informativa.");
        }catch(Exception e){
            Log.i("Erro", "Erro ao exibir botão");
        }



        // Abrir o banco de dados
        progressoDAO.abrir();

        int contador = progressoDAO.getContador();
        int level = progressoDAO.getLevel();
        int maximo = progressoDAO.getMaximo();


        int lvl = level;
        int max = maximo;
        progressBar.setMax(maximo);
        if (contador >= maximo) {
            progressoDAO.setContador(1);
            progressBar.setProgress(0);


            max +=10;
            progressoDAO.setMaximo(max);

            lvl++;
            progressoDAO.setLevel(lvl);


        }else if (contador <0){
            contador = 1;

        }else {

            progressBar.setProgress(contador);
        }








        progressoDAO.fechar();

        criarConquista(level);

        textLevel.setText(String.valueOf(level));
        textXP.setText(String.valueOf(contador));
        banco.close();


    }

    public void carregarListaConquistas(){

        ConquistaDAO conquistaDAO = new ConquistaDAO(getApplicationContext());
        listaConquista = conquistaDAO.listar();

        // Adapter
        AdapterConquista adapter = new AdapterConquista(getApplicationContext(),listaConquista);

        //Configuração do recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerConquista.setLayoutManager(layoutManager);
        recyclerConquista.setHasFixedSize(true);
        recyclerConquista.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerConquista.setAdapter(adapter);
        conquistaDAO.fechar();

    }

    @Override
    protected void onStart() {
        carregarListaConquistas();
        super.onStart();
    }

    public void criarConquista(int level) {
        ConquistaDAO conquistaDAO = new ConquistaDAO(getApplicationContext());

        if (level >= 0) {
            // Verifica se a conquista já existe no banco de dados
            boolean conquistaExiste = conquistaDAO.conquistaExiste("Primeira Conquista");

            if (!conquistaExiste) {
                Conquistas conquista = new Conquistas();
                conquista.setNomeConquista("Primeira Conquista");
                conquista.setDescricaoConquista("Baixou e instalou o APP");

                conquistaDAO.salvar(conquista);
                this.listaConquista.add(conquista);
            }
        }

        if (level >= 1) {
            // Verifica se a conquista já existe no banco de dados
            boolean conquistaExiste = conquistaDAO.conquistaExiste("Nível 1");

            if (!conquistaExiste) {
                Conquistas conquista = new Conquistas();
                conquista.setNomeConquista("Nível 1");
                conquista.setDescricaoConquista("Parabéns, você atingiu o primeiro nível!");
                conquista.setNivelConquista(1);

                conquistaDAO.salvar(conquista);
                this.listaConquista.add(conquista);
            }
        }

        if (level >= 2) {
            // Verifica se a conquista já existe no banco de dados
            boolean conquistaExiste = conquistaDAO.conquistaExiste("Nível 2");

            if (!conquistaExiste) {
                Conquistas conquista = new Conquistas();
                conquista.setNomeConquista("Nível 2");
                conquista.setDescricaoConquista("Parabéns, você atingiu o segundo nível!");
                conquista.setNivelConquista(2);


                conquistaDAO.salvar(conquista);
                this.listaConquista.add(conquista);
            }
        }

        if (level >= 5) {
            // Verifica se a conquista já existe no banco de dados
            boolean conquistaExiste = conquistaDAO.conquistaExiste("Nível 5");

            if (!conquistaExiste) {
                Conquistas conquista = new Conquistas();
                conquista.setNomeConquista("Nível 5");
                conquista.setDescricaoConquista("Parabéns, você atingiu o nível 5");
                conquista.setNivelConquista(5);

                conquistaDAO.salvar(conquista);
                this.listaConquista.add(conquista);
            }
        }
        if (level >= 10) {
            // Verifica se a conquista já existe no banco de dados
            boolean conquistaExiste = conquistaDAO.conquistaExiste("Nível 10");

            if (!conquistaExiste) {
                Conquistas conquista = new Conquistas();
                conquista.setNomeConquista("Nível 10");
                conquista.setDescricaoConquista("Eu ainda não desenvolvi essa parte.");
                conquista.setNivelConquista(10);

                conquistaDAO.salvar(conquista);
                this.listaConquista.add(conquista);
            }
        }

        // Notifique o adapter somente após adicionar todas as conquistas relevantes
        adapterConquista.notifyDataSetChanged();
        conquistaDAO.fechar();
    }


}