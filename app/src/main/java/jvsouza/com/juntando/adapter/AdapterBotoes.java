package jvsouza.com.juntando.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.activity.MainActivity;
import jvsouza.com.juntando.helper.Banco;

public class AdapterBotoes extends RecyclerView.Adapter<AdapterBotoes.MyViewHolder> {

    private Context context;

    private List imageList;









    public AdapterBotoes(Context context, List imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layoutbotoes, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Banco banco = new Banco(context);

        // Defina a cor da linha de divisão para o item atual


        holder.imageView.setImageResource((Integer) imageList.get(position));
        int posicaoLista = position;
        banco.inserirNumero(posicaoLista);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (posicaoLista ==  1 || posicaoLista ==  3 || posicaoLista == 5 || posicaoLista == 7 || posicaoLista == 9){
                    banco.atualizarNumero(posicaoLista);

                }else if(posicaoLista ==  0){
                    banco.atualizarNumero(posicaoLista);
                    banco.atualizarRes(posicaoLista);
                }else{
                    banco.atualizarRes(posicaoLista);
                }







                //Toast.makeText(context, String.valueOf(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line to set the flag
                context.startActivity(intent);






            }
        });




    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imageView);
        }
    }



}
