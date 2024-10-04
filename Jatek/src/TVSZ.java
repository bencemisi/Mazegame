import com.sun.nio.sctp.SctpStandardSocketOptions;

import javax.swing.*;

/**
 * Az osztály a “TVSZ denevérbőrre nyomtatott példánya” tárgy viselkedését valósítja meg.
 * Felelőssége, hogy ha egy hallgató használja a “TVSZ denevérbőrre nyomtatott példánya” n
 * evű tárgyat, akkor az megvédje őt adott alkalommal attól, hogy egy oktató kiszívja a lelkét.
 */
public class TVSZ extends Item{
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public TVSZ(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     *  A metódus a hallgató állapotát beállítja oktató lélek kiszívása ellen védetté (SoulProtection) így az oktatók támadásai nem hatnak a hallgatóra.
     */
    @Override
    public void effect() {
        this.getOwner().setSoulProtection(true);
        SoulProtection so = new SoulProtection(this.getOwner(),30);
        this.getOwner().addProperty(so);
        this.setCurentlyUsed(true);
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
        return "player " + playerName + " get protected from teachers";
    }

    /**
     * Visszaadja a tárgy ikonját.
     * @return az ikon
     */
    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("tvsz.png");
    }
}
