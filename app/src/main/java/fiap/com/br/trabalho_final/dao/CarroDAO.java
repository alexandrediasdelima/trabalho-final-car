package fiap.com.br.trabalho_final.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import fiap.com.br.trabalho_final.model.Carro;

/**
 * Created by smj2081 on 15/03/2017.
 */

public class CarroDAO {

    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public CarroDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public String add(Carro carro) {
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", carro.getNome());
        values.put("marca", carro.getMarca());
        values.put("cor", carro.getCor());
        values.put("ano", carro.getAno());
        values.put("valor", carro.getValor());
        resultado = db.insert("carro",
                null,
                values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public List<Carro> getAll() {
        List<Carro> carros = new LinkedList<>();
        String rawQuery = "SELECT * FROM carro";

        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Carro carro = null;
        if (cursor.moveToFirst()) {
            do {
                carro = new Carro();
                carro.setNome(cursor.getString(1));
                carro.setMarca(cursor.getString(2));
                carro.setCor(cursor.getString(3));
                carro.setAno(cursor.getString(4));
                carro.setValor(cursor.getString(5));

                carros.add(carro);
            } while (cursor.moveToNext());
        }
        return carros;
    }


}
