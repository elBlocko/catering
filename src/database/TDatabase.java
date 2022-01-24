package database;


import java.sql.*;

import javax.swing.JOptionPane;

import logic.*;

public class TDatabase {
	
	public static final TDatabase TDatabase = new TDatabase();
	public static Connection connection;
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}

	// Klasse als SINGLETON -->
	// es wird programmweit nur eine Instanz dieses Objektes gebildet
	// --------------------------------------------------------------------------

	private TDatabase() {
	}

	public static TDatabase getInstance() {
		return TDatabase;
	}

	// --------------------------------------------------------------------------

	public void connect() {
		try {
			if (connection != null)
				return;
			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + TConstants.CDatabasePath);			
			if (!connection.isClosed())
				System.out.println("...Connection established");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						if (connection.isClosed())
							System.out.println("Connection to Database closed");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
				if (connection.isClosed())
					System.out.println("Connection to Database closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void pragma() {
		String sql = ("PRAGMA foreign_keys = ON;");
		
		try {
			Statement stmt = connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim ausführen des PRAGAM FOREIGN KEY ON Befehls");
		}
		
	}
	
	public void createTablesIFNOTEXIST() {
		String sql = ("create table IF NOT EXISTS tblKunden ("
				+ "KuNr integer not null primary key autoincrement,"
				+ "Firma1 varchar (100),"
				+ "Firma2 varchar (100),"
				+ "Strasse varchar (100),"
				+ "PLZ varchar (5),"
				+ "Ort varchar (100)"
				+ ");");
		
		try {
			Statement stmt = connection.createStatement();
			// execute the insert statement
			stmt.executeUpdate(sql);
			sql = ("create table IF NOT EXISTS tblEssen ("
					+ "EssenNr integer not null primary key autoincrement,"
					+ "Bezeichnung varchar (100),"
					+ "Kategorie varchar (100),"
					+ "Preis real (100)"
					+ ");");
			
			stmt.executeUpdate(sql);
			sql = ("create table IF NOT EXISTS tblKundenEssen ("
					+ "PKid integer not null primary key autoincrement,"
					+ "KuNr integer null references tblKunden (KuNr) on delete cascade on update cascade,"
					+ "ENr integer null references tblEssen (EssenNr) on delete cascade on update cascade,"
					+ "Anzahl integer (5),"
					+ "Datum varchar (40)"
					+ ");"
					+ "");
			stmt.executeUpdate(sql);
			
			sql = ("create table IF NOT EXISTS tblWochenplan ("
					+ "PKid integer not null primary key autoincrement,"
					+ "ENr integer null references tblEssen (EssenNr) on delete cascade on update cascade,"
					+ "Datum varchar (40)"
					+ ");");
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim erstellen der Tebellen, heho!");
		}
		
		
	}
}
