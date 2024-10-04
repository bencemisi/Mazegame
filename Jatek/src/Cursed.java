/**
 * Az elátkozott állapot rendelhető segítségével a szobákhoz.
 */
public class Cursed extends RoomProperty{
    /**
     * A szobához tartozó típusokat jelző értékeket beállítja.
     */
    @Override
    public void effect() {
        room.SetCursed(true);
    }
}
