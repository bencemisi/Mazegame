/**
 * Olyan absztrakt osztály, melynek felelőssége az, hogy a segítségével
 * a karakterek státuszait nyilván tudjuk tartani.
 */
public abstract class CharacterProperty {
    /** A karakter amire érvényes */
    private Character character;
    /** A hatás érvényességének ideje */
    private int timelimit;

    /**
     * Paraméteres konstruktor
     * @param pCharacter A karakter amire érvényes
     * @param pTimelimit A hatás érvényességének ideje
     */
    public CharacterProperty(Character pCharacter, int pTimelimit) {
        this.character = pCharacter;
        this.timelimit = pTimelimit;
    }
    /**
     * Ezzel a metódussal csökkentjük a hatás érévényességét.
     */
    public void lower() {

         if(timelimit > 0)
            timelimit--;
    }
    /**
     * Visszaadja a hatás érvényességének idejét.
     * @return A hátramaradt idő
     */
    public int getTimelimit() {
        return timelimit;
    }
    public void setTimeLimit(int timelimit) {
         this.timelimit+=timelimit;
    }
    /**
     * Evvel a metódussal rakunk hatást a karakterre
     */
    public void effect() {}


    /**
     * Visszaadja azt a karaktert akire a hatások érévnyesek.
     * @return A karakter akire a hatások érévnyesek.
     */
    public Character getCharacter() {
        return character;
    }


}
