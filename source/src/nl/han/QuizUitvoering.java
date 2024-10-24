package nl.han;

import java.util.ArrayList;
import java.util.Collections;

public class QuizUitvoering {
    private ArrayList<String> gegevenAntwoorden;
    private int aantalGoed;
    private Woordchecker woordchecker;
    private Long startTijd;
    private int score;
    private Quiz quiz;
    private Speler speler;
    private int huidigeVraagID;

    // strategy specific
    private ScoreBerekening scoreBerekening;

    public QuizUitvoering(Quiz quiz, Speler speler) {
        gegevenAntwoorden = new ArrayList<>();
        woordchecker = new Woordchecker();
        startTijd = System.currentTimeMillis();
        aantalGoed = 0;
        this.quiz = quiz;
        this.speler = speler;
//        this.scoreBerekening = scoreBerekening;
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

    public String nextQuestion() {
        if (quiz.getVraag(huidigeVraagID) instanceof MeerkeuzeVraag meerkeuzeVraag) {
            var antwoorden = (meerkeuzeVraag).getAntwoorden();
            Collections.shuffle(antwoorden);
            return meerkeuzeVraag.getVraagtekst() + " " + antwoorden.get(0).getAlternatief() + " - " + antwoorden.get(1).getAlternatief() + " - " + antwoorden.get(2).getAlternatief() + " - " + antwoorden.get(3).getAlternatief();
        } else {
            return quiz.getVraag(huidigeVraagID).getVraagtekst();
        }
    }
}
