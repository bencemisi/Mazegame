import javax.swing.*;

/**
 * Az osztály a “Nedves táblatörlő rongy” tárgy viselkedését
 * valósítja meg. Felelőssége, hogy a hallgató a használata
 * során képes legyen egy oktatót megbénítani,
 * és ez által kisebb előnyre tegyen szert a játék során.
 */
public class WetTowel extends Item{
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public WetTowel(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     *  A metódus az a szobában lévő oktatókat lebénítja de a hallgatókra nem hat.
     */
    @Override
    public void effect() {
        this.getOwner().getLocation().getCharacters().forEach(character -> {
                character.stun();

        });
        this.setDurability(1);
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
        return "player " + playerName + " stunned teachers in " + roomIdx + ".room";
    }

    /**
     * Visszaadja a tárgy ikonját.
     * @return az ikon
     */
    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("wettowel.png");
    }
}
