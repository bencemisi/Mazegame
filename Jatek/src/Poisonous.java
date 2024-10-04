/**
 * A mérgező állapot rendelhető segítségével a szobákhoz.
 */
public class Poisonous extends RoomProperty{
    /**
     * A szobához tartozó típusokat jelző értékeket beállítja.
     */
    @Override
    public void effect() {
        room.SetPoisonous(true);
    }
}
