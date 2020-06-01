package rentCar;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Fahrzeug {

    private static int id = 1;
    int idFahrzeug;
    String marke;
    String model;
    String kennzeichen;
    String typ;
    String getriebe;
    String kraftstoffart;
    int alter;
    int sitzplaetze;
    int erstzulassung;
    int kilometer;
    int mietpreis;
    boolean verfuegbarkeit;
    LocalDate abholzeit;
    LocalDate rueckgabezeit;
    int tagenGebucht = 1;

    Fahrzeug(String marke, String model, String kennzeichen, String typ, String getriebe, String kraftstoffart, int alter, int sitzplaetze, int erstzulassung, int kilometer, int mietpreis, boolean verfuegbarkeit){

        this.idFahrzeug = id++;
        this.marke = marke;
        this.model = model;
        this.kennzeichen = kennzeichen;
        this.typ = typ;
        this.getriebe = getriebe;
        this.kraftstoffart = kraftstoffart;
        this.alter = alter;
        this.sitzplaetze = sitzplaetze;
        this.erstzulassung = erstzulassung;
        this.kilometer = kilometer;
        this.mietpreis = mietpreis;
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public abstract void fuehrerscheinKategorie();

    public static void fahrzeugListe(ArrayList<Fahrzeug> fahrzeugs) {

        System.out.println();
        System.out.println("Flottenuebersicht: ");
        for (int i = 0; i < fahrzeugs.size(); i++ ) {
            System.out.println(i+1 + ". " + fahrzeugs.get(i).marke + " - \t" + fahrzeugs.get(i).model + " - \t" + fahrzeugs.get(i).kennzeichen +  " - \t" + fahrzeugs.get(i).kilometer + " km" + " - \t" + fahrzeugs.get(i).mietpreis + "�/Tag" + " - \t" + " ab " + fahrzeugs.get(i).alter+ " Jahre - \t\t" + " verfuegbarkeit: " + fahrzeugs.get(i).verfuegbarkeit + " - \t" + "Mietzeitraum: " + fahrzeugs.get(i).abholzeit + " bis " + fahrzeugs.get(i).rueckgabezeit);
        }
//        Office.menuListe();
    }
    
    public static void gesuchtenFahrzeug() {
		
    	ArrayList<Fahrzeug> auswahl = new ArrayList<Fahrzeug>();
    	auswahl.clear();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Was fuer einen Fahrzeug suchst Du: ");
    	String gesuchtenBegrief = scanner.nextLine();
    	
    	for (Fahrzeug fahrzeug : gibbAlleFahrzeuge()) {
			try {
				if (fahrzeug.mietpreis <= Integer.parseInt(gesuchtenBegrief)) {
					auswahl.add(fahrzeug);
				}
			}
			catch (NumberFormatException ex) {
				if (fahrzeug.marke.equalsIgnoreCase(gesuchtenBegrief) || fahrzeug.typ.equalsIgnoreCase(gesuchtenBegrief))
					auswahl.add(fahrzeug);
			}
			catch (Exception ex) {
				System.out.println("Keine Treffer!");
			}
		}
    	fahrzeugListe(auswahl);
//		fahrzeugListe(FahrzeugFilter.zeigeFahrzeugs(fahrzeugs, a -> a.marke.equalsIgnoreCase(gesuchtenBegrief)));
	}
    
    public static ArrayList<Fahrzeug> gibbAlleFahrzeuge () {
		
		ArrayList<Fahrzeug> alleFahrzeuge = new ArrayList<Fahrzeug>();
		for (Fahrzeug fahrzeug : Auto.autos) {
			alleFahrzeuge.add(fahrzeug);
		}
		for (Fahrzeug fahrzeug : Motorrad.motorrads) {
			alleFahrzeuge.add(fahrzeug);
		}
		return alleFahrzeuge;
	}
    
    public static void fahrzeugAusleihen(Kunde kunde) {

		System.out.print("Was fuer einen Fahrzeug moechtest Du haben? Gibb bitte die Kennzeichen an: ");
		Scanner scanner = new Scanner(System.in);
		
		boolean buchung = false;
		GoTo:
		while(!buchung) {
			String fahrzeug = scanner.nextLine();
			for (Fahrzeug item : gibbAlleFahrzeuge()) {
				if (item.kennzeichen.equals(fahrzeug.trim()) && item.verfuegbarkeit == true) {

					if (Kunde.checkKunde(kunde, item)) {
						mietZeitraum(item, kunde);
						System.out.println("Mietzeitraum = " + item.tagenGebucht + " Tagen.");
						item.verfuegbarkeit = false;
						buchung = true;
						System.out.println("Der Fahrzeug ist gebucht!");
						item.fuehrerscheinKategorie();
						Kunde.deineBuchung(kunde,item);
						Fahrzeug.fahrzeugListe(gibbAlleFahrzeuge());
					}
					else {
						System.out.println("Du bist zu jung. Du kannst nicht den Fahrzeug buchen. Der Fahrzeug is ab " + item.alter + " Jahre.");
						break GoTo;
					}
				}
			}
			if (buchung == false) {
				System.out.print("Falsche Kennzeichen! Gibb nochmal die Kennzeichen an: ");
			}
		}
	}
    
    public static void mietZeitraum(Fahrzeug item, Kunde kunde) {

		boolean korekteMietZeitraum = false;
		while (korekteMietZeitraum != true) {
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.print("Gibb bitte die Abholzeit im richtigen Format (zB.: 31 12 1999) an: ");
				DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
				item.abholzeit = LocalDate.parse(scanner.nextLine(), f);
				if (item.abholzeit.minusDays(0).isBefore(LocalDate.now())) {
					System.out.println("Falsche Datum!");
					continue;
				}
				System.out.print("Gibb bitte die Rueckgabezeit im richtigen Format (zB.: 31 12 1999) an: ");
				item.rueckgabezeit = LocalDate.parse(scanner.nextLine(), f);
				if (item.rueckgabezeit.minusDays(0).isBefore(item.abholzeit)) {
					System.out.println("Falsche Datum!");
					continue;
				}
				korekteMietZeitraum = true;
			} catch (DateTimeException ex) {
				System.out.println("Du hast falschen Datum-Format eingegeben!");
//			System.out.println(ex.getMessage());
			} catch (Exception ex) {
				System.out.println(ex);
			} finally {
				System.out.println("Die alle Daten wurden gespeichert.");
			}
		}
		LocalDate upTo = item.abholzeit;
		while (upTo.plusDays(1).isBefore(item.rueckgabezeit)) {
			upTo = upTo.plusDays(1);
			item.tagenGebucht++;
		}
	}
    
    public static void fahrzeugRueckgabe() {

		System.out.print("Was fuer einen Fahrzeug moechtest Du zurueck geben? Gibb bitte die Kennzeichen an: ");
		Scanner scanner = new Scanner(System.in);
		boolean rueckgabe = false;
		while(!rueckgabe) {
			String fahrzeug = scanner.nextLine();
			for (Fahrzeug item : gibbAlleFahrzeuge()) {
				if (item.kennzeichen.equals(fahrzeug.trim()) && item.verfuegbarkeit == false) {
					boolean km = false;
					while(!km) {
						System.out.print("Gibb bitte neuen/richtigen Kilometerstand an: ");
						int kmStand = scanner.nextInt();
						if (kmStand > item.kilometer) {
							km = true;
							item.kilometer = kmStand;
							System.out.println("Der Fahrzeug ist zurueckgegeben. Der neuen Kilometerstand: " + item.kilometer);
							item.verfuegbarkeit = true;
							rueckgabe = true;
							item.abholzeit = null;
							item.rueckgabezeit = null;
							Fahrzeug.fahrzeugListe(gibbAlleFahrzeuge());
						}
					}
				}
			}
			if (rueckgabe == false) {
				System.out.print("Falsche Kennzeichen!");
				Office.menuListe();
			}
		}
	}
}
