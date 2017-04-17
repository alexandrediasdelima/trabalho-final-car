package fiap.com.br.trabalho_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import fiap.com.br.trabalho_final.adapter.ListaCarroAdapter;
import fiap.com.br.trabalho_final.dao.CarroDAO;
import fiap.com.br.trabalho_final.model.Carro;

public class ItemActivity extends AppCompatActivity {

    private RecyclerView rvLista;
    private ListaCarroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        rvLista = (RecyclerView) findViewById(R.id.rvLista);

        CarroDAO carroDAO = new CarroDAO(this);

        List<Carro> lista = carroDAO.getAll();
        setUpLista(lista);
    }

    private void setUpLista(List<Carro> lista) {

        adapter = new ListaCarroAdapter(this, lista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(adapter);

    }

    public void voltar(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
