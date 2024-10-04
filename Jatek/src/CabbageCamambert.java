import javax.swing.*;

/**
 *  Az osztály a Dobozos káposztás camembert tárgy viselkedését valósítja meg.
 *  A hallgatók az osztály felhasználásával a szobának
 *  a típusát meg tudják változtatni “Poisonous”-re.
 */
public class CabbageCamambert extends Item{
    /**
     * Az osztály paraméteres konstruktora
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public CabbageCamambert(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }

    /**
     * A szobát, amelyben a hallgató ezt a tárgyat használja,
     * “Poisonous” típusúvá alakítja. Amennyiben
     * már a szoba “Poisonous” volt, akkor nem történik semmi.
     */
    @Override
    public void effect() {
        Poisonous poisonous = new Poisonous();
        this.getItemLocation().addProperty(poisonous);
        poisonous.setRoom(this.getItemLocation());
        poisonous.effect();
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
        return "player " + playerName + " made " + roomIdx + ".room poisonous";
    }

    /**
     * Visszaadja a CabbageCamambert tárgyhoz tartozó képet
     * @return
     */
    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("cabbagecamambert.png");
    }
}
