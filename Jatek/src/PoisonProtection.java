/**
 * A karakterek mérgezés elleni védettségének beállítására szolgál.
 */
public class PoisonProtection extends CharacterProperty {
    /**
     * Paraméteres konstruktor
     * @param pCharacter A karakter amire érvényes
     * @param pTimelimit A hatás érvényességének ideje
     */
    public PoisonProtection(Character pCharacter, int pTimelimit) {
        super(pCharacter, pTimelimit);
    }

    /**
     * Evvel a metódussal rakunk mérgezés elleni védettséget a karakterre
     */
    public void effect() {
        if(this.getTimelimit()==0){
            this.getCharacter().setPoisonProtection(false);
        }
        else
        {this.getCharacter().setPoisonProtection(true);}
    }


}
