package fiap.com.br.trabalho_final.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fiap.com.br.trabalho_final.R;

/**
 * Created by smj2081 on 16/03/2017.
 */

public class CarroItemHolder extends RecyclerView.ViewHolder {

    public TextView tvNome;
    public TextView tvMarca;
    public TextView tvCor;
    public TextView tvAno;
    public TextView tvValor;

    public CarroItemHolder(View itemView) {
        super(itemView);

        tvNome = (TextView) itemView.findViewById(R.id.tvNome);
        tvMarca = (TextView) itemView.findViewById(R.id.tvMarca);
        tvCor = (TextView) itemView.findViewById(R.id.tvCor);
        tvAno = (TextView) itemView.findViewById(R.id.tvAno);
        tvValor = (TextView) itemView.findViewById(R.id.tvValor);
    }
}
