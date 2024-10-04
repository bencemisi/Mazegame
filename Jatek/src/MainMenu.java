import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * A főmenüt megvalósító osztály.
 */
public class MainMenu extends JPanel {
    /**
     * A panelt tartalmazó JFrame
     */
    private JFrame parentFrame;
    /**
     * A karakter hozzáadást megvalósító gomb.
     */
    private JButton addButton;
    /**
     * A játékindítást megvalósító gomb.
     */
    private JButton startButton;
    /**
     * A karakter hozzáadás során a játékos nevét ebben lehet megadni.
     */
    private JTextField nameField;
    /**
     * A már hozzáadott karakterek megjelenítéséért felelős.
     */
    private JTextArea charactersArea;
    /**
     * A különböző pályák közötti választást valósítja meg.
     */
    private JComboBox<String> mapComboBox;

    /**
     * Konstruktor, létrehozza az objektumot
     * @param frame a tartalmazó frame
     * @param game maga a játék
     */
    public MainMenu(MainWindow frame, Game game) {

        parentFrame = frame;
        setSize(800, 500);

        setLayout(new GridLayout(1, 3));

        JPanel panel0 = new JPanel();
        panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
        panel0.setOpaque(false);
        add(panel0);

        panel0.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel label0 = new JLabel("Slipstick Game");
        label0.setForeground(new Color(229, 225, 220));
        label0.setFont(new Font("Lucida Console",Font.BOLD,36));
        label0.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel0.add(label0);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setOpaque(false);
        add(panel1);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel label1 = new JLabel("Player name");
        label1.setForeground(new Color(229, 225, 220));
        label1.setFont(new Font("Lucida Console",Font.BOLD,20));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(label1);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(1300, 30));
        nameField.setFont(new Font("Lucida Console",Font.BOLD,20));
        nameField.setBackground(new Color(163, 184, 204));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(115, 95, 152)));
        nameField.setBorder(new LineBorder(new Color(31, 23, 68),5));

        panel1.add(nameField);

        panel1.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel label2 = new JLabel("Players");
        label2.setForeground(new Color(229, 225, 220));
        label2.setFont(new Font("Lucida Console",Font.BOLD,30));
        label2.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(label2);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        charactersArea = new JTextArea();
        charactersArea.setMaximumSize(new Dimension(1300, 230));
        charactersArea.setEditable(false);
        charactersArea.setFont(new Font("Lucida Console",Font.BOLD,20));
        charactersArea.setBackground(new Color(163, 184, 204));
        charactersArea.setBorder(new LineBorder(new Color(31, 23, 68),5));
        panel1.add(charactersArea);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel label3 = new JLabel("Map");
        label3.setForeground(new Color(229, 225, 220));
        label3.setFont(new Font("Lucida Console",Font.BOLD,20));
        label3.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(label3);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        mapComboBox = new JComboBox<>();
        mapComboBox.setMaximumSize(new Dimension(1300, 50));
        mapComboBox.setFont(new Font("Lucida Console",Font.BOLD,20));
        mapComboBox.setForeground(new Color(187, 158, 158));
        mapComboBox.setBackground(new Color(63, 44, 103));
        mapComboBox.setBorder(new LineBorder(new Color(31, 23, 68),5,true));
        panel1.add(mapComboBox);

        mapComboBox.addItem("Map 1");
        mapComboBox.addItem("Map 2");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setOpaque(false);
        add(panel2);

        panel2.add(Box.createRigidArea(new Dimension(10, 35)));

        addButton = new JButton("Add Player");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(100, 30));
        addButton.setBackground(new Color(115, 95, 152));
        addButton.setForeground((new Color(187, 168, 168)));
        addButton.setFont(new Font("Lucida Console",Font.BOLD,14));
        addButton.setBorder(BorderFactory.createLineBorder(new Color(115, 95, 152)));
        panel2.add(addButton);

        addButton.addActionListener(new ActionListener() {
            /**
             * Eseménykezelő a hozzáadó gombhoz. Elvégzi a karakter hozzáadását a játékhoz.
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                int roomIdx = 0;

                if (!nameField.getText().isEmpty()) {
                    for (int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
                        if (game.getLabyrinth().getRooms().get(i).getCharacters().size() < game.getLabyrinth().getRooms().get(i).getCapacity()) {
                            roomIdx = i;
                            break;
                        }
                    }
                    Student student = new Student(new ArrayList<>(), nameField.getText(), false, new ArrayList<>(), game.getLabyrinth().getRooms().get(roomIdx), false, false);
                    game.getLabyrinth().getRooms().get(roomIdx).addCharacter(student);
                    game.addCharacter(student);
                    update(game);
                    nameField.setText(null);
                }
            }
        });

        panel2.add(Box.createRigidArea(new Dimension(10, 323)));

        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(150, 50));
        startButton.setBackground(new Color(56, 41, 110));
        startButton.setForeground((new Color(187, 158, 158)));
        startButton.setFont(new Font("Lucida Console",Font.BOLD,20));
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.add(startButton);


        /**
         * A játék indítását megvalósító gomb eseménykezelője.
         * Mapvalasztas + map itemekkel feltoltese + számítógép által irányított karakterek hozzáadása
         */
        startButton.addActionListener(new ActionListener() {
            /**
             * A kezdést megvalósító gomb eseménykezelő függvénye.
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {

                if(game.getCharacters().isEmpty()){
                    JOptionPane.showMessageDialog(null, "The game cannot be started with 0 players.");
                } else {
                    if (mapComboBox.getSelectedItem() == "Map 1") {
                        game.getLabyrinth().addRoom(2);
                        game.getLabyrinth().addRoom(3);
                        game.getLabyrinth().addRoom(4);
                        game.getLabyrinth().addRoom(5);
                        game.getLabyrinth().addRoom(6);
                        game.getLabyrinth().connectRooms(0, 6, 1);
                        game.getLabyrinth().connectRooms(0,7, 1);
                        game.getLabyrinth().connectRooms(1,7,1);
                        game.getLabyrinth().connectRooms(2,7,1);
                        game.getLabyrinth().connectRooms(3,7,1);
                        game.getLabyrinth().connectRooms(4,7,1);
                    }
                    else {
                        game.getLabyrinth().addRoom(2);
                        game.getLabyrinth().addRoom(3);
                        game.getLabyrinth().addRoom(4);
                        game.getLabyrinth().addRoom(4);
                        game.getLabyrinth().addRoom(4);
                        game.getLabyrinth().addRoom(7);
                        game.getLabyrinth().addRoom(8);
                    }

                    Random random = new Random();
                    for (int i = 0; i < game.getLabyrinth().getRooms().size(); i++) {
                        for (int j = 0; j < 5; j++) {
                            int rand = random.nextInt(9);
                            if(rand == 0) {
                                game.getLabyrinth().addItem("tvsz", i, game);
                            }
                            if(rand == 1) {
                                game.getLabyrinth().addItem("holybeermug", i, game);
                            }
                            if(rand == 2) {
                                game.getLabyrinth().addItem("ffp2", i, game);
                            }
                            if(rand == 3) {
                                game.getLabyrinth().addItem("wettowel", i, game);
                            }
                            if(rand == 4) {
                                game.getLabyrinth().addItem("cabbagecamambert", i, game);
                            }
                            if(rand == 5) {
                                game.getLabyrinth().addItem("airspray", i, game);
                            }
                            if(rand == 6) {
                                game.getLabyrinth().addItem("faketvsz", i, game);
                            }
                            if(rand == 7) {
                                game.getLabyrinth().addItem("fakeslipstick", i, game);
                            }
                            if(rand == 8) {
                                game.getLabyrinth().addItem("transistor", i, game);
                            }
                        }
                    }

                    for (int i = 0; i < 3; i++) {
                        int rand = random.nextInt(game.getLabyrinth().getRooms().size());
                        Teacher teacher = new Teacher(new ArrayList<Item>(), "Teacher " + i, false, new ArrayList<CharacterProperty>(), game.getLabyrinth().getRooms().get(rand),false, false);
                        game.getLabyrinth().getRooms().get(rand).addCharacter(teacher);
                        game.addCharacter(teacher);
                    }

                    int rand = random.nextInt(game.getLabyrinth().getRooms().size());
                    Cleaner cleaner = new Cleaner(new ArrayList<Item>(), "Cleaner", false, new ArrayList<CharacterProperty>(), game.getLabyrinth().getRooms().get(rand), false, false);
                    game.getLabyrinth().getRooms().get(rand).addCharacter(cleaner);
                    game.addCharacter(cleaner);

                    rand = random.nextInt(game.getLabyrinth().getRooms().size());
                    game.getSlipStick().setItemLocation(game.getLabyrinth().getRooms().get(rand));
                    game.getLabyrinth().getRooms().get(rand).putItem(game.getSlipStick());

                    for (int x = 0; x < game.getLabyrinth().getRooms().size(); x++) {
                        rand = random.nextInt(100);
                        if(rand % 10 == 0) {
                            game.getLabyrinth().getRooms().get(x).SetCursed(true);
                        }
                    }
                    frame.showInterface();
                }
            }
        });
    }

    /**
     * A háttér megjelenítéséért felelős.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon background = new ImageIcon("center.png");
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * A megjelenés frissítéséért felelős.
     * @param game a játék objektum
     */
    public void update(Game game) {
        StringBuilder nameofPlayers = new StringBuilder();
        for (int x = 0; x < game.getLabyrinth().getRooms().size(); x++) {
            for (int i = 0; i < game.getLabyrinth().getRooms().get(x).getCharacters().size(); i++) {
                String name = game.getLabyrinth().getRooms().get(x).getCharacters().get(i).getName();
                nameofPlayers.append(name);
                nameofPlayers.append("\n");
            }
        }
        charactersArea.setText(nameofPlayers.toString());
    }
}