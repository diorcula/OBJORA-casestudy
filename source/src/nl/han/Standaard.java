package nl.han;

public class Standaard implements ScoreBerekening {

    @Override
    public int calculateScore(String woord, long tijd, int aantalgoed) {
        int woordScore = woord.length() * 3;
        int tijdScore = 50 - ((int) tijd / 1000);
        int aantalGoedScore = aantalgoed * 2;

        return woordScore + tijdScore + aantalGoedScore;
    }
}
