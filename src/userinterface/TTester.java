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

	public static void main(String[] args) {
		TDatabase database1 = TDatabase.getInstance();
		database1.connect();
		// create and fill List
		TKundenListe KundenListe1; // declare
		KundenListe1 = new TKundenListe(new ArrayList<TKunde>()); // init
		KundenListe1.setContent(); // fill list
		System.out.println(KundenListe1.get(0).getFirma1());
	}

}
