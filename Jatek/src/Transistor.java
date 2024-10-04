import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Az osztály a Tranzisztor tárgy viselkedését valósítja meg. Felelőssége, hogy biztosítja azon hallgatók számára
 * a teleportálást, akiknek van két összekötött tranzisztoruk és ezek közül az egyik már azon szobába le van helyezve,
 * ahová a hallgató teleportálni szeretne és használja a másikat.
 */
public class Transistor extends Item {

    private boolean connected=false;
    /**
     * Összekapcsolt tranzisztor esetén a tranzisztor párja (egy másik tranzisztor)
     */
    private Item pair;
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     * @param pPair Összekapcsolt tranzisztor esetén a tranzisztor párja
     * */
    public Transistor(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner, Item pPair) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
        pair = pPair;
    }
    /**
     * Ezen metódussal történik meg a hallgató elteleportálása a már letett és összekapcsolt tranzisztorhoz.
     */
    @Override
    public void effect() {
        if(this.getPair()==null){
            return;
        }
        if(this.getPair().getItemLocation()==null){
            connected=false;
            pair=null;
            return;
        }
        this.getOwner().getLocation().deleteCharacter(this.getOwner());
        this.getOwner().setLocation(pair.getItemLocation());
        pair.getItemLocation().addCharacter(this.getOwner());
        this.destruct();
        pair.destruct();
    }

    /**
     * Visszadja a párt
     * @return A párban részt vevő másik tranzisztor
     */
    public Item getPair() {
        return pair;
    }
    /**
     * Ezzel a függvénnyel kapcsoljuk össze a tranzisztorunkat.
     * @param item A tárgy akivel összekapcsoljuk(Ez csak tranzisztor lesz.)
     */
    @Override
    public void connect(Item item) {

        if(!item.equals(this)){
            if(item.getPair()==null||item.getPair()==this){
                pair = item;
                connected=true;
            }
            if(item.getPair()==null)
                item.connect(this);

            if(connected){
                System.out.println("connected to " + pair.getName());
            }
        }
    }
    /**
     * Ez a metódus felel azért, hogy ha egy hallgatónál már van egy Transistor, akkor egy másik felvételénél a párosodás teljesüljön.
     */
    @Override
    public void onPickUp() {
        pair = null;
        connected = false;
        List<Item> inventory = this.getOwner().getInventory();
        for(Item item : inventory) {
            if(!connected){
                this.connect(item);
            }
        }
    }
    /**
     * Kiírja a tranzisztor adatait.
     */
    @Override
    public String toString() {
        return  "name:" + this.getName() + "\n" +
                "durability: " + this.getDurability() + "\n" +
                "currentlyUsed: " + this.isCurentlyUsed() + "\n" +
                "connected: " + (pair != null);
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
        return "player " + playerName + " teleported from " + roomIdx + ".room to " + roomIdx2 + ".room";
    }


    /**
     * Visszaadja a tárgy ikonját annak megfelelően, hogy a tárgy milyen állapotban van.
     * @return az ikon
     */
    @Override
    public ImageIcon getIcon() {

        if(connected && pair.getItemLocation()!=null){
            if(pair.getOwner() == null){
                return new ImageIcon("transistor_ready.png");
            } else {
                return new ImageIcon("transistor_paired.png");
            }
        } else {
            return new ImageIcon("transistor.png");
        }
    }
}
