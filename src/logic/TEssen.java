package logic;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TEssen {

	// interface
	// PROPERTIES
	private int FID;
	private String FBezeichnung;
	private String FKategorie;
	private float FPreis;
	private Object FKundenListeLokal;

	int FAnzahl;
	String FDatum;
	int FKundenEssenID;

	// implement
	// CONSTRUCTOR
	// @param to @property --> compile+
	public TEssen(int AID, String ABezeichnung, String AKategorie, float APreis, int AAnzahl, String ADatum,
			int AKundenEssenID) {
		this.FID = AID;
		this.FBezeichnung = ABezeichnung;
		this.FKategorie = AKategorie;
		this.FPreis = APreis;
		this.FAnzahl = AAnzahl;
		this.FDatum = ADatum;
		this.FKundenEssenID = AKundenEssenID;

		FKundenListeLokal = new TKundenListeLokal(new ArrayList<TKunde>()); // init
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

	// LISTE

	public Object getKunden() {
		return FKundenListeLokal;
	}

	public void setKunden(Object KundenListeLokal) {
		this.FKundenListeLokal = KundenListeLokal;
	}

	// PROPERTY READ Anzahl WRITE Anzahl
	public int getAnzahl() {
		return FAnzahl;
	}

	public void setAnzahl(int Anzahl) {
		this.FAnzahl = Anzahl;
	}

	// PROPERTY READ Datum WRITE Datum
	public String getDatum() {
		return FDatum;
	}

	public void setDatum(String Datum) {
		this.FDatum = Datum;
	}

	// PROPERTY READ Anzahl WRITE Anzahl
	public int getKundenEssenID() {
		return FKundenEssenID;
	}

	public void setKundenEssenID(int KundenEssenID) {
		this.FKundenEssenID = KundenEssenID;
	}

	/***************************************************************
	 * METHODEN
	 */

	public int save(String Bezeichnung, String Kategorie, float Preis) {
		String sql = "insert into tblEssen (Bezeichnung,Kategorie,Preis) values ('" + Bezeichnung + "','" + Kategorie
				+ "'," + Preis + ");";
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

	public int saveOrder(int KuNr, int ENr, int Anzahl, String Datum) {

		String sql = "insert into tblKundenEssen (KuNr,ENr,Anzahl,Datum) values (" + KuNr + "," + ENr + "," + Anzahl
				+ ",'" + Datum + "');";
		int PKid = -1;
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			PKid = stmt.getGeneratedKeys().getInt(1);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim speichern der Daten in der KundenEssen Tabelle");
		}
		return PKid;

	}

	public void saveUpdate(int PKid, int Anzahl) {
		String sql = "update tblKundenEssen set Anzahl = " + Anzahl + " where PKid = " + PKid + ";";

		try {
			Statement stmt = TDatabase.connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim update der Daten in der KundenEssen Tabelle");
		}

	}


	
}
