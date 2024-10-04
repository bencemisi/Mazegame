import java.util.*;

/**
 * A labirintust felépítő szobák és azok viselkedésének megvalósításáért felelős osztály.
 */
public class Room {
    /** A szobában tartózkodó karakterek maximális száma.*/
    private int capacity;
    /** A szoba mérgezett állapotát tároló érték.*/
    private boolean poisonous;



    /**
     * A szoba elátkozott állapotát tároló érték.
     * */
    private boolean cursed;

    private boolean cleaned;

    /**
     * A szoba sticky-vé válásához szükséges karakterek száma
     */
    private int stickylimit;

    /**
     * A sticky-ség időlimitje.
     */
    private int stickyduration = -1;

    /**
     * A szobában lévő karakterek.
     */
    private List<Character> Characters;

    /**
     * A szoba korábbi szomszédai.
     */
    private List<Room> previousNeighbours;

    /** A szobában lévő tárgyak.*/
    private List<Item> items;
    /** A szoba szomszédai.*/
    private List<Room> neighbours;
    /** A szoba tulajdonságai.*/
    private List<RoomProperty> roomProperties;
    /**
     * Paraméteres konsturktor
     * @param pCapacity  A szobában tartózkodó karakterek maximális száma.
     * @param pPoisonous A szoba mérgezett állapotát tároló érték
     * @param pCursed A szoba elátkozott állapotát tároló érték.
     * @param pCharacters A szobában lévő karakterek.
     * @param pItems A szobában lévő tárgyak.
     * @param pNeighbours A szoba szomszédai.
     * @param pRoomProperties A szoba tulajdonságai.
     */
    public Room(int pCapacity, boolean pPoisonous, boolean pCursed, boolean pCleaned, int pStickyLimit, int pStickyDuration, List<Character> pCharacters, List<Item> pItems, List<Room> pNeighbours, List<RoomProperty> pRoomProperties) {
        this.capacity = pCapacity;
        this.poisonous = pPoisonous;
        this.cursed = pCursed;
        this.cleaned=pCleaned;
        this.stickylimit=pStickyLimit;
        this.stickyduration=pStickyDuration;

        if(pCharacters == null) {
            Characters = new ArrayList<>();
        }
        else {
            this.Characters = pCharacters;
        }

        if(pItems == null) {
            items = new ArrayList<>();
        }
        else {
            this.items = pItems;
        }

        this.neighbours = pNeighbours;

        if (pRoomProperties == null) {
            roomProperties = new ArrayList<>();
        }
        else {
            this.roomProperties = pRoomProperties;
        }
        this.previousNeighbours = new ArrayList<>();
    }
    /**
     * A szobában új tárgy helyeződik el.
     * @param item Az új tárgy.
     */
    public void putItem(Item item) {
        items.add(item);
        item.setItemLocation(this);
    }
    /**
     * A szobából egy - a szobában lévő- tárgyat felvettek a karakterek.
     * @param item A felvett tárgy
     */
    public void deleteItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                items.remove(i);
                break;
            }
        }
    }
    /**
     * Visszaadja a szoba szomszédait.
     * @return A szomszédok listája.
     */
    public List<Room> getNeighbours() {
        return neighbours;

    }

    /**
     * Getter a previousNeighbours tagváltozóhoz
     * @return previousNeighbours
     */
    public List<Room> getPreviousNeighbours() {
        return previousNeighbours;
    }

    /**
     * Setter a previousNeighbours tagváltozóhoz
     * @param prevNeighbours
     */
    public void setPreviousNeighbours(List<Room> prevNeighbours) {
        previousNeighbours = prevNeighbours;
    }

    /**
     * A szoba összes szomszédai állítására.
     * @param roomList A szomszédok listája
     */
    public void setNeighbours (List<Room> roomList) {
        neighbours = roomList;
    }
    /**
     * A szoba állapota(i) kérdezhető(k) le vele.
     * @return A szoba állapotainak listája.
     */
    public List<RoomProperty> getProperties() {
        return roomProperties;
    }
    /**
     * A szobában tartózkodó karakterek listáját adja vissza.
     * @return A karakterek listája.
     */
    public List<Character> getCharacters() {
        return Characters;
    }
    /**
     * A szoba karakterlistájának állítása
     * @param characters A karakterek listája
     */
    public void setCharacters(List<Character> characters) {
        Characters = characters;
    }

    /**
     * A szoba kapacitását adja vissza.
     * @return A szoba kapacitása.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * A szoba állapotához lehet rendelni újat.
     * @param property Az új állapot.
     */
    public void addProperty(RoomProperty property) {
        property.setRoom(this);
        roomProperties.add(property);
        roomProperties.get(roomProperties.size() - 1).effect();
    }

    /**
     * Megjelenik egy ajtó a paraméterként kapott szoba felé
     * @param toRoom Az ús szomszéd
     */
    public void addOneWayDoor(Room toRoom) {
        if (!this.getNeighbours().contains(toRoom)) {
            this.neighbours.add(toRoom);
        }
    }

    /**
     * Eltűnik egy ajtó a paraméterként kapott szoba felé
     * @param toRoom A már nem szomszédos szoba
     */
    public void deleteOneWayDoor(Room toRoom) {
        if(this.getNeighbours().contains(toRoom)) {
            this.getNeighbours().remove(toRoom);                            //  this.deleteTwoWayDoor(toRoom);
        }
    }

    /**
     * Egy szoba osztódását valósítja meg a követelményekben leírt módón.
     */
    public List<Room> roomSplit() {
       // Room first = new Room(capacity, false,false, false, 0, -1, null, null);
        List<Room> new_rooms = new ArrayList<Room>();

        Room first = null;
        Room second = null;
        boolean iscursed =isCursed();
        boolean iscleaned =isCleaned();
        boolean ispoisonous =isPoisonous();
        int stickydurationValue = getStickyduration();
        int stickylimitValue = getStickylimit();

        if(Main.randomize) {
            List<Room> new_room_neighbours_first = new ArrayList<>();
            List<Room> new_room_neighbours_second = new ArrayList<>();
            for(Room n : neighbours){
                if(new Random().nextBoolean()){
                    new_room_neighbours_first.add(n);
                }else{
                    new_room_neighbours_second.add(n);
                }
            }

            List<Item> new_room_items_first = new ArrayList<>();
            List<Item> new_room_items_second = new ArrayList<>();
            for(Item i : items){
                if(new Random().nextBoolean()){
                    new_room_items_first.add(i);
                }else{
                    new_room_items_second.add(i);
                }
            }

            List<Character> new_room_characters_first = new ArrayList<>();
            List<Character> new_room_characters_second = new ArrayList<>();

            for(Character ch : Characters){
                if(new Random().nextBoolean()){
                    new_room_characters_first.add(ch);
                }else{
                    new_room_characters_second.add(ch);
                }
            }
             first = new Room(capacity, ispoisonous,iscursed, iscleaned, stickylimitValue, stickydurationValue, new_room_characters_first, new_room_items_first, new_room_neighbours_first, roomProperties);
             second = new Room(capacity, ispoisonous,iscursed, iscleaned, stickylimitValue, stickydurationValue, new_room_characters_second, new_room_items_second, new_room_neighbours_second, roomProperties);
            first.addTwoWayDoor(second);


        }

        else {
            //Mindent megkap ebben az esetben az első szoba
            first = new Room(capacity, ispoisonous,iscursed, iscleaned, stickylimitValue, stickydurationValue, Characters, items, neighbours, roomProperties);
            second = new Room(capacity, ispoisonous,iscursed, iscleaned, stickylimitValue, stickydurationValue, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), roomProperties);
            first.addTwoWayDoor(second);
        }

        for(Room r: first.neighbours){
            if(r.neighbours.contains(this)){
                r.addOneWayDoor(first);
                r.neighbours.remove(this);
            }
        }

        for(Room r: second.neighbours){
            if(r.neighbours.contains(this)){
                r.addOneWayDoor(second);
                r.neighbours.remove(this);
            }
        }

        for(Item i: first.items){
            i.setItemLocation(first);
        }

        for(Item i: second.items){
            i.setItemLocation(second);
        }

        for(Character ch: first.Characters){
            ch.setLocation(first);
        }

        for(Character ch: second.Characters){
            ch.setLocation(second);
        }

        new_rooms.add(first);
        new_rooms.add(second);
        return  new_rooms;
    }

    /**
     * Két szoba egyesülését valósítja meg a követelményekben leírt módon.
     * @param room A másik egyesülendő szoba
     */
    public Room roomMerge(Room room) {
        if(neighbours.contains(room)){
            int new_room_size;

            if(capacity > room.getCapacity()){
                new_room_size = capacity;
            }else{
                new_room_size = room.getCapacity();
            }

            this.neighbours.addAll(room.getNeighbours());
            neighbours.remove(this);
            neighbours.remove(room);
            List<Room> new_room_neighbours = new ArrayList<Room>(new HashSet<Room>(neighbours));


            this.roomProperties.addAll((room.getProperties()));
            List<RoomProperty> new_room_properties = roomProperties;

            List<Item> items1 = items;
            List<Item> items2 = room.getItems();
            items1.addAll(items2);
            List<Item> new_room_items = items1;

            List<Character> characters1 = Characters;
            List<Character> characters2 = room.getCharacters();
            characters1.addAll(characters2);
            List<Character> new_room_characters = characters1;

            if(new_room_characters.size() > new_room_size){
                new_room_size = new_room_characters.size();
            }


            Room newRoom =new Room(new_room_size,false,false,false,(int) Math.floor(new_room_size*0.8),-1,new_room_characters,new_room_items,new_room_neighbours,new_room_properties);
            for(int i = 0; i< newRoom.getProperties().size();i++){
                newRoom.getProperties().get(i).setRoom(newRoom);
            }
            newRoom.roomPropertyUpdate();
            for(var ch : new_room_characters){
                ch.setLocation(newRoom);
            }

            for(var it : new_room_items){
                it.setItemLocation(newRoom);
            }
            return newRoom;
        }
        return null;
    }

    /**
     * A szoba mérgezett állapotát adja vissza
     * @return A mérgezettség értéke.
     */
    public boolean isPoisonous() {
        return poisonous;
    }

    /**
     * A szoba mérgezett állapotának beállítására
     * @param poisonousparam Az új érték
     */
    public void SetPoisonous(boolean poisonousparam){poisonous=poisonousparam;}

    /**
     * A szoba elátkozott állapotát adja vissza
     * @return Az elátkozottság értéke.
     */
    public boolean isCursed() {
        return cursed;
    }

    /**
     * A szoba átkozott állapotának beállítására
     * @param Cursedparam Az új érték
     */
    public void SetCursed(boolean Cursedparam) {
        previousNeighbours = neighbours;
        cursed = Cursedparam;
    }

    /**
     * A paraméterként megkapott szobával már nem szomszédos a szoba.
     * @param room A már nem szomszéd szoba
     */
    public void deleteTwoWayDoor(Room room) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i) == room) {
                this.deleteOneWayDoor(room);
                room.deleteOneWayDoor(this);
                break;
            }
        }
    }

    /**
     * A paraméterként megkapott szobával szomszédos lett a szoba.
     * @param room Az új szomszéd.
     */
    public void addTwoWayDoor(Room room) {
        this.addOneWayDoor(room);
        room.addOneWayDoor(this);
    }

    /**
     * A szobához új karakter adható vele.
     *
     * @param character A szobába belépett karakter
     */
    public void addCharacter(Character character) {
        Characters.add(character);
    }
    /**
     * A szobából karakter vehető ki vele
     * @param character A szobából kilépett karakter
     */
    public void deleteCharacter(Character character) {
        if(Characters.contains(character)){
            Characters.remove(character);
        }
    }

    /**
     * A stickyduration változó értékét adja vissza
     * @return stickyduration
     */
    public int getStickyduration() {
        return stickyduration;
    }
    /**
     * A stickyduration változó értékét állítja be
     *  @param stickyduration új érték
     */
    public void setStickyduration(int stickyduration) {
        this.stickyduration = stickyduration;
    }

    /**
     * Az állapotokat jelző flagek beállításáért felelős.
     */
    public void roomPropertyUpdate(){
        SetCursed(false);
        SetPoisonous(false);
        int stickydurationvalue = getStickyduration();
        for(var property : getProperties()){
            property.setRoom(this);
            property.effect();
        }
        if(stickydurationvalue != -1)
            setStickyduration(stickydurationvalue-1);

    }

    /**
     * A cleaned változó értékét adja vissza
     * @return
     */
    public boolean isCleaned() {
        return cleaned;
    }

    /**
     * A cleaned változó értékét állítja be
     * @param cleaned új érték
     */
    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    /**
     * Setter a poisonous tagváltozóhoz
     * @param poisonous a kívánt érték
     */
    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }
    /**
     * A stickylimit változó értékét adja vissza
     * @return
     */
    public int getStickylimit() {
        return stickylimit;
    }
    /**
     * A stickylimit változó értékét állítja be
     * @param stickylimit új érték
     */
    public void setStickylimit(int stickylimit) {
        this.stickylimit = stickylimit;
    }

    /**
     * A szoba tulajdonság listáját törlő függvény.
     *  @param was_airspray_effect igaz, ha airspray effectnél használjuk, amúgy meg false
     */
    public void deleteProperties(boolean was_airspray_effect){

        boolean iscursed =isCursed();
        boolean ispoisonous =isPoisonous();
        int stickydurationValue = getStickyduration();
        this.roomProperties.clear();
        if(was_airspray_effect){
            SetPoisonous(false);
        }else{
            if(ispoisonous){
                roomProperties.add(new Poisonous());
            }
        }

        if(iscursed){
            roomProperties.add(new Cursed());
        }
        if(stickydurationValue!=-1){
            roomProperties.add(new Sticky());
        }
    }

    /**
     * Getter az itemekhez
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }






}