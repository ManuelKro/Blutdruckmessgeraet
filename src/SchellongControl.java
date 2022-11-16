/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

public class SchellongControl extends TestControl {

    public SchellongControl(){
        this.setSize(300,200);
    }

    public void stateMachine()
    {
        super.setTitle("Schellong Test");
        switch(super.state)
        {
            case 1:
                super.instructionLabel.setText("Entspannt 10 Minuten auf der Liege sich ablegen das der Kreislauf sich beruhigt");
                break;

            case 2:
                super.instructionLabel.setText("---Blutdruck wird gemessen---");
                break;

            case 3:
                super.instructionLabel.setText("---Puls wird gemessen---");
                break;

            case 4:
                super.instructionLabel.setText("Bitte zügig aufstehen und 10 Minuten lang stehen");
                break;

            case 5:
                super.instructionLabel.setText("---Blutdruck wird gemessen---");
                break;

            case 6:
                super.instructionLabel.setText("---Puls wird gemessen---");
                break;

            case 7:
                super.instructionLabel.setText("Messung erfolgreich beendet!");
                super.furtherButton.setVisible(false);
                super.cancelButton.setText("Schliessen");
                break;
        }
    }
}