package com.example.nyt_meneghetti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Articolo extends AppCompatActivity {

    private TextView txt_paroleChiavi;
    private TextView autori;
    private TextView txt_share;
    private TextView txt_open;
    private String urlArticolo;
    private String autoreArticolo;
    private String paroleChiavi;
    private FloatingActionButton floatingActions;
    private FloatingActionButton floating_share;
    private FloatingActionButton floating_open;
    private Animation rotate_open;
    private Animation rotate_close;
    private Animation from_bottom;
    private Animation to_bottom;
    private boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articolo);
        init();
        getData();
        buttonsInit();
    }

    private void setAnimation() {
        if (!clicked) {
            txt_open.startAnimation(from_bottom);
            txt_share.startAnimation(from_bottom);
            floating_open.startAnimation(from_bottom);
            floating_share.startAnimation(from_bottom);
            floatingActions.startAnimation(rotate_open);
        } else {
            txt_open.startAnimation(to_bottom);
            txt_share.startAnimation(to_bottom);
            floating_open.startAnimation(to_bottom);
            floating_share.startAnimation(to_bottom);
            floatingActions.startAnimation(rotate_close);
        }

    }

    private void setVisibility() {

        if (!clicked) {
            txt_open.setVisibility(View.VISIBLE);
            txt_share.setVisibility(View.VISIBLE);
            floating_open.setVisibility(View.VISIBLE);
            floating_share.setVisibility(View.VISIBLE);
        } else {
            txt_open.setVisibility(View.INVISIBLE);
            txt_share.setVisibility(View.INVISIBLE);
            floating_open.setVisibility(View.INVISIBLE);
            floating_share.setVisibility(View.INVISIBLE);
        }

    }

    private void setClicked() {

        if (!clicked) {
            floating_open.setClickable(true);
            floating_share.setClickable(true);
        } else {
            floating_open.setClickable(false);
            floating_share.setClickable(false);
        }

    }

    private void buttonsInit() {

        floatingActions.setOnClickListener(view -> {
            setVisibility();
            setAnimation();
            setClicked();
            clicked = !clicked;
        });

        // Apre una schermata dove si può condividere il link dell'articolo in un altra app.
        floating_share.setOnClickListener(view -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, urlArticolo);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        // Apre il browser predefinito per aprire il link dell'articolo
        floating_open.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlArticolo));
            startActivity(browserIntent);
        });

    }

    private void init() {
        autori = findViewById(R.id.textView_Autore);
        txt_paroleChiavi = findViewById(R.id.textView_paroleChiavi);
        floatingActions = findViewById(R.id.floating_actions);

        floating_open = findViewById(R.id.floating_apriArticolo);
        floating_share  = findViewById(R.id.floating_share);

        txt_share = findViewById(R.id.txt_share);
        txt_open = findViewById(R.id.txt_apri);

        rotate_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_open);
        rotate_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_close);

        from_bottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_bottom);
        to_bottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_bottom);
    }

    // Prendo i dati dell'articolo e li inserisco nella view.
    private void getData() {

        urlArticolo = getIntent().getStringExtra("urlArticolo");
        autoreArticolo = String.format("Autori: %s\n\n", getIntent().getStringExtra("autoreArticolo"));
        paroleChiavi = String.format("\nParole chiave:\n\n%s", getIntent().getStringExtra("paroleChiave"));

        autoreArticolo = autoreArticolo.replace("By", "");
        paroleChiavi = paroleChiavi.replace(";", "\n");

        autori.setText(autoreArticolo);
        txt_paroleChiavi.setText(paroleChiavi);
    }
}