public class Sticky extends RoomProperty{

    /**
     * A Stickyduration csökkentése a RoomPropertyUpdate()-ben van megvalósítva
     */
    @Override
    public void effect() {
        room.setStickyduration(3);
    }
}
