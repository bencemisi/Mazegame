import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * A játék felületét megvalósító osztály.
 */
public class GameInterface extends JPanel {

    /**
     * A panelt tartalmazó frame.
     */
    private MainWindow parentFrame;

    /**
     * A körön lévő játékos nevét jeleníti meg.
     */
    private JLabel playerLabel;

    /**
     * A körön lévő játékos szobájának sorszámát jeleníti meg.
     */
    private JLabel roomLabel;

    /**
     * A szobában lévő játékosok nevét írja ki.
     */
    private JTextArea textArea;
    //TODO new - ez mi

    /**
     * A háttér megjelenítéséért felelős panel.
     */
    private RoomPanel panel2;

    /**
     * A háttér megjelenítéséért felelős panel.
     */
    private RoomPanel panel1;

    /**
     * Az inventory-t megvalósító gombok tömbje.
     */
    private JButton[] buttons;

    /**
     * Az item-ek felvételét valósítja meg.
     */
    private JComboBox<String> itemComboBox;

    /**
     * A szobákba lépést valósítja meg.
     */
    private JComboBox<String> roomComboBox;

    /**
     * Az item-ekhez tartozó művelet kiválsztását valósítja meg.
     */
    private JComboBox<String> itemOperation;

    /**
     * Konstruktor
     * @param frame - a tartalmazó frame
     * @param game - a játék objektum
     */
    public GameInterface(MainWindow frame, Game game) {

        game.startGame();

        parentFrame = frame;
        setSize(800, 500);
        setLayout(new GridLayout(1, 2));
        setBackground(Color.WHITE);

        panel1 = new RoomPanel() {};

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        add(panel1);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));


        playerLabel = new JLabel("It's " + game.getActualCharacter().getName() + " turn.");
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel.setForeground((new Color(148, 160, 164)));
        playerLabel.setFont(new Font("Lucida Console",Font.BOLD,20));
        panel1.add(playerLabel);

        panel1.add(Box.createRigidArea(new Dimension(0, 240)));

        JLabel label13 = new JLabel("Rooms:");
        label13.setForeground((new Color(148, 160, 164)));
        label13.setFont(new Font("Lucida Console",Font.BOLD,20));
        label13.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(label13);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        panel2 = new RoomPanel();

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        add(panel2);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        roomLabel = new JLabel("You are in Room 6.");
        roomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel2.add(roomLabel);

        panel2.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel label11 = new JLabel("Characters in this room: ");
        label11.setForeground(new Color(229, 225, 220));
        label11.setFont(new Font("Lucida Console",Font.BOLD,20));
        label11.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(label11);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        textArea = new JTextArea("Player Four\nTeacher One");
        textArea.setMaximumSize(new Dimension(200, 150));
        textArea.setFont(new Font("Lucida Console",Font.BOLD,20));
        textArea.setBackground(new Color(163, 184, 204));
        textArea.setBorder(new LineBorder(new Color(31, 23, 68),5));
        textArea.setEditable(false);
        panel2.add(textArea);

        panel2.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel label12 = new JLabel("Items in this room: ");
        label12.setForeground(new Color(229, 225, 220));
        label12.setFont(new Font("Lucida Console",Font.BOLD,20));
        label12.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(label12);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        itemComboBox = new JComboBox<>();
        itemComboBox.setMaximumSize(new Dimension(200, 30));
        itemComboBox.setFont(new Font("Lucida Console",Font.PLAIN,20));
        itemComboBox.setForeground(new Color(187, 158, 158));
        itemComboBox.setBackground(new Color(63, 44, 103));
        itemComboBox.setBorder(new LineBorder(new Color(31, 23, 68),1,true));
        itemComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(itemComboBox);

        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton pickUpButton = new JButton("Pick up");
        pickUpButton.setMaximumSize(new Dimension(150, 30));
        pickUpButton.setBackground(new Color(56, 41, 110));
        pickUpButton.setForeground((new Color(187, 158, 158)));
        pickUpButton.setFont(new Font("Lucida Console",Font.BOLD,20));
        pickUpButton.setBorder(new LineBorder(new Color(31, 23, 68),1,true));
        pickUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(pickUpButton);

        pickUpButton.addActionListener(new ActionListener() {
            /**
             * A tárgyfelvétel gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                game.pickUpItem(itemComboBox.getSelectedIndex());
                game.skipTurn_Action();
                update(game);
            }
        });

        roomComboBox = new JComboBox<>();
        roomComboBox.setMaximumSize(new Dimension(200, 30));
        roomComboBox.setBackground(new Color(106, 133, 146));
        roomComboBox.setForeground((new Color(11, 27, 37)));
        roomComboBox.setFont(new Font("Lucida Console",Font.BOLD,20));
        roomComboBox.setBorder(new LineBorder(new Color(1, 1, 1),1,true));
        roomComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(roomComboBox);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton moveButton = new JButton("Move");
        moveButton.setMaximumSize(new Dimension(100, 30));
        moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveButton.setBackground(new Color(106, 133, 146));
        moveButton.setForeground((new Color(11, 27, 37)));
        moveButton.setFont(new Font("Lucida Console",Font.BOLD,20));
        moveButton.setBorder(new LineBorder(new Color(1, 1, 1),1,true));
        panel1.add(moveButton);

        moveButton.addActionListener(new ActionListener() {
            /**
             * A lépés gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                if(game.moveCharacter(cutter((String) roomComboBox.getSelectedItem()))){
                    game.skipTurn_Action();
                    update(game);
                }

            }
        });

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel panel3;

        panel3 = new JPanel() {
            /**
             * A háttérkép beállítása
             * @param g the <code>Graphics</code> object to protect
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Háttérkép beállítása
                ImageIcon background = new ImageIcon("rightwing.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        add(panel3);

        buttons = new JButton[5];

        panel3.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel inventoryLabel = new JLabel("Inventory");
        inventoryLabel.setFont(new Font("Lucida Console", Font.BOLD, 20));
        inventoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventoryLabel.setForeground(new Color(183, 181, 170));
        panel3.add(inventoryLabel);
        panel3.add(Box.createRigidArea(new Dimension(0, 10)));

        itemOperation = new JComboBox<>();
        itemOperation.setMaximumSize(new Dimension(150, 30));
        itemOperation.setFont(new Font("Lucida Console",Font.PLAIN,20));
        itemOperation.setForeground(new Color(0, 0, 0));
        itemOperation.setBackground(new Color(115, 96, 82));
        itemOperation.setBorder(new LineBorder(new Color(0, 0, 0),1,true));
        itemOperation.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(itemOperation);

        itemOperation.addItem("Use");
        itemOperation.addItem("Drop");
        itemOperation.addItem("Discard");

        panel3.add(Box.createRigidArea(new Dimension(0, 20)));

        for (int i = 0; i < 5; i++) {
            buttons[i] = new JButton();
            buttons[i].setMaximumSize(new Dimension(60, 60));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons[i].setBackground(new Color(183, 181, 170));
            panel3.add(buttons[i]);

        }

        buttons[0].addActionListener(new ActionListener() {
            /**
             * Az 0. inventory gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                switch (itemOperation.getSelectedIndex()){
                    case 0:
                        game.useItem(0);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 1:
                        game.dropItem(0);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 2:
                        game.discardItem(0);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    default:
                        break;
                }
            }
        });

        buttons[1].addActionListener(new ActionListener() {
            /**
             * Az 1. inventory gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                switch (itemOperation.getSelectedIndex()){
                    case 0:
                        game.useItem(1);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 1:
                        game.dropItem(1);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 2:
                        game.discardItem(1);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    default:
                        break;
                }
            }
        });

        buttons[2].addActionListener(new ActionListener() {
            /**
             * Az 2. inventory gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                switch (itemOperation.getSelectedIndex()){
                    case 0:
                        game.useItem(2);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 1:
                        game.dropItem(2);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 2:
                        game.discardItem(2);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    default:
                        break;
                }
            }
        });

        buttons[3].addActionListener(new ActionListener() {
            /**
             * Az 3. inventory gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                switch (itemOperation.getSelectedIndex()){
                    case 0:
                        game.useItem(3);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 1:
                        game.dropItem(3);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 2:
                        game.discardItem(3);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    default:
                        break;
                }
            }
        });

        buttons[4].addActionListener(new ActionListener() {
            /**
             * Az 4. inventory gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                switch (itemOperation.getSelectedIndex()){
                    case 0:
                        game.useItem(4);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 1:
                        game.dropItem(4);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    case 2:
                        game.discardItem(4);
                        game.skipTurn_Action();
                        update(game);
                        break;
                    default:
                        break;
                }
            }
        });

        panel3.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton skipButton = new JButton("Skip");
        skipButton.setMaximumSize(new Dimension(60, 30));
        skipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        skipButton.setBackground(new Color(106, 133, 146));
        skipButton.setForeground((new Color(11, 27, 37)));
        skipButton.setFont(new Font("Lucida Console",Font.BOLD,20));
        skipButton.setBorder(new LineBorder(new Color(1, 1, 1),1,true));
        panel1.add(skipButton);

        skipButton.addActionListener(new ActionListener() {
            /**
             * A skip gomb eseménykezelője
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                game.skipTurn();
                update(game);
            }
        });

        update(game);
    }

    /**
     * Feldarabol egy stringet és az első elemből int-et csinál.
     * @param room a string
     * @return a szám
     */
    public int cutter(String room) {
        int index;

        String[] parts = room.split(" ");

        index = Integer.parseInt(parts[1]);
        return index;
    }

    /**
     * Frissíti a felületet.
     * @param game A játék
     */
    public void update(Game game) {

        itemComboBox.removeAllItems();
        roomComboBox.removeAllItems();

        for (JButton button : buttons) {
            button.setIcon(null);
        }

        playerLabel.setText("It's " + game.getActualCharacter().getName() + " turn.");
        int idx = -1;
        for(int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
            for(int j = 0; j < game.getLabyrinth().getRooms().get(i).getCharacters().size(); j++) {
                if (game.getLabyrinth().getRooms().get(i).getCharacters().get(j) == game.actualCharacter) {
                    idx = i;
                }
                if(idx != -1) {
                    break;
                }
            }
        }
        roomLabel.setText("You are in room " + idx);
        roomLabel.setFont(new Font("Lucida Console",Font.BOLD,20));
        roomLabel.setForeground(new Color(229, 225, 220));

        for(int i = 0; i < game.getLabyrinth().getRooms().get(idx).getItems().size(); i++) {
            itemComboBox.addItem(game.getLabyrinth().getRooms().get(idx).getItems().get(i).getName());
        }

        if (game.getLabyrinth().getRooms().get(idx).isPoisonous()) {
            panel2.setBackground("poisonous.png");
        }
        else {
            panel2.setBackground("Center.png");
        }

        if (game.actualCharacter.getPoisonProtection() && game.actualCharacter.getSoulProtection()) {
            panel1.setBackground("Kép4.png");
        }
        else if(game.actualCharacter.getSoulProtection()){
            panel1.setBackground("Kép5.png");

        }
        else if(game.actualCharacter.getPoisonProtection()){
            panel1.setBackground("Kép1.png");

        }
        else{
            panel1.setBackground("left.png");

        }

        for(int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
            for (int j = 0; j < game.getActualCharacter().getLocation().getNeighbours().size(); j++) {
                if(game.getLabyrinth().getRooms().get(i) == game.getActualCharacter().getLocation().getNeighbours().get(j)) {
                    roomComboBox.addItem("Room " + i);
                }
            }
        }

        for (int i = 0; i < game.getActualCharacter().getInventory().size(); i++) {
            buttons[i].setIcon(game.getActualCharacter().getInventory().get(i).getIcon());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < game.getActualCharacter().getLocation().getCharacters().size(); i++) {
            if(game.getActualCharacter().getLocation().getCharacters().get(i) != game.getActualCharacter()) {
                sb.append(game.getActualCharacter().getLocation().getCharacters().get(i).getName());
                sb.append("\n");
            }
        }
        textArea.setText(sb.toString());
    }
}