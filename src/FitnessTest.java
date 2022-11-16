/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import java.io.Serializable;
import java.util.*;

public class FitnessTest extends GenericTest implements Serializable {

    //Serialisierung
    private static final long serialVersionUID = 42L;

    //Deklaration
    private Measurement measures4[] = new Measurement[4];
    private FitnessControl fitnessControl = new FitnessControl();

    //Konstruktor für Test Name
    public FitnessTest(String name)
    {
        super(name);
        measures = measures4;
        this.measurementList=Arrays.asList(measures4);
    }

    //Konstruktor für Test Name + Messwerten
    public FitnessTest(String name, Measurement[] valuesOfMeasurement)
    {
        super(name, valuesOfMeasurement);
    }

    public String analyzeValues()
    {
        int sysbt=0, diabt=0, puls=0;
        String test = ("Fitness test:");
        test=test+"\n" + this.getName() + "\n";

        if(this.getMeasurements()!=null) {

//-------------Analyse der Werte des systolischen Blutdruck im Fitnesstest----------------------------------------------
            for (int i = 0; i < 4; i++) {
                if (this.getMeasurements().get(i) != null) {
                    sysbt = sysbt + this.getMeasurements().get(i).getSysbt();
                }
            }
            sysbt = sysbt/4;
            test=test+"\n"+"Systolischer Blutdruck:";
            if(sysbt<100 && sysbt!=0) test = test + "\nNiedriger Blutdruck\n";
            else if (sysbt>=110 && sysbt<130) test = test + "\nNormaler Blutdruck\n";
            else if (sysbt>=130 && sysbt<140) test = test + "\nHochnormaler Blutdruck\n";
            else if (sysbt>=140) test = test + "\nHoher Blutdruck\n";
            else if(sysbt==0) test = test + "\nMessung fehlgeschlagen\n";

//-------------Analyse der Werte des diastolischen Blutdruck im Fitnesstest---------------------------------------------
            for (int i = 0; i < 4; i++) {
                if (this.getMeasurements().get(i) != null) {
                    diabt = diabt + this.getMeasurements().get(i).getDiabt();
                }
            }
            diabt = diabt/4;
            test=test+"\n"+"Diastolischer Blutdruck:";
            if(diabt<60 && diabt!=0) test = test + "\nNiedriger Blutdruck\n";
            else if (diabt>=60 && diabt<84) test = test + "\nNormaler Blutdruck\n";
            else if (diabt>=85 && diabt<89) test = test + "\nHochnormaler Blutdruck\n";
            else if (diabt>=90) test = test + "\nHoher Blutdruck\n";
            else if(diabt==0) test = test + "\nMessung fehlgeschlagen\n";

//-------------Analyse der Pulswerte im Fitnesstest---------------------------------------------------------------------
            for (int i = 0; i < 4; i++) {
                if (this.getMeasurements().get(i) != null) {
                    puls = puls + this.getMeasurements().get(i).getPuls();
                }
            }
            puls = puls/4;
            test=test+"\n"+"Puls:";
            if(puls<60 && puls!=0) test = test + "\nLangsamer Ruhepuls (Bradykardie)\n";
            else if (puls>=60 && puls<100) test = test + "\nNormaler Puls (Normokardie)\n";
            else if (puls>=100) test = test + "\nSchneller Ruhepuls (Tachykardie)\n";
            else if(puls==0) test = test + "\nMessung fehlgeschlagen\n";
        }
        return test;
    }

    public void startTest() {
        super.startTest();
        fitnessControl.setVisible(true);
    }

    public String toString() { return  (super.name + "Fitness Test"); }
}
