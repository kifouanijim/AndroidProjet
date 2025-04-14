package com.example.myapplication.model; // ou .ui si tu préfères séparer

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.myapplication.R;
import com.example.myapplication.utils.App;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PageAjoutClient extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNom, txtEmail, txtTelephone, txtStatut, txtNote;
    private Button btEnregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_client);

        txtNom = findViewById(R.id.txtNom);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtStatut = findViewById(R.id.txtStatut);
        txtNote = findViewById(R.id.txtNote);
        btEnregistrer = findViewById(R.id.btEnregistrer);

        btEnregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Vérification simple des champs
        if (txtNom.getText().toString().isEmpty() ||
                txtEmail.getText().toString().isEmpty() ||
                txtTelephone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez remplir les champs obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        Client client = new Client(
                "",
                txtNom.getText().toString(),
                txtEmail.getText().toString(),
                txtTelephone.getText().toString(),
                txtStatut.getText().toString(),
                txtNote.getText().toString()
        );

        new InsertionClient().execute(client);
    }

    private class InsertionClient extends AsyncTask<Client, Void, String> {
        @Override
        protected String doInBackground(Client... clients) {
            String url = "http://10.0.222.21:8888/SD_2025/api_orange/insertClient.php";
            Client client = clients[0];

            try {
                url += "?nom=" + URLEncoder.encode(client.getNom(), "UTF-8");
                url += "&email=" + URLEncoder.encode(client.getEmail(), "UTF-8");
                url += "&telephone=" + URLEncoder.encode(client.getTelephone(), "UTF-8");
                url += "&statut=" + URLEncoder.encode(client.getStatut(), "UTF-8");
                url += "&note=" + URLEncoder.encode(client.getNote(), "UTF-8");

                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return new JSONArray(reader.readLine()).getJSONObject(0).getString("message");

            } catch (Exception e) {
                Log.e("Erreur Insert Client", e.toString());
                return "Erreur lors de l'insertion.";
            }
        }

        @Override
        protected void onPostExecute(String message) {
            Toast.makeText(PageAjoutClient.this, "Message: " + message, Toast.LENGTH_LONG).show();
        }
    }
}
