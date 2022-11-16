/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DialogName extends JDialog implements ActionListener {

    //Deklaration
    private boolean closedByOk = false;
    private String Rename;
    private MainContent mC;

    //Konstruktor
    private JButton okButton = new JButton();
    private JButton zurueckButton = new JButton();
    private JPanel theOkPanel = new JPanel();
    private JTextField renameTextfield = new JTextField(20);

    public DialogName(MainContent nf) {
        super((JFrame) null, "Name des Tests ändern", true);

        mC = nf;
        renameTextfield.setText("");

        okButton.setText("OK");
        okButton.addActionListener(this);

        zurueckButton.setText("Zurueck");
        zurueckButton.addActionListener(this);

        theOkPanel.add(okButton);
        theOkPanel.add(zurueckButton);

        this.add(renameTextfield, BorderLayout.NORTH);
        this.add(theOkPanel, BorderLayout.SOUTH);
    }

    //Action Listener
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("OK")) {
            if (renameTextfield.getText().isEmpty() == false) {
                Rename = (renameTextfield.getText() + " ");
                mC.getActualTest().setName(Rename);
                renameTextfield.setText("");
                closedByOk = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Name eingeben", "Error", JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }

    //Setter Methode
    public void setName(String name2) { this.renameTextfield.setText(name2); }
}