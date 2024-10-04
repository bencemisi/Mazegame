import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Az osztály felelőssége a felhasználótól érkező parancsok feldolgozása és a parancsok kimenetének konzolra írása.
 */
public class Controller {

    private Game game;
    private ArrayList<Character> characters;
    private ArrayList<Room> rooms;
    private SlipStick slipStick;
    private boolean acceptCommands;
    private Scanner consoleScanner;
    private Scanner fileScanner;
    private boolean started = false;
    /**
     * A Controller osztály konstruktora
     */
    public Controller(){

        characters = new ArrayList<>();
        rooms = new ArrayList<>();
        SlipStick logarlec =new SlipStick("Logarlec",1,false,null,null);
        game = new Game(characters,new Labyrinth(rooms),logarlec);
        acceptCommands = true;
    }

    /**
     * A játék elindítása
     */
    private void createGame() {
        game = new Game(characters, new Labyrinth(rooms), slipStick); // mi értelme van itt a slipsticket átadni? sokkal bonyolultabb lesz az egész
        game.startGame();
    }

    /**
     * A felhasználó által kiadott parancsok feldolgozása és a megfelelő metódusok meghívása
     * @param command A parancs
     *
     */

    private void processCommand(String command) {
        String[] commandPieces = command.split(" ");

        if(!command.isEmpty() && commandPieces.length > 0){

                switch (commandPieces[0]) {

                    case "add" -> addHandler(commandPieces);
                    case "connect" -> connectHandler(commandPieces);
                    case "put" -> putHandler(commandPieces);
                    case "controller" -> controllerHandler(commandPieces);
                    case "load" -> loadHandler(commandPieces);
                    case "reset" -> resetHandler(commandPieces);
                    case "start" -> startHandler(commandPieces);
                    case "unconnect" -> unconnectHandler(commandPieces);
                    case "move" -> moveHandler(commandPieces);
                    case "pickup" -> pickupHandler(commandPieces);
                    case "discard" -> discardHandler(commandPieces);
                    case "drop" -> dropHandler(commandPieces);
                    case "use" -> useHandler(commandPieces);
                    case "kill" -> killHandler(commandPieces);
                    case "skip" -> skipHandler(commandPieces);
                    case "print" -> printHandler(commandPieces);
                    default -> System.out.println("No such command.");
                }
        }
    }

    /**
     * A konzolos mód indítása
     */
    public void consoleMode() {
        consoleScanner = new Scanner(System.in);
        String command;

        while(acceptCommands){
            command = consoleScanner.nextLine();
            processCommand(command);
        }
    }

    /**
     * A fájlból való olvasás mód indítása
     * @param filename A fájl neve
     */
    public void fileMode(String filename) {
        try{
            fileScanner = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }

        String command;

        while(fileScanner.hasNextLine()){
            command = fileScanner.nextLine();
            processCommand(command);
        }
    }
    /**
     * Az add feldolgozása
     * @param command A parancs
     */
    private void addHandler(String[] command){

        switch (command[1]) {
            case "room" -> {
                if (command.length == 3) {
                    int idx;
                    try {
                        idx = Integer.parseInt(command[2]);
                        game.getLabyrinth().addRoom(idx);
                    } catch (NumberFormatException ignored) {

                    }
                }
            }
            case "character" -> {
                if (command.length == 5) {
                    int idx;
                    try {
                        idx = Integer.parseInt(command[4]);
                        game.getLabyrinth().addCharacter(command[2], command[3], idx, game);
                    }
                    catch (NumberFormatException ignored) {

                    }
                }
            }
            case "item" -> {
                if (command.length == 4) {
                    int idx;
                    try {
                        idx = Integer.parseInt(command[3]);
                        game.getLabyrinth().addItem(command[2], idx, game);
                    } catch (NumberFormatException ignored) {

                    }
                }
            }
            default -> System.out.println("No such command.");
        }
    }

    /**
     * A connect feldolgozása
     * @param command A parancs
     */
    private void connectHandler(String[] command){

        if(command.length == 4){
            int idx1, idx2, mutual;
            try{
                idx1 = Integer.parseInt(command[1]);
                idx2 = Integer.parseInt(command[2]);
                mutual = Integer.parseInt(command[3]);
                game.getLabyrinth().connectRooms(idx1, idx2, mutual);
            } catch (NumberFormatException ignored){

            }
        }
    }

