/**
 * A karakterek eszméletvesztés állapotának beállítására szolgál.
 */
public class KnockedOut extends CharacterProperty {
    /**
     * Paraméteres konstruktor
     * @param pCharacter A karakter amire érvényes
     * @param pTimelimit A hatás érvényességének ideje
     */
    public KnockedOut(Character pCharacter, int pTimelimit) {
        super(pCharacter, pTimelimit);
    }

    /**
     * Evvel a metódussal rakunk eszméletvesztett hatást a karakterre
     */
    @Override
    public void effect() {
        this.getCharacter().setKnockedOut(true);
    }
}
