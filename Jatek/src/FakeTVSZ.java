import javax.swing.*;

public class FakeTVSZ extends Item {
    /**
     * Az osztály paraméteres konstruktora
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public FakeTVSZ(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     * A tárgy használatának hatását leíró függvény
     */
    @Override
    public void effect() {
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
        JOptionPane.showMessageDialog(null, "it was fake");
        return "it was fake";
    }

    /**
     * A tárgy ikonját visszaadó függvény
     * @return a tárgy ikonja
     */
    public ImageIcon getIcon() {
        return new ImageIcon("tvsz.png");
    }
}

