package nl.han;

import java.util.Collections;

public class Parola {
    private static Parola parola;
    private Quiz quiz;
    private boolean quizFinished;
    private int huidigeVraagID;
    private Antwoordformulier antwoordformulier;

    public static Parola getInstance(){
        if(parola == null){
            parola = new Parola();
        }

        return parola;
    }

    public void startQuiz(String playername){
        quiz = new Quiz().mockedQuiz();
        antwoordformulier = new Antwoordformulier();
        quizFinished = false;
        huidigeVraagID = 0;
    }

    public String nextQuestion(String playername){
        if(quiz.getVraag(huidigeVraagID) instanceof MeerkeuzeVraag meerkeuzeVraag){
            var antwoorden = (meerkeuzeVraag).getMultiplechoise();
            Collections.shuffle(antwoorden);
            return meerkeuzeVraag.getVraagtekst() + " " + antwoorden.get(0).getAntwoord() + " - " + antwoorden.get(1).getAntwoord() + " - " + antwoorden.get(2).getAntwoord() + " - " + antwoorden.get(3).getAntwoord();
        } else {
            return quiz.getVraag(huidigeVraagID).getVraagtekst();
        }
    }

    public void processAnswer(String playername, String antwoord){
        antwoordformulier.addAntwoord(antwoord);
        huidigeVraagID++;

        if(huidigeVraagID == 8){
            quizFinished = true;
        }
    }

    public boolean quizFinished(String playername){
        return quizFinished;
    }
    
    public String getLettersForRightAnswers(String playername){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            if(quiz.getVraag(i).checkAntwoord(antwoordformulier.getAntwoord(i))){
                antwoordformulier.vraagGoed();
                stringBuilder.append(" ").append(quiz.getVraag(i).getLetter());
            }
        }

        return stringBuilder.toString();
    }

    public int calculateScore(String playername, String woord){
        antwoordformulier.maakWoord(woord);

        // Geef aan welke berekening gebruikt moet worden (BerekeningA of BerekeningB)
        return antwoordformulier.berekenScore(new ConcreteBerekeningA());
    }
}
