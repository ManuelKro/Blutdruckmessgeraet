/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

public class FitnessControl extends TestControl{

    public FitnessControl(){
        this.setSize(300,200);
    }

    public void stateMachine()
    {
        super.setTitle("Fitness Test Laufband");
        switch(super.state)
        {
            case 1:
                super.instructionLabel.setText("Aufgabe: 10 Sekunden auf 8 km/h gehen");
                break;

            case 2:
                super.instructionLabel.setText("Laufe auf 10 km/h bis zum ertönen des Signals");
                super.furtherButton.setVisible(false);
                super.theTimer.setInitialDelay(5000);
                super.theTimer.start();
                break;

            case 3:
                super.instructionLabel.setText("Erhöhen auf 12 km/h");
                super.furtherButton.setVisible(true);
                break;

            case 4:
                super.instructionLabel.setText("Laufe auf 14 km/h bis zum ertönen des Signals");
                super.furtherButton.setVisible(false);
                super.theTimer.setInitialDelay(5000);
                super.theTimer.start();
                break;

            case 5:
                super.instructionLabel.setText("Messung erfolgreich beendet!");
                super.furtherButton.setVisible(false);
                super.cancelButton.setText("Schliessen");
                break;
        }
    }
}
