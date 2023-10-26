package com.teknos.elpenjat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teknos.elpenjat.entitats.Party;
import com.teknos.elpenjat.entitats.Player;
import com.teknos.elpenjat.singleton.PenjatSingleton;

public class PantallaInicial extends AppCompatActivity {

    private EditText playerEditText;
    private EditText wordEditText;
    private EditText hintEditText;
    private TextView playerErrorText;
    private TextView wordErrorText;
    private TextView hintErrorText;
    private boolean hint;

    public void hintYes(View view) {
        this.hintEditText.setEnabled(true);
        this.hint = true;

        Button hintYes = findViewById(R.id.hintYes);
        hintYes.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        hintYes.setTextColor(Color.parseColor("#FFFFFF"));

        Button hintNo = findViewById(R.id.hintNo);
        hintNo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00989898")));
        hintNo.setTextColor(Color.parseColor("#000000"));
    }
    public void hintNo(View view) {
        this.hintEditText.setEnabled(false);
        this.hint = false;
        this.hintEditText.setText("");

        Button hintNo = findViewById(R.id.hintNo);
        hintNo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        hintNo.setTextColor(Color.parseColor("#FFFFFF"));

        Button hintYes = findViewById(R.id.hintYes);
        hintYes.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00989898")));
        hintYes.setTextColor(Color.parseColor("#000000"));
    }

    public void start(View view) {

        String playerText = playerEditText.getText().toString();
        String wordText = wordEditText.getText().toString();
        String hintText = hintEditText.getText().toString();

        if (playerText.isEmpty() || wordText.isEmpty() || !(wordText.length() >= 6 && wordText.length() <= 12) || this.hint && hintText.isEmpty() || !wordText.contains(hintText)) {
            if (playerText.isEmpty()) {
                String errorText = "Has d'entrar el nom del jugador.";
                this.playerErrorText.setText(errorText);
            } else {
                this.playerErrorText.setText("");
            }

            if (wordText.isEmpty()) {
                String errorText = "Has d'entrar una paraula.";
                this.wordErrorText.setText(errorText);
            } else if (!(wordText.length() >= 6 && wordText.length() <= 12)) {
                String errorText = "La paraula ha de ser de entre 6 i 12 lletres.";
                this.wordErrorText.setText(errorText);
            } else {
                this.wordErrorText.setText("");

            }

            if (this.hint && hintText.isEmpty()) {
                String errorText = "Has seleccionat que vols pista, escriu la lletra o selecciona que no vols pista.";
                this.hintErrorText.setText(errorText);
            } else if (!wordText.contains(hintText)) {
                String errorText = "La pista ha de ser una lletra de la paraula.";
                this.hintErrorText.setText(errorText);
            } else {
                this.hintErrorText.setText("");
            }

        } else {
            Party party;
            if (hintText.isEmpty()) {
                party = new Party(new Player(playerText), wordText);
            } else {
                party = new Party(new Player(playerText), wordText, hintText.charAt(0));
            }



            PenjatSingleton singleton = PenjatSingleton.getInstance();
            singleton.setParty(party);

            Intent intent = new Intent(PantallaInicial.this, PantallaDeJoc.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);

        this.playerEditText = findViewById(R.id.playerEditText);
        this.wordEditText = findViewById(R.id.wordEditText);
        this.hintEditText = findViewById(R.id.hintEditText);

        this.playerErrorText = findViewById(R.id.playerErrorText);
        this.wordErrorText = findViewById(R.id.wordErrorText);
        this.hintErrorText = findViewById(R.id.hintErrorText);

        this.hint = false;

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View view = getCurrentFocus();
                    if (view != null && view instanceof EditText) {
                        int[] touchCoordinates = new int[2];
                        view.getLocationOnScreen(touchCoordinates);
                        float x = event.getRawX();
                        float y = event.getRawY();

                        if (x < touchCoordinates[0] || x > touchCoordinates[0] + view.getWidth() ||
                                y < touchCoordinates[1] || y > touchCoordinates[1] + view.getHeight()) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            view.clearFocus();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        this.playerEditText.setText("");
        this.wordEditText.setText("");
        this.hintEditText.setText("");

        this.playerErrorText.setText("");
        this.wordErrorText.setText("");
        this.hintErrorText.setText("");

        this.hint = false;

        this.hintEditText.setEnabled(false);


        Button hintYes = findViewById(R.id.hintYes);
        hintYes.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00989898")));
        hintYes.setTextColor(Color.parseColor("#000000"));

        Button hintNo = findViewById(R.id.hintNo);
        hintNo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        hintNo.setTextColor(Color.parseColor("#FFFFFF"));
    }
}