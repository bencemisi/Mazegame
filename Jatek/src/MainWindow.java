import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A játék főablaka, amely a főmenüt és a játék kezelőfelületét tartalmazza.
 */
public class MainWindow extends JFrame {
    /**
     * Főmenü panel
     */
    private MainMenu menu;

    /**
     * Játék interface panel
     */
    private GameInterface anInterface;

    /**
     * A panelek közötti váltásért felelős
     */
    private JPanel cardPanel;

    /**
     * A panelek közötti váltásért felelős panel layout-ja
     */
    private CardLayout layout;

    /**
     * A logikai játék objektum
     */
    private Game game;

    /**
     * Konstruktor
     */
    public MainWindow() {

        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        Labyrinth labyrinth = new Labyrinth(rooms);
        labyrinth.addRoom(-1);
        labyrinth.addRoom(0);
        labyrinth.addRoom(1);
        SlipStick slipStick = new SlipStick("slipstick", 1, false, null, null);
        game = new Game(characters, new Labyrinth(rooms), slipStick);



        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Slipstick Game");


        menu = new MainMenu(this, game);
        //anInterface = new GameInterface(this, game);
        cardPanel = new JPanel(new CardLayout());
        add(cardPanel);

        layout = (CardLayout) cardPanel.getLayout();

        cardPanel.add(menu, "MainMenu");
        //cardPanel.add(anInterface, "Interface");
        //layout.show(cardPanel, "Interface");
        layout.show(cardPanel, "MainMenu");
    }

    /**
     * Megjeleníti a főmenüt
     */
    public void showMenu(){
        //a menüre vált
        layout.show(cardPanel, "MainMenu");
    }

    /**
     * Megjeleníti a játék kezelőfelületét
     */
    public void showInterface() {
        //az interface-re vált
        anInterface = new GameInterface(this, game);
        cardPanel.add(anInterface, "Interface");
        layout.show(cardPanel, "Interface");
    }

    /**
     * Visszaadja a logikai játék objektumot.
     * @return a logikai játék objektum
     */
    public Game getGame(){
        return game;
    }
}
