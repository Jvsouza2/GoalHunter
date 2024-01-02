package jvsouza.com.juntando.activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Random;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.MyGifView;
import jvsouza.com.juntando.helper.TarefaDAO;
import jvsouza.com.juntando.model.Tarefas;

public class Formulario extends AppCompatActivity {

    TextInputEditText textNome, textDescricao;
    RadioGroup radioGroup;
    Button botao;
    ImageButton botaoCalendario;
    TextView textView, textPrazo, textDia, textMes, textHora, textMinuto;
    TextInputLayout textLayout, textLayout2;
    RadioButton radio1, radio2, radio3;
    private Calendar calendar;
    private MyGifView gifView;

    private Tarefas tarefaAtual;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int selectedDay, selectedMonth, selectedHour, selectedMinute;


    //private int contador;

    //private ProgressoDAO progressoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        textNome = findViewById(R.id.editNome);
        textDescricao = findViewById(R.id.editDescricao);
        radioGroup = findViewById(R.id.radioGroup);
        botao = findViewById(R.id.buttonEnviar);
        textView = findViewById(R.id.textView);
        textLayout = findViewById(R.id.textInputLayout);
        textLayout2 = findViewById(R.id.textInputLayout2);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        textPrazo = findViewById(R.id.textView4);
        textDia = findViewById(R.id.textDia);
        textMes = findViewById(R.id.textMes);
        textHora = findViewById(R.id.textHora);
        textMinuto = findViewById(R.id.textMinuto);

        View backgroundView = findViewById(R.id.ConstraintView2);
        int newColor = getResources().getColor(R.color.light); // Substitua pela nova cor desejada
        backgroundView.setBackgroundColor(newColor);



