package nl.han;

import jdk.jshell.execution.Util;

import java.util.Random;

public class Score {
    private String woord;
    private Berekening berekening;

    public int berekenScore(Berekening berekening, long verstrekenTijd, int aantalGoed){
        return berekening.berekenScore(woord, verstrekenTijd, aantalGoed);
    }

    public void checkWoord(String woord){
        this.woord = woord;
    }
}
