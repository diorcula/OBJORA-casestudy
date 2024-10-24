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
    de gebruiker gerelateerde dingen moeten
    overeenkomen met de sequence diagrammen
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

    public Speler laadSpeler(String gebruikersnaam) {
        for (Speler speler : spelers) {
            if (speler.getGebruikersnaam().equals(gebruikersnaam)) {
                return speler;
            } else {
                throw new IllegalArgumentException("Speler niet gevonden");
            }
        }
        return null;
    }

    // todo: implementeren
//    public void koopCredits(String gebruikersnaam, int aantalCredits) {
//    }
//
//    public void startTransactie(String gebruikersnaam) {
//    }
//
//    public void verifieerbetaling() {
//    }

    // todo: vragen toevoegen aan een quiz
    public void maakQuiz(String naamQuiz) {
        Quiz quiz = new Quiz(naamQuiz);
        for (Vraag vraag : vragen) {
            quiz.addVraag(vraag);
        }
        quizzes.add(quiz);
    }

    // todo: soort vragen toevoegen
//    public void vraagToevoegen(String vraagType, String vraag, String categorie, boolean actief, char letter) {
//        if (vraagType = "kortantwoord") {
//            Vraag newVraag = new Kortantwoordvraag(vraag, categorie, actief, letter);
//            vragen.add(newVraag);
//        } else if (vraagType = "meerkeuze") {
//            Vraag newVraag = new MeerkeuzeVraag(vraag, categorie, actief, letter);
//            vragen.add(newVraag);
//        }
//    }


    public String nextQuestion(String playername) {
        if (quiz.getVraag(huidigeVraagID) instanceof MeerkeuzeVraag meerkeuzeVraag) {
            var antwoorden = (meerkeuzeVraag).getAntwoorden();
            Collections.shuffle(antwoorden);
            return meerkeuzeVraag.getVraagtekst() + " " + antwoorden.get(0).getAlternatief() + " - " + antwoorden.get(1).getAlternatief() + " - " + antwoorden.get(2).getAlternatief() + " - " + antwoorden.get(3).getAlternatief();
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
