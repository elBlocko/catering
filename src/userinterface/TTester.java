package userinterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.swing.JOptionPane;



import database.TDatabase;
import logic.*;
public class TTester {
	public static TKundenListeGlobal KundenListe1; // declare
	
	public static void main(String[] args) {
		TDatabase database1 = TDatabase.getInstance();
		database1.connect();
		// create and fill List
		
		TEssensListeGlobal EssenListe1;
		TKunde oKunde;
		oKunde = null;
		
		KundenListe1 = new TKundenListeGlobal(new ArrayList<TKunde>()); // init
		EssenListe1 = new TEssensListeGlobal(new ArrayList<TEssen>()); // init
		KundenListe1.setContent(); // fill list
		EssenListe1.setContent();
		
		// ID des Kunden (KuNr)
		int tempKuNr = KundenListe1.get(0).getID();
		
		// gehe die KundenListe durch und suche den kunden mit der tempID
		for (TKunde tempKunde : KundenListe1) {
			if (tempKunde.getID() == tempKuNr) {
				oKunde = tempKunde;
				break;
			}
		}
		
		// gib die lokale Essensliste dieses Kunden aus , jeweils Bezeichnung und die Anzahl der Essen
		for (int i = 0; i < oKunde.getEssen().size();i++) {
		System.out.println(oKunde.getEssen().get(i).getBezeichnung());
		System.out.println(oKunde.getEssen().get(i).getAnzahl());
		
		}
	}

}
