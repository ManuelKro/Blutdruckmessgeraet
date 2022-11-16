/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class GenericTest implements Serializable {

    //Serialisierung
    private static final long serialVersionUID = 41L;

    //Deklaration
    protected String name;
    protected List<Measurement> measurementList = new ArrayList<>();
    private Date datum;
    private Random zufallswert = new Random();
    Measurement[] measures = new Measurement[10];

    //Konstruktor
    public GenericTest(String nameOfTest) {
        this.name = nameOfTest;
    }

    //Konstruktor
    public GenericTest(String nameOfTest, Measurement[] valuesOfMeasurement) {
        datum = new Date();
        this.name = nameOfTest;
        this.measurementList = Arrays.asList(valuesOfMeasurement);
    }

    // Getter und Setter Methoden
    public String getName() { return this.name; }

    public void setName(String nameOfTest) { this.name = nameOfTest; }

    public Measurement[] getMeasures() { return this.measures; }

    public List<Measurement> getMeasurements() { return this.measurementList; }

    //Zufallsgenerator der Blutdruckwerte
    public void readValues() {
        for (int i = 0; i < measures.length; i++) {
            measures[i] = new Measurement(50 + zufallswert.nextInt(70), 90 + zufallswert.nextInt(70), 50 + zufallswert.nextInt(70), new Date());
        }
    }

    //Speicherung des Testzeitpunkt
    public void startTest() {
        datum = new Date();
    }

    // Formatierung der Ausgabe des Druckformat in eine Datei mit Testname, Datum und den Werten des Tests
    public void print(PrintWriter PrintWriter) {
        int size;
        size = measurementList.size();

        PrintWriter.println(name);
        PrintWriter.println(this.analyzeValues());

        if (datum != null) {
            SimpleDateFormat theFormatter = new SimpleDateFormat("dd.MM.yyyy");
            String theString = theFormatter.format(datum);
            PrintWriter.println(theString);
        }

        //Aufruf der Methode printValues der Klasse Measurement
        for (int i = 0; i < size; i++) {
            measurementList.get(i).printValues(PrintWriter);
        }
    }

    public abstract String analyzeValues();
}

