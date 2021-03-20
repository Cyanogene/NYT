package com.example.nyt_meneghetti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Root result;
    ArrayList<Results> resultsArrayList;
    ArrayAdapter adapter;
    ListView listView;
    Spinner dropdown;
    String s;
    Button b;
    CheckBox c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        dropdown = findViewById(R.id.spinner);
        b = findViewById(R.id.button);
        c = findViewById(R.id.checkBox);
        String[] items = new String[]{"1", "7", "30"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        listView.setSmoothScrollbarEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent form = new Intent(MainActivity.this, BitcoinPerMoneta.class);
//                form.putExtra("nomeMoneta", listaMonete.get(position).getNome());
//                startActivity(form);
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.setSelection(position);
                s = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    getAllCurrencies();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getAllCurrencies() throws MalformedURLException {
        URL url = null;
        if (c.isChecked()) {
            url = new URL(String.format("https://api.nytimes.com/svc/mostpopular/v2/shared/%s/facebook.json?api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D", s));
        } else {
            url = new URL(String.format("https://api.nytimes.com/svc/mostpopular/v2/viewed/%s.json?api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D", s));
        }
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
            result = gson.fromJson(s, Root.class);
            resultsArrayList = result.getResults();

            adapter = new ArrayAdapter<Results>(getApplicationContext(), R.layout.row_custom, R.id.textView, resultsArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView tv_years = view.findViewById(R.id.textView);
                    String s = String.format("%s", resultsArrayList.get(position).getTitle());
                    tv_years.setTextColor(Color.parseColor("#BCB6B6"));
                    tv_years.setText(s);
                    return view;
                }
            };
            listView.setAdapter(adapter);
        }
    }
}