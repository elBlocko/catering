package logic;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TKunde {

	// interface
	// PROPERTIES
	private int FID;
	private String FFirma1;
	private String FFirma2;
	private String FStrasse;
	private String FPLZ;
	private String FOrt;

	// implement
	// CONSTRUCTOR
	// @param to @property --> compile+
	public TKunde(int AID, String AFirma1, String AFirma2, String AStrasse, String APLZ, String AOrt) {
		this.FID = AID;
		this.FFirma1 = AFirma1;
		this.FFirma2 = AFirma2;
		this.FStrasse = AStrasse;
		this.FPLZ = APLZ;
		this.FOrt = AOrt;
	}

	// ***********************************************************
	// PROPERTY READ id WRITE id
	public int getID() {
		return FID;
	}

	public void setID(int ID) {
		this.FID = ID;
	}

	// PROPERTIE read Firma1 write Firma1
	public String getFirma1() {
		return FFirma1;
	}

	public void setFirma1(String Firma1) {
		this.FFirma1 = Firma1;
	}

	// PROPERTIE read Firma2 write Firma2
	public String getFirma2() {
		return FFirma2;
	}

	public void setFirma2(String Firma2) {
		this.FFirma2 = Firma2;
	}

	// PROPERTIE read Strasse write Strasse
	public String getStrasse() {
		return FStrasse;
	}

	public void setStrasse(String Strasse) {
		this.FStrasse = Strasse;
	}

	// PROPERTIE read PLZ write PLZ
	public String getPLZ() {
		return FPLZ;
	}

	public void setPLZ(String PLZ) {
		this.FPLZ = PLZ;
	}

	// PROPERTIE read Ort write Ort
	public String getOrt() {
		return FOrt;
	}

	public void setOrt(String Ort) {
		this.FOrt = Ort;
	}

// METHODEN *******************************************************************************************************
	
	public int save(String Firma1, String Firma2, String Strasse, String PLZ, String Ort) {
		String sql = "insert into tblKunden (Firma1,Firma2,Strasse,PLZ,Ort) values ('" + Firma1 + "','" + Firma2 + "','"
				+ Strasse + "','" + PLZ + "','" + Ort + "');";
		int KuNr = -1;
		try {
			Statement stmt = TDatabase.connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			KuNr = stmt.getGeneratedKeys().getInt(1);
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim speichern der Daten in der Kunden Tabelle");
		}
		return KuNr;
	}

}
