//import java.util.ArrayList;
//import java.util.List;
//
///**
// * A teszteléshez készített osztály, benne minden függvény egy teszteset
// */
//public class Test {
//    /**
//     * Egy, a labirintusban lévő szoba két másikra osztódik szét,
//     * olyan módon, ahogyan a követelményekben lefektettük.
//     */
//  /*  public void splitRooms() {
//        IndentationHandler.indentation = 0;
//        System.out.print("splitRooms tesztkörnyezet beállítása\n");
//        List<RoomProperty> propertyRoom =new ArrayList<>();
//        List<Room> neighboursRoom1 =new ArrayList<>();
//        Room room = new Room(3, false, false, (List)null, (List)null, neighboursRoom1, propertyRoom);
//        System.out.print("splitRooms tesztelése\n");
//        room.roomSplit();
//    }*/
//
//    /**
//     * Egy, a labirintus lévő két szoba egyesül eggyé,
//     * olyan módon, ahogyan a követelményekben lefektettük.
//     */
//    public void mergeRooms(){
//        IndentationHandler.indentation = 0;
//        System.out.print("mergeRooms tesztkörnyezet beállítása\n");
//        List<RoomProperty> propertyRoom1 =new ArrayList<>();
//        List<RoomProperty> propertyRoom2 =new ArrayList<>();
//        List<Room> neighboursRoom1 =new ArrayList<>();
//        List<Room> neighboursRoom2 =new ArrayList<>();
//        Room room2 = new Room(3, false, false, (List)null, (List)null, neighboursRoom2, propertyRoom2);
//        neighboursRoom1.add(room2);
//        Room room1 = new Room(3, false, false, (List)null, (List)null, neighboursRoom1, propertyRoom1);
//        System.out.print("mergeRooms tesztelése\n");
//        room1.roomMerge(room2);
//    }
//
//    /**
//     * Az elátkozott típusú szobában megjelenik egy ajtó,
//     * melyet használhatnak a karakterek a lépések során.
//     */
//    public void addDoor(){
//        IndentationHandler.indentation = 0;
//        System.out.print("addDoor tesztkörnyezet beállítása\n");
//        List<Room> neighboursRoom1 =new ArrayList<>();
//        Room room1 = new Room(3, false, false, (List)null, (List)null, neighboursRoom1, (List)null);
//        Room room2 = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        System.out.print("addDoor tesztelése\n");
//        room1.addDoor(room2);
//    }
//
//    /**
//     * Az elátkozott típusú szobában eltűnik egy ajtó, így azt a karakterek már nem használhatják a lépések során.
//     */
//    public void deleteDoor(){
//        IndentationHandler.indentation = 0;
//        System.out.print("deleteDoor tesztkörnyezet beállítása\n");
//        List<Room> neighboursRoom1 =new ArrayList<>();
//        Room room2 = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        neighboursRoom1.add(room2);
//        Room room1 = new Room(3, false, false, (List)null, (List)null, neighboursRoom1, (List)null);
//        System.out.print("deleteDoor tesztelése\n");
//        room1.deleteDoor(room2);
//    }
//
//    /**
//     * A szoba elátkozott típusúvá válik.
//     */
//    public void roomGetsCursed(){
//        IndentationHandler.indentation = 0;
//        System.out.print("roomGetsCursed tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        Cursed cursed= new Cursed();
//        System.out.print("roomGetsCursed tesztelése\n");
//        room.addProperty(cursed);
//    }
//
//    /**
//     * A szoba típusa mérgezővé válik.
//     */
//    public void roomGetsPoisonous(){
//        IndentationHandler.indentation = 0;
//        System.out.print("roomGetsPoisonous tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        Poisonous poison= new Poisonous();
//        System.out.print("roomGetsPoisonous tesztelése\n");
//        room.addProperty(poison);
//    }
//
//    /**
//     * A hallgató a jelenlegi szobájából
//     * egy másik, azzal szomszédos szobába lép.
//     */
//    public void moveStudent(){
//        IndentationHandler.indentation = 0;
//        System.out.print("moveStudent tesztkörnyezet beállítása\n");
//        Room second = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        firstNeighbours.add(second);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Student student = new Student((List)null, "Student", false, (List)null, first, false, false);
//        System.out.print("moveStudent tesztelése\n");
//        student.move();
//    }
//    /**
//     * A hallgató a jelenlegi szobájából
//     * egy másik, azzal szomszédos szobába lép,
//     * ami már tele van.
//     */
//    public void moveStudent_butRoomisFull(){
//        IndentationHandler.indentation = 0;
//        System.out.print("moveStudent_butRoomisFull tesztkörnyezet beállítása\n");
//        Room second = new Room(1, false, false, (List)null, (List)null, (List)null, (List)null);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        List<Character> characters = new ArrayList<Character>();
//        firstNeighbours.add(second);
//        Student student = new Student((List)null, "Student", false, (List)null, second, false, false);
//        second.addCharacter(student);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Student movingStudent = new Student((List)null, "Student", false, (List)null, first, false, false);
//        System.out.print("moveStudent_butRoomisFull tesztelése\n");
//        movingStudent.move();
//    }
//    /**
//     * Az oktató a jelenlegi szobájából
//     * egy másik, azzal szomszédos szobába lép.
//     */
//    public void moveTeacher(){
//        IndentationHandler.indentation = 0;
//        System.out.print("moveTeacher tesztkörnyezet beállítása\n");
//        Room second = new Room(3, false, false, (List)null, (List)null, (List)null, (List)null);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        firstNeighbours.add(second);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Teacher teacher = new Teacher((List)null, "Teacher", false, (List)null, first, false, false);
//        System.out.print("moveTeacher tesztelése\n");
//        teacher.move();
//    }
//    /**
//     * Az oktató a jelenlegi szobájából
//     * egy másik, azzal szomszédos szobába lép,
//     * ami már tele van.
//     */
//    public void moveTeacher_butRoomisFull(){
//        IndentationHandler.indentation = 0;
//        System.out.print("moveTeacher_butRoomisFull tesztkörnyezet beállítása\n");
//        Room second = new Room(1, false, false, (List)null, (List)null, (List)null, (List)null);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        List<Character> characters = new ArrayList<Character>();
//        firstNeighbours.add(second);
//        Teacher teacher= new Teacher((List)null, "Teacher", false, (List)null, second, false, false);
//        second.addCharacter(teacher);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Teacher movingTeacher = new Teacher((List)null, "Teacher", false, (List)null, first, false, false);
//        System.out.print("moveTeacher_butRoomisFull tesztelése\n");
//        movingTeacher.move();
//    }
//    /**
//     * A hallgató az FFP2-es maszk használatával védetté válik a mérgezés ellen.
//     */
//    public void studentGetsPoisonProtected(){
//        IndentationHandler.indentation = 0;
//        System.out.print("studentGetsPoisonProtected tesztkörnyezet beállítása\n");
//        Student student = new Student((List)null, "Student", false, (List)null, null, false, false);
//        PoisonProtection po= new PoisonProtection(student,3);
//        System.out.print("studentGetsPoisonProtected tesztelése\n");
//        student.addProperty(po);
//    }
//
//    /**
//     * A hallgató a TVSZ vagy a szent söröspohár használatával védetté válik az oktatók támadásai ellen.
//     */
//    public void studentGetsSoulProtected(){
//        IndentationHandler.indentation = 0;
//        System.out.print("studentGetsSoulProtected tesztkörnyezet beállítása\n");
//        Student student = new Student((List)null, "Student", false, (List)null, null, false, false);
//        SoulProtection so=new SoulProtection(student,3);
//        System.out.print("studentGetsSoulProtected tesztelése\n");
//        student.addProperty(so);
//    }
//
//    /**
//     * A karakter belép egy olyan szobába amely mérgező,
//     * A karakter nem védett a mérgezés
//     * ellen ezért a mérgező szoba hatására a karakter az eszméletét veszti.
//     */
//    public void Knock() {
//        IndentationHandler.indentation = 0;
//        System.out.print("Knock tesztkörnyezet beállítása\n");
//        List<RoomProperty> property =new ArrayList<>();
//        Poisonous poisonous=new Poisonous();
//        property.add(poisonous);
//        Room second = new Room(3, true, false, null, (List)null, (List)null, property);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        firstNeighbours.add(second);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Student student = new Student((List)null, "Student", false, (List)null, first, false, false);
//        System.out.print("Knock tesztelése\n");
//        student.move();
//    }
//    /**
//     * A karakter belép egy olyan szobába, amely mérgező.
//     * A karakter védett a méreg ellen.
//     */
//    public void KnockwithProtection(){
//        IndentationHandler.indentation = 0;
//        System.out.print("KnockwithProtection tesztkörnyezet beállítása\n");
//        List<RoomProperty> property =new ArrayList<>();
//        Poisonous poisonous=new Poisonous();
//        property.add(poisonous);
//        List<CharacterProperty> characterProperties=new ArrayList<>();
//        Room second = new Room(3, true, false, null, (List)null, (List)null, property);
//        List<Room> firstNeighbours = new ArrayList<Room>();
//        firstNeighbours.add(second);
//        Room first = new Room(3, false, false, null, (List)null, firstNeighbours, (List)null);
//        Student student = new Student((List)null, "Student", false, characterProperties, first, true, false);
//        PoisonProtection poisonProtection=new PoisonProtection(student,5);
//        student.addProperty(poisonProtection);
//        System.out.print("KnockwithProtection tesztelése\n");
//        student.move();
//    }
//    /**
//     *Az oktató megöli a vele egy szobában lévő nem védett hallgatókat.
//     */
//    public void killStudent_Teacher_withoutProtection() {
//        IndentationHandler.indentation = 0;
//        System.out.print("killStudent_Teacher_withoutProtection tesztkörnyezet beállítása\n");
//        List<Character> characters=new ArrayList<>();
//        Room room = new Room(3, false, false, characters, (List)null, (List)null, (List)null);
//        Teacher teacher = new Teacher((List)null, "Teacher", false, (List)null, room, false, false);
//        Student student = new Student((List)null, "Student", false, (List)null, room, false, false);
//        room.addCharacter(student);
//        System.out.print("killStudent_Teacher_withoutProtection tesztelése\n");
//        teacher.kill();
//    }
//    /**
//     *Az oktató megpróbálja megölni a vele egy szobában lévő  védett hallgatókat.
//     */
//    public void killStudent_Teacher_withProtection() {
//        IndentationHandler.indentation = 0;
//        System.out.print("killStudent_Teacher_withProtection tesztkörnyezet beállítása\n");
//        List<Character> characters=new ArrayList<>();
//        List<CharacterProperty> characterProperties=new ArrayList<>();
//        Room room = new Room(3, false, false,characters, (List)null, (List)null, (List)null);
//        Teacher teacher = new Teacher((List)null, "Teacher", false, (List)null, room, false, false);
//        Student student = new Student((List)null, "Student", false, characterProperties, room, false, true);
//        student.addProperty(new SoulProtection(student,5));
//        room.addCharacter(student);
//        System.out.print("killStudent_Teacher_withProtection tesztelése\n");
//        teacher.kill();
//    }
//
//    /**
//     * A hallgató megpróbálja megölni a vele egy szobába került hallgatókat.
//     */
//    public void killStudent_Student() {
//        IndentationHandler.indentation = 0;
//        System.out.print("killStudent_Student tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        Student second = new Student(null, "Student", false, null, room, false, false);
//        room.addCharacter(first);
//        room.addCharacter(second);
//        System.out.print("killStudent_Student tesztelése\n");
//        first.kill();
//
//    }
//
//    /**
//     * A hallgató felvesz egy tárgyat.
//     */
//    public void pickupItemStudent() {
//        List<Item>  items=new ArrayList<>();
//        IndentationHandler.indentation = 0;
//        System.out.print("pickupItemStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, items, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, null);
//        room.putItem(item);
//        System.out.print("pickupItemStudent tesztelése\n");
//        first.pickUp(item);
//    }
//
//    public void pickupItemStudentwithInventoryFull() {
//        IndentationHandler.indentation = 0;
//        System.out.print("pickupItemStudentwithInventoryFull tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, null);
//        TVSZ item2 = new TVSZ("Item", 0, false, room, null);
//        TVSZ item3 = new TVSZ("Item", 0, false, room, null);
//        TVSZ item4 = new TVSZ("Item", 0, false, room, null);
//        TVSZ item5 = new TVSZ("Item", 0, false, room, null);
//        TVSZ item6 = new TVSZ("Item", 0, false, room, null);
//
//        List<Item> inventory = new ArrayList<>();
//        inventory.add(item);
//        inventory.add(item2);
//        inventory.add(item3);
//        inventory.add(item4);
//        inventory.add(item5);
//        first.setInventory(inventory);
//
//        System.out.print("pickupItemStudentwithInventoryFull tesztelése\n");
//        first.pickUp(item6);
//    }
//
//    /**
//     * A hallgató lerak egy tárgyat.
//     */
//    public void dropItemStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("dropItemStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, first);
//        System.out.print("dropItemStudent tesztelése\n");
//        first.drop(item);
//    }
//
//    /**
//     * A hallgató az eszköztárában lévő tárgyat elégeti.
//     */
//    public void discardItemStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("discardItemStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, first);
//        System.out.print("discardItemStudent tesztelése\n");
//        first.discard(item);
//    }
//
//    /**
//     * Az oktató felvesz egy tárgyat.
//     */
//    public void pickupItemTeacher() {
//        System.out.print("pickupItemTeacher tesztkörnyezet beállítása\n");
//        IndentationHandler.indentation = 0;
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Teacher", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, null);
//        System.out.print("pickupItemTeacher tesztelése\n");
//        first.pickUp(item);
//    }
//
//    /**
//     * Az oktató letesz egy nála levő tárgyat.
//     */
//    public void dropItemTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("dropItemTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Teacher", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, first);
//        System.out.print("dropItemTeacher tesztelése\n");
//        first.drop(item);
//    }
//
//    /**
//     * Az oktató elégeti az egyik nála lévő tárgyat.
//     */
//    public void discardItemTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("discardItemTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Teacher", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 0, false, room, first);
//        first.discard(item);
//    }
//
//    /**
//     * A hallgató felhasználja az eszköztárában lévő dobozolt káposztás camembert.
//     */
//    public void useCabbageCamambertStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useCabbageCamambertStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        CabbageCamambert item = new CabbageCamambert("Item", 2, false, room, first);
//        System.out.print("useCabbageCamambertStudent tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * A hallgató felhasználja az eszköztárában lévő FFP2Mask tárgyat.
//     */
//    public void useFFP2MaskStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useFFP2MaskStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        FFP2Mask item = new FFP2Mask("Item", 2, false, room, first);
//        System.out.print("useFFP2MaskStudent tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * A hallgató felhasználja az eszköztárában lévő szent söröspoharat.
//     */
//    public void useHolyBeerMugStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useHolyBeerMugStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        HolyBeerMug item = new HolyBeerMug("Item", 2, false, room, first);
//        System.out.print("useHolyBeerMugStudent tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * A hallgató felhasználja az eszköztárában lévő TVSZ tárgyat.
//     */
//    public void useTVSZStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useTVSZStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 2, false, room, first);
//        System.out.print("useTVSZStudent tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * A hallgató felhasználja az eszköztárában levő nedves táblatörlő
//     * rongyot egy oktatóval szemben.
//     */
//    public void useWetTowelStudentonTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useWetTowelStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        Teacher teacher = new Teacher(null, "Teacher", false, null, room, false, false);
//        WetTowel item = new WetTowel("Item", 2, false, room, first);
//        room.addCharacter(first);
//        room.addCharacter(teacher);
//        System.out.print("useWetTowelStudent tesztelése\n");
//        first.useItem(item);
//    }
//    /**
//     * A hallgató felhasználja az eszköztárában levő nedves táblatörlő
//     * rongyot egy hallgatóval szemben.
//     */
//    public void useWetTowelStudentonStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useWetTowelStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        Student second = new Student(null, "Student", false, null, room, false, false);
//        WetTowel item = new WetTowel("Item", 2, false, room, first);
//        room.addCharacter(first);
//        room.addCharacter(second);
//        System.out.print("useWetTowelStudent tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * A hallgató felveszi a Logarléc tárgyat.
//     */
//    public void pickupSlipStickStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("pickupSlipStickStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        List<Character> characterList = new ArrayList<>();
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(room);
//        characterList.add(first);
//        SlipStick item = new SlipStick("Item", 2, false, room, null, null);
//        Labyrinth labyrinth = new Labyrinth(rooms);
//        Game game = new Game(characterList, labyrinth, item);
//        item.setGame(game);
//        System.out.print("pickupSlipStickStudent tesztelése\n");
//        first.pickUp(item);
//    }
//
//    /**
//     * A hallgató az eszköztárában lévő, korábban már egy másik
//     * tranzisztorral összekapcsolt tranzisztort felhasznál.
//     */
//    public void useTransistorStudent() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useTransistorStudent tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Student first = new Student(null, "Student", false, null, room, false, false);
//        Transistor transistorfirst = new Transistor("Item", 2, false, room, first, null);
//        Transistor transistorsecond = new Transistor("Item", 2, false, room, first, null);
//
//        transistorfirst.connect(transistorsecond);
//        System.out.print("useTransistorStudent tesztelése\n");
//        first.useItem(transistorfirst);
//
//
//    }
//    /**
//     * A hallgató az eszköztárában lévő két tranzisztort összekapcsolja.
//     */
//    public void connectTransistor() {
//        IndentationHandler.indentation = 0;
//        System.out.print("connectTransistor tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Transistor transistorfirst = new Transistor("Item", 2, false, room, null, null);
//        Transistor transistorsecond = new Transistor("Item", 2, false, room, null, null);
//        List<Item> inventory = new ArrayList<>();
//        inventory.add(transistorfirst);
//        inventory.add(transistorsecond);
//        Student first = new Student(inventory, "Student", false, null, room, false, false);
//        System.out.print("connectTransistor tesztelése\n");
//        first.pickUp(transistorfirst);
//    }
//
//    /**
//     * Az oktató az eszköztárában levő dobozolt káposztás camambert felhasználja.
//     */
//    public void useCabbageCamambertTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useCabbageCamambertTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        CabbageCamambert item = new CabbageCamambert("Item", 2, false, room, first);
//        System.out.print("useCabbageCamambertTeacher tesztelése\n");
//        first.useItem(item);
//
//    }
//
//    /**
//     * Az oktató az eszköztárában lévő FFP2Mask tárgyat felhasználja.
//     */
//    public void useFFP2MaskTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useFFP2MaskTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        FFP2Mask item = new FFP2Mask("Item", 2, false, room, first);
//        System.out.print("useFFP2MaskTeacher tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * Az oktató az eszköztárában lévő HolyBeerMug tárgyat felhasználja.
//     */
//    public void useHolyBeerMugTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useHolyBeerMugTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        HolyBeerMug item = new HolyBeerMug("Item", 2, false, room, first);
//        System.out.print("useHolyBeerMugTeacher tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * Az oktató az eszköztárában lévő TVSZ tárgyat felhasználja.
//     */
//    public void useTVSZTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useTVSZTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        TVSZ item = new TVSZ("Item", 2, false, room, first);
//        System.out.print("useTVSZTeacher tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * Az oktató az eszköztárában lévő nedves táblatörlő rongy tárgyat felhasználja.
//     */
//    public void useWetTowelTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useWetTowelTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        WetTowel item = new WetTowel("Item", 2, false, room, first);
//        System.out.print("useWetTowelTeacher tesztelése\n");
//        first.useItem(item);
//    }
//
//    /**
//     * Az oktató felveszi a Logarléc tárgyat.
//     */
//    public void pickupSlipStickTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("pickupSlipStickTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        List<Character> characterList = new ArrayList<>();
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(room);
//        characterList.add(first);
//        SlipStick item = new SlipStick("Item", 2, false, room, first, null);
//        Labyrinth labyrinth = new Labyrinth(rooms);
//        Game game = new Game(characterList, labyrinth, item);
//        item.setGame(game);
//        System.out.print("pickupSlipStickTeacher tesztelése\n");
//        first.pickUp(item);
//    }
//
//    /**
//     * Az oktató az eszköztárában lévő Transistor tárgyat felhasználja.
//     */
//    public void useTransistorTeacher() {
//        IndentationHandler.indentation = 0;
//        System.out.print("useTransistorTeacher tesztkörnyezet beállítása\n");
//        Room room = new Room(3, false, false, null, null, null, null);
//        Teacher first = new Teacher(null, "Student", false, null, room, false, false);
//        Transistor transistorfirst = new Transistor("Item", 2, false, room, first, null);
//        Transistor transistorsecond = new Transistor("Item", 2, false, room, first, null);
//        transistorfirst.connect(transistorsecond);
//        System.out.print("useTransistorTeacher tesztelése\n");
//        first.useItem(transistorfirst);
//    }
//}
