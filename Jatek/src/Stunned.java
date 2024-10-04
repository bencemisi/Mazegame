/**
 * A karakterek bénult állapotának beállítására szolgál.
 */
public class Stunned extends CharacterProperty {
    /**
     * Paraméteres konstruktor
     * @param pCharacter A karakter amire érvényes
     * @param pTimelimit A hatás érvényességének ideje
     */
    public Stunned(Character pCharacter, int pTimelimit) {
        super(pCharacter, pTimelimit);
    }
    /**
     * Evvel a metódussal rakunk bénult hatást a karakterre
     */
    public void effect() {
        this.getCharacter().setStunned(true);
    }
}
