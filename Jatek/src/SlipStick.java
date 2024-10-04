/**
 * Az osztály a Logarléc tárgy viselkedését valósítja meg.
 * Felelőssége, hogy ha a hallgatók eszköztárába kerül a Logarléc nevű tárgy, akkor
 * sikeresen teljesítették a küldetésüket és megnyerték a játékot.
 */
public class SlipStick extends Item {
    /**
     * A játék maga amit a hallagtók megnyernek ha felveszik a logarlécet
     */
    private Game game;

    public boolean isFelvettek() {
        return felvettek;
    }

    boolean felvettek = false;
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public SlipStick(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }

    /**
     * A játék változó beállítása
     * @param game A beállítandó érték
     */
    public void setGame(Game game){
        this.game = game;
    }
    /**
     * Ha ezt a tárgyat felveszi egy karakter, akkor lép működésbe
     * ez a metódus. Ha egy hallgató veszi fel ezt a tárgyat,
     * akkor győzelemmel zárul a játék, ha egy oktató,
     * akkor pedig letételre kerül máshol és nem ér véget a játék.
     */

    public void onPickUp() {
        felvettek=true;
        game.endGame(this.isFelvettek());

    }
    /**
     * Biztosítja, hogy a Logarlécet ne lehessen elégetni.
     */
    @Override
    public void destruct(){
    }
    /**
     *  Ez a metódus akkor fut le, ha egy hallgató eszköztárába kerül
     *  a Logarléc nevezetű tárgy,
     *  és ennek hatására megnyerik a hallgatók a játékot.
     */
    public void effect() {
    }

    /**
     * A használat során kiírandó üzenet.
     * @param playerName a használó játékos neve
     * @param roomIdx az első szoba indexe
     * @param roomIdx2 a második szoba indexe
     * @return az üzenet
     */
    @Override
    public String useToString(String playerName, int roomIdx, int roomIdx2){
        return "";
    }
}
