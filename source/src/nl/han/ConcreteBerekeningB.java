package nl.han;

public class ConcreteBerekeningB implements Berekening{
    @Override
    public int berekenScore(String woord, long tijd, int aantalgoed){
        int woordScore = woord.length() * 8;
        int tijdScore = 100 - ((int)tijd/1000);
        int aantalGoedScore = aantalgoed * 2;

        return woordScore + tijdScore + aantalGoedScore;
    }
}
