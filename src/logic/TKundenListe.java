package logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import database.TDatabase;
import java.sql.*;

public class TKundenListe extends ArrayList<TKunde> {

	private static final long serialVersionUID = -123456789012345678L;
	List<TKunde> FKundenListe;

	public TKundenListe(List<TKunde> AKundenListe) {
		this.FKundenListe = AKundenListe;
	}
	// METHODEN ------------------------------------------------------------------

	public void setContent() {
		int tempKuNr;
		String tempFirma1;
		String tempFirma2;
		String tempStrasse;
		String tempPLZ;
		String tempOrt;
		TKunde tempKunde;

		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblKunden];");
			while (rs.next()) {
				tempFirma1 = rs.getString("Firma1"); // Werte holen
				tempFirma2 = rs.getString("Firma2"); 
				tempStrasse = rs.getString("Strasse"); 
				tempPLZ = rs.getString("PLZ"); 
				tempOrt = rs.getString("Ort"); 
				
				tempKuNr = rs.getInt("KuNr");

				tempKunde = new TKunde(tempKuNr, tempFirma1,tempFirma2,tempStrasse,tempPLZ,tempOrt); // Objekt erstellen
				this.add(tempKunde); // objekt der Liste zufügen
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die Kunden Liste");
		}

	}

}
