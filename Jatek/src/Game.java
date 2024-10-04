import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Az osztály a játék indításáért és a körök menedzseléséért felelős,
 * illetve lehetővé teszi a karakterek számára, hogy akciót hajtsanak végre
 * a körön belül (azaz “sorra kerüljenek”). A játék végének kezelése is az
 * osztály felelőssége.
 */
public class Game {
    /** A játékban részt vevő karakterek*/
    private List<Character> characters;

    /** A jelenlegi karakter*/
    Character actualCharacter;

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    /** A játétérként szolgáló labirintus*/
    private Labyrinth labyrinth = new Labyrinth(new ArrayList<>());

    /** A játék végéhez használandó változó*/
    private boolean game_won = false;

    /**
     * A SlipStick gettere.
     * @return
     */
    public SlipStick getSlipStick() {
        return SlipStick;
    }

    /**
     * A SlipStick settere.
     * @param slipStick
     */
    public void setSlipStick(SlipStick slipStick) {
        SlipStick = slipStick;
    }

    /** A Logarléc amit ha felvesznek a hallagtók vége a játéknak.*/
    private SlipStick SlipStick;


    /**
     * Visszaadja a jelenlegi karaktert.
     * @return A jelenlegi karakter
     */
    public Character getActualCharacter() {
        return actualCharacter;
    }

    /**
     * Paraméteres konstruktor
     * @param pCharacters A játékban részt vevő karakterek
     * @param pLabyrinth A játétérként szolgáló labirintus
     * @param pSlipStick A Logarléc amit ha felvesznek a hallgatók vége a játéknak.
     */
    public Game(List<Character> pCharacters, Labyrinth pLabyrinth, SlipStick pSlipStick) { // ezt a konstruktort át kellene gondolni
        this.characters = pCharacters;
        this.labyrinth = pLabyrinth;
        SlipStick = pSlipStick;
    }
    /**
     * Játék indítása, kezdőállapot betöltése.
     */
    public void startGame() {
        SlipStick.setGame(this);
        System.out.println("game started");
        nextRound();
    }

    /**
     * A karakterek listájához hozzáad egy új karaktert.
     * @param character A karakter, aki hozzáadásra kerül a listához
     */
    public void addCharacter(Character character) {
        characters.add(character);
    }

    /**
     * A karakterek listáját visszaadó függvény
     */
    public List<Character> getCharacters() {
        return characters;
    }


    /**
     * Következő kör indításának megvalósítása.
     */
    public void nextRound() {

        if(actualCharacter!=null) {
            for (int i = 0; i < actualCharacter.getProperties().size(); i++) {
                actualCharacter.getProperties().get(i).lower();
                if (actualCharacter.getProperties().get(i).getTimelimit() == 0) {
                    actualCharacter.getProperties().get(i).effect();
                    actualCharacter.getProperties().remove(this);
                }
            }
        }
        if(game_won) {
            return;
        }

        if(characters.size() < 5){
            JOptionPane.showMessageDialog(null, "All students have been killed. Teachers won");
            System.exit(0);
            return;
        }

        for(int i=0; i < characters.size(); i++) {
            if (characters.get(i).getLocation() == null) {
                characters.remove(characters.get(i));
            }
        }

        if(characters.size() < 5) {
            JOptionPane.showMessageDialog(null, "All students have been killed. Teachers won");
            System.exit(0);
        }

        if(!characters.isEmpty() && actualCharacter==null) {
            actualCharacter=characters.get(0);
            System.out.println("its "+ actualCharacter.getName()+"s turn!");
            actualCharacter.managedbyComputer(this);
        }

        else {
            //actualCharacter.propertyUpdate();
            for (int i = 0; i < characters.size(); i++) {
                if (characters.get(i).equals(actualCharacter)) {
                    if (characters.size() - 1 == i) {
                        randomizeRooms();
                        actualCharacter = characters.get(0);
                        System.out.println("its " + actualCharacter.getName() + "s turn!");
                        if(actualCharacter.getStunned()) {
                            stunnedskip();
                        } else if (actualCharacter.getKnockedOut()) {
                            knockedskip();
                        }
                        if(characters.size() < 5) {
                            JOptionPane.showMessageDialog(null, "All students have been killed. Teachers won");
                            System.exit(0);
                        }
                        actualCharacter.managedbyComputer(this);
                        break;
                    }
                    else {
                        actualCharacter = characters.get(i + 1);

                        if(actualCharacter.getStunned()){
                            stunnedskip();
                        } else if (actualCharacter.getKnockedOut()) {
                            knockedskip();
                        }
                        if(characters.size() < 5) {
                            JOptionPane.showMessageDialog(null, "All students have been killed. Teachers won");
                            System.exit(0);
                        }
                        actualCharacter.managedbyComputer(this);
                        break;
                    }
                }
            }
        }
    }

