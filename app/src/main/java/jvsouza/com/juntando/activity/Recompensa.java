package jvsouza.com.juntando.activity;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.adapter.Adapter;
import jvsouza.com.juntando.adapter.AdapterBotoes;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.MyGifView;
import jvsouza.com.juntando.helper.ProgressoDAO;

public class Recompensa extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List listona = new ArrayList<>();

    TextView textView1, textView2;
    private MyGifView gifView;

    private  AdapterBotoes adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompensas);


        recyclerView = findViewById(R.id.recyclerBotao);
        textView1 = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView3);
        gifView = findViewById(R.id.gifView);
        gifView.setAlpha(0.0f);
        View view = findViewById(R.id.view);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // Obtém a instância da ActionBar
        ActionBar actionBar = getSupportActionBar();

        bottomNavigationView.setSelectedItemId(R.id.recompensas);
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
            Banco banco = new Banco(getApplicationContext());
            View backgroundView = findViewById(R.id.ConstraintView1); // Substitua pelo ID da sua View

            int botaoAtual = banco.getRes();


            if(botaoAtual == 2) {
                int newColor = getResources().getColor(R.color.ColorA); // Substitua pela nova cor desejada
                backgroundView.setBackgroundColor(newColor);


                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                view.setBackgroundColor(Branco);

                // Verifica se a ActionBar não é nula
                if (actionBar != null) {
                    // Define a cor de fundo da ActionBar
                    int minhaCor = getResources().getColor(R.color.ColorB);
                    actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                    actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                }

                // Código para mudar a cor das linhas
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                Drawable verticalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.vertical_divider);
                verticalDecoration.setDrawable(verticalDivider);
                recyclerView.addItemDecoration(verticalDecoration);

                DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                Drawable horizontalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.horizontal_divider);
                horizontalDecoration.setDrawable(horizontalDivider);
                recyclerView.addItemDecoration(horizontalDecoration);


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
                int cinza = getResources().getColor(R.color.ColorA); // Substitua pela nova cor desejada
                bottomNavigationView.setBackgroundColor(cinza);


            }else if (botaoAtual == 4) {

                gifView.setAlpha(1f);

                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                view.setBackgroundColor(Branco);

                // Verifica se a ActionBar não é nula
                if (actionBar != null) {
                    // Define a cor de fundo da ActionBar
                    int minhaCor = getResources().getColor(R.color.ColorD);
                    actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                    actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                }
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



                // Código para mudar a cor das linhas
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                Drawable verticalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.vertical_divider);
                verticalDecoration.setDrawable(verticalDivider);
                recyclerView.addItemDecoration(verticalDecoration);

                DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                Drawable horizontalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.horizontal_divider);
                horizontalDecoration.setDrawable(horizontalDivider);
                recyclerView.addItemDecoration(horizontalDecoration);

            }else if(botaoAtual == 6){
                gifView.setAlpha(0f);

                textView1.setTextColor(getResources().getColor(R.color.white));
                textView2.setTextColor(getResources().getColor(R.color.white));
                AnimationDrawable animationDrawable = (AnimationDrawable) backgroundView.getBackground();
                animationDrawable.setEnterFadeDuration(2500);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();

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

                // Código para mudar a cor das linhas
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.HORIZONTAL);
                Drawable verticalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.vertical_divider);
                verticalDecoration.setDrawable(verticalDivider);
                recyclerView.addItemDecoration(verticalDecoration);

                DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                Drawable horizontalDivider = ContextCompat.getDrawable(getApplicationContext(), R.drawable.horizontal_divider);
                horizontalDecoration.setDrawable(horizontalDivider);
                recyclerView.addItemDecoration(horizontalDecoration);


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
                int newColor = getResources().getColor(R.color.light); // Substitua pela nova cor desejada
                backgroundView.setBackgroundColor(newColor);
                int Branco = getResources().getColor(R.color.white); // Substitua pela nova cor desejada
                int Preto = getResources().getColor(R.color.black); // Substitua pela nova cor desejada
                bottomNavigationView.setBackgroundColor(Branco);
                view.setBackgroundColor(Preto);
            }


        }catch(Exception e){
            Log.i("Erro", "Erro ao exibir botão");
        }



        try {
            ProgressoDAO progressoDAO = new ProgressoDAO(getApplicationContext());
            progressoDAO.abrir();
            int level = progressoDAO.getLevel();

            listona.clear(); // Limpa a lista para adicionar as figuras corretas

            Banco banco = new Banco(getApplicationContext());


            int botaoAtual = banco.getRes();

            int posicaoAdicao = 0; // Variável para armazenar a posição

            for (int i = 0; i <= level; i++) {
                // Adiciona as figuras correspondentes a cada nível
                switch (i) {
                    case 0:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.fbranco); // Adiciona na posição atual
                        } else {
                            listona.add(posicaoAdicao, R.drawable.f);
                        }
                        posicaoAdicao++; // Atualize a posição
                        break;
                    case 1:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.abranco);
                        } else {
                            listona.add(posicaoAdicao, R.drawable.a);
                        }
                        int c1 = R.drawable.c1;
                        listona.add(posicaoAdicao + 1, c1); // Adiciona na próxima posição
                        posicaoAdicao += 2; // Atualize a posição
                        break;
                    case 2:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.bbranco);
                        } else {
                            listona.add(posicaoAdicao, R.drawable.b);
                        }
                        int c2 = R.drawable.c2;
                        listona.add(posicaoAdicao + 1, c2); // Adiciona na próxima posição
                        posicaoAdicao += 2; // Atualize a posição
                        break;
                    case 3:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.cbranco);
                        } else {
                            listona.add(posicaoAdicao, R.drawable.c);
                        }
                        int c3 = R.drawable.c3;
                        listona.add(posicaoAdicao + 1, c3); // Adiciona na próxima posição
                        posicaoAdicao += 2; // Atualize a posição
                        break;
                    case 4:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.dbranco);
                        } else {
                            listona.add(posicaoAdicao, R.drawable.d);
                        }
                        int c4 = R.drawable.c4;
                        listona.add(posicaoAdicao + 1, c4); // Adiciona na próxima posição
                        posicaoAdicao += 2; // Atualize a posição
                        break;
                    case 5:
                        if (botaoAtual == 2 || botaoAtual == 4) {
                            listona.add(posicaoAdicao, R.drawable.ebranco);
                        } else {
                            listona.add(posicaoAdicao, R.drawable.e);
                        }
                        int c5 = R.drawable.c5;
                        listona.add(posicaoAdicao + 1, c5); // Adiciona na próxima posição
                        posicaoAdicao += 2; // Atualize a posição
                        break;

                }
            }



            progressoDAO.fechar();

        }catch(Exception e){
            Log.i("Erro", "Deu errado");
        }







        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new AdapterBotoes(getApplicationContext(),listona));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

















    }
}