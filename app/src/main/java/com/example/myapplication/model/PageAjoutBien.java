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
import java.util.*;

public class PageAjoutBien extends AppCompatActivity implements View.OnClickListener {

    private EditText txtTitre, txtDescription, txtPrix, txtAdresse;
    private Spinner spStatut;
    private Button btEnregistrer;
    private static String message = "";

    public static void setMessage(String msg) {
        message = msg;
    }

    public static String getMessage() {
        return message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_ajout_bien);

        txtTitre = findViewById(R.id.txtTitre);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrix = findViewById(R.id.txtPrix);
        txtAdresse = findViewById(R.id.txtAdresse);
        spStatut = findViewById(R.id.spStatut);
        btEnregistrer = findViewById(R.id.btEnregistrer);

        btEnregistrer.setOnClickListener(this);

        ArrayList<String> lesStatuts = new ArrayList<>(Arrays.asList("Disponible", "Vendu", "En attente"));
        ArrayAdapter<String> unAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lesStatuts);
        unAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatut.setAdapter(unAdapter);
    }

    @Override
    public void onClick(View view) {
        String titre = txtTitre.getText().toString().trim();
        String description = txtDescription.getText().toString().trim();
        String prixStr = txtPrix.getText().toString().trim();
        String adresse = txtAdresse.getText().toString().trim();
        String statut = spStatut.getSelectedItem().toString();

        if (titre.isEmpty() || description.isEmpty() || prixStr.isEmpty() || adresse.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        float prix;
        try {
            prix = Float.parseFloat(prixStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Prix invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        Bien unBien = new Bien(titre, description, prix, adresse, statut);
        new InsertionBien().execute(unBien);
    }
}

class InsertionBien extends AsyncTask<Bien, Void, String> {
    @Override
    protected String doInBackground(Bien... biens) {
        String url = "http://10.0.222.21:8888/SD_2025/api_orange/insertBien.php";
        Bien bien = biens[0];
        String resultatJSON = "";

        try {
            url += "?titre=" + URLEncoder.encode(bien.getTitre(), "UTF-8");
            url += "&description=" + URLEncoder.encode(bien.getDescription(), "UTF-8");
            url += "&prix=" + bien.getPrix();
            url += "&adresse=" + URLEncoder.encode(bien.getAdresse(), "UTF-8");
            url += "&statut=" + URLEncoder.encode(bien.getStatut(), "UTF-8");

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) sb.append(ligne);
            resultatJSON = sb.toString();
        } catch (IOException e) {
            Log.e("Erreur Bien", "Erreur connexion " + url, e);
        }

        try {
            JSONArray tabJson = new JSONArray(resultatJSON);
            JSONObject obj = tabJson.getJSONObject(0);
            return obj.getString("message");
        } catch (JSONException e) {
            Log.e("Erreur JSON", resultatJSON);
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        PageAjoutBien.setMessage(s);
        Toast.makeText(App.getContext(), "Message: " + s, Toast.LENGTH_LONG).show();
    }
}
