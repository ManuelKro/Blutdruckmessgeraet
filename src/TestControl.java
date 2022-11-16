/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestControl extends JDialog implements ActionListener {

    int delay = 1000;
    int state = 0;

    //Action Listener
    //transient keyword um den Error "java.io.NotSerializableException: TestControl$1" zu vermeiden
    transient ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            state++;
            stateMachine();
            Toolkit.getDefaultToolkit().beep();
            theTimer.stop();
        }};

    //Deklaration
    protected transient Timer theTimer = new Timer(delay, taskPerformer);
    protected transient JLabel instructionLabel = new JLabel();
    protected transient JButton furtherButton = new JButton();;
    protected transient JButton cancelButton = new JButton();;
    private transient JPanel decisionPanel = new JPanel();

    //Action Events
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Weiter")) {
            state++;
            stateMachine();
        }
        else if (e.getActionCommand().equals("Abbrechen")) {
            theTimer.stop();
            dispose();
        }
        else if (e.getActionCommand().equals("Schliessen")) {
            theTimer.stop();
            dispose();
        }
        else if (e.getActionCommand().equals("Timer")) {
            state++;
            stateMachine();
            Toolkit.getDefaultToolkit().beep();
            theTimer.stop();
        }
    }

    //Konstruktor
    public TestControl() {
            super((JFrame) null, "Test Control", true);

            instructionLabel.setText("instruction");

            furtherButton.setText("Weiter");
            furtherButton.addActionListener(this);

            cancelButton.setText("Abbrechen");
            cancelButton.addActionListener(this);

            decisionPanel.add(furtherButton);
            decisionPanel.add(cancelButton);

            this.add(instructionLabel, BorderLayout.CENTER);
            this.add(decisionPanel, BorderLayout.SOUTH);
        }

    protected void stateMachine(){};
}
