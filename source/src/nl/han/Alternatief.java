package nl.han;

public class Alternatief {
    private String alternatief;
    private boolean isCorrect;

    public Alternatief(String alternatief, boolean isCorrect) {
        this.alternatief = alternatief;
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getAlternatief() {
        return alternatief;
    }

}
