package com.teknos.elpenjat.entitats;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Party {
    private Player player;
    private String word;
    private char[] wordLetters;
    private char hint;
    private boolean winner;
    private ArrayList<Character> usedLetters;
    private Drawable penjatImage;

    public Party(Player player, String word, char hint) {
        this.player = player;
        this.hint = hint;
        this.word = word;
        this.winner = false;
        this.usedLetters = new ArrayList<>();
        this.usedLetters.add(hint);
        this.wordLetters = new char[(word.length() * 2) - 1];

        this.wordLetters[0] = ' ';
        this.wordLetters[(word.length() * 2) - 2] = ' ';

        for(int i = 0; i < (word.length() * 2) - 1; i += 2) {
            this.wordLetters[i] = word.charAt(i / 2);
            if (i + 1 < (word.length() * 2) - 1) {
                this.wordLetters[i + 1] = ' ';
            }
        }
    }
    public Party(Player player, String word) {
        this.player = player;
        this.word = word;
        this.winner = false;
        this.usedLetters = new ArrayList<>();
        this.wordLetters = new char[(word.length() * 2) - 1];

        this.wordLetters[0] = ' ';
        this.wordLetters[(word.length() * 2) - 2] = ' ';

        for(int i = 0; i < (word.length() * 2) - 1; i += 2) {
            this.wordLetters[i] = word.charAt(i / 2);
            if (i + 1 < (word.length() * 2) - 1) {
                this.wordLetters[i + 1] = ' ';
            }
        }
    }

    public String replaceCharacters(String underScores, char c) {
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < underScores.length(); i++) {
            char currentChar = this.wordLetters[i];

            if (currentChar == c) {
                resultBuilder.append(currentChar);
            } else {
                resultBuilder.append(underScores.charAt(i));
            }
        }

        String result = resultBuilder.toString();

        if (result.replaceAll("\\s", "").equals(this.word)) {
            this.winner = true;
        }

        return resultBuilder.toString();
    }

    public boolean letterNotInHiddenWord(char c) {
        int index = this.word.indexOf(c);
        return index == -1;
    }

    public boolean letterNotInUsedLetters(char c) {
        return !this.usedLetters.contains(c);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char[] getWordLetters() {
        return wordLetters;
    }

    public void setWordLetters(char[] wordLetters) {
        this.wordLetters = wordLetters;
    }

    public char getHint() {
        return hint;
    }

    public void setHint(char hint) {
        this.hint = hint;
    }

    public ArrayList<Character> getUsedLetters() {
        return usedLetters;
    }

    public void addUsedLetter(char usedLetter) {
        this.usedLetters.add(usedLetter);
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Drawable getPenjatImage() {
        return penjatImage;
    }

    public void setPenjatImage(Drawable penjatImage) {
        this.penjatImage = penjatImage;
    }
}
