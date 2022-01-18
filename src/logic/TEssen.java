package logic;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TEssen {

	// interface
	// PROPERTIES
	private int FID;
	private String FBezeichnung;
	private String FKategorie;
	private float FPreis;

	// implement
	// CONSTRUCTOR
	// @param to @property --> compile+
	public TEssen(int AID, String ABezeichnung, String AKategorie, float APreis) {
		this.FID = AID;
		this.FBezeichnung = ABezeichnung;
		this.FKategorie = AKategorie;
		this.FPreis = APreis;	
	}

	// ***********************************************************
	// PROPERTY READ id WRITE id
	public int getID() {
		return FID;
	}

	public void setID(int ID) {
		this.FID = ID;
	}

	// PROPERTIE read Bezeichnung write Bezeichnung
	public String getBezeichnung() {
		return FBezeichnung;
	}

	public void setBezeichnung(String Bezeichnung) {
		this.FBezeichnung = Bezeichnung;
	}

	// PROPERTIE read Kategorie write Kategorie
	public String getKategorie() {
		return FKategorie;
	}

	public void setKategorie(String Kategorie) {
		this.FKategorie = Kategorie;
	}

	// PROPERTIE read Preis write Preis
	public float getPreis() {
		return FPreis;
	}

	public void setPreis(float Preis) {
		this.FPreis = Preis;
	}

	/***************************************************************
	 * METHODEN
	 */
	
	public int save(String Bezeichnung, String Kategorie, float Preis) {
		String sql = "insert into tblKunden (Firma1,Firma2,Strasse,PLZ,Ort) values ('" + Bezeichnung + "','" + Kategorie
				+ "',"+ Preis + ");";
		int EssenNr = -1;
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			EssenNr = stmt.getGeneratedKeys().getInt(1);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim speichern der Daten in der Essens Tabelle");
		}
		return EssenNr;
	}
}
