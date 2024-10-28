package nl.han;

import java.util.ArrayList;
import java.util.Collections;

public class QuizUitvoering {
    private ArrayList<String> spelerAntwoorden;
    private Long startTijd;
    private String verkregenLetters;

    private Woordchecker woordchecker;
    private Quiz quiz;
    private Speler speler;

    private int huidigeVraagID;
    private int aantalGoed;
    private int score;
    private boolean quizFinished = false;

    // strategy specific
    private ScoreBerekening scoreBerekening;

    public QuizUitvoering(Quiz quiz, Speler speler) {
        spelerAntwoorden = new ArrayList<>();
        woordchecker = new Woordchecker();
        startTijd = System.currentTimeMillis(); //vervangt startTimer()
        aantalGoed = 0;
        this.quiz = quiz;
        this.speler = speler;
    }

    public String nextQuestion() {
        if (quiz.getVraag(huidigeVraagID) instanceof MeerkeuzeVraag meerkeuzeVraag) {
            var antwoorden = (meerkeuzeVraag).getAntwoorden();
            Collections.shuffle(antwoorden);
            return meerkeuzeVraag.getVraagtekst() + " " +
                    antwoorden.get(0).getAlternatief() + " - "
                    + antwoorden.get(1).getAlternatief() + " - "
                    + antwoorden.get(2).getAlternatief() + " - "
                    + antwoorden.get(3).getAlternatief();
        } else {
            return quiz.getVraag(huidigeVraagID).getVraagtekst();
        }
    }

    public void beantwoordVraag(String antwoord) {
        if (quiz.getVraag(huidigeVraagID).checkAntwoord(antwoord)) {
            aantalGoed++;
        }
        spelerAntwoorden.add(antwoord); //todo: check of dit nodig is
        huidigeVraagID++;
        if (huidigeVraagID > 8) {
            quizFinished = true;
        }
    }

    // note: stuurt een string terug van de behaalde letters
    public String getLettersForRightAnswer() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= 8; i++) {
            if (quiz.getVraag(i).checkAntwoord(spelerAntwoorden.get(i))) {
                aantalGoed++;
                stringBuilder.append(" ").append(quiz.getVraag(i).getLetter());
            }
        }
        return stringBuilder.toString();
    }

    public int calculateScore(String woord) {
        var verstrekenTijd = System.currentTimeMillis() - startTijd;

        //Bereken de score met berekening A
        //return score.berekenScore(scoreBerekening, verstrekenTijd, aantalGoed);
        score = scoreBerekening.calculateScore(woord, verstrekenTijd, aantalGoed);
        return score;
    }

        public boolean quizFinished() {
            return quizFinished;
        }
    }

    //----------------------------------------
//todo: implementeren

//    public void maakWoord(String woord) {
//        // woordchecker.checkWoord(woord);
//    }

//}
