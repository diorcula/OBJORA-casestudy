package nl.han;

import java.util.ArrayList;

public class Antwoordformulier {
    private ArrayList<String> gegevenAntwoorden;
    private int aantalGoed;
    private Score score;
    private Long startTijd;

    public Antwoordformulier(){
        gegevenAntwoorden = new ArrayList<>();
        score = new Score();
        startTijd = System.currentTimeMillis();
        aantalGoed = 0;
    }

    public void vraagGoed(){ aantalGoed++;};

    public void addAntwoord(String gegevenAntwoord){
        gegevenAntwoorden.add(gegevenAntwoord);
    }

    public String getAntwoord(int i){
        return gegevenAntwoorden.get(i);
    }

    public void maakWoord(String woord){
        score.checkWoord(woord);
    }

    public int berekenScore(Berekening berekening){
        var verstrekenTijd = System.currentTimeMillis() - startTijd;

        //Bereken de score met berekening A
        return score.berekenScore(berekening, verstrekenTijd, aantalGoed);
    }
}