        botaoCalendario = findViewById(R.id.botaoCalendario);
        calendar = Calendar.getInstance();
        gifView = findViewById(R.id.gifView);



        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefas) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar caixa de texto
        if (tarefaAtual != null) {
            textNome.setText(tarefaAtual.getNomeTarefa());
            textDescricao.setText(tarefaAtual.getDescricaoTarefa());

        }


        // Obtém a instância da ActionBar
        ActionBar actionBar = getSupportActionBar();
        gifView.setAlpha(0.0f);


        // Alterar a cor de background da View desejada aqui
        try {
            Banco banco = new Banco(getApplicationContext());


            int botaoAtual = banco.getRes();

                if (botaoAtual == 2) {
                    int newColorA = getResources().getColor(R.color.ColorA); // Substitua pela nova cor desejada
                    backgroundView.setBackgroundColor(newColorA);
                    botao.setBackgroundColor(getResources().getColor(R.color.ColorB));
                    botao.setTextColor(getResources().getColor(R.color.white));
                    botaoCalendario.setImageResource(R.drawable.calendariobranco);
                    botaoCalendario.setBackgroundColor(getResources().getColor(R.color.ColorB));


                    // Verifica se a ActionBar não é nula
                    if (actionBar != null) {
                        // Define a cor de fundo da ActionBar
                        int minhaCor = getResources().getColor(R.color.ColorB);
                        actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                    }

                    textNome.setTextColor(getResources().getColor(R.color.white));
                    textDescricao.setTextColor(getResources().getColor(R.color.white));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textPrazo.setTextColor(getResources().getColor(R.color.white));
                    textDia.setTextColor(getResources().getColor(R.color.white));
                    textMes.setTextColor(getResources().getColor(R.color.white));
                    textHora.setTextColor(getResources().getColor(R.color.white));
                    textMinuto.setTextColor(getResources().getColor(R.color.white));

                    textLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));


                    // Criar um ColorStateList para definir as cores do botão nos diferentes estados
                    int[][] states = new int[][]{
                            new int[]{android.R.attr.state_checked}, // Estado quando selecionado
                            new int[]{-android.R.attr.state_checked} // Estado quando não selecionado
                    };

                    int[] colors = new int[]{
                            getResources().getColor(R.color.ColorB), // Cor quando selecionado
                            getResources().getColor(R.color.white) // Cor quando não selecionado
                    };

                    ColorStateList colorStateList = new ColorStateList(states, colors);

                    radio1.setTextColor(getResources().getColor(R.color.white));
                    radio2.setTextColor(getResources().getColor(R.color.white));
                    radio3.setTextColor(getResources().getColor(R.color.white));


                } else if (botaoAtual == 4) {

                    gifView.setAlpha(1f);

                    // Verifica se a ActionBar não é nula
                    if (actionBar != null) {
                        // Define a cor de fundo da ActionBar
                        int minhaCor = getResources().getColor(R.color.ColorD);
                        actionBar.setBackgroundDrawable(new ColorDrawable(minhaCor));
                        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>GoalHunter</font>"));

                    }
                    botao.setBackgroundColor(getResources().getColor(R.color.ColorC));
                    botao.setTextColor(getResources().getColor(R.color.white));
                    botaoCalendario.setBackgroundColor(getResources().getColor(R.color.ColorD));
                    botaoCalendario.setImageResource(R.drawable.calendariobranco);

                    textNome.setTextColor(getResources().getColor(R.color.white));
                    textDescricao.setTextColor(getResources().getColor(R.color.white));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textPrazo.setTextColor(getResources().getColor(R.color.white));
                    textDia.setTextColor(getResources().getColor(R.color.white));
                    textMes.setTextColor(getResources().getColor(R.color.white));
                    textHora.setTextColor(getResources().getColor(R.color.white));
                    textMinuto.setTextColor(getResources().getColor(R.color.white));

                    textLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));


                    // Criar um ColorStateList para definir as cores do botão nos diferentes estados
                    int[][] states = new int[][]{
                            new int[]{android.R.attr.state_checked}, // Estado quando selecionado
                            new int[]{-android.R.attr.state_checked} // Estado quando não selecionado
                    };

                    int[] colors = new int[]{
                            getResources().getColor(R.color.ColorC), // Cor quando selecionado
                            getResources().getColor(R.color.white) // Cor quando não selecionado
                    };

                    ColorStateList colorStateList = new ColorStateList(states, colors);

                    radio1.setTextColor(getResources().getColor(R.color.white));
                    radio2.setTextColor(getResources().getColor(R.color.white));
                    radio3.setTextColor(getResources().getColor(R.color.white));


                } else if (botaoAtual == 6) {
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

                    botao.setBackgroundColor(Color.TRANSPARENT);
                    botao.setTextSize(30);
                    //botao.setBackgroundResource(R.drawable.back2);
                    botao.setTextColor(getResources().getColor(R.color.white));
                    //botaoCalendario.setBackgroundResource(R.drawable.back2);
                    botaoCalendario.setBackgroundColor(Color.TRANSPARENT);
                    botaoCalendario.setImageResource(R.drawable.calendariobranco);

                    textNome.setTextColor(getResources().getColor(R.color.white));
                    textDescricao.setTextColor(getResources().getColor(R.color.white));
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textPrazo.setTextColor(getResources().getColor(R.color.white));
                    textDia.setTextColor(getResources().getColor(R.color.white));
                    textMes.setTextColor(getResources().getColor(R.color.white));
                    textHora.setTextColor(getResources().getColor(R.color.white));
                    textMinuto.setTextColor(getResources().getColor(R.color.white));

                    textLayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    textLayout2.setDefaultHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));


                    // Criar um ColorStateList para definir as cores do botão nos diferentes estados
                    int[][] states = new int[][]{
                            new int[]{android.R.attr.state_checked}, // Estado quando selecionado
                            new int[]{-android.R.attr.state_checked} // Estado quando não selecionado
                    };

                    int[] colors = new int[]{
                            getResources().getColor(R.color.Colorf), // Cor quando selecionado
                            getResources().getColor(R.color.Colorg) // Cor quando não selecionado
                    };

                    ColorStateList colorStateList = new ColorStateList(states, colors);

                    radio1.setTextColor(getResources().getColor(R.color.white));
                    radio2.setTextColor(getResources().getColor(R.color.white));
                    radio3.setTextColor(getResources().getColor(R.color.white));
                }else {

                    backgroundView.setBackgroundColor(newColor);


                }



        banco.close();
        } catch (Exception e) {
            Log.i("Erro", "Erro ao exibir botão");
        }


        botaoCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();


            }
        });


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    String textDificuldade = "";
                    boolean radioButtonSelected = false; // Variável para controlar se um RadioButton foi selecionado

                    // Determinar a dificuldade com base no RadioButton selecionado
                    switch (checkedId) {
                        case R.id.radio1:
                            textDificuldade = "Fácil";
                            radioButtonSelected = true; // Um RadioButton foi selecionado
                            break;
                        case R.id.radio2:
                            textDificuldade = "Médio";
                            radioButtonSelected = true; // Um RadioButton foi selecionado
                            break;
                        case R.id.radio3:
                            textDificuldade = "Difícil";
                            radioButtonSelected = true; // Um RadioButton foi selecionado
                            break;
                    }

                    String txtNome = textNome.getText().toString();
                    if (txtNome.isEmpty() || selectedDay <= 0 || !radioButtonSelected){
                        Toast.makeText(getApplicationContext(), "Você não selecionou algum recurso necessário para a criação da tarefa.", Toast.LENGTH_SHORT).show();
                    }else{


                    Random random = new Random();

                    // Gere um número aleatório de 1 a 10
                    int numeroAleatorio = random.nextInt(4) + 1;

                    if (numeroAleatorio == 1) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Formulario.this);
                        dialog.setTitle("Desafio");
                        dialog.setMessage("Aceita o desafio? Terminar a tarefa em menos de 15 minutos - ganhe um nível");

                        dialog.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Calendar calendar = Calendar.getInstance();

                                int minuto = calendar.get(Calendar.MINUTE); // Minutos atuais (0-59)
                                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                                saveDataToDatabase(minuto, hora, 1);

                            }
                        });

                        dialog.setNegativeButton("Recusar", null);

                        dialog.show();
                    } else if (numeroAleatorio == 3 ) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Formulario.this);
                        dialog.setTitle("Desafio");
                        dialog.setMessage("Aceita o desafio? Terminar a tarefa em menos de 3 horas - ganhe o dobro de pontos");

                        dialog.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Calendar calendar = Calendar.getInstance();

                                int minuto = calendar.get(Calendar.MINUTE); // Minutos atuais (0-59)
                                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                                saveDataToDatabase(minuto, hora, 2);
                            }
                        });

                        dialog.setNegativeButton("Recusar", null);

                        dialog.show();

                    }

                     else {
                        Calendar calendar = Calendar.getInstance();

                        int minuto = calendar.get(Calendar.MINUTE); // Minutos atuais (0-59)
                        int hora = calendar.get(Calendar.HOUR_OF_DAY);
                        saveDataToDatabase(minuto, hora, 0);

                    }
                    }
                }catch(Exception e){

                }





            }
        });



    }



    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Hora atual
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);  // Dia do mês (1-31)
        int mes = calendar.get(Calendar.MONTH);// Mês (0-11)

        // Atualize as variáveis globais quando a data for selecionada
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Verifique se a data selecionada está no passado
                        if (year < calendar.get(Calendar.YEAR) || (year == calendar.get(Calendar.YEAR) && month < calendar.get(Calendar.MONTH)) || (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) && dayOfMonth < calendar.get(Calendar.DAY_OF_MONTH))) {
                            // Data no passado, exiba um AlertDialog informativo
                            new AlertDialog.Builder(Formulario.this)
                                    .setTitle("Data no Passado")
                                    .setMessage("A data selecionada já passou. Por favor, selecione uma data futura.")
                                    .setPositiveButton("OK", null)
                                    .show();


                        } else {
                            // Data válida, atualize as variáveis globais com a data selecionada
                            selectedDay = dayOfMonth;
                            selectedMonth = month;
                            textMes.setText(String.valueOf(month + 1));
                            textDia.setText(String.valueOf(dayOfMonth));
                            showTimePickerDialog();
                        }
                    }
                }, year, month, day);


        // Exiba o DatePickerDialog
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // Obtenha a hora atual
        int minute = calendar.get(Calendar.MINUTE); // Obtenha o minuto atual
        int dia = calendar.get(Calendar.DAY_OF_MONTH);  // Dia do mês (1-31)

        // Atualize as variáveis globais quando a hora for selecionada
        timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Crie um objeto Calendar para a data e hora atual
                        Calendar currentDateTime = Calendar.getInstance();

                        // Crie um objeto Calendar para a data e hora selecionadas
                        Calendar selectedDateTime = Calendar.getInstance();
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);

                        // Verifique se a data selecionada é igual ao dia atual
                        if (dia == selectedDay) {
                            int horaAtual = hour;
                            // Verifique se a hora selecionada já passou
                            if (hourOfDay < horaAtual) {

                                // Hora no passado no mesmo dia, exiba um AlertDialog informativo
                                new AlertDialog.Builder(Formulario.this)
                                        .setTitle("Hora no Passado")
                                        .setMessage("A hora selecionada já passou no mesmo dia. Por favor, selecione uma hora futura.")
                                        .setPositiveButton("OK", null)
                                        .show();
                            } else {
                                // Hora válida, atualize as variáveis globais com a hora selecionada
                                selectedHour = hourOfDay;
                                selectedMinute = minute;
                                textHora.setText(String.valueOf(hourOfDay));
                                textMinuto.setText(String.valueOf(minute));
                            }
                        } else {
                            // Dia futuro, hora válida
                            selectedHour = hourOfDay;
                            selectedMinute = minute;
                            textHora.setText(String.valueOf(hourOfDay));
                            textMinuto.setText(String.valueOf(minute));
                        }
                    }
                }, hour, minute, true);

        // Exiba o TimePickerDialog
        timePickerDialog.show();
    }






    private void saveDataToDatabase(int minuto,int hora, int desafio) {

        int checkedId = radioGroup.getCheckedRadioButtonId();
        String textDificuldade = "";
        boolean radioButtonSelected = false; // Variável para controlar se um RadioButton foi selecionado

        // Determinar a dificuldade com base no RadioButton selecionado
        switch (checkedId) {
            case R.id.radio1:
                textDificuldade = "Fácil";
                radioButtonSelected = true; // Um RadioButton foi selecionado
                break;
            case R.id.radio2:
                textDificuldade = "Médio";
                radioButtonSelected = true; // Um RadioButton foi selecionado
                break;
            case R.id.radio3:
                textDificuldade = "Difícil";
                radioButtonSelected = true; // Um RadioButton foi selecionado
                break;
        }

        String txtNome = textNome.getText().toString();
        String txtDescricao = textDescricao.getText().toString();


        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        if (tarefaAtual != null) { //edição


            if (!txtNome.isEmpty() && selectedDay != -1 && radioButtonSelected) {
                Tarefas tarefa = new Tarefas();
                tarefa.setNomeTarefa(txtNome);
                tarefa.setDescricaoTarefa(txtDescricao);
                tarefa.setDificuldadeTarefa(textDificuldade);
                tarefa.setChecado(0);
                tarefa.setDia(selectedDay);
                tarefa.setMes(selectedMonth);
                tarefa.setHora(selectedHour);
                tarefa.setMinuto(selectedMinute);
                tarefa.setHcria(hora);
                tarefa.setMcria(minuto);
                tarefa.setDesafio(desafio);

                //atualizar banco de dados
                if (tarefaDAO.atualizar(tarefa)) {

                    finish();
                    Toast.makeText(Formulario.this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show();


                }

            } else {

                Toast.makeText(Formulario.this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();

            }
        } else {//salvar


            if (!txtNome.isEmpty() && selectedDay != -1 && radioButtonSelected) {
                Tarefas tarefa = new Tarefas();
                tarefa.setNomeTarefa(txtNome);
                tarefa.setDescricaoTarefa(txtDescricao);
                tarefa.setDificuldadeTarefa(textDificuldade);
                tarefa.setChecado(0);
                tarefa.setDia(selectedDay);
                tarefa.setMes(selectedMonth);
                tarefa.setHora(selectedHour);
                tarefa.setMinuto(selectedMinute);

                tarefa.setHcria(hora);
                tarefa.setMcria(minuto);
                tarefa.setDesafio(desafio);

                if (tarefaDAO.salvar(tarefa)) {

                    finish();
                    Toast.makeText(Formulario.this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Formulario.this, "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
                }


            }

        }


        finish();


    }


}



