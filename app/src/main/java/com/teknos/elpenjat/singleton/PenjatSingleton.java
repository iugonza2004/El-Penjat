package com.teknos.elpenjat.singleton;

import com.teknos.elpenjat.entitats.Party;

public class PenjatSingleton {

    private Party party;
    private String hiddenWord;

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    private static class SingletonInstance {
        private static PenjatSingleton INSTANCE = new PenjatSingleton();
    }

    public static PenjatSingleton getInstance() {
        return SingletonInstance.INSTANCE;
    }

    private PenjatSingleton() {
        //Constructor Singleton
    }
}