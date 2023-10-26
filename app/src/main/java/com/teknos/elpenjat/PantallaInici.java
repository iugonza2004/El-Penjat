package com.teknos.elpenjat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PantallaInici extends AppCompatActivity {

    public void start(View view) {
        Intent intent = new Intent(PantallaInici.this, PantallaInicial.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inici);
    }
}