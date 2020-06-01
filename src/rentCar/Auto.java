package rentCar;

import java.util.ArrayList;

public class Auto extends Fahrzeug {

    int tueren;

    Auto(String marke, String model, String kennzeichen, String typ, String getriebe, String kraftstoffart, int alter, int sitzplaetze, int erstzulassung, int kilometer, int mietpreis, boolean verfuegbarkeit, int tueren){

        super(marke, model, kennzeichen, typ, getriebe, kraftstoffart, alter, sitzplaetze, erstzulassung, kilometer, mietpreis, verfuegbarkeit);
        this.tueren = tueren;
    }

    @Override
    public void fuehrerscheinKategorie(){
        System.out.println("ACHTUNG! Fuer den Fahrzeug brauchst Du Fuehrerschein Kategorie B.");
    }

    static ArrayList<Fahrzeug> autos = new ArrayList<>();
    static {
        autos.add(new Auto("Mercedes", "A", "MAR 2145", "Limousinen", "Automatik", "Benzin", 21, 5, 2013, 125000, 65, true, 4));
        autos.add(new Auto("BMW", "M3", "MRS 2148", "Coupe", "Automatik", "Benzin", 25, 4, 2019, 12500, 190, true, 4));
        autos.add(new Auto("Audi", "RS6", "MAS 1984", "Kombi", "Automatik", "Benzin", 25, 5, 2020, 5500, 250, true, 4));
        autos.add(new Auto("BMW", "X6", "BMW 2148", "SUV", "Automatik", "Disel", 25, 4, 2019, 9500, 150, true, 4));
        autos.add(new Auto("Audi", "R8", "NAS 1999", "Coupe", "Automatik", "Benzin", 25, 2, 2019, 9900, 400, true, 2));
    }
}

