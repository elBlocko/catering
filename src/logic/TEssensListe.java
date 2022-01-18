package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TEssensListe  extends ArrayList<TEssen>{

	private static final long serialVersionUID = -123455559012345678L;
	List<TEssen> FEssensListe;

	public TEssensListe(List<TEssen> AEssensListe) {
		this.FEssensListe = AEssensListe;
	}
	// METHODEN ------------------------------------------------------------------

	public void setContent() {
		int tempEssenNr;
		String tempBezeichnung;
		String tempKategorie;
		float tempPreis;
		TEssen tempEssen;
		
		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblEssen];");
			while (rs.next()) {
				tempBezeichnung = rs.getString("Bezeichnung"); // Werte holen
				tempKategorie = rs.getString("Kategorie"); 
				
				tempPreis = rs.getFloat("Preis"); 
	
				
				tempEssenNr = rs.getInt("EssenNr");

				tempEssen = new TEssen(tempEssenNr, tempBezeichnung,tempKategorie,tempPreis); // Objekt erstellen
				this.add(tempEssen); // objekt der Liste zufügen
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die Essens Liste");
		}

	}
	
	public void delete(int ID) {
		String sql = "DELETE FROM [tblEssen] WHERE EssenNr = " + ID + ";";
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the delete statement
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim löschen der Daten in der Essens Tabelle");
		}
	}

	

}
