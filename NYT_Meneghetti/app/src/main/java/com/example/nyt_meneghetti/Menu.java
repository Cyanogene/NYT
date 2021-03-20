package com.example.nyt_meneghetti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private String scelta;
    private Button articoliPopolari;
    private Button cercaArticoli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        onButtonsClick();
    }

    private void init() {
        articoliPopolari = findViewById(R.id.btn_articoliPopolari);
        cercaArticoli = findViewById(R.id.btn_cercaArticolo);
    }

    private void onButtonsClick() {
        articoliPopolari.setOnClickListener(view -> {
            scelta = "Articoli popolari";
            cambiaSchermata();
        });
        cercaArticoli.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_LONG).show();
            //scelta = "Cerca articoli";
            //cambiaSchermata();
        });
    }

    public void cambiaSchermata() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("scelta", scelta);
        startActivity(intent);
    }
}