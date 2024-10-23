package nl.han;

import java.util.ArrayList;

public class QuizUitvoering {
    private ArrayList<String> gegevenAntwoorden;
    private int aantalGoed;
    private Woordchecker woordchecker;
    private Long startTijd;
    private int score;

    // strategy specific
    private ScoreBerekening scoreBerekening;

    public QuizUitvoering(ScoreBerekening scoreBerekening) {
        gegevenAntwoorden = new ArrayList<>();
        woordchecker = new Woordchecker();
        startTijd = System.currentTimeMillis();
        aantalGoed = 0;
        this.scoreBerekening = scoreBerekening;
    }

    public void vraagGoed() {
        aantalGoed++;
    }


    public void addAntwoord(String gegevenAntwoord) {
        gegevenAntwoorden.add(gegevenAntwoord);
    }

    public String getAntwoord(int i) {
        return gegevenAntwoorden.get(i);
    }

    public void maakWoord(String woord) {
        // woordchecker.checkWoord(woord);
    }

    public int calculateScore(String woord) {
        var verstrekenTijd = System.currentTimeMillis() - startTijd;

        //Bereken de score met berekening A
        //return score.berekenScore(scoreBerekening, verstrekenTijd, aantalGoed);
        score = scoreBerekening.calculateScore(woord, verstrekenTijd, aantalGoed);
        return score;
    }
}