    /**
     * A karakterek akciójának megvalósítása.
     * @param character A karakter aki végrehajtja az akciót.
     */
    public void action(Character character) {

        if(character.getKnockedOut()){
            knockedskip();
        } else if (character.getStunned()) {
            stunnedskip();
        } else {
            actualCharacter = character;
        }

    }
    /**
     * A játék végének kezelése attól függően, hogy ki nyert.
     * @param result A hallgatók szempontjából ki nyerte a játékot
     *               true = a hallgatók, false = az oktatók     */
    public void endGame(boolean result) {
        if(result){
            JOptionPane.showMessageDialog(null, "You have picked up the SlipStick. Students won");
            System.out.println("Students won the game");
            game_won = true;
            System.exit(0);
        }
    }

    /**
     * A szobák randomizálását megvalósító függvény.
     * A szobák vagy összeolvadnak, vagy szétválnak
     * A Cursed szobák ajtajai időnként eltűnnek vagy újra megjelennek
     */
    public void randomizeRooms() {

        Random rand = new Random();
        int r = rand.nextInt(1000);

        if(r % 25 == 0) {
            int size = labyrinth.getRooms().size();
            int firstroomindex = rand.nextInt(1, size);
            int neighbours = labyrinth.getRooms().get(firstroomindex - 1).getNeighbours().size();
            if(neighbours > 0){
                int secondroomindex = rand.nextInt(neighbours);
                labyrinth.mergeRooms(firstroomindex, secondroomindex);
            }
        }

        //splitroom
        if(r % 40 == 0) {
            int size = labyrinth.getRooms().size();
            int roomindex = rand.nextInt(size);
            labyrinth.splitRoom(roomindex);
        }

        //cursed rooms unconnect
        if (r % 15 == 0) {
           for (int i = 0; i < labyrinth.getRooms().size(); i++) {
               if (labyrinth.getRooms().get(i).isCursed()) {
                  for (int x = 0; x < labyrinth.getRooms().get(i).getNeighbours().size(); x++) {
                      int g = 0;
                      for(int z = 0; z < labyrinth.getRooms().size(); z++) {
                          if(labyrinth.getRooms().get(z) == labyrinth.getRooms().get(i).getNeighbours().get(x)) {
                              g = z;
                              break;
                          }
                      }
                      labyrinth.unconnectRooms(i, g);
                  }
               }
           }
        }

        //cursed rooms reconnect
        if(r % 12 == 0) {
            for(int i = 0; i < labyrinth.getRooms().size(); i++) {

                if (labyrinth.getRooms().get(i).isCursed()) {
                    if (labyrinth.getRooms().get(i).getPreviousNeighbours() != labyrinth.getRooms().get(i).getNeighbours()) {
                        for (int x = 0; x < labyrinth.getRooms().get(i).getPreviousNeighbours().size(); x++) {
                            int g = 0;
                            for (int z = 0; z < labyrinth.getRooms().size(); z++) {
                                if (labyrinth.getRooms().get(z) == labyrinth.getRooms().get(i).getPreviousNeighbours().get(x)) {
                                    g = z;
                                    break;
                                }
                            }
                            labyrinth.connectRooms(i, g, 1);
                        }
                        labyrinth.getRooms().get(i).setPreviousNeighbours(labyrinth.getRooms().get(i).getNeighbours());
                    }
                }
            }
        }
    }


