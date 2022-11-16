/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import javax.swing.*;
import java.awt.*;

public class main {
    static public void main(String[] args) {

        JFrame theFrame = new JFrame();
        theFrame.setSize(900, 400);
        theFrame.setLocation(600, 300);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainContent thePane = new MainContent();
        theFrame.setContentPane(thePane);
        theFrame.setVisible(true);

    }
}
