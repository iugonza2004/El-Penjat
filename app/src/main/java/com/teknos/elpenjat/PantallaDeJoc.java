package com.teknos.elpenjat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.teknos.elpenjat.entitats.Party;
import com.teknos.elpenjat.singleton.PenjatSingleton;

public class PantallaDeJoc extends AppCompatActivity {

    private Party party;
    private TextView hiddenWord;
    private TextView usedLetters;
    private int mistakesDone;
    private TextView errorTextTextView;
    private PenjatSingleton penjatSingleton;

    public void stopGame(View view) {
        finish();
    }

    public void restartGame(View view) {
        Intent intent = new Intent(PantallaDeJoc.this, PantallaDeJoc.class);
        startActivity(intent);
        finish();
    }

    public void checkLetter(View view) {

          EditText letterToCheckEditText = findViewById(R.id.letterToCheckEditText);
          String letterToCheckString = letterToCheckEditText.getText().toString();

          if (!letterToCheckString.isEmpty()) {

              char letterToCheck = letterToCheckString.charAt(0);

              if (this.party.letterNotInUsedLetters(letterToCheck)) {
                  String text = this.usedLetters.getText().toString() + letterToCheck + ", ";
                  this.usedLetters.setText(text);

                  String replacedHiddenWord = this.party.replaceCharacters(this.hiddenWord.getText().toString(), letterToCheck);
                  this.hiddenWord.setText(replacedHiddenWord);

                  letterToCheckEditText.setText("");

                  if (this.party.letterNotInHiddenWord(letterToCheck)) {
                      this.mistakesDone++;
                      ImageView image = findViewById(R.id.penjatImage);

                      int resourceId;
                      Resources resources;
                      switch (this.mistakesDone) {
                          case 1:
                              resourceId = R.drawable.penjat1;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              break;
                          case 2:
                              resourceId = R.drawable.penjat2;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              break;
                          case 3:
                              resourceId = R.drawable.penjat3;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              break;
                          case 4:
                              resourceId = R.drawable.penjat4;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              break;
                          case 5:
                              resourceId = R.drawable.penjat5;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              break;
                          case 6:
                              resourceId = R.drawable.penjat6;
                              image.setImageResource(resourceId);
                              resources = getResources();
                              this.party.setPenjatImage(resources.getDrawable(resourceId));
                              finishGame();
                              break;
                      }
                  }
                  this.party.addUsedLetter(letterToCheck);
                  this.errorTextTextView.setText("");
                  if (this.party.isWinner()) {
                      finishGame();
                  }
              } else {
                  this.errorTextTextView.setText("La lletra que has entrat ja ha estat utilitzada.");
                  letterToCheckEditText.setText("");
              }
          } else {
              this.errorTextTextView.setText("Has d'entrar una lletra.");
          }
    }

    private void finishGame() {
        this.penjatSingleton.setHiddenWord(this.hiddenWord.getText().toString());

        Intent intent = new Intent(PantallaDeJoc.this, PantallaFinal.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_joc);

        this.errorTextTextView = findViewById(R.id.errorText);

        this.mistakesDone = 0;

        this.penjatSingleton = PenjatSingleton.getInstance();
        this.party = this.penjatSingleton.getParty();


        TextView player = findViewById(R.id.playerNameText);
        player.setText(this.party.getPlayer().getName());

        this.hiddenWord = findViewById(R.id.hiddenWord);

        String hidden = "";
        for (int i = 1; i < this.party.getWord().length(); i++) {
            hidden += "_ ";
        }
        hidden += "_";
        this.hiddenWord.setText(hidden);

        TextView hint = findViewById(R.id.wordToFindTextView);
        if (this.party.getHint() == '\u0000') {
            hint.setText("Sense Pista");
            this.usedLetters = findViewById(R.id.usedLetters);
            String text = "";
            this.usedLetters.setText(text);
        } else {
            hint.setText(String.valueOf(this.party.getHint()));
            String hiddenWordReplaced = this.party.replaceCharacters(this.hiddenWord.getText().toString(), this.party.getHint());
            this.hiddenWord.setText(hiddenWordReplaced);

            this.usedLetters = findViewById(R.id.usedLetters);
            String text = this.usedLetters.getText().toString() + this.party.getHint() + ", ";
            this.usedLetters.setText(text);
        }

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
}