/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import javax.swing.*;
import java.awt.*;


public class SignArea extends JPanel {
    private MainContent mainContent;
    private GenericTest test;

    public SignArea(MainContent content)
    {mainContent = content;}

    public void SetTester(GenericTest Test)
    {
        this.test = Test;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Measurement theMeasurement;

        g.drawLine( 50,280,320,280); //X-Achse   x1,y1,x2,y2
        g.drawLine(315, 275, 320, 280); //Pfeil
        g.drawLine(315, 285, 320, 280); //Pfeil
        for(int a=0;a<6;a++) {
            int b=100+(35*a);
        g.drawLine(b, 275, b, 285); //Messpunkte der X-Achse
        }

        g.drawLine(70,300,70,50); //Y-Achse   x1,y1,x2,y2
        g.drawLine(65, 55, 70, 50); //Pfeil
        g.drawLine(75, 55, 70, 50); //Pfeil
        for(int c=0;c<6;c++) {
            int d=100+(30*c);
        g.drawLine(65, d, 75, d); //Messskala Striche der Y-Achse
        }

        g.setColor(Color.GREEN);
        g.drawString("Puls", 60, 15);       //Y-Achse
        g.setColor(Color.BLUE);
        g.drawString("Diastolischer Blutdruck", 60, 30); //Y-Achse
        g.setColor(Color.RED);
        g.drawString("Systolischer Blutdruck",60,45);    //Y-Achse
        g.setColor(Color.BLACK);
        g.drawString("Messwert", 330, 290); //X-Achse

        if(mainContent.getActualTest() != null)
        {
            int x = 95;

            for(int i = 0; i < mainContent.getActualTest().getMeasurements().size(); i++) {
                if(mainContent.getActualTest().getMeasurements().get(i) != null){

                    theMeasurement = mainContent.getActualTest().getMeasurements().get(i);

                    g.setColor(Color.BLUE);
                    g.fillOval(x, theMeasurement.getDiabt()*-1 + 250, 10, 10);

                    g.setColor(Color.GREEN);
                    g.fillOval(x, theMeasurement.getPuls()*-1 + 250, 10, 10);

                    g.setColor(Color.RED);
                    g.fillOval(x, theMeasurement.getSysbt()*-1 + 250, 10, 10);
                    x = x+35;
                }
            }
        }
    }
}
