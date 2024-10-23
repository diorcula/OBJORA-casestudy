package nl.han;

public class Woordchecker {
    private String woord;
    private ScoreBerekening scoreBerekening;

    public int berekenScore(ScoreBerekening scoreBerekening, long verstrekenTijd, int aantalGoed){
        return scoreBerekening.calculateScore(woord, verstrekenTijd, aantalGoed);
    }

    public boolean checkWoord(String woord){
        // Check if the word is in de list of the library, deze code is niet geimplementeerd
        this.woord = woord;
        return true;
    }
}
