package com.example.nyt_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Articolo extends AppCompatActivity {

    private ImageButton share;
    private Button apriArticolo;
    private TextView txt_paroleChiavi;
    private TextView autori;
    private String urlArticolo;
    private String autoreArticolo;
    private String paroleChiavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articolo);
        init();
        getData();
        buttonsInit();
    }

    private void buttonsInit() {

        // Apre una schermata dove si può condividere il link dell'articolo in un altra app.
        share.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, urlArticolo);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        // Apre il browser predefinito per aprire il link dell'articolo
        apriArticolo.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlArticolo));
            startActivity(browserIntent);
        });

    }

    private void init() {
        share = findViewById(R.id.imageButton);
        apriArticolo = findViewById(R.id.btn_apriArticolo);
        autori = findViewById(R.id.textView_Autore);
        txt_paroleChiavi = findViewById(R.id.textView_paroleChiavi);
    }

    // Prendo i dati dell'articolo e li inserisco nella view.
    private void getData() {
        urlArticolo = getIntent().getStringExtra("urlArticolo");
        autoreArticolo = String.format("Autori: %s", getIntent().getStringExtra("autoreArticolo"));
        paroleChiavi = String.format("Parole chiavi:\n\n%s", getIntent().getStringExtra("paroleChiavi"));
        autoreArticolo = autoreArticolo.replace("By", "");
        paroleChiavi = paroleChiavi.replace(";", "\n");
        autori.setText(autoreArticolo);
        txt_paroleChiavi.setText(paroleChiavi);
    }
}