
import java.util.ArrayList;
import java.util.List;

/**
 *Ez egy absztrakt osztály, amely a játékben
 * szereplő karaktereknek az absztrakt osztálya.
 */
public abstract class Character {
    /** A karakternél lévő tárgyak. */
    private List<Item> inventory;

    public String getName() {
        return name;
    }

    /** A karakter neve. */
    private String name;
    /** A karakter bénított állapotát jelző érték. */
    private boolean stunned;
    /** A karakterre érvényben lévő állapotok. */

    private List<CharacterProperty> characterProperties;
    /** Azon szoba amelyben a karakter tartózkodik.*/

    private Room location;
    /** A karakter méreg elleni védettségét jelző érték. */
    private boolean PoisonProtection;
    /** A karakter oktató lélek kiszívása elleni védettségét jelző érték.*/
    private boolean soulProtection;

    private boolean knockedOut;

    /**
     * Paraméteres kontruktor
     * @param pInventory  A karakternél lévő tárgyak.
     * @param pName  A karakter neve
     * @param pStunned A karakter bénított állapotát jelző érték.
     * @param pCharacterProperties  A karakterre érvényben lévő állapotok.
     * @param pLocation Azon szoba amelyben a karakter tartózkodik.
     * @param pPoisonProtection A karakter méreg elleni védettségét jelző érték.
     * @param pSoulProtection A karakter oktató lélek kiszívása elleni védettségét jelző érték.
     */

    public Character(List<Item> pInventory, String pName, boolean pStunned, List<CharacterProperty> pCharacterProperties, Room pLocation, boolean pPoisonProtection, boolean pSoulProtection) {

        if (pInventory == null) {
            this.inventory = new ArrayList<>();
        }
        else {
            this.inventory = pInventory;
        }
        this.name = pName;
        this.stunned = pStunned;

        if(pCharacterProperties == null) {
            this.characterProperties = new ArrayList<>();
        }
        else {
            this.characterProperties = pCharacterProperties;
        }

        this.location = pLocation;
        this.PoisonProtection = pPoisonProtection;
        this.soulProtection = pSoulProtection;
    }

    /**
     * A karakter mozgását megvalósító függvény.
     */
    public boolean move(Room whereto, int param) {
        return false;
    }

    /**
     * A karakterre vonatkozó hatásokat lehet vele állítani.
     * @param property Az új hatás ami érvényes lesz a karakterre
     */
    public void addProperty(CharacterProperty property) {
        characterProperties.add(property);
        characterProperties.get(characterProperties.size() - 1).effect();
    }

    /**
     *  A karakterre érvényben lévő hatásokat adja vissza egy listában.
     * @return A hatások listája
     */
    List<CharacterProperty> getProperties() {
        return characterProperties;
    }
    /**
     * A karakterre érvényben
     * lévő hatások értékeit frissíti az akció elején.
     */
    public void propertyUpdate() {

        for(int i = 0; i < characterProperties.size(); i++) {
            characterProperties.get(i).effect();
        }
    }

    /**
     * Visszaadja a karakter mely szobában helyezkedik el.
     * @return A szoba amelyben a karakter tartózkodik
     */
    public Room getLocation() {
        return location;
    }


    public void stun() {}
    /**
     * A karakter tartózkodási helyét lehet vele állítani.
     * @param room Az új tartózkodási hely
     */
    public void setLocation(Room room) {
        this.location=room;
    }
    /**
     *  A karakter felveszi a szobában lévő tárgyat,
     *  így a tárgy bekerül a karakter eszköztárába.
     * @param item A felvenni kívánt tárgy
     */
    public abstract boolean pickUp(Item item);

