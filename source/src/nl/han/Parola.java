package nl.han;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Parola {
    // Design Class Diagram implementatie
    private ArrayList<Vraag> vragen = new ArrayList<Vraag>();
    private ArrayList<Speler> spelers = new ArrayList<Speler>();
    private ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
    private HashMap<String, QuizUitvoering> spelerQuiz = new HashMap<String, QuizUitvoering>();

    // overige
    private static Parola parola;
    private Quiz quiz;
    private QuizUitvoering quizUitvoering;
    private boolean quizFinished;
    private int huidigeVraagID;

    public static Parola getInstance() {
        if (parola == null) {
            parola = new Parola();
        }
        return parola;
    }

    public void startQuiz(String gebruikersnaam) {
        quiz = new Quiz().mockedQuiz();
        quizUitvoering = new QuizUitvoering(); //hier moet nog niet de berekening aan meegegeven worden
        quizFinished = false;
        huidigeVraagID = 0;
    }

    // to implement
    public Quiz selecteerQuiz() {
        return quizzes.get(0);
    }

    /*
    - het registreren van een gebruiker houdt geen rekening met of een gebruiker dubbel mag bestaan
    - registreer gebruiker
    - controleer of gebruikersnaam al bestaat
    - controleren van een speler moet met een wachtwoord gebeur
     */

    public void registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        Speler speler = new Speler(gebruikersnaam, wachtwoord);
        spelers.add(speler);
    }

    public boolean controleerGebruikersnaam(String gebruikersnaam) {
        for (Speler speler : spelers) {
            if (speler.getGebruikersnaam().equals(gebruikersnaam)) {
                return true;
            }
        }
        return false;
    }

    public Speler laadSpeler(String gebruikersnaam, String wachtwoord) {
        for (Speler speler : spelers) {
            if (speler.getGebruikersnaam().equals(gebruikersnaam)) {
                return speler;
            }

        }
        registreerGebruiker(gebruikersnaam, wachtwoord);
        return new Speler(gebruikersnaam, wachtwoord);
    }

    public void koopCredits(String gebruikersnaam, int aantalCredits) {
    }

    public void startTransactie(String gebruikersnaam) {
    }

    public void verifieerbetaling() {
    }

    public void maakQuiz(String naamQuiz) {
    }


    public String nextQuestion(String playername) {
        if (quiz.getVraag(huidigeVraagID) instanceof MeerkeuzeVraag meerkeuzeVraag) {
            var antwoorden = (meerkeuzeVraag).getMultiplechoise();
            Collections.shuffle(antwoorden);
            return meerkeuzeVraag.getVraagtekst() + " " + antwoorden.get(0).getAntwoord() + " - " + antwoorden.get(1).getAntwoord() + " - " + antwoorden.get(2).getAntwoord() + " - " + antwoorden.get(3).getAntwoord();
        } else {
            return quiz.getVraag(huidigeVraagID).getVraagtekst();
        }
    }

    public void processAnswer(String playername, String antwoord) {
        quizUitvoering.addAntwoord(antwoord);
        huidigeVraagID++;

        if (huidigeVraagID == 8) {
            quizFinished = true;
        }
    }

    public boolean quizFinished(String playername) {
        return quizFinished;
    }

    public String getLettersForRightAnswers(String playername) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            if (quiz.getVraag(i).checkAntwoord(quizUitvoering.getAntwoord(i))) {
                quizUitvoering.vraagGoed();
                stringBuilder.append(" ").append(quiz.getVraag(i).getLetter());
            }
        }

        return stringBuilder.toString();
    }

    public int calculateScore(String playername, String woord) {
        quizUitvoering.maakWoord(woord);

        // Geef aan welke berekening gebruikt moet worden (BerekeningA of BerekeningB)
        return quizUitvoering.calculateScore(new Standaard());
    }
}
