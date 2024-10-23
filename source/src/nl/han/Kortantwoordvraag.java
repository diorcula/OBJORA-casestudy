package nl.han;

public class Kortantwoordvraag extends Vraag{

    public Kortantwoordvraag(String vraagtekst, String categorie, Character letter) {
        super(vraagtekst, categorie, letter);
    }

    public void addAntwoord(Antwoord antwoord){
        antwoorden.add(antwoord);
    }

    @Override
    public boolean checkAntwoord(String gegevenAntwoord) {
        return antwoorden.stream().anyMatch(a -> gegevenAntwoord.toLowerCase().matches(a.getAntwoord().toLowerCase()));
    }
}
