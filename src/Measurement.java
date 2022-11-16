/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Measurement implements Serializable {

    //Serialisierung
    private static final long serialVersionUID = 44L;

    //Deklaration
    private int puls;
    private int sysbt;
    private int diabt;
    private Date measuretime;

    //Konstruktor
    public Measurement(int puls, int sysbt, int diabt, Date measuretime) {
        this.puls = puls;
        this.sysbt = sysbt;
        this.diabt = diabt;
        this.measuretime = measuretime;
    }

    //Getter Methoden
    public int getDiabt() { return this.diabt; }
    public int getSysbt() { return this.sysbt; }
    public int getPuls() { return this.puls; }
    public Date getDatum(){return this.measuretime;}

    // Formatierung der Ausgabe der Blutdruck Werte + Datum
    public void printValues(PrintWriter PrintWriter) {
        SimpleDateFormat theFormatter = new SimpleDateFormat("dd.MM.yyyy");
        String DateString = theFormatter.format(measuretime);

        //System.out.println(DateString);
        //System.out.println("Puls:" + this.puls + "\nsystolischer Blutdruck: " + this.sysbt + "\ndiastolischer Blutdruck:" + this.diabt + "\n");
        PrintWriter.println(DateString);
        PrintWriter.println("Puls:" + this.puls + "\nsystolischer Blutdruck: " + this.sysbt + "\ndiastolischer Blutdruck:" + this.diabt + "\n");

    }
}
