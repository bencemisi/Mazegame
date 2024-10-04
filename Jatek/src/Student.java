import javax.swing.*;
import java.util.List;
/**
 * Ez az osztály felel a hallgatók viselkedésének a megvalósításáért.
 */
public class Student extends Character {
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
    public Student(List<Item> pInventory, String pName, boolean pStunned, List<CharacterProperty> pCharacterProperties, Room pLocation, boolean pPoisonProtection, boolean pSoulProtection) {
        super(pInventory, pName, pStunned, pCharacterProperties, pLocation, pPoisonProtection, pSoulProtection);
    }

    /**
     *  Ennek a függvénynek a segítségével képes egy hallgató,
     *  az eszköztárában szereplő tárgyak közül egyet használni.
     * @param item A használni kívánt tárgy.
     */
    public void useItem(Item item) {

        if(item.isCurrentlyUsed()){
            return;
        }
        for (int i = 0; i < getInventory().size(); i++) {
            if (getInventory().get(i).equals(item)) {
                item.effect();
                return;
            }
        }
    }

    /**
     * Ha a hallgatónak a lelkét kiszívják, akkor ez a metódus
     * felel a hallgató játékból való kieséséért.
     */
    public void death(Game game) {
        JOptionPane.showMessageDialog(null, "You have been killed.");
        for(int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
            if (this.getLocation() == game.getLabyrinth().getRooms().get(i)) {
               game.getLabyrinth().getRooms().get(i).deleteCharacter(this);
               break;
            }
        }
        game.getCharacters().remove(this);
        this.setLocation(null);
    }

    /**
     * Mivel egy hallgató nem képes lelket kiszívni,
     * emiatt ez a metódus nem csinál semmit.
     */
    public void kill() {}
    /**
     * A függvény segítségével képes egy hallgató felvenni a
     * szobában lévő tárgyat, így a tárgy bekerül az eszköztárába.
     * @param item A felvenni kívánt tárgy
     */
    @Override
    public boolean pickUp(Item item) {
        if(this.getInventory().size() >= 5) {
            return false;
        }
        if (this.getLocation().getStickylimit() == 0) {
            return false;
        }
        item.setOwner(this);
        this.getInventory().add(item);
        this.getLocation().deleteItem(item);
        item.onPickUp();
        return true;
    }

    /**
     * A függvény segítségével képes egy hallgató,
     * az eszköztárából a szobába letenni a kiválasztott tárgyat.
     * @param item A ledobni kívánt tárgy
     */
    public void drop(Item item) {

        this.getInventory().remove(item);
        item.setOwner(null);
        getLocation().putItem(item);
    }

    /**
     * A függvény segítségével képes egy hallgató,
     * az eszköztárából elégetni a kiválasztott tárgyat.
     * @param item Az elégetni kívánt tárgy.
     */
    public void discard(Item item) {

        for(int i = 0; i < this.getInventory().size(); i++) {
            if(this.getInventory().get(i).equals(item)) {
                this.getInventory().get(i).destruct();
               // this.getInventory().remove(i);
                return;
            }
        }
    }

    /**
     *  Mivel egy hallgató nem képes megbénulni, emiatt
     *  ez a függvény nem csinál semmit.
     */
    public void stun() {}

    /**
     * A hallgató mozgását megvalósító függvény.
     */
    public boolean move(Room whereto, int param) {

        for (int i = 0; i < this.getLocation().getNeighbours().size(); i++) {
            if (this.getLocation().getNeighbours().get(i).equals(whereto)) {
            if(this.getLocation().getNeighbours().get(i).getCharacters().size() < this.getLocation().getNeighbours().get(i).getCapacity()) {
                this.getLocation().deleteCharacter(this);
                this.getLocation().getNeighbours().get(i).addCharacter(this);
                this.setLocation(this.getLocation().getNeighbours().get(i));

                if(this.getLocation().isCleaned()) {
                    if (this.getLocation().getStickylimit() > 0) {
                        this.getLocation().setStickylimit(this.getLocation().getStickylimit() - 1);
                        if (this.getLocation().getStickylimit() == 0) {
                            this.getLocation().addProperty(new Sticky());
                            System.out.println("limit exceeded - room " + param + " became slippery");
                        }
                    }
                }

                if(this.getLocation().isPoisonous()) {
                    if (!this.getPoisonProtection()) {
                        JOptionPane.showMessageDialog(null, "You have been poisoned.");
                        KnockedOut ko = new KnockedOut(this, 2);
                        this.addProperty(ko);
                        this.knockOut();
                    }
                }
                return true;
            }
            else {
                return false;
            }
            }
        }
        return false;
    }

    /**
     * A CharacterPropertyk helyes kezelését megvalósító függvény.
     */

    public void deleteCharacterProp(){

        boolean ispoprotected =getPoisonProtection();
        boolean issoprotected =getSoulProtection();

        this.getProperties().clear();

        if(ispoprotected){

            this.getProperties().add(new PoisonProtection(this,2));
        }
        if(issoprotected){
            this.getProperties().add(new SoulProtection(this,2));
        }
    }

}

