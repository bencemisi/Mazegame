/**
 * A karakterek oktató támadása elleni védettségének beállítására szolgál.
 */
public class SoulProtection extends CharacterProperty {
    /**
     * Paraméteres konstruktor
     * @param pCharacter A karakter amire érvényes
     * @param pTimelimit A hatás érvényességének ideje
     */
    public SoulProtection(Character pCharacter, int pTimelimit) {
        super(pCharacter, pTimelimit);
    }
    /**
     * Evvel a metódussal rakunk oktató támadása elleni védettséget a karakterre
     */
    public void effect() {
        if(this.getTimelimit()>4){
            setTimeLimit(-10);
        }
        if(this.getTimelimit()==0){
            this.getCharacter().setSoulProtection(false);
        }
        else
        {this.getCharacter().setSoulProtection(true);}
    }

}
