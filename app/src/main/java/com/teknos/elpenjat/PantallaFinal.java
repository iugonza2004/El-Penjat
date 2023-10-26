package com.teknos.elpenjat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teknos.elpenjat.entitats.Party;
import com.teknos.elpenjat.singleton.PenjatSingleton;

public class PantallaFinal extends AppCompatActivity {

    private Party party;

    public void stopGame(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_final);

        PenjatSingleton penjatSingleton = PenjatSingleton.getInstance();
        this.party = penjatSingleton.getParty();

        TextView playerNameText = findViewById(R.id.playerNameText);
        TextView hintText = findViewById(R.id.hintText3);
        TextView word = findViewById(R.id.wordToFindTextView);
        ImageView penjat = findViewById(R.id.penjatImage);
        TextView usedLetters = findViewById(R.id.usedLetters);
        TextView hiddenWord = findViewById(R.id.hiddenWord);
        TextView finalText = findViewById(R.id.finalText);

        playerNameText.setText(this.party.getPlayer().getName());
        if (this.party.getHint() == '\u0000') {
            hintText.setText("Sense Pista");
        } else {
            hintText.setText(String.valueOf(this.party.getHint()));
        }
        word.setText(this.party.getWord());
        penjat.setImageDrawable(this.party.getPenjatImage());
        String usedLettersText = "";
        for (char letter : this.party.getUsedLetters()) {
            usedLettersText += letter + ", ";
        }
        usedLettersText = usedLettersText.substring(0, usedLettersText.length() - 1);
        usedLetters.setText(usedLettersText);
        hiddenWord.setText(penjatSingleton.getHiddenWord());
        if(this.party.isWinner()) {
            finalText.setText("HAS GUANYAT!!");
            finalText.setTextColor(Color.parseColor("#0FFF00"));
        } else {
            finalText.setText("HAS PERDUT!!");
            finalText.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}