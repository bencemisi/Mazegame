import javax.swing.*;
import java.io.OutputStreamWriter;

/**
 *  Az osztály a Légfrissítő tárgy viselkedését valósítja meg.
 *  A hallgatók az osztály felhasználásával a szobát
 *  nem mérgezővé tudják teeni.
 */
public class AirSpray extends Item{
    /**
     * Az osztály paraméteres konstruktora
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public AirSpray(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }
    /**
     * A szobát, amelyben a hallgató ezt a tárgyat használja,
     * “Poisonous” típusúvá alakítja. Amennyiben
     * már a szoba “Poisonous” volt, akkor nem történik semmi.
     */
    @Override
    public void effect() {

        if(this.getOwner().getLocation().isPoisonous()){
            getOwner().getLocation().deleteProperties(true);

            if(this.getOwner().getLocation().isCursed()){
                Cursed cursed=new Cursed();
                getOwner().getLocation().addProperty(cursed);
                cursed.effect();
            }
            if(this.getOwner().getLocation().getStickyduration()>0){
                Sticky sticky=new Sticky();
                getOwner().getLocation().addProperty(sticky);
                sticky.effect();
            }
            getOwner().getLocation().setPoisonous(false);
        }
        this.setDurability(1);

    }

    /**
     * A használat során kiírandó üzenet.
     * @param playerName a használó játékos neve
     * @param roomIdx az első szoba indexe
     * @param roomIdx2 a második szoba indexe
     * @return az üzenet
     */
    @Override
    public String useToString(String playerName, int roomIdx, int roomIdx2){
        return "player " + playerName + " used air spray in " + roomIdx + ".room";
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon("airspray.png");
    }
}
