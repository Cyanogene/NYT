package com.example.nyt_meneghetti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_articolo);
        listView = findViewById(R.id.listview_articoli);
        try {
            gitGUD();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void gitGUD() throws MalformedURLException {
        URL url = new URL("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D");
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
            HttpURLConnection urlConnection;
            String json = "";

            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                int data = isr.read();
                while (data != -1) {
                    json += (char) data;
                    data = isr.read();
                }

            } catch (IOException e) {
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
                    String s = String.format("%s\n\n%s", resultsArrayList.get(position).getPub_date(), resultsArrayList.get(position).getHeadline().getMain());
                    tv_years.setTextColor(Color.parseColor("#BCB6B6"));
                    tv_years.setText(s);
                    return view;
                }
            };
            listView.setAdapter(adapter);
        }
    }
}