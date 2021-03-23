package com.example.nyt_meneghetti;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RootPopularArticles result;
    private ArrayList<Results> resultsArrayList;
    private ArrayAdapter adapter;
    private ListView listView;
    private Spinner spinner;
    private String numeroGiorniSelezionati;
    private Button scaricaArticoli;
    private CheckBox checkbox_facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        spinnerSetup();
        listViewSetup();
        btn_articoliSetup();
    }

    private void init() {
        listView = findViewById(R.id.listview_articoli);
        spinner = findViewById(R.id.spinner_giorniPopolari);
        scaricaArticoli = findViewById(R.id.btn_scaricaArticoliPopolari);
        checkbox_facebook = findViewById(R.id.checkBox_facebook);
    }

    private void spinnerSetup() {
        String[] items = new String[]{"1", "7", "30"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.setSelection(position);
                numeroGiorniSelezionati = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void listViewSetup() {
        listView.setSmoothScrollbarEnabled(true); // Rende più fluido lo scorrimento della listView.
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent form = new Intent(MainActivity.this, Articolo.class);
            form.putExtra("urlArticolo", resultsArrayList.get(position).getUrl());
            form.putExtra("autoreArticolo", resultsArrayList.get(position).getByline());
            form.putExtra("paroleChiave", resultsArrayList.get(position).getAdx_keywords());
            startActivity(form);
        });
    }

    private void btn_articoliSetup() {
        scaricaArticoli.setOnClickListener(view -> {
            try {
                getAllPopularArticles();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    // Scarica e mostra sulla listView gli articoli ottenuti.
    public void getAllPopularArticles() throws MalformedURLException {
        String url_ = "https://api.nytimes.com/svc/mostpopular/v2/";
        if (checkbox_facebook.isChecked()) {
            url_ += String.format("shared/%s/facebook.json", numeroGiorniSelezionati);
        } else {
            url_ += String.format("viewed/%s.json", numeroGiorniSelezionati);
        }
        url_ += "?api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D";
        URL url = new URL(url_);
        DownloadInternet downloadInternet = new DownloadInternet();
        downloadInternet.execute(url);
    }

    private class DownloadInternet extends AsyncTask<URL, String, String> {

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

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
            result = gson.fromJson(s, RootPopularArticles.class);
            resultsArrayList = result.getResults();

            adapter = new ArrayAdapter<Results>(getApplicationContext(), R.layout.row_custom, R.id.textView, resultsArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView tv_years = view.findViewById(R.id.textView);
                    String s = String.format("%s\n\n%s", resultsArrayList.get(position).getPublished_date(), resultsArrayList.get(position).getTitle());
                    tv_years.setTextColor(Color.parseColor("#BCB6B6"));
                    tv_years.setText(s);
                    return view;
                }
            };
            listView.setAdapter(adapter);
        }
    }
}