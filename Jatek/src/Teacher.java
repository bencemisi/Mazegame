import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ez az osztály felel az oktatók viselkedésének a megvalósításáért.
 */
public class Teacher extends Character {
    /**
     * Paraméteres kontruktor
     *
     * @param pInventory           A karakternél lévő tárgyak.
     * @param pName                A karakter neve
     * @param pStunned             A karakter bénított állapotát jelző érték.
     * @param pCharacterProperties A karakterre érvényben lévő állapotok.
     * @param pLocation            Azon szoba amelyben a karakter tartózkodik.
     * @param pPoisonProtection    A karakter méreg elleni védettségét jelző érték.
     * @param pSoulProtection      A karakter oktató lélek kiszívása elleni védettségét jelző érték.
     */
    public Teacher(List<Item> pInventory, String pName, boolean pStunned, List<CharacterProperty> pCharacterProperties, Room pLocation, boolean pPoisonProtection, boolean pSoulProtection) {
        super(pInventory, pName, pStunned, pCharacterProperties, pLocation, pPoisonProtection, pSoulProtection);
    }

    /**
     * Mivel egy oktató nem tud tárgyat használni a metódus nem csinál semmit.
     *
     * @param item A használni kívánt tárgy.
     */
    public void useItem(Item item) {

        for (int i = 0; i < getInventory().size(); i++) {
            if (getInventory().get(i).equals(item)) {
                getInventory().get(i).destruct();
                return;
            }
        }

    }

    /**
     * Mivel egy oktató nem tud meghalni, ezért a metódus nem csinál semmit.
     */
    public void death() {
    }

    /**
     * Az oktató a vele egy szobában lévő hallgatók lelkét kiszívja.
     */
    public void kill(Game game) {

        List<Character> temp = new ArrayList<>(getLocation().getCharacters());
        for (Character character : temp) {
            if(character.getSoulProtection()){
                for (int i = 0; i < character.getProperties().size(); i++) {
                    character.getProperties().get(i).effect();
                }
            }
            if (!character.getSoulProtection()) {
                character.death(game);
            }
        }
        System.out.println("teacher tried to kill the students in the room");
    }

    /**
     * A függvény segítségével képes egy oktató felvenni a szobában lévő tárgyat,
     * így a tárgy bekerül az eszköztárába.
     *
     * @param item A felvenni kívánt tárgy
     */
    @Override
    public boolean pickUp(Item item) {
        item.setOwner(this);
        this.getLocation().deleteItem(item);
        discard(item);
        return true;
    }

    /**
     * A függvény segítségével képes egy oktató , az eszköztárából a
     * szobába letenni a kiválasztott tárgyat.
     *
     * @param item A ledobni kívánt tárgy
     */
    public void drop(Item item) {
        this.getInventory().remove(item);
        item.setOwner(null);
        this.getLocation().putItem(item);
    }

    /**
     * A függvény segítségével képes egy oktató ,
     * az eszköztárából elégetni a kiválasztott tárgyat.
     *
     * @param item Az elégetni kívánt tárgy.
     */
    public void discard(Item item) {
        item.destruct();
    }

    /**
     * Az oktató megbénításáért felelős függvény.
     */
    public void stun() {
        JOptionPane.showMessageDialog(null, "You have stunned " + this.getName());
        Stunned stun = new Stunned(this, 2);
        this.addProperty(stun);
        stun.effect();
    }

    /**
     * Az oktató mozgását megvalósító függvény.
     */
    public boolean move(Room whereto, int param) {

        for (int i = 0; i < this.getLocation().getNeighbours().size(); i++) {

            if (this.getLocation().getNeighbours().get(i).equals(whereto)) {

                if (this.getLocation().getNeighbours().get(i).getCharacters().size() < this.getLocation().getNeighbours().get(i).getCapacity()) {

                    this.getLocation().deleteCharacter(this);
                    this.getLocation().getNeighbours().get(i).addCharacter(this);
                    this.setLocation(this.getLocation().getNeighbours().get(i));

                    if (this.getLocation().getNeighbours().get(i).isPoisonous()) {
                        if (!this.getPoisonProtection()) {
                            KnockedOut ko = new KnockedOut(this, 5);
                            this.addProperty(ko);
                            this.knockOut();
                        }
                    }

                    if (this.getLocation().isCleaned()) {
                        if (this.getLocation().getStickylimit() > 0) {
                            this.getLocation().setStickylimit(this.getLocation().getStickylimit() - 1);
                            if (this.getLocation().getStickylimit() == 0) {
                                this.getLocation().addProperty(new Sticky());
                                System.out.println("limit exceeded - room " + param + " became slippery");
                            }
                        }
                    }
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }


    /**
     * Az oktató viselkedését szimuláló függvény.
     * Ez biztosítja, hogy a számítógép irányítsa az oktatót.
     */
    @Override
    public void managedbyComputer(Game game) {

        Random rand = new Random();
        int r = rand.nextInt(5);

        if (r == 0 ) {
            if(!this.getLocation().getNeighbours().isEmpty()) {
                var g = rand.nextInt(5);
                var z = -1;
                for (int i = 0; i < this.getLocation().getNeighbours().size(); i++) {
                    if (g % 2 == 0) {
                        z = i;
                    }
                    if (z != -1) {
                        break;
                    }
                }
                if (z == -1) {
                    z = 0;
                }
                for (int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
                    if (this.getLocation().getNeighbours().get(z) == game.getLabyrinth().getRooms().get(i)) {
                        z = i;
                        break;
                    }
                }
                game.moveCharacter(z);
                game.skipTurn_Action();
            }else{
                game.skipTurn_Action();
            }
        }
        else if (r == 1) {
           game.killCharacter();
            game.skipTurn_Action();
        }
        else if (r == 2 && !this.getLocation().getItems().isEmpty() ) {
            int b = -1;
            for(int z = 0; z < this.getLocation().getItems().size(); z++) {
                int g = rand.nextInt(10);
                if(g % 2 == 0) {
                    b = z;
                }
                if(b != -1) {
                    break;
                }
            }
            if(b == -1) {
                b = 0;
            }

            game.pickUpItem(b);
            game.skipTurn_Action();

        }
        else if(r == 3 && !this.getInventory().isEmpty()) {
            int b = getValueforManaging(rand);
            game.dropItem(b);
            game.skipTurn_Action();

        }
        else {
            if(!this.getInventory().isEmpty()) {
                int b = getValueforManaging(rand);
                game.discardItem(b);
                game.skipTurn_Action();

            }else{
                game.skipTurn_Action();
            }

        }
    }

    /**
     * A oktató számítógép által történő irányításához szükséges függvény.
     */
    public int getValueforManaging(Random rand) {
        int b = -1;
        for(int z = 0; z < getInventory().size(); z++) {
            int g = rand.nextInt(10);
            if(g % 2 == 0) {
                b = z;
            }
            if(b != -1) {
                break;
            }
        }
        if(b == -1) {
            b = 0;
        }
        return b;
    }

    /**
     * Az oktató karakter tulajdonságait törli.
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
