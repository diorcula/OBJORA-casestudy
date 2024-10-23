package nl.han;

public class Antwoord {
    private String antwoord;
    private boolean correct;

    public Antwoord(String antwoord, boolean correct){
        this.antwoord = antwoord;
        this.correct = correct;
    }

    public Antwoord(String antwoord){
        this.antwoord = antwoord;
    }

    public String getAntwoord(){
        return antwoord;
    }

    public boolean isCorrect(){
        return correct;
    }
}
