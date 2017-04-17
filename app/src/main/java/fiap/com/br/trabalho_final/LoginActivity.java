package fiap.com.br.trabalho_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fiap.com.br.trabalho_final.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private final String LOGIN_DEFAULT = "android";
    private final String SENHA_DEFAULT = "mobile";
    public static final String KEY_APP_PREFERENCES = "login";
    public static final String KEY_LOGIN = "login";
    private TextInputLayout tilLogin;
    private TextInputLayout tilSenha;
    private CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilLogin = (TextInputLayout) findViewById(R.id.tilLogin);
        tilSenha = (TextInputLayout) findViewById(R.id.tilSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);

        if (isConectado()) {
            iniciarApp();
        }

    }

    public void logar(View v) {

        if (isLoginValido()) {
            if (cbManterConectado.isChecked()) {
                manterConectado();
            }
            iniciarApp();

        } else {
            Toast.makeText(this, "Usuario ou senha invalido", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if (login.equals(""))
            return false;
        else
            return true;
    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean isLoginValido() {
        String login = tilLogin.getEditText().getText().toString();
        String senha = tilSenha.getEditText().getText().toString();
        if (login.equals(LOGIN_DEFAULT)
                && senha.equals(SENHA_DEFAULT)) {
            return true;
        } else
            return false;
    }

    private void manterConectado() {
        String login = tilLogin.getEditText().getText().toString();
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }

    private class BuscaDados extends AsyncTask<String, Void, String> {

        ProgressDialog pdLoading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading = new ProgressDialog(LoginActivity.this);
            pdLoading.setMessage("Carregando os Dados");
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);

                conn.setRequestMethod("GET");

                conn.setDoOutput(true);

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream is = conn.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(is));

                    StringBuilder result = new StringBuilder();

                    String linha;

                    while ((linha = buffer.readLine()) != null) {
                        result.append(linha);
                    }
                    conn.disconnect();
                    return result.toString();

                }

            } catch (MalformedURLException e) {

            } catch (IOException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);

            if (s == null) {

                Toast.makeText(LoginActivity.this, "Deu ruim!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray jsonArray = json.getJSONArray("android");

                    List<Usuario> lista = new ArrayList<>();

                    for(int i=0; i< jsonArray.length(); i++) {

                        JSONObject data = jsonArray.getJSONObject(i);

                        Usuario usuario = new Usuario();

                        usuario.setLogin(data.getString("usuario"));
                        usuario.setSenha(data.getString("senha"));


                        lista.add(usuario);

                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            pdLoading.dismiss();
        }
    }



}
