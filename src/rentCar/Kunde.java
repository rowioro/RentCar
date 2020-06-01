package rentCar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Kunde {

    private static int id = 1;
    int idKunde;
	String name;
    String vorname;
    String ausweisNummer;
    String wohnort;
    String strasse;
    String hausNummer;
    String postleizahl;
    String telefonNummer;
    LocalDate geburtsDatum;

    Kunde(String name, String vorname, String ausweisNummer, String wohnort, String strasse, String hausNummer, String postleizahl, String telefonNummer, LocalDate geburtsDatum){
        
    	this.idKunde = id++;
    	this.name = name;
        this.vorname = vorname;
        this.ausweisNummer = ausweisNummer;
        this.wohnort = wohnort;
        this.strasse = strasse;
        this.hausNummer = hausNummer;
        this.postleizahl = postleizahl;
        this.telefonNummer = telefonNummer;
        this.geburtsDatum = geburtsDatum;
    }

    Kunde(){
        this.idKunde = id++;
    }

    static ArrayList<Kunde> kundenListe = new ArrayList<Kunde>();
    static {
        Kunde kunde1 = new Kunde("Kowalski", "Jan", "PL12345", "Munich", "Augustenstrasse", "73", "80333", "004917632307556", LocalDate.of(1998,4,26));
        kundenListe.add(kunde1);
    }

    public static void kundenListe(){

        System.out.println();
        System.out.println("Kundenlisteuebersicht: ");
        for (Kunde kunde: kundenListe) {
            System.out.println(kunde.idKunde + " - \t" + kunde.vorname + " " + kunde.name + " - \t" + kunde.ausweisNummer + " - \t" + kunde.postleizahl + " " + kunde.wohnort + ", " + kunde.strasse + " " + kunde.hausNummer);
        }
//        Office.menuListe();
    }

    public static void datenKundeEingeben() {

        boolean korekteGeburtsDatum = false;
        Scanner scanner = new Scanner(System.in);
        Kunde kunde = new Kunde();
        System.out.println(">>>KUNDENDATEN<<< ");
        while(korekteGeburtsDatum != true){
            try {
                System.out.print("Gibb die Geburtsdatum im richtigen Format (zB.: 31 12 1999) an: ");
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
                kunde.geburtsDatum = LocalDate.parse(scanner.nextLine(), f);
                korekteGeburtsDatum = true;
                System.out.print("Gibb die Vorname an: ");
                kunde.vorname = scanner.nextLine();
                System.out.print("Gibb die Name an: ");
                kunde.name = scanner.nextLine();
                System.out.print("Gibb die Ausweis-Nummer an: ");
                kunde.ausweisNummer = scanner.nextLine();
                System.out.print("Gibb der Wohnort an: ");
                kunde.wohnort = scanner.nextLine();
                System.out.print("Gibb die Strasse an: ");
                kunde.strasse = scanner.nextLine();
                System.out.print("Gibb die Haus-Nummer an: ");
                kunde.hausNummer = scanner.nextLine();
                System.out.print("Gibb die Postleizahl an: ");
                kunde.postleizahl = scanner.nextLine();
                System.out.print("Gibb die Telefon-Nummer an: ");
                kunde.telefonNummer = scanner.nextLine();
            }
            catch (DateTimeException ex){
                System.out.println("Du hast falschen Datum-Format eingegeben!");
 //               System.out.println(ex.getMessage());
            }
            catch (Exception ex){
                System.out.println(ex);
            }
            finally {
                System.out.println("Die alle Daten wurden gespeichert.");
            }
        }
        kundenListe.add(kunde);
        Fahrzeug.fahrzeugAusleihen(kunde);
    }

    public static boolean checkKunde(Kunde kunde, Fahrzeug fahrzeug) {
        boolean go = false;
        int alter = fahrzeug.alter;
        if (kunde.geburtsDatum.plusYears(alter).isBefore(LocalDate.now())) {
            go =  true;
        }
        return go;
    }

    public static void deineBuchung(Kunde kunde, Fahrzeug item) {

        File file = new File("kundeBuchung.txt");
        try{
            if (!file.exists()){
                file.createNewFile();
                System.out.println("Die Datei wurde erfolgreich erstellt.");
            }
            if (file.canWrite()){
                Formatter formatter;
                try (FileWriter fileWriter = new FileWriter(file, false)) {
                    formatter = new Formatter(fileWriter);
                    formatter.format("%s \r\n", "Deine Fahrzeug-Buchung:"
                            + "\n~~~~~~~~~~~~~~~~~~Deine Daten~~~~~~~~~~~~~~~~~~~ \n"
                            + "Kunden-Nummer Id: " + kunde.idKunde
                            + "\nDeine Name: " + kunde.vorname + " " + kunde.name
                            + "\nAusweiss-Nummer: " + kunde.ausweisNummer
                            + "\nDeine Adresse: " + kunde.postleizahl + " " + kunde.wohnort + ", " + kunde.strasse + " " + kunde.hausNummer
                            + "\n~~~~~~~~~~~~~~~~~Dein Fahrzeug~~~~~~~~~~~~~~~~~~ \n"
                            + "Fahrzeug-Nummer Id: " + item.idFahrzeug
                            + "\nMarke: " + item.marke
                            + "\nModel: " + item.model
                            + "\nMietzeitraum: " + item.abholzeit + " bis " + item.rueckgabezeit
                            + "\nMietpreis: " + item.mietpreis + " �/Tag"
                            + "\nDu hast den Fahrzeug fuer " + item.tagenGebucht + " Tagen gebucht."
                            + "\nTotal Rechnung = " + item.tagenGebucht * item.mietpreis + " �.");
                }
                System.out.println("Die Rechnung wurde erfolgreich erstellt.");

                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    System.out.println(scanner.nextLine());
                }

                formatter.close();
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

