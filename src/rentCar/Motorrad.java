package rentCar;

import java.util.ArrayList;

public class Motorrad extends Fahrzeug {

    String antriebsart;

    Motorrad(String marke, String model, String kennzeichen, String typ, String getriebe, String kraftstoffart, int alter, int sitzplaetze, int erstzulassung, int kilometer, int mietpreis, boolean verfuegbarkeit, String antriebsart){

        super(marke, model, kennzeichen, typ, getriebe, kraftstoffart, alter, sitzplaetze, erstzulassung, kilometer, mietpreis, verfuegbarkeit);
        this.antriebsart = antriebsart;
    }

    @Override
    public void fuehrerscheinKategorie(){
        System.out.println("ACHTUNG! Fuer den Fahrzeug brauchst Du Fuehrerschein Kategorie A.");
    }

    static ArrayList<Fahrzeug> motorrads = new ArrayList<>();
    static {
        motorrads.add(new Motorrad("Honda", "Africa Twin", "ASP 1999", "Reiseenduro", "Schaltgetriebe", "Benzin", 18, 2, 1995, 75000, 45, true, "Kette"));
        motorrads.add(new Motorrad("Suzuki", "Dual Sport", "ASP 1971", "Enduro", "Schaltgetriebe", "Benzin", 18, 2, 2015, 13500, 60, true, "Kette"));
    }
}

