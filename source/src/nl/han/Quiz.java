package nl.han;

import java.util.ArrayList;

public class Quiz {
    private String quiznaam;
    private ArrayList<Vraag> vragen;

    public Quiz(){}

    public Quiz(String quiznaam) {
        this.quiznaam = quiznaam;
        vragen = new ArrayList<>();
    }

    public Vraag getVraag(int vraagNummer){
        return vragen.get(vraagNummer);
    }

    public void addVraag(Vraag vraag){
        vragen.add(vraag);
    }

    public Quiz mockedQuiz(){
        Quiz mockedQuiz = new Quiz("Oefen Quiz");

        MeerkeuzeVraag vraag1 = new MeerkeuzeVraag("Wat is 2x2?", "Wiskunde", 'e', "1", "2", "3", "4");
        mockedQuiz.addVraag(vraag1);

        Kortantwoordvraag vraag2 = new Kortantwoordvraag("Voornaam van de koningin?", "Koningshuis", 'm');
        vraag2.addAntwoord(new Antwoord("Maxima"));
        mockedQuiz.addVraag(vraag2);

        Kortantwoordvraag vraag3 = new Kortantwoordvraag("In welk land is Christiano Ronaldo geboren?", "Voetbal", 'p');
        vraag3.addAntwoord(new Antwoord("Portugal"));
        mockedQuiz.addVraag(vraag3);

        MeerkeuzeVraag vraag4 = new MeerkeuzeVraag("Welke kleur krijg je als je rood en groen mengt?", "Kunst",'e',"Blauw", "Paars", "Bruin", "Geel");
        mockedQuiz.addVraag(vraag4);

        MeerkeuzeVraag vraag5 = new MeerkeuzeVraag("Hoeveel liter is 1 kuub?", "Wiskunde", 'u',"100", "10", "10000", "1000");
        mockedQuiz.addVraag(vraag5);

        Kortantwoordvraag vraag6 = new Kortantwoordvraag("Welke artiesten zijn bekend van het nummer 'Het regent zonnestralen'?", "Muziek", 'a');
        vraag6.addAntwoord(new Antwoord("Acda en de munnik"));
        vraag6.addAntwoord(new Antwoord("Acda & de munnik"));
        vraag6.addAntwoord(new Antwoord("Acda & de munik"));
        vraag6.addAntwoord(new Antwoord("Acda en de munik"));
        vraag6.addAntwoord(new Antwoord("Acda & munnik"));
        vraag6.addAntwoord(new Antwoord("Acda en munnik"));
        mockedQuiz.addVraag(vraag6);

        Kortantwoordvraag vraag7 = new Kortantwoordvraag("Wat is het kleinste land ter wereld?", "Geografie", 'v');
        vraag7.addAntwoord(new Antwoord("Vaticaanstad"));
        vraag7.addAntwoord(new Antwoord("Vaticaan"));
        vraag7.addAntwoord(new Antwoord("Het Vaticaan"));
        mockedQuiz.addVraag(vraag7);

        MeerkeuzeVraag vraag8 = new MeerkeuzeVraag("Hoe heet de schrijver van de Millennium-trilogie, een reeks misdaadromans? Het eerste boek uit de reeks heet\n" +
                "Mannen die vrouwen haten.", "Literatuur", 'l', "Henning Mankell", "Jo Nesbø", "Lars Kepler", "Stieg Larsson");
        mockedQuiz.addVraag(vraag8);

        return mockedQuiz;
    }
}
