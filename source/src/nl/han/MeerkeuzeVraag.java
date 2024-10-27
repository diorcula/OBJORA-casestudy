package nl.han;

import java.util.ArrayList;

public class MeerkeuzeVraag extends Vraag {
    private ArrayList<Alternatief> antwoorden = new ArrayList<>();

    public MeerkeuzeVraag(String vraagtekst, String categorie, Character letter,
                          String fout1, String fout2, String fout3, String correct) {
        super(vraagtekst, categorie, letter);
        antwoorden.add(new Alternatief(fout1, false));
        antwoorden.add(new Alternatief(fout2, false));
        antwoorden.add(new Alternatief(fout3, false));
        antwoorden.add(new Alternatief(correct, true));
    }

    public boolean checkAntwoord(String gegevenAntwoord) {
        Alternatief antwoord = (Alternatief) antwoorden.stream().filter(a -> gegevenAntwoord.toLowerCase()
                .matches(a.getAlternatief().toLowerCase()));
        return antwoord.isCorrect();
    }

    @Override
    public ArrayList<Alternatief> getAntwoorden() {
        return antwoorden;
    }
}
