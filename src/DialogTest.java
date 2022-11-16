/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogTest extends JDialog implements ActionListener{

    //Deklaration
    private boolean closedByOk = false;
    private boolean chooseTest = true;
    private GenericTest selectedTest;
    private String nameTest;

    //Konstruktor
    private JTextField inputField = new JTextField();
    private JRadioButton rbFitnesstest = new JRadioButton();
    private JRadioButton rbSchellongtest = new JRadioButton();
    private JButton confirmButton = new JButton();

    public DialogTest(){
        super((JFrame)null, "Test Dialog", true);

        rbFitnesstest.setText("Fitness Test");
        rbSchellongtest.setText("Schellong Test");
        confirmButton.setText("Bestaetigen");

        this.add(inputField, BorderLayout.NORTH);
        this.add(rbFitnesstest, BorderLayout.EAST);
        this.add(rbSchellongtest, BorderLayout.WEST);
        this.add(confirmButton, BorderLayout.SOUTH);

        rbFitnesstest.addActionListener(this);
        rbSchellongtest.addActionListener(this);
        confirmButton.addActionListener(this);
    }

    //Action-Listener
   public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Bestaetigen")) {
           if (inputField.getText().isEmpty() == false) {
               closedByOk = true;
               nameTest = (inputField.getText() + " ");
               inputField.setText("");

               rbSchellongtest.setSelected(false);
               rbFitnesstest.setSelected(false);
               rbFitnesstest.setEnabled(true);
               rbSchellongtest.setEnabled(true);
               dispose();
           } else {
               JOptionPane.showMessageDialog(null, "Name eingeben", "Error", JOptionPane.OK_CANCEL_OPTION);
           }

       } else if (e.getActionCommand().equals("Fitness Test")) {
           rbFitnesstest.setEnabled(false);
           rbSchellongtest.setEnabled(true);
           rbSchellongtest.setSelected(false);
           chooseTest = true;
       }

       else if (e.getActionCommand().equals("Schellong Test")) {
           rbSchellongtest.setEnabled(false);
           rbFitnesstest.setEnabled(true);
           rbFitnesstest.setSelected(false);
           chooseTest = false;
       }
   }

   //Getter Methoden
    public GenericTest getSelectedTest()
    {
        return selectedTest;
    }

    public String getName() {
        return nameTest;
    }

    public boolean getclosedByOk() { return (closedByOk); }

    public boolean getTestType() { return (chooseTest); }
}
