import javax.imageio.stream.ImageInputStream;
import javax.swing.*;

/**
 * Az osztály az FFP2-es maszk tárgy viselkedését valósítja meg.
 * felelőssége, hogy a hallgatók az osztály felhasználásával a
 * védettséget kapjanak a mérges gáz hatása ellen.
 */
public class FFP2Mask extends Item{
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public FFP2Mask(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     *  A metódus a hallgató állapotát beállítja mérges gáz ellen védetté
     *  (PoisonProtection) így a gáz hatása nem érvényes a hallgatóra.
     */
    @Override
    public void effect() {
        this.getOwner().setPoisonProtection(true);
        PoisonProtection po = new PoisonProtection(this.getOwner(), 4);
        this.getOwner().addProperty(po);
        this.setCurentlyUsed(true);
        this.destruct();
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
        return "player " + playerName + " get protected from poison" ;
    }

    /**
     * A tárgyhoz tartozó ikont adja vissza.
     */
    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("ffp2mask.png");
    }
}
