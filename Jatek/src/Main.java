import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Main osztály, amely a játékot futtatja.
 */



public class Main {
    public static boolean  randomize = true;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });

        //JOptionPane.showMessageDialog(null, "You have picked up the Slipstick. Students won");

    }
}