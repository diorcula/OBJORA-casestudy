package nl.han;

import java.util.ArrayList;

public abstract class Vraag {
    protected String vraagtekst;
    protected String categorie;
    protected Character letter;
    protected ArrayList<Antwoord> antwoorden;

    protected Vraag(String vraagtekst, String categorie, Character letter){
        this.vraagtekst = vraagtekst;
        this.categorie = categorie;
        this.letter = letter;
        antwoorden = new ArrayList<>();
    }

    public String getVraagtekst() {
        return vraagtekst;
    }

    public Character getLetter(){ return letter;}

    public abstract boolean checkAntwoord(String gegevenAntwoord);
}
