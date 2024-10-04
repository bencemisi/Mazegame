import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A játéktér létrehozásáért és a szobák nyilvántartásáért felelős osztály.
 */
public class Labyrinth {
    public List<Room> getRooms() {
        return rooms;
    }

    /**A szobák amelyekből a labirintus áll*/
    private List<Room> rooms;

    /**
     * Paraméteres konstruktor
     * @param pRooms A szobák amelyekből a labirintus áll
     */

    public Labyrinth(List<Room> pRooms) {
        this.rooms = pRooms;
    }

    public Labyrinth() {
        this.rooms = new ArrayList<Room>();
    }

    /**
     * A labirintus felépítését, a kezdeti állapotának beállítását valósítja meg.
     */
    public void initialize(){
        // ez mi a picsát csinál
    }

    /**
     * Új szoba hozzáadása a labirintushoz.
     * @param idx A szoba indexe
     */
    public void addRoom (int idx) {

        int capacity = 0;
        if(Main.randomize) {
            Random rnd = new Random();
             capacity = rnd.nextInt(2, 6);
        }else {
            capacity = 5;
        }

        if(idx>rooms.size()-1 || idx<-1){
            System.out.println("Hibas szoba parameter");
            return;             //Hibás parancs
        }
        if(idx == -1 && rooms.isEmpty()){
            rooms.add(new Room(capacity,false,false,false, (int) Math.floor(capacity*0.8),-1,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
            System.out.println("the first room has been added to the labyrinth");
        }else{
            rooms.add(new Room(capacity,false,false,false,(int) Math.floor(capacity*0.8),-1,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
                rooms.get(idx).addTwoWayDoor(rooms.get(rooms.size()-1));
            System.out.println("a room was added as a neighbour of "+ idx+".room");
        }
    }

    /**
     * Karakter hozzáadása a megadott szobához.
     * @param characterType A karakter típusa
     * @param name A karakter neve
     * @param roomIdx A szoba indexe
     */
    public void addCharacter(String characterType, String name, int roomIdx, Game game){
        if(roomIdx>rooms.size()-1 || roomIdx<0){
            System.out.println("Hibas szoba parameter");
            return;
        }

        switch (characterType){
            case "student" -> {
                Student student = new Student(new ArrayList<>(),name,false,new ArrayList<>(),rooms.get(roomIdx),false,false);
                rooms.get(roomIdx).addCharacter(student);
                game.addCharacter(student);
                System.out.println("a student was added to "+roomIdx+ ".room");
            }
            case "teacher" -> {
                Teacher teacher = new Teacher(new ArrayList<>(),name,false,new ArrayList<>(),rooms.get(roomIdx),false,false);
                rooms.get(roomIdx).addCharacter(teacher);
                game.addCharacter(teacher);
                System.out.println("a teacher was added to "+roomIdx+".room");}
            case "cleaner" -> {
                Cleaner cleaner = new Cleaner(new ArrayList<>(),name,false,new ArrayList<>(),rooms.get(roomIdx),false,false);
                rooms.get(roomIdx).addCharacter(cleaner);
                game.addCharacter(cleaner);
                System.out.println("a cleaner was added to "+roomIdx+".room");}
            default -> System.out.println("No such command.");
        }

    }

    /**
     * Tárgy hozzáadása a megadott szobához.
     * @param itemType A tárgy típusa
     * @param roomIdx A szoba indexe
     */
    public void addItem(String itemType, int roomIdx, Game game){

        if(roomIdx>rooms.size()-1 || roomIdx<0){
            System.out.println("Hibás szoba paraméter");
            return;             //Hibás parancs
        }
        switch (itemType){
            case "tvsz" -> {rooms.get(roomIdx).putItem(new TVSZ("tvsz",1, false, rooms.get(roomIdx), null));
                System.out.println("a tvsz was added to "+roomIdx+".room");
            }
            case "holybeermug" -> {rooms.get(roomIdx).putItem(new HolyBeerMug("holybeermug",1, false, rooms.get(roomIdx), null));
                System.out.println("a holybeermug was added to "+roomIdx+".room");}
            case "ffp2" -> {rooms.get(roomIdx).putItem(new FFP2Mask("FFP2Mask",1, false, rooms.get(roomIdx), null));
                System.out.println("a ffp2 was added to "+roomIdx+".room");}
            case "slipstick" -> {SlipStick logar = new SlipStick("slipstick",1, false, rooms.get(roomIdx), null);
                logar.setGame(game);
                game.setSlipStick(logar);
                rooms.get(roomIdx).putItem(logar);
                System.out.println("a slipstick was added to "+roomIdx+".room");}
            case "wettowel" -> {rooms.get(roomIdx).putItem(new WetTowel("wettowel",3, false, rooms.get(roomIdx), null));
                System.out.println("a wettowel was added to "+roomIdx+".room");}
            case "cabbagecamambert" -> {rooms.get(roomIdx).putItem(new CabbageCamambert("cabbagecamambert",1, false, rooms.get(roomIdx), null));
                System.out.println("a cabbagecamambert was added to "+roomIdx+".room");}
            case "airspray" -> {rooms.get(roomIdx).putItem(new AirSpray("airspray",1, false, rooms.get(roomIdx), null));
                System.out.println("a airspray was added to "+roomIdx+".room");}
            case "faketvsz" -> {rooms.get(roomIdx).putItem(new FakeTVSZ("tvsz",1, false, rooms.get(roomIdx), null));
                System.out.println("a faketvsz was added to "+roomIdx+".room");}
            case "fakeffp2" -> {rooms.get(roomIdx).putItem(new FFP2Mask("ffp2",1, false, rooms.get(roomIdx), null));
                System.out.println("a fakeffp2 was added to "+roomIdx+".room");}
            case "fakeslipstick" -> {rooms.get(roomIdx).putItem(new FakeSlipStick("slipstick",1, false, rooms.get(roomIdx), null));
                System.out.println("a fakeslipstick was added to "+roomIdx+".room");}
            case "transistor" -> {rooms.get(roomIdx).putItem(new Transistor("transistor",1, false, rooms.get(roomIdx), null, null));
                System.out.println("a transistor was added to "+roomIdx+".room");}
            default -> System.out.println("Hibás item név");
        }
    }
    /**
     * Szoba összekötése.
     * @param room1 Az egyik szoba indexe
     * @param room2 A másik szoba indexe
     * @param mutual A kapcsolat típusa
     */
    public void connectRooms(int room1, int room2, int mutual) {

        if(room1>rooms.size()-1 || room2> rooms.size()-1 || room1<0 || room2<0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        if(mutual==0){
            rooms.get(room1).addOneWayDoor(rooms.get(room2));
            System.out.println("a "+room1+".room became neighbour of "+room2+".room");
        } else if (mutual==1) {
            rooms.get(room1).addTwoWayDoor(rooms.get(room2));
            System.out.println("a "+room1+".room became neighbour of "+room2+".room");
            System.out.println("a "+room2+".room became neighbour of "+room1+".room");
        }else{
            System.out.println("Rossz mutual parameter");
        }
    }
    /**
     * Szoba összekötésének megszüntetése.
     * @param idx1 Az egyik szoba indexe
     * @param idx2 A másik szoba indexe
     */
    public void unconnectRooms(int idx1, int idx2){

       if(idx1 > rooms.size()-1 ||idx2 > rooms.size()-1 || idx1 < 0||idx2 < 0){
           System.out.println("Hibás szoba paraméter");
           return;
       }
       if(!rooms.get(idx1).getNeighbours().contains(rooms.get(idx2))){
           System.out.println("A két szoba nem szomszédos");
           return;
       }else{
          rooms.get(idx1).deleteTwoWayDoor(rooms.get(idx2));
           System.out.println("a " + idx1 + ".room is not longer a neighbour of " + idx2 + ".room");
           System.out.println("a " + idx2 + ".room is not longer a neighbour of " + idx1 + ".room");
       }
    }


    /**
     * Tárgy hozzáadása a karakter eszköztárához
     * @param characterName A karakter neve
     * @param itemType A tárgy típusa
     */
    public void putItem(String characterName, String itemType){

        boolean van = false;
        Character modosit= null;
        for(int i = 0; i< rooms.size();i++){
            for(int j = 0; j < rooms.get(i).getCharacters().size();j++){
                if(characterName.equals(rooms.get(i).getCharacters().get(j).getName())){
                    van = true;
                    modosit = rooms.get(i).getCharacters().get(j);
                    break;
                }
            }
        }

        if(van){
            switch (itemType){
                case "tvsz" -> {modosit.getInventory().add(new TVSZ("tvsz",1, false, modosit.getLocation(), modosit));
                    System.out.println("a tvsz was added to "+modosit.getName()+"s inventory");}
                case "holybeermug" ->{modosit.getInventory().add(new HolyBeerMug("holybeermug",1, false, modosit.getLocation(), modosit));
                    System.out.println("a holybeermug was added to "+modosit.getName()+"s inventory");}
                case "ffp2" ->{modosit.getInventory().add(new FFP2Mask("ffp2",1, false, modosit.getLocation(), modosit));
                    System.out.println("a ffp2mask was added to "+modosit.getName()+"s inventory");}
                case "slipstick" ->{modosit.getInventory().add(new SlipStick("slipstick",1, false, modosit.getLocation(), modosit));
                    System.out.println("a slipstick was added to "+modosit.getName()+"s inventory");}
                case "wettowel" ->{modosit.getInventory().add(new WetTowel("wettowel",3, false, modosit.getLocation(), modosit));
                    System.out.println("a wettowel was added to "+modosit.getName()+"s inventory");}
                case "cabbagecamambert" -> {modosit.getInventory().add(new CabbageCamambert("cabbagecamambert",3, false, modosit.getLocation(), modosit));
                    System.out.println("a cabbagecamambert was added to "+modosit.getName()+"s inventory");}
                case "airspray" ->  {modosit.getInventory().add(new AirSpray("airspray",1, false, modosit.getLocation(), modosit));
                    System.out.println("a airspray was added to "+modosit.getName()+"s inventory");}
                case "faketvsz" -> {modosit.getInventory().add(new FakeTVSZ("faketvsz",1, false, modosit.getLocation(), modosit));
                    System.out.println("a faketvsz was added to "+modosit.getName()+"s inventory");}
                case "fakeffp2" -> {modosit.getInventory().add(new FakeFFP2Mask("fakeffp2",1, false, modosit.getLocation(), modosit));
                    System.out.println("a fakeffp2mask was added to "+modosit.getName()+"s inventory");}
                case "fakeslipstick" -> {modosit.getInventory().add(new FakeSlipStick("fakeslipstick",1, false, modosit.getLocation(), modosit));
                    System.out.println("a fakeslipstick was added to "+modosit.getName()+"s inventory");}
                case "transistor" -> {modosit.getInventory().add(new Transistor("transistor",1, false, modosit.getLocation(), modosit,null));
                    System.out.println("a transistor was added to "+modosit.getName()+"s inventory");}
                default -> System.out.println("Hibás item név");
            }
        }else{
            System.out.println("Nincs ilyen nevű karakter");
        }

    }

    /**
     * A szoba mérgezővé tétele
     * @param idx
     */
    public void makePoisonous(int idx){
        if(idx > rooms.size()-1 || idx < 0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        rooms.get(idx).addProperty(new Poisonous());
        rooms.get(idx).roomPropertyUpdate();
        System.out.println( idx +".room was poisoned by controller");

    }
    /**
     * A szoba elátkozottá tétele
     * @param idx
     */
    public void makeCursed(int idx){
        if(idx > rooms.size()-1 || idx < 0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        rooms.get(idx).addProperty(new Cursed());
        rooms.get(idx).roomPropertyUpdate();
        System.out.println( idx +".room was cursed by controller");

    }

    /**
     * A szoba csúszóssá tétele
     * @param idx
     */
    public void makeSlippery(int idx){

        if(idx > rooms.size()-1 || idx < 0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        rooms.get(idx).addProperty(new Sticky());
        rooms.get(idx).roomPropertyUpdate();
        System.out.println( idx +".room was made slippery by controller");
    }
    /**
     * Két szoba összeolvasztása
     * @param idx1 Az egyik szoba indexe
     * @param idx2 A másik szoba indexe
     */
    public void mergeRooms(int idx1, int idx2){

        if(idx1 > rooms.size()-1 ||idx2 > rooms.size()-1 || idx1 < 0||idx2 < 0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        Room szeddki1 = rooms.get(idx1);
        Room szeddki2 = rooms.get(idx2);

         Room uj = rooms.get(idx1).roomMerge(rooms.get(idx2));
        if(uj == null){
            System.out.println(idx1 +".room and "+ idx2+".room couldn’t merge, because they aren’t neighbours");
        }
        else{
            System.out.println(idx1+". room was merge with "+idx2+" by controller");

            rooms.add(idx1, uj);


            rooms.remove(szeddki1);
            rooms.remove(szeddki2);
        }
    }

    /**
     * Szoba szétválasztása
     * @param idx A szoba indexe
     */
    public void splitRoom(int idx){

        if(idx > rooms.size()-1 || idx < 0){
            System.out.println("Hibás szoba paraméter");
            return;
        }
        List<Room> szobak = rooms.get(idx).roomSplit();

        if(szobak.size()!=2){
            System.out.println("Hiba a Split-nél, nem keletkezett 2 szoba");
            return;
        }else{
            System.out.println(idx+".room was split by controller.");

            rooms.add(idx,szobak.get(0));
            rooms.add(idx + 1, szobak.get(1));
            rooms.remove(idx+2);
        }

    }

    /**
     * A labirintus alaphelyzetbe állítása
     */
    public void reset(){

        for(int i = 0; i < rooms.size(); i++){
            for(int x = 0; x < rooms.get(i).getCharacters().size(); x++) {
                rooms.get(i).getCharacters().get(x).setLocation(null);
            }
            rooms.get(i).getCharacters().clear();
        }

        for(int i = 0; i < rooms.size(); i++){
            for(int x = 0; x < rooms.get(i).getItems().size(); x++) {
                rooms.get(i).getItems().get(x).setItemLocation(null);
            }
            rooms.get(i).getItems().clear();
            rooms.get(i).getProperties().clear();
        }
    }

    /**
     * A szoba adatainak kiírása
     * @param idx
     */
    public void printRoom(int idx){

        if(rooms.size() > idx && idx >= 0){
            System.out.println("details of room " + idx + ":");
            System.out.println("cursed: " + rooms.get(idx).isCursed());
            System.out.println("poisonous: " + rooms.get(idx).isPoisonous());
            System.out.println("slippery: " + (rooms.get(idx).getStickyduration() != -1));
            System.out.println("cleaned: " + rooms.get(idx).isCleaned());
            System.out.println("capacity: " + rooms.get(idx).getCapacity());
            System.out.println("stickylimit: " + rooms.get(idx).getStickylimit());

            System.out.print("neighbours: ");
            for(int i = 0; i < rooms.size(); i++){
                if(rooms.get(idx).getNeighbours().contains(rooms.get(i))){
                    System.out.print("" + i +",");
                }
            }

            System.out.println();

            System.out.println("items:");
            for(int i = 0; i < rooms.get(idx).getItems().size(); i++){
                System.out.println("details of item " + i);
                System.out.println(rooms.get(idx).getItems().get(i).toString());
            }
           // System.out.println();

        }
    }
    /**
     * A labirintus adatainak kiírása
     */
    public void printLabyrinth(){

        for(int i = 0; i < rooms.size(); i++){
            printRoom(i);
        }

    }

}
