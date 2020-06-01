package rentCar;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FahrzeugFilter {

	static ArrayList<Fahrzeug> zeigeFahrzeugs (ArrayList<Fahrzeug> fahrzeugs, Predicate<Fahrzeug> wahl){
		
		ArrayList<Fahrzeug> auswahl = new ArrayList<Fahrzeug>();
		for (Fahrzeug fahrzeug : fahrzeugs) {
			if (wahl.test(fahrzeug)) {
				auswahl.add(fahrzeug);
			}
		}
		return auswahl;
	}
}
