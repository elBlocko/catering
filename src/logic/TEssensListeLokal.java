package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import database.TDatabase;

public class TEssensListeLokal extends TEssensListeBasis {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7957938812721245036L;

	public TEssensListeLokal(List<TEssen> AEssensListe) {
		super(AEssensListe);

	}

	public double getJahresBrutto(int ID, int Year) {
		double jbw = 0;
		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT SUM(tblEssen.preis*tblKundenEssen.anzahl) AS Brutto FROM tblKundenEssen LEFT JOIN tblEssen ON tblEssen.EssenNr = tblKundenEssen.ENr \r\n"
							+ "WHERE tblKundenEssen.KuNr = " + ID + " AND substr(Datum, 1, 4) = '" + Year + "'");
			jbw = rs.getDouble("Brutto");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Fehler beim Berechnen des Brutto Jahres Preises aller Essen eines Kunden");

		}
		return jbw;
	}
	
	public double getMonatsBrutto(int ID, int Month, int Year) {
		double jbw = 0;
		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT SUM(tblEssen.preis*tblKundenEssen.anzahl) AS Brutto FROM tblKundenEssen LEFT JOIN tblEssen ON tblEssen.EssenNr = tblKundenEssen.ENr \r\n"
							+ "WHERE tblKundenEssen.KuNr = " + ID + " AND substr(Datum, 6, 2) = '" + Month +"' AND substr(Datum, 1, 4) = '" + Year + "'");
			jbw = rs.getDouble("Brutto");
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Fehler beim Berechnen des Brutto Jahres Preises aller Essen eines Kunden");

		}
		return jbw;
	}
	
		

}
