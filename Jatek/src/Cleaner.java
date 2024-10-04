import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Cleaner extends Character {

    /**
     * A Cleaner osztály konstruktora
     * @param pInventory - a karakter inventory-ja
     * @param pName - a karakter neve
     * @param pStunned - a karakter stunned állapota
     * @param pCharacterProperties - a karakter tulajdonságai
     * @param pLocation - a karakter jelenlegi helye
     * @param pPoisonProtection - a karakter mérgezés elleni védelme
     * @param pSoulProtection - a karakter lelki védelme
     */
    public Cleaner(List<Item> pInventory, String pName, boolean pStunned, List<CharacterProperty> pCharacterProperties, Room pLocation, boolean pPoisonProtection, boolean pSoulProtection) {
        super(pInventory, pName, pStunned, pCharacterProperties, pLocation, pPoisonProtection, pSoulProtection);
    }


    /**
     * A Cleaner karakter mozgatását végző függvény
     * Amennyiben a karaktert tartalmazó szobának van szomszédja, akkor moveCharacter függvény segítségével mozgatja a karaktert
     * Ha nincs a szobának szomszédja, akkor a Cleaner karakter kihagyja a körét
     */
    @Override
    public void managedbyComputer(Game game) {

        Random random = new Random();

        if(!this.getLocation().getNeighbours().isEmpty()) {
            for (int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {

                if (game.getLabyrinth().getRooms().get(i).getStickyduration() > 0) {
                    game.getLabyrinth().getRooms().get(i).setStickyduration(game.getLabyrinth().getRooms().get(i).getStickyduration() - 1);
                    if (game.getLabyrinth().getRooms().get(i).getStickyduration() == 0) {
                        game.getLabyrinth().getRooms().get(i).setStickyduration(-1);
                        game.getLabyrinth().getRooms().get(i).setStickylimit((int) Math.floor(game.getLabyrinth().getRooms().get(i).getCapacity() * 0.8));
                    }
                }
            }

            int r = random.nextInt(5);
            int z = -1;
            for (int i = 0; i < this.getLocation().getNeighbours().size(); i++) {
                if (r % 2 == 0) {
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

    /**
     * A Cleaner karakter mozgatását végző függvény
     */
    public boolean move(Room whereto, int param) {
        for (int i = 0; i < this.getLocation().getNeighbours().size(); i++) {
            if (this.getLocation().getNeighbours().get(i).equals(whereto)) {
                if (this.getLocation().getNeighbours().get(i).getCharacters().size() < this.getLocation().getNeighbours().get(i).getCapacity()) {
                    this.getLocation().deleteCharacter(this);
                    this.getLocation().getNeighbours().get(i).addCharacter(this);
                    this.setLocation(this.getLocation().getNeighbours().get(i));
                    onEntryClean();
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
     * A Cleaner karakter tárggyal történő interakcióját végző függvény
     * @param item - az Item, amivel a karakter interakcióba lép
     * @return - visszatérési érték
     */
    @Override
    public boolean pickUp(Item item) {
        return false;
    }

    /**
     * A Cleaner karakter fő műveletét, a szoba kitakarítását végrehajtó függvény
     * A Cleaner karakter kitakarítja a szobát, amiben éppen tartózkodik és a nem stunnolt karaktereket a szomszédos szobákba mozgatja
     */
    public void onEntryClean() {

        for (int i = 0; i < this.getLocation().getCharacters().size(); i++) {
            if (this.getLocation().getCharacters().get(i) != this && !this.getLocation().getCharacters().get(i).getStunned() && !this.getLocation().getCharacters().get(i).getKnockedOut()) {
                int x = 0;
                while (x < this.getLocation().getNeighbours().size()) {
                    boolean moved = this.getLocation().getCharacters().get(i).move(this.getLocation().getNeighbours().get(x), x);
                    if(moved) {
                        break;
                    }
                    x++;
                }
            }
        }
        this.getLocation().setCleaned(true);
        this.getLocation().setStickyduration(-1);
        this.getLocation().setStickylimit((int) Math.floor(getLocation().getCapacity()*0.8));
        this.getLocation().deleteProperties(true);
        this.getLocation().setPoisonous(false);
    }
}
