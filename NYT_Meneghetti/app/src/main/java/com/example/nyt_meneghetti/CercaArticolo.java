package com.example.nyt_meneghetti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CercaArticolo extends AppCompatActivity {

    private RootFindArticles result;
    private ArrayList<Docs> resultsArrayList;
    private ArrayAdapter adapter;
    private ListView listView;
    private Button scaricaArticoli;
    private EditText cercaArticoli;
    private Spinner spinnerArgomenti;
    private Spinner spinnerTempo;
    private String argomentoSelezionato;
    private String tempoSelezionato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_articolo);
        init();
        spinnerArgomentiSetup();
        spinnerTempoSetup();
        listViewSetup();
        btn_articoliSetup();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); // La view rimane fissa quando la tastiera viene mostrata a schermo, cosi si può vedere quello che si scrive
    }

    private void init() {
        listView = findViewById(R.id.listview_articoli);
        scaricaArticoli = findViewById(R.id.btn_scaricaArticoliPopolari);
        cercaArticoli = findViewById(R.id.editText_articoli);
        spinnerArgomenti = findViewById(R.id.spinner_giorniPopolari);
        spinnerTempo = findViewById(R.id.spinner_ordinaPerTempo);
    }

    private void spinnerArgomentiSetup() {
        String[] items = new String[]{"World", "Politics", "N.Y.", "Business", "Opinion", "Tech", "Science", "Health", "Sports"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerArgomenti.setAdapter(spinnerAdapter);

        spinnerArgomenti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.setSelection(position);
                argomentoSelezionato = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void spinnerTempoSetup() {
        String[] items = new String[]{"newest", "oldest", "relevance"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTempo.setAdapter(spinnerAdapter);

        spinnerTempo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.setSelection(position);
                tempoSelezionato = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private String parsingKeywords(int position){
        String risultato = "";
        ArrayList<Keywords> keywords = resultsArrayList.get(position).getKeywords();
        for (Keywords k:keywords) {
            risultato += String.format("%s;",k.getValue());
        }
        return risultato;
    }

    private void listViewSetup() {
        listView.setSmoothScrollbarEnabled(true); // Rende più fluido lo scorrimento della listView.
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent form = new Intent(CercaArticolo.this, Articolo.class);
            form.putExtra("urlArticolo", resultsArrayList.get(position).getWeb_url());
            form.putExtra("autoreArticolo", resultsArrayList.get(position).getByline().getOriginal());
            form.putExtra("paroleChiave", parsingKeywords(position));
            startActivity(form);
        });
    }

    private void btn_articoliSetup() {
        scaricaArticoli.setOnClickListener(view -> {
            try {
                getAllSearchedArticles();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    public void getAllSearchedArticles() throws MalformedURLException {
        // filtri disponibili: cerca per parola chiave, argomento; ordina per recente / vecchio / importanza
        String url_ = "https://api.nytimes.com/svc/search/v2/articlesearch.json?";
        if (!TextUtils.isEmpty(cercaArticoli.getText()))
        {
            url_ += String.format("q=%s&",cercaArticoli.getText());
        }
        url_ += String.format("fq=news_desk:(\"%s\")&sort=%s&api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D",argomentoSelezionato,tempoSelezionato);
        URL url = new URL(url_);
        DownloadInternet downloadInternet = new DownloadInternet();
        downloadInternet.execute(url);
    }

    private class DownloadInternet extends AsyncTask<URL, String, String> {

        final ProgressDialog dialog = new ProgressDialog(CercaArticolo.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Sto scaricando gli articoli...");
            dialog.show();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String json = "";

            try {
                HttpURLConnection conn = (HttpURLConnection) urls[0].openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    json += line;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            Gson gson = new Gson();
            result = gson.fromJson(s, RootFindArticles.class);
            resultsArrayList = result.getResponse().getDocs();

            adapter = new ArrayAdapter<Docs>(getApplicationContext(), R.layout.row_custom, R.id.textView, resultsArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView tv_years = view.findViewById(R.id.textView);
                    String s = String.format("%s\n\n%s", resultsArrayList.get(position).getPub_date().substring(0,10), resultsArrayList.get(position).getHeadline().getMain());
                    tv_years.setTextColor(Color.parseColor("#BCB6B6"));
                    tv_years.setText(s);
                    return view;
                }
            };
            listView.setAdapter(adapter);
        }
    }
}