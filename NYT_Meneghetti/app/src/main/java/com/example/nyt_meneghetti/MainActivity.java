package com.example.nyt_meneghetti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView aa;
    Root result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getAllCurrencies();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getAllCurrencies() throws MalformedURLException {
        URL url = new URL("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=yes,you%20are%20person%20of%20the%20year&api-key=xun6cBbMyOBLuPyJ1pBr9497IIz07P2D");
        DownloadInternet downloadInternet = new DownloadInternet();
        downloadInternet.execute(url);
    }

    private class DownloadInternet extends AsyncTask<URL, String, String> {

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Downloading...");
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

//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                for (int i = 0; i < jsonObject.length(); i++) {
//                    Iterator<String> keys = jsonObject.keys();
//                    String name = keys.next();
//                    JSONObject jsonObject1 = jsonObject.getJSONObject(name);
//                    double m15 = jsonObject1.getDouble("15m");
//                    //listaMonete.add(new Bitcoin(m15, name));
//                    jsonObject.remove(name);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//            adapter = new ArrayAdapter<Bitcoin>(getApplicationContext(), R.layout.row_custom, R.id.textView, listaMonete) {
//                @NonNull
//                @Override
//                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//                    View view = super.getView(position, convertView, parent);
//                    TextView tv_years = view.findViewById(R.id.textView);
//                    String s = String.format("NOME : %s\nVALUTA : %s", listaMonete.get(position).getNome(), listaMonete.get(position).getM15());
//                    tv_years.setText(s);
//                    return view;
//                }
//            };
//            listView.setAdapter(adapter);
        }
    }
}