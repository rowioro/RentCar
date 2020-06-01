package rentCar;

import java.util.Scanner;

public class Office {

	public static void main(String[] args) {

		menuListe();
	}

	public static void menuListe() {

		for(;;) {
		System.out.println();
		System.out.println("MENU");
		System.out.println("--------------------------------------------------------");
		System.out.println("1. PKW Flottenuebersicht ansehen");
		System.out.println("2. Motorrad Flottenuebersicht ansehen");
		System.out.println("3. Fahrzeug ausleihen");
		System.out.println("4. Fahrzeug Rueckgabe");
		System.out.println("5. Kunden Liste ansehen");
		System.out.println("6. Suche Fahrzeug");
		System.out.println("7. EXIT");
		System.out.println("--------------------------------------------------------");
		try {
			System.out.print("Was willst Du machen? Gibst Du die Aufgabennummer ein: ");
			Scanner scanner = new Scanner(System.in);
			int menu = scanner.nextInt();

			switch (menu) {
				case 1:
					Fahrzeug.fahrzeugListe(Auto.autos);
					break;
				case 2:
					Fahrzeug.fahrzeugListe(Motorrad.motorrads);
					break;
				case 3:
					Kunde.datenKundeEingeben();
					break;
				case 4:
					Fahrzeug.fahrzeugRueckgabe();
					break;
				case 5:
					Kunde.kundenListe();
					break;
				case 6:
					Fahrzeug.gesuchtenFahrzeug();
					break;
				case 7:
					return;
				default:
					System.out.println("Du hast keine richtige Aufgabenummer eingegeben!");
					break;
			}
		}
		catch (StackOverflowError e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception ex){
			System.out.println("Du hast keine richtige Aufgabenummer eingegeben!");
		}
		finally {
			System.out.println("Die alle Daten wurden gespeichert.");
		}
		}
	}
}