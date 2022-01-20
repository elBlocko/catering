package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.TDatabase;
import userinterface.TTester;
import userinterface.UMain;

@SuppressWarnings("serial")
public class TEssensListeGlobal extends TEssensListeBasis {

	public TEssensListeGlobal(List<TEssen> AEssensListe) {
		super(AEssensListe);
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

				tempEssen = new TEssen(tempEssenNr, tempBezeichnung, tempKategorie, tempPreis, -1, null, -1); // Objekt
																												// erstellen
				this.add(tempEssen); // objekt der Liste zufügen
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die Essens Liste");
		}
		// hier Zuordnung der Bestellungen ESSEN <--> KUNDEN
		essenZuKunden();
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

	// LOKALE LISTEN ZUORDNEN
	private void essenZuKunden() {

		int tempKundenEssenID;
		int tempKuNr; // Fremdschlüssel auf PK von tblKunden
		int tempENr; // FK auf PK von tblEssen
		int tempAnzahl;

		String tempDatum;
		int i = 0;

		TEssen oEssen;
		TEssen oEssenNeu;
		TKunde oKunde;

		try {
			Statement stmt = TDatabase.connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM [tblKundenEssen];");
			while (rs.next()) {
				tempKundenEssenID = rs.getInt("PKid");
				tempKuNr = rs.getInt("KuNr"); // Werte holen
				tempENr = rs.getInt("ENr");
				tempAnzahl = rs.getInt("Anzahl");
				tempDatum = rs.getString("Datum");

				// wenn gleiches Objekt gefunden wird, werden werte überschrieben
				oEssen = null;
				for (TEssen tempEssen : this) {
					if (tempEssen.getID() == tempENr) {
						oEssen = tempEssen;
						break;
					}
				}
				oKunde = null;
				for (TKunde tempKunde : UMain.KundenListe1) {
					if (tempKunde.getID() == tempKuNr) {
						oKunde = tempKunde;
						break;
					}
				}

				oEssenNeu = new TEssen(i, oEssen.getBezeichnung(), oEssen.getKategorie(), oEssen.getPreis(), tempAnzahl,
						tempDatum, tempKundenEssenID);
				/*
				 * oEssen.setID(i); oEssen.setAnzahl(tempAnzahl); oEssen.setDatum(tempDatum);
				 * oEssen.setKundenEssenID(tempKundenEssenID);
				 */
				// sobald die ENr erneut vorkommt, werden die Werte Anzahl, Datum und ID des
				// Kunden überschrieben

				oKunde.getEssen().add(oEssenNeu); // getEssen() ist die lokale EssensListe an einem Kunden
				((TKundenListeLokal) oEssen.getKunden()).add(oKunde);
				i++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten in die lokalen Listen");

		}

	}
}
