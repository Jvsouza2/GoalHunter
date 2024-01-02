package jvsouza.com.juntando.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvsouza.com.juntando.R;
import jvsouza.com.juntando.helper.Banco;
import jvsouza.com.juntando.helper.ConquistaDAO;
import jvsouza.com.juntando.model.Conquistas;

public class AdapterConquista extends RecyclerView.Adapter<AdapterConquista.MyViewHolder> {

    private List<Conquistas> listaConquista;
    private Context context;

    public AdapterConquista(List<Conquistas> lista) {
        this.listaConquista = lista;
    }

    public AdapterConquista(Context context, List<Conquistas> listaConquista) {
        this.context = context;
        this.listaConquista = listaConquista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutprogresso, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ConquistaDAO conquistaDAO = new ConquistaDAO(context);

        Conquistas conquista = listaConquista.get(position);
        holder.nomeConquista.setText(conquista.getNomeConquista());
        holder.descricaoConquista.setText(conquista.getDescricaoConquista());


        Banco banco = new Banco(context);
        int botaoAtual = banco.getRes();

            if(botaoAtual == 2) {

                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);

                holder.nomeConquista.setTextColor(minhaCor);
                holder.descricaoConquista.setTextColor(minhaCor);

                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorB);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.white);



            }else if (botaoAtual == 4) {

                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);

                holder.nomeConquista.setTextColor(minhaCor);
                holder.descricaoConquista.setTextColor(minhaCor);

                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.ColorD);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.white);

            }else if(botaoAtual == 6){



                int minhaCor = holder.itemView.getContext().getResources().getColor(R.color.white);

                holder.nomeConquista.setTextColor(minhaCor);
                holder.descricaoConquista.setTextColor(minhaCor);

                int minhaCorMarcado = holder.itemView.getContext().getResources().getColor(R.color.Colorf);
                int minhaCorDesmarcado = holder.itemView.getContext().getResources().getColor(R.color.white);
            }

        if (conquista.getNivelConquista() >=2 && conquista.getNivelConquista() <5){
            if(botaoAtual == 4){
                holder.imageView.setImageResource(R.drawable.star3branco);
            }else{
                holder.imageView.setImageResource(R.drawable.star2);
            }

        }else if(conquista.getNivelConquista() >=5 && conquista.getNivelConquista() <10){
            if(botaoAtual == 4){
                holder.imageView.setImageResource(R.drawable.star2branco);
            }else{
                holder.imageView.setImageResource(R.drawable.star3);
            }

        }else if(conquista.getNivelConquista() >=10){
            holder.imageView.setImageResource(R.drawable.star4);
        }else if(conquista.getNivelConquista() <= 1 ) {
            if (botaoAtual == 4) {
                holder.imageView.setImageResource(R.drawable.star1branco);
            } else {
                holder.imageView.setImageResource(R.drawable.star2);
            }
        }
        banco.close();
        conquistaDAO.fechar();



    }

    @Override
    public int getItemCount() {
        return listaConquista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeConquista, descricaoConquista;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeConquista = itemView.findViewById(R.id.textConquista);
            descricaoConquista = itemView.findViewById(R.id.textDescri);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