    /**
     *  A karakter az eszköztárából abba a szobába teszi
     *  le a kiválasztott tárgyat amiben éppen tartózkodik
     * @param item A ledobni kívánt tárgy
     */
    public void drop(Item item) {

        for(int i = 0; i < inventory.size(); i++) {

            if(inventory.get(i).equals(item)) {
                inventory.get(i).setItemLocation(location);
                inventory.get(i).setOwner(null);
                inventory.remove(i);
                return;
            }
        }

    }
    /**
     * A karakter az eszköztárából elégeti a kiválasztott tárgyat.
     * @param item Az elégetni kívánt tárgy.
     */
    public void discard(Item item) {

        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).equals(item)) {
                inventory.get(i).destruct();
                inventory.remove(i);
                return;
            }
        }
    }
    /**
     * Ennek a függvénynek a segítségével képes egy hallgató,
     * az eszköztárában szereplő tárgyak közül egyet használni,
     * azonban ha egy oktató próbálná meg ezt a metódust használni,
     * akkor nem történik semmi.
     * @param item A használni kívánt tárgy.
     */
    public void useItem(Item item) {}

    /**
     * Ha a hallgatónak a lelkét kiszívják, akkor ez a metódus felel
     * a hallgató játékból való kieséséért, ha pedig ezt a függvényt
     * egy oktatón hívják meg akkor nem történik semmi
     */
    public void death(Game game) {}
    /**
     *  Ezzel a metódussal képes egy oktató egy hallgató lelkét kiszívni,
     *  ha ugyanabban a szobában tartózkodnak, és ha a hallgató nem rendelkezik
     *  megfelelő védelemmel, ha egy hallgató próbálná ezt a metódust használni,
     *  akkor nem történik semmi
     */
    public void kill(Game game) {}
    public void knockOut() {
        for(int i = 0; i < inventory.size(); i++) {
            inventory.get(i).setItemLocation(location);
            inventory.remove(i);
        }
        System.out.println(name + " knocked out");
    }
    /**
     * Visszaadja a “stunned” tagváltozó értékét.
     * Evvel jelezve hogy a karakter bénult-e.
     * @return Bénultság állapota
     */
    public boolean getStunned() {
        return stunned;
    }
    /**
     * Visszaadja a “poisonProtection” tagváltozó értékét.
     * Evvel jelezve hogy a karakter mérgezett-e.
     * @return Mérgezettség állapota
     */
    public boolean getPoisonProtection() {
        return PoisonProtection;
    }
    /**
     * Visszaadja a “knockedOut” tagváltozó értékét.
     * Evvel jelezve hogy a karakter eszméletvesztett-e.
     * @return Eszméletvesztés állapota
     */
    public boolean getKnockedOut() {
        return knockedOut;
    }
    /**
     * Visszaadja a “soulProtection” tagváltozó értékét.
     * Evvel jelezve hogy a karakter védett-e az oktatók ellen.
     * @return Védettség állapota
     */
    public boolean getSoulProtection() {
        return soulProtection;
    }
    /**
     * Visszaadja a karakter inventory-ját a kapott listával.
     */

    //TODO ez nem kell majd
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
    /**
     * Beállítja a “stunned” tagváltozó értékét.
     * @param stunned Az új érték
     */
    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
    /**
     * Beállítja a “poisonProtection” tagváltozó értékét.
     * @param poisonProtect Az új érték
     */
    public void setPoisonProtection(boolean poisonProtect) {
        this.PoisonProtection = poisonProtect;
    }
    /**
     * Beállítja a “soluProtection” tagváltozó értékét.
     * @param soulProtect Az új érték.
     * @return A művelet sikeres-e.
     */
    public void setSoulProtection(boolean soulProtect) {
        this.soulProtection = soulProtect;
    }
    /**
     * Beállítja a “knockedOut” tagváltozó értékét.
     * @param knocked Az új érétk
     * @return A művelet sikeres-e
     */
    public void setKnockedOut(boolean knocked) {
        this.knockedOut = knocked;
    }
    /**
     * A játékosnál található tárgyak listáját adja vissza
     * @return A lista
     */
    List<Item> getInventory() {
        return inventory;
    }

    /**
     * A Cleanerhez tartozó művelet.
     */
    public void OnEntryClean() {
    }

    /**
     * Biztosítja, hogy a Cleanert és a Teachert a számítógép irányítsa.
     */
    public void managedbyComputer(Game game) {}

    /**
     * A CharacterProperty-k megfelelő törlésére szolgál.
     */
    public void deleteCharacterProp(){}
}