    /**
     * Az unconnect feldolgozása
     * @param command A parancs
     */
    private void unconnectHandler(String[] command){
        if(command.length == 3){
            int idx1, idx2;
            try{
                idx1 = Integer.parseInt(command[1]);
                idx2 = Integer.parseInt(command[2]);
                game.getLabyrinth().unconnectRooms(idx1, idx2);
            } catch (NumberFormatException ignored){

            }
        }
    }
    /**
     * A put feldolgozása
     * @param command A parancs
     */
    private void putHandler(String[] command){

        if(command.length == 4 && command[1].equals("item")){
            game.getLabyrinth().putItem(command[2], command[3]);
        }
    }
    /**
     * A controller által kiadott parancsok feldolgozása
     * @param command A parancs
     */
    private void controllerHandler(String[] command){

        if(command.length > 1){
            switch (command[1]) {
                case "poisonous" -> {
                    if (command.length == 3) {
                        int idx;
                        try{
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().makePoisonous(idx);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "cursed" -> {
                    if (command.length == 3) {
                        int idx;
                        try{
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().makeCursed(idx);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "slippery" -> {
                    if (command.length == 3) {
                        int idx;
                        try{
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().makeSlippery(idx);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "merge" -> {
                    if (command.length == 4) {
                        int idx1, idx2;
                        try {
                            idx1 = Integer.parseInt(command[2]);
                            idx2 = Integer.parseInt(command[3]);
                            game.getLabyrinth().mergeRooms(idx1, idx2);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "split" -> {
                    if (command.length == 3) {
                        int idx;
                        try{
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().splitRoom(idx);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "toggle" -> {
                    if(command.length == 3){
                        if(command[2].equals("random")){
                            Main.randomize = !Main.randomize;
                        }
                    }
                }

                case "cleaned" -> {
                    if (command.length == 3) {
                        int idx;
                        try{
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().getRooms().get(idx).setCleaned(true);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
            }
        }
    }

    /**
     * A load feldolgozása
     * @param command A parancs
     */
    private void loadHandler(String[] command){

        if(command.length == 3 && command[1].equals("state")){
            this.fileMode(command[2]);
        }

    }
    /**
     * A reset feldolgozása
     * @param command A parancs
     */
    private void resetHandler(String[] command){

        if(command.length == 3 && command[1].equals("state")){
            game.getLabyrinth().reset();
        }

    }
    /**
     * A start feldolgozása
     * @param command A parancs
     */
    private void startHandler(String[] command){
        if(command.length == 2 && command[1].equals("game")){
            game.startGame();
            started = true;
        }
    }
    /**
     * A move feldolgozása
     * @param command A parancs
     */
    private void moveHandler(String[] command){

        if(command.length == 2){
            int idx;
            try{
                idx = Integer.parseInt(command[1]);
               if(game.moveCharacter(idx))
                game.skipTurn_Action();
            } catch (NumberFormatException ignored){

            }
        }

    }
    /**
     * A pickup feldolgozása
     * @param command A parancs
     */
    private void pickupHandler(String[] command){

        if(command.length == 2){
            int idx;
            try{
                idx = Integer.parseInt(command[1]);
                game.pickUpItem(idx);
                game.skipTurn_Action();
            } catch (NumberFormatException ignored){

            }
        }
    }
    /**
     * A discard feldolgozása
     * @param command A parancs
     */
    private void discardHandler(String[] command){

        if(command.length == 2){
            int idx;
            try{
                idx = Integer.parseInt(command[1]);
                game.discardItem(idx);
                game.skipTurn_Action();
            } catch (NumberFormatException ignored){

            }
        }
    }
    /**
     * A drop feldolgozása
     * @param command A parancs
     */
    private void dropHandler(String[] command){

        if(command.length == 2){
            int idx;
            try{
                idx = Integer.parseInt(command[1]);
                game.dropItem(idx);
                game.skipTurn_Action();
            } catch (NumberFormatException ignored){

            }
        }
    }
    /**
     * A use feldolgozása
     * @param command A parancs
     */
    private void useHandler(String[] command){

        if(command.length == 2){
            int idx;
            try{
                idx = Integer.parseInt(command[1]);
                game.useItem(idx);
                game.skipTurn_Action();
            } catch (NumberFormatException ignored){

            }
        }
    }
    /**
     * A kill feldolgozása
     * @param command A parancs
     */
    private void killHandler(String[] command){

        if(command.length == 1){
            game.killCharacter();
            game.skipTurn_Action();
        }
    }
    /**
     * A skip feldolgozása
     * @param command A parancs
     */
    private void skipHandler(String[] command){

        if(command.length == 1){
            game.skipTurn();
        }
    }
    /**
     * A print feldolgozása
     * @param command A parancs
     */
    private void printHandler(String[] command){

        if(command.length > 1){

            switch (command[1]){
                case "inventory" -> {
                    if(command.length == 3){
                        game.printInventory(command[2]);
                    }
                }
                case "currRoom" -> {
                    if(command.length == 3){
                        game.printCurrRoom(command[2]);
                    }
                }
                case "room" -> {
                    if(command.length == 3){
                        int idx;
                        try {
                            idx = Integer.parseInt(command[2]);
                            game.getLabyrinth().printRoom(idx);
                        } catch (NumberFormatException ignored){

                        }
                    }
                }
                case "labyrinth" -> {
                    if (command.length == 2) {
                        game.getLabyrinth().printLabyrinth();
                    }
                }
            }
        }

    }

}
