import javax.swing.*;

public class FakeSlipStick extends Item {
    /**
     * Az osztály paraméteres konstruktora
     * @param pName A tárgy neve
     * @param pDurability A tárgy használatának a számát jelez, amely hogyha eléri a 0-át, akkor a tárgy használhatatlanná válik
     * @param pCurrentlyUsed Használva van-e
     * @param pItemLocation A szoba ahol jelenleg a tárgy van
     * @param pOwner A tárgyak birtokló karakter
     */
    public FakeSlipStick(String pName, int pDurability, boolean pCurrentlyUsed, Room pItemLocation, Character pOwner) {
        super(pName, pDurability, pCurrentlyUsed, pItemLocation, pOwner);
    }

    /**
     * A szobát, amelyben a hallgató ezt a tárgyat használja,
     * “Poisonous” típusúvá alakítja. Amennyiben
     * már a szoba “Poisonous” volt, akkor nem történik semmi.
     */
    @Override
    public void onPickUp() {
        this.destruct();
        JOptionPane.showMessageDialog(null, "It was fake.");
        System.out.println("it was fake");
    }

    /**
     * A tárgy használatának hatását leíró metódus.
     */
    @Override
    public void effect() {
        this.destruct();
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
        return "it was fake";
    }

}