    /**
     * A karakter mozgatását megvalósító függvény.
     * @param idx A szoba indexe, ahova a karakter mozogni szeretne
     * @return A karakter sikeresen mozgott-e
     */
    public boolean moveCharacter(int idx){

        if(idx > labyrinth.getRooms().size()-1 || idx < 0){
            System.out.println("Hibás szoba paraméter");
            return false;
        }

        if(actualCharacter.move(labyrinth.getRooms().get(idx), idx)){
            System.out.println("player " + actualCharacter.getName() + " moved to " + idx + ".room");
            return true;
        }else{
            System.out.println("player " + actualCharacter.getName() + " tried to move to " + idx + ".room, but could not do it");
            return false;
        }
    }

    /**
     * A karakter tárgy felvételét megvalósító függvény.
     * @param idx A tárgy indexe, amit a karakter felvenni szeretne
     */
    public void pickUpItem(int idx){

        if(idx > actualCharacter.getLocation().getItems().size()-1|| idx< 0){
            System.out.println("Hibás pickup item paraméter");
            return;
        }
        int index = labyrinth.getRooms().indexOf(actualCharacter.getLocation());

        if(actualCharacter.getInventory().size() == 5) {
            JOptionPane.showMessageDialog(null, "Could not pick up an item, because inventory is full");
            System.out.println("player " + actualCharacter.getName() + " tried to pick up item but his inventory full");
            return;
        }

        if(actualCharacter.getLocation().getStickylimit() == 0) {
            JOptionPane.showMessageDialog(null, "Could not pick up an item, because room is sticky");
            System.out.println(actualCharacter.getName() + " could not pick up item, because room is slippery");
            return;
        }
        System.out.println("player " + actualCharacter.getName() + " picked up " + idx + ".item (" + actualCharacter.getLocation().getItems().get(idx).getName() + ") from " + index + ".room");
        if (actualCharacter.pickUp(actualCharacter.getLocation().getItems().get(idx))){
           // System.out.println("player " + actualCharacter.getName() + " picked up " + idx + ".item " + actualCharacter.getLocation().getItems().get(idx).getName() + " from " + ".room");
        }
    }

    /**
     * A karakter tárgy eldobását megvalósító függvény.
     * @param idx A tárgy indexe, amit a karakter eldobni szeretne
     */
    public void discardItem(int idx){

        if(idx > actualCharacter.getInventory().size()-1|| idx< 0){
            System.out.println("Hibás discard item paraméter");
            return;
        }


        System.out.println("player " + actualCharacter.getName() + " discarded " + idx + ".item (" + actualCharacter.getInventory().get(idx).getName() + ") from inventory");
        actualCharacter.discard(actualCharacter.getInventory().get(idx));

    }

    /**
     * A karakter tárgy eldobását megvalósító függvény.
     * @param idx A tárgy indexe, amit a karakter eldobni szeretne
     */
    public void dropItem(int idx){

        if(idx > actualCharacter.getInventory().size()-1|| idx< 0){
            System.out.println("Hibás drop item paraméter");
            return;
        }

        int szobaindex=-1;
        Room szobaja = actualCharacter.getLocation();
        for(int i = 0; i<this.getLabyrinth().getRooms().size();i++){
            if(szobaja==this.getLabyrinth().getRooms().get(i)){
                szobaindex= i;
                break;
            }
        }
        if(szobaindex==-1){
            System.out.println("Hibás szoba index");
            return;
        }

        System.out.println("player " + actualCharacter.getName() + " dropped " + idx + ".item (" + actualCharacter.getInventory().get(idx).getName() + ") from inventory to " + szobaindex + ".room");
        actualCharacter.drop(actualCharacter.getInventory().get(idx));
    }

