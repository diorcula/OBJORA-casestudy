package nl.han;

import java.util.ArrayList;

public abstract class Vraag {
    protected String vraagtekst;
    protected String categorie;
    protected Character letter;
    private boolean actief;
    protected ArrayList<Antwoord> antwoorden;

    protected Vraag(String vraagtekst, String categorie, Character letter) {
        this.vraagtekst = vraagtekst;
        this.categorie = categorie;
        this.letter = letter;
        antwoorden = new ArrayList<>();
    }

    public String getVraagtekst() {
        return vraagtekst;
    }

    public Character getLetter() {
        return letter;
    }

    // todo: hoe chekc je of een antwoord goed is, is dit een boolean?
    public boolean checkAntwoord(String gegevenAntwoord) {
        return antwoorden.stream().anyMatch(a -> gegevenAntwoord.
                toLowerCase().matches(a.getAntwoord().toLowerCase())
        );

    }

        public void addAntwoord(String antwoord){
        antwoorden.add(new Antwoord(antwoord));
    }

    public ArrayList getAntwoorden(){
        return antwoorden.stream().
                map(Antwoord::getAntwoord).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }
}
