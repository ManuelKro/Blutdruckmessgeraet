/**
 * Name: Manuel Kromer
 * Matrikelnummer: 298173
 * E-Mail: manuel.kromer@htwg-konstanz.de
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class MainContent extends JPanel implements ActionListener{

//-----------------------------------Deklarationsteil der Benutzeroberfläche--------------------------------------------
    private GenericTest[] tests = new GenericTest[10];
    private GenericTest[] actualTest = new GenericTest[1];

    private int number = 0;

    private TextArea resultTest;
    private JComboBox<GenericTest> selectTest;
    private DialogName renameTest;
    private DialogTest createTest;

    private File saveFile;
    private FileOutputStream outputFile;
    private ObjectOutputStream objectOutputStream;
    private File openFile;
    private FileInputStream inputFile;
    private ObjectInputStream objectInputStream;
    private File printFile;
    private PrintWriter PrintWriter;

//----------------------------Programm zur Steuerung des Blutdruckmessgerätes-------------------------------------------
    public MainContent() {

        this.setLayout(new BorderLayout());
        repaint();

        //Menu Leiste
        JMenuBar theBar = new JMenuBar();
        JMenu theMenu = new JMenu("Menu");
        JMenuItem theItem1 = new JMenuItem("Starten des Tests");
        JMenuItem theItem2 = new JMenuItem("Einlesen der Messwerte vom Messgeraet");
        JMenuItem theItem3 = new JMenuItem("Gespeicherte Daten laden");
        JMenuItem theItem4 = new JMenuItem("Daten speichern");
        JMenuItem theItem5 = new JMenuItem("Beenden der Anwendung");
        JMenuItem theItem6 = new JMenuItem("Neuen Test anlegen");
        JMenuItem theItem7 = new JMenuItem("Namen des Tests editieren");
        JMenuItem theItem8 = new JMenuItem("Test ausdrucken(in eine Datei)");

        theMenu.add(theItem1);theItem1.addActionListener(this);
        theMenu.add(theItem2);theItem2.addActionListener(this);
        theMenu.add(theItem3);theItem3.addActionListener(this);
        theMenu.add(theItem4);theItem4.addActionListener(this);
        theMenu.add(theItem5);theItem5.addActionListener(this);
        theMenu.add(theItem6);theItem6.addActionListener(this);
        theMenu.add(theItem7);theItem7.addActionListener(this);
        theMenu.add(theItem8);theItem8.addActionListener(this);


//-------------------------------Benutzeroberfläche des Blutdruckmessgerätes--------------------------------------------

        //Init-Block der Menu Leiste
        theBar.add(theMenu);
        this.add(theBar, BorderLayout.NORTH);

        //Init-Block der drei Buttons
        JButton theStartButton = new JButton("Starten des Tests");
        theStartButton.setPreferredSize(new Dimension(200,40));
        this.add(theStartButton); theStartButton.addActionListener(this);

        JButton theReadButton = new JButton("Einlesen der Messwerte");
        theReadButton.setPreferredSize(new Dimension(200,40));
        this.add(theReadButton); theReadButton.addActionListener(this);

        JButton theExitButton = new JButton("Beenden der Anwendung");
        theExitButton.setPreferredSize(new Dimension(200,40));
        this.add(theExitButton); theExitButton.addActionListener(this);

        JPanel theButtonPane = new JPanel();
        theButtonPane.setPreferredSize(new Dimension(220,150));
        theButtonPane.add(theStartButton);
        theButtonPane.add(theReadButton);
        theButtonPane.add(theExitButton);
        this.add(theButtonPane,BorderLayout.WEST);

        //Init-Block der Combo Box
        selectTest = new JComboBox<>();
        selectTest.addActionListener(this::selectTest_ActionPerformed);
        this.add(selectTest, BorderLayout.SOUTH);

        //Init-Block der Zeichenfläche
        SignArea signarea = new SignArea(this);
        this.add(signarea, BorderLayout.CENTER);

        //Init-Block der Textfläche zu der Analyse der Testergebnisse
        resultTest = new TextArea("Analyse", 5,30);
        resultTest.setSize(100,100);
        this.add(resultTest, BorderLayout.EAST);

//-------------------------------Dialog Felder--------------------------------------------------------------------------
        //Init-Block des Dialog zur Test-Namensänderung
        renameTest = new DialogName(this);
        renameTest.setSize(300,200);
        renameTest.setLocation(850, 400);

        //Init-Block des Dialog um einen neuen Test anzulegen
        createTest = new DialogTest();
        createTest.setSize(300,200);
        createTest.setLocation(850, 400);
    }

//-----------------------------------ActionPerformed für die ComboBox um Tests auszuwählen------------------------------
    private void selectTest_ActionPerformed(ActionEvent evt) {
        actualTest[0] = (GenericTest) selectTest.getSelectedItem();


        int index;
        //System.out.println(selectTest.getSelectedIndex());
        index = selectTest.getSelectedIndex();

        if(index==-1)index=0;

        //Übergabe aus Combobox in actualTest Vartiable
        //System.out.println(index);
        if(tests[index]!=null) {
            actualTest[0] = selectTest.getItemAt(index);}

        //Analyse der Werte des Ausgewählten Tests aus der Combobox für Zeichen und Textfläche
        if (actualTest[0] != null) {
            resultTest.setText(actualTest[0].analyzeValues()); }
        repaint();
    }

//---------------------------------Action Listener für die drei Buttons-------------------------------------------------
    public void actionPerformed(ActionEvent e)
    {   if(e.getActionCommand().equals("Starten des Tests")){
            if(actualTest[0]!=null)  {
                actualTest[0].startTest();
            }
            else { JOptionPane.showMessageDialog(null,"Lege einen neuen Test an","Error",JOptionPane.OK_CANCEL_OPTION);}
        }

        else if(e.getActionCommand().equals("Einlesen der Messwerte")){
            if(actualTest[0]!=null) {
                actualTest[0].readValues();
                resultTest.setText(actualTest[0].analyzeValues());
                repaint();
            }
            else {
                JOptionPane.showMessageDialog(null,"Lege einen neuen Test an","Error",JOptionPane.OK_CANCEL_OPTION);}
        }

        else if(e.getActionCommand().equals("Beenden der Anwendung")) {
            System.exit(0);
        }


//------------------------------------Action Listener für die Menü Auswahl----------------------------------------------
        else if(e.getActionCommand().equals("Einlesen der Messwerte vom Messgeraet")){

            int k = 0;
            int[] measuredIntArray = new int[3];
            String[] measuredStringArray;
            String measuredValueLine;
            Measurement[] measurement = new Measurement[6];
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setDialogTitle("Messwerte auswaehlen");
            fileChooser.showOpenDialog(this);

            openFile = fileChooser.getSelectedFile();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(openFile));

                while ((measuredValueLine = bufferedReader.readLine()) != null) {
                    System.out.println(measuredValueLine);
                    measuredStringArray = measuredValueLine.split(" ");
                    for (int h = 0; h < 3; h++) {
                        measuredIntArray[h] = Integer.parseInt(measuredStringArray[h]);
                    }
                    measurement[k] = new Measurement(measuredIntArray[2], measuredIntArray[1], measuredIntArray[0], new Date());
                    k++;
                }
                bufferedReader.close();

                k = 0;
                for (int i = 0; i < 6; i++) {
                    if (measurement[k] != null) {
                        k++;
                    }
                }

                if (k == 6) {
                    tests[number] = new SchellongTest(openFile.getName() + " ", measurement);
                } else if (k == 4) {
                    tests[number] = new FitnessTest(openFile.getName() + " ", measurement);
                }

                actualTest[0] = tests[number];
                number++;

                if (number > 9) number = 0;
                selectTest.addItem(actualTest[0]);
                selectTest.setSelectedIndex(selectTest.getItemCount() - 1);

                resultTest.setText(actualTest[0].analyzeValues());
                actualTest[0] = (GenericTest) selectTest.getSelectedItem();
            } catch (IOException ex) { ex.printStackTrace(); }
        }

        else if(e.getActionCommand().equals("Neuen Test anlegen")) {

            if(selectTest.getItemCount()>=10){
                JOptionPane.showMessageDialog(null,"Kein Speicherplatz mehr","ERROR",JOptionPane.OK_CANCEL_OPTION);
            }

            else {
                createTest.setVisible(true);

                if (createTest.getclosedByOk()) {
                    if (createTest.getTestType()) {
                        tests[number] = new FitnessTest(createTest.getName());
                        actualTest[0] = tests[number];
                    }
                    else{
                        tests[number]=new SchellongTest(createTest.getName());
                        actualTest[0]=tests[number];
                    }

                    selectTest.addItem(actualTest[0]);
                    selectTest.setSelectedIndex(selectTest.getItemCount()-1);
                }
                number++;
            }

        }

        else if(e.getActionCommand().equals("Gespeicherte Daten laden")){

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(this);

            openFile = fileChooser.getSelectedFile();

            try { inputFile = new FileInputStream(openFile); }
                catch (FileNotFoundException ex) { ex.printStackTrace(); }
            try { objectInputStream = new ObjectInputStream(inputFile); }
                catch (IOException ex) { ex.printStackTrace(); }
            try { tests = (GenericTest[]) objectInputStream.readObject();
                selectTest.removeAllItems();
                for(int j=0;j<10;j++) {
                    if(tests[j]!=null) selectTest.insertItemAt(tests[j],j);
                    selectTest.setSelectedIndex(0);
                    actualTest[0] = (GenericTest) selectTest.getSelectedItem();
                }

                number = selectTest.getItemCount();
                repaint(); }
                catch (IOException | ClassNotFoundException ex) { ex.printStackTrace(); }
        }

        else if(e.getActionCommand().equals("Daten speichern")) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);

            saveFile = fileChooser.getSelectedFile();

                try { outputFile = new FileOutputStream(saveFile); }
                    catch (IOException ex) { ex.printStackTrace(); }
                try{ objectOutputStream = new ObjectOutputStream(outputFile); }
                    catch (IOException ex) { ex.printStackTrace(); }
                try{ objectOutputStream.writeObject(tests); }
                    catch (IOException ex) { ex.printStackTrace(); }
        }

        else if(e.getActionCommand().equals("Namen des Tests editieren")){

            if(actualTest[0]!=null) {
                renameTest.setName(actualTest[0].getName());
                renameTest.setVisible(true);
            }

            else {JOptionPane.showMessageDialog(null,"Erstelle zuerst einen Test","Error",JOptionPane.OK_CANCEL_OPTION);}

            repaint();
        }

        else if(e.getActionCommand().equals("Test ausdrucken(in eine Datei)")){

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showDialog(this, "Datei drucken");

            printFile = fileChooser.getSelectedFile();

            try { PrintWriter = new PrintWriter(printFile); }
                catch (FileNotFoundException ex) { ex.printStackTrace(); }

            actualTest[0].print(PrintWriter);
            PrintWriter.close();
        }

    }

    public GenericTest getActualTest() { return actualTest[0]; }
}