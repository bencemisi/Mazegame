import javax.swing.*;
import java.awt.*;

/**
 * A játékban szereplő tárgyaknak egy absztrakt osztályt biztosít.
 * A tárgyak hatáskifejtéséért is felelős.
 */
public abstract class Item {


    /** A tárgy neve. */
    private String name;
    /** A tárgy használatinak a számát jelez, amely hogyha eléri a 0-át,
     *  akkor a tárgy használhatatlanná válik.  */
    private int durability;
    /** Ezzel az attribútummal vizsgáljuk,
     * hogy az adott tárgy használatban van-e  */
    private boolean curentlyUsed;


    /** A szoba ahol jelenleg a tárgy van (felvétel után a karakter szobájával egyezik meg).*/
    private Room location;
    /**A karakter aki felvette a tárgyat. */
    private Character owner;
    /**
     * Paraméteres konstruktor
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public Item(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        this.name = pName;
        this.durability = pDurability;
        this.curentlyUsed = pCurrentlyUsed;
        this.location = pItemLocation;
        this.owner = pOwner;
    }

    /**
     * A “durability” attribútum értékét kapjuk vissza.
     * @return Az érték ahányszor még használható a tárgy
     */
    public int getDurability(){
        return durability;
    }
    /**
     * A "durability" attribútum értékének állítása.
     * @param dur Az új érték.
     */
    public void setDurability(int dur){
        durability = durability - dur;
        if(durability == 0) {
            this.destruct();
        }

    }
    /**
     * A “currentlyUsed” attribútum értékét kapjuk vissza.
     * @return A "használatbanlevőség" értéke.
     */
    public boolean isCurrentlyUsed() {
        return curentlyUsed;
    }
    /**
     * A “currentlyUsed” attribútum értékét állítja.
     * @param curentlyUsed Az új érték.
     */

    public void setCurentlyUsed(boolean curentlyUsed) {
        this.curentlyUsed=curentlyUsed;
    }
    /**
     * Visszaadja, hogy a tárgy éppen hol van.
     * @return A szoba ahol a tárgy van.
     */
    public Room getItemLocation(){
        return this.location;
    }

    /**
     *  Beállítja a tárgy melyik szobában van.
     * @param location Az új szoba.
     */
    public void setItemLocation(Room location) {
        this.location = location;
    }

    /**
     *  Beállítja a tárgy melyik szobában van.
     * @param location Az új szoba.
     */
    public void putItem(Room location) {
        this.location = location;
    }
    /**
     * A tárgy tulajdonosát állítja be.
     * @param character Az új tulajdonos.
     */
    public void setOwner(Character character) {
        owner = character;
    }
    /**
     * A tárgy tuljadonosát adja vissza.
     * @return A karakter akinél a tárgy van.
     */
    public Character getOwner(){
        return owner;
    }

    /**
     * Getter a name-hez
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter a currentlyUsed-höz
     * @return currentlyUsed
     */
    public boolean isCurentlyUsed() {
        return curentlyUsed;
    }

    /**
     * Visszaadja a tárgy ikonját.
     * @return az ikon
     */
    public ImageIcon getIcon() {
        return null;
    }

    /**
     * A tárgy hatását kifejtő függvény.
     */
    public abstract void effect();
    /**
     * Transistorok-nál ez a metódus felel azért,hogy összekapcsolódjanak,
     * a többi Item esetében ez a metódus nem csinál semmit.
     * @param item A tárgy akivel összekapcsoljuk(Ez csak tranzisztor lesz.)
     */
    public void connect (Item item) {
    }
    /**
     * Tárgy felvételénél lefutó metódus, ami csak a “SlipStick”, illetve a
     * “Transistor” nevű tárgyaknál fejt ki hatást.
     */
    public void onPickUp() {
    }
    /**
     * A tárgy elégését valósítja meg.
     */
    public void destruct() {
        if(this.getOwner()==null){
            this.getItemLocation().getItems().remove(this);
        }
        else {
            this.getOwner().getInventory().remove(this);
        }
        setOwner(null);
        setItemLocation(null);
    }

    /**
     * A tranzisztor összekapcsolt párját adja vissza
     * @return A pár másik tagja
     */
    public Item getPair() {
        return this;
    }

    /**
     * Overrideolt toString metódus
     * @return string
     */
    @Override
    public String toString() {
        return  "name:" + name + "\n" +
                "durability: " + durability + "\n" +
                "currentlyUsed: " + curentlyUsed;
    }

    /**
     * A használat során kiírandó üzenet.
     * @param playerName a használó játékos neve
     * @param roomIdx1 az első szoba indexe
     * @param roomIdx2 a második szoba indexe
     * @return az üzenet
     */
    public abstract String useToString(String playerName, int roomIdx1, int roomIdx2);
}
