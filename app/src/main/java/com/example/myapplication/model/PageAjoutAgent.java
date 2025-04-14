package com.example.myapplication.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.myapplication.R;
import com.example.myapplication.Menu;
import com.example.myapplication.utils.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

public class PageAjoutAgent extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNom, txtEmail, txtTelephone, txtAgence, txtRole;
    private Button btEnregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_agent);

        txtNom = findViewById(R.id.txtNom);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtAgence = findViewById(R.id.txtAgence);
        txtRole = findViewById(R.id.txtRole);
        btEnregistrer = findViewById(R.id.btEnregistrer);

        btEnregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nom = txtNom.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String telephone = txtTelephone.getText().toString().trim();
        String agence = txtAgence.getText().toString().trim();
        String role = txtRole.getText().toString().trim();

        if (nom.isEmpty() || email.isEmpty() || telephone.isEmpty() || agence.isEmpty() || role.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Agent agent = new Agent(
                "",  // ID vide par défaut (à générer côté serveur)
                nom,
                email,
                telephone,
                agence,
                role
        );
        new InsertionAgent().execute(agent);
    }
}

class InsertionAgent extends AsyncTask<Agent, Void, String> {
    @Override
    protected String doInBackground(Agent... agents) {
        String url = "http://10.0.222.21:8888/SD_2025/api_orange/insertAgent.php";
        Agent agent = agents[0];

        try {
            url += "?nom=" + URLEncoder.encode(agent.getNom(), "UTF-8");
            url += "&email=" + URLEncoder.encode(agent.getEmail(), "UTF-8");
            url += "&telephone=" + URLEncoder.encode(agent.getTelephone(), "UTF-8");
            url += "&agence=" + URLEncoder.encode(agent.getAgence(), "UTF-8");
            url += "&role=" + URLEncoder.encode(agent.getRole(), "UTF-8");

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            JSONArray tabJson = new JSONArray(response);
            JSONObject obj = tabJson.getJSONObject(0);
            return obj.getString("message");

        } catch (Exception e) {
            Log.e("Erreur Insert Agent", e.toString());
            return "Erreur lors de l'enregistrement";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(App.getContext(), "Message : " + s, Toast.LENGTH_LONG).show();
        if (App.getContext() instanceof PageAjoutAgent) {
            PageAjoutAgent activity = (PageAjoutAgent) App.getContext();
            Intent intent = new Intent(activity, Menu.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
