package fiap.com.br.trabalho_final.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fiap.com.br.trabalho_final.R;
import fiap.com.br.trabalho_final.holder.CarroItemHolder;
import fiap.com.br.trabalho_final.model.Carro;

/**
 * Created by smj2081 on 16/03/2017.
 */

public class ListaCarroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Carro> carros;

    public ListaCarroAdapter(Context context, List<Carro> carros) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.carros = carros;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new CarroItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CarroItemHolder carroItemHolder = (CarroItemHolder) holder;

        carroItemHolder.tvNome.setText(carros.get(position).getNome());
        carroItemHolder.tvMarca.setText(carros.get(position).getMarca());
        carroItemHolder.tvCor.setText(carros.get(position).getCor());
        carroItemHolder.tvAno.setText(carros.get(position).getAno());
        carroItemHolder.tvValor.setText(carros.get(position).getValor());

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }
}
