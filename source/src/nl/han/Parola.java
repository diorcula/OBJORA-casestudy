package nl.han;

import java.util.ArrayList;
import java.util.HashMap;

public class Parola {
    // Design Class Diagram implementatie
    private ArrayList<Vraag> vragen = new ArrayList<Vraag>();
    private ArrayList<Speler> spelers = new ArrayList<Speler>();
    private ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
    private HashMap<String, QuizUitvoering> spelerQuiz = new HashMap<String, QuizUitvoering>(); //voor de het bijhouden van de gespeelde quizuitvoeringen van de spelers

    // overige
    private static Parola parola;
    private Quiz quiz;
    private QuizUitvoering quizUitvoering;
    private Speler activeSpeler;

    public static Parola getInstance() {
        if (parola == null) {
            parola = new Parola();
        }
        return parola;
    }

    public void startQuiz(String gebruikersnaam, String quizName) {
        quiz = new Quiz().mockedQuiz(); // gebruik hier quizName for non-mocked
        quizzes.add(quiz);
        quizUitvoering = new QuizUitvoering(quiz, laadSpeler(gebruikersnaam));
        activeSpeler = laadSpeler(gebruikersnaam);
        verminderCredits(activeSpeler, quiz);

        ScoreBerekening scoreBerekening = new Standaard();
        boolean quizFinished = false;
        int huidigeVraagID = 0;
    }

    private void verminderCredits(Speler activeSpeler, Quiz activeQuiz) {
        if (activeSpeler.getCredits() < quiz.getPrijs()) {
            throw new IllegalArgumentException("Niet genoeg credits");
        } else {
            activeSpeler.verminderCredits(quiz.getPrijs());
        }
    }

    public Quiz selecteerQuiz(String quizNaam) {
        return quizzes.stream().filter(quiz -> quiz.getQuiznaam().equals(quizNaam)).findFirst().orElse(null);
    }

    public void registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        if (controleerGebruikersnaam(gebruikersnaam)) {
            Speler speler = new Speler(gebruikersnaam, wachtwoord);
            speler.voegCreditsToe(1000);
            spelers.add(speler);
            System.out.println("Gebruiker geregistreerd");
        } else {
            throw new IllegalArgumentException("Gebruikersnaam bestaat al");
        }

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
                this.activeSpeler = speler;
                return activeSpeler;
            } else {
                throw new IllegalArgumentException("Speler niet gevonden");
            }
        }
        return null;
    }

    public void maakQuiz(String naamQuiz, int prijs) {
        Quiz quiz = new Quiz(naamQuiz, prijs);

        // add 4 Meerkeuzevragen to the quiz from the vragen list
        for (int i = 0; i < 4; i++) {
            if (vragen.get(i) instanceof MeerkeuzeVraag) {
                quiz.addVraag(vragen.get(i));
            }
        }

        // add 4 Kortanwoordvragen to the quiz from the vragen list
        for (int i = 0; i < 4; i++) {
            if (vragen.get(i) instanceof Kortantwoordvraag) {
                quiz.addVraag(vragen.get(i));
            }
        }

        quizzes.add(quiz);
    }

    // voegt vraag toe aan de vragenpool
    public void vraagToevoegen(String vraag, String categorie, boolean actief, char letter, int vraagtype) {
        if (vraagtype == 1) {
            MeerkeuzeVraag newVraag = new MeerkeuzeVraag(vraag, categorie, letter, "1", "2", "3", "4");
            vragen.add(newVraag);
        } else if (vraagtype == 2) {
            Kortantwoordvraag newVraag = new Kortantwoordvraag(vraag, categorie, letter, actief);
            vragen.add(newVraag);
        } else {
            throw new IllegalArgumentException("Vraagtype niet bekend");
        }
    }

    public String getQuizzes() {
        for (Quiz quiz : quizzes) {
            return quiz.getQuiznaam();
        }
        return null;
    }

    public String nextQuestion() {
        return quizUitvoering.nextQuestion();
    }

    public void processAnswer(String answer) {
        quizUitvoering.beantwoordVraag(answer);
    }

    public boolean quizFinished() {
        return quizUitvoering.quizFinished();
    }

    public String getLettersForRightAnswers(String playername) {
        return quizUitvoering.getLettersForRightAnswer();
    }

    public int calculateScore(String playername, String word) {
        return quizUitvoering.calculateScore(word);
    }
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
