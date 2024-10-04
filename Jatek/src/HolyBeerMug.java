import javax.swing.*;

/**
 * Az osztály a Szent söröspohár tárgy viselkedését valósítja meg.
 * Felelőssége, hogy ha egy hallgató használja ezt tárgyat, akkor az megvédje őt
 * egy adott ideig attól, hogy egy oktató kiszívja a lelkét.
 */
public class HolyBeerMug extends Item{
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public HolyBeerMug(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     * A metódus a hallgató állapotát
     * beállítja oktató lélek kiszívása ellen védetté (SoulProtection)
     * így az oktatók támadásai nem hatnak a hallgatóra.
     */
    @Override
    public void effect() {
        SoulProtection so = new SoulProtection(this.getOwner(), 4);
        this.getOwner().addProperty(so);
        this.setCurentlyUsed(true);
        boolean isDroped = false;
        for(int i=0; i<getOwner().getInventory().size(); i++){

            if(!getOwner().getInventory().get(i).isCurrentlyUsed() && !isDroped){
                getOwner().drop(getOwner().getInventory().get(i));
                isDroped = true;
            }
        }
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
     * A tárgy ikonját visszaadó metódus
     * @return ImageIcon a tárgy ikonja
     */
    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("holybeermug.png");
    }
}