    /**
     * A karakter tárgy használatát megvalósító függvény.
     * @param idx A tárgy indexe, amit a karakter használni szeretne
     */
    public void useItem(int idx) {
        if(idx > actualCharacter.getInventory().size()-1|| idx< 0){
            System.out.println("Hibás use item paraméter");
            return;
        }

        int szobaindex=-1;
        Room szobaja = actualCharacter.getLocation();
        for(int i = 0; i<this.getLabyrinth().getRooms().size();i++){
            if(szobaja==this.getLabyrinth().getRooms().get(i)){
                szobaindex= i;
                break;
            }
        }

        Item item =  actualCharacter.getInventory().get(idx);
        actualCharacter.useItem(actualCharacter.getInventory().get(idx));

        int szobaindex2 = -1;
        szobaja = actualCharacter.getLocation();
        for(int i = 0; i<this.getLabyrinth().getRooms().size();i++){
            if(szobaja==this.getLabyrinth().getRooms().get(i)){
                szobaindex2 = i;
                break;
            }
        }
        System.out.println(item.useToString(actualCharacter.getName(), szobaindex, szobaindex2));
    }

    /**
     * A karakter támadását megvalósító függvény.
     */
    public void killCharacter() {
        actualCharacter.kill(this);
    }

    /**
     * A karakter skippelését megvalósító függvény.
     */
    public void skipTurn_Action(){
        nextRound();
    }

    /**
     * A karakter skippelését megvalósító függvény, amennyiben le van stunolva.
     */
    public void stunnedskip(){
        System.out.println(actualCharacter.getName() + " is stunned");
        actualCharacter.setStunned(false);
        actualCharacter.deleteCharacterProp();
        nextRound();
    }

    /**
     * A karakter skippelését megvalósító függvény, amennyiben ki van ütve.
     */
    public void knockedskip(){
        System.out.println(actualCharacter.getName() + " is knocked out");
        actualCharacter.setKnockedOut(false);
        actualCharacter.deleteCharacterProp();
        nextRound();
    }

    /**
     * A karakter skippelését megvalósító függvény.
     */
    public void skipTurn(){
        System.out.println(actualCharacter.getName() + " has skipped a turn");
        nextRound();
    }

    /**
     * A karakter inventoryjának kiírását megvalósító függvény.
     * @param characterName A karakter neve, akinek az inventoryját ki szeretnénk írni
     */
    public void printInventory(String characterName) {

        for(Character character : characters) {

            if(character.getName().equals(characterName)) {
                System.out.println(characterName+"s inventory:");
                for(int i = 0; i < character.getInventory().size(); i++){
                    System.out.println("details of item " + i);
                    System.out.println(character.getInventory().get(i).toString());
                }
                break;
            }
        }
    }

    /**
     * A karakter aktuális szobájának a paramétereinek a  kiírását megvalósító függvény.
     * @param characterName A karakter neve, akihez tartozó szoba paramétereit ki szeretnénk írni
     */
    public void printCurrRoom(String characterName){

        for(int i =0; i < labyrinth.getRooms().size(); i++) {
            if(labyrinth.getRooms().get(i).equals(actualCharacter.getLocation())) {
                System.out.println("details of room " + i + ":");
                System.out.println("cursed: " + labyrinth.getRooms().get(i).isCursed());
                System.out.println("poisonous: " + labyrinth.getRooms().get(i).isPoisonous());
                System.out.println("slippery: " + (labyrinth.getRooms().get(i).getStickyduration() != -1));
                System.out.println("cleaned: " + labyrinth.getRooms().get(i).isCleaned());
                System.out.println("capacity: " + labyrinth.getRooms().get(i).getCapacity());
                System.out.println("stickylimit: " + labyrinth.getRooms().get(i).getStickylimit());

                System.out.print("neighbours: ");
                for(int x = 0; x < labyrinth.getRooms().get(i).getNeighbours().size(); x++){
                    System.out.print("" + getLabyrinth().getRooms().indexOf(labyrinth.getRooms().get(i).getNeighbours().get(x)) +",");
                }
                System.out.println();
                System.out.println("items:");
                for(int x = 0; x < labyrinth.getRooms().get(i).getItems().size(); x++){
                    System.out.println("details of item " + i);
                    System.out.println(labyrinth.getRooms().get(i).getItems().get(x).toString());
                }
                break;
            }
        }
    }
}

