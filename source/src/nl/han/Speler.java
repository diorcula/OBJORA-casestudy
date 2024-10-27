package nl.han;

public class Speler {
    private String gebruikersnaam;
    private String wachtwoord;
    private int credits;

    public Speler(String gebruikersnaam, String wachtwoord){
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public void voegCreditsToe(int aantal){
        // Add credits to the player
        credits += aantal;
    }
    public void verminderCredits(int aantal){
        // Remove credits from the player
        credits -= aantal;
    }

    public String getGebruikersnaam(){
        return gebruikersnaam;
    }
    public String getWachtwoord(){
        return wachtwoord;
    }
    public int getCredits(){
        return credits;
    }
}
