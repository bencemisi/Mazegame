/**
 * Olyan absztrakt osztály, melynek felelőssége az, hogy a segítségével a szobák állapotait nyilván tudjuk tartani.
 */
public abstract class RoomProperty {

    protected Room room;
    /**
     * A szobához tartozó típusokat jelző értékeket beállítja.
     */
    public abstract void effect();
    public void setRoom(Room room) {
        this.room = room;
    }
}

