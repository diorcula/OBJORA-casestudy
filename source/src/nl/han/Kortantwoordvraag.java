package nl.han;

import java.util.ArrayList;

public class Kortantwoordvraag extends Vraag{
private ArrayList<Antwoord> antwoorden = new ArrayList<>();

    public Kortantwoordvraag(String vraagtekst, String categorie,
                             Character letter, boolean isCorrect) {
        super(vraagtekst, categorie, letter);
        antwoorden.add(new Antwoord(vraagtekst, isCorrect));
    }

    @Override
    public boolean checkAntwoord(String gegevenAntwoord) {
        return antwoorden.stream().anyMatch(a -> gegevenAntwoord.toLowerCase()
                .matches(a.getAntwoord().toLowerCase()));
    }

    @Override
    public ArrayList<String> getAntwoorden(){
        return antwoorden.stream().
                map(Antwoord::getAntwoord).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
