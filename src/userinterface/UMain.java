package userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.TDatabase;
import logic.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class UMain extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane1;
	private JButton btnKunden;
	private JButton btnEssen;
	private JButton btnEssensbestellung;
	private JButton btnBeenden;
	private UEssen essenForm1;
	private UEssensbestellung essensBestellungForm1;
	private UJahr jahrForm1;
	private UMonat monatForm1;
	private UInfo infoForm1;
	private UKunden kundenForm1;
	// LISTEN
	public TEssensListe EssenListe1;
	public TKundenListe KundenListe1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMain frame = new UMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				connectDatabase();
				createLists();
				setListContent();
			}
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					TDatabase.connection.close();

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizePanes();
			}
		});
		setTitle("Catering");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 479);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnStammdaten = new JMenu("Stammdaten");
		menuBar.add(mnStammdaten);

		JMenuItem mntmEssen = new JMenuItem("Essen");
		mntmEssen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEssenForm();

			}
		});

		JMenuItem mntmKunden = new JMenuItem("Kunden");
		mntmKunden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openKundenForm();
			}
		});
		mnStammdaten.add(mntmKunden);
		mnStammdaten.add(mntmEssen);

		JMenu mnBestellungen = new JMenu("Bestellungen");
		menuBar.add(mnBestellungen);

		JMenuItem mntmEssensbestellungen = new JMenuItem("Essensbestellungen");
		mntmEssensbestellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEssensbestellungForm();
			}
		});
		mnBestellungen.add(mntmEssensbestellungen);

		JMenu mnAuswertung = new JMenu("Auswertung");
		menuBar.add(mnAuswertung);

		JMenuItem mntmJahr = new JMenuItem("Jahres\u00FCbersicht");
		mntmJahr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJahrForm();

			}
		});
		mnAuswertung.add(mntmJahr);

		JMenuItem mntmMonat = new JMenuItem("Monats\u00FCbersicht");
		mntmMonat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMonatForm();
			}
		});
		mnAuswertung.add(mntmMonat);

		JMenu mnExtras = new JMenu("Extras");
		menuBar.add(mnExtras);

		JMenuItem mntmInformation = new JMenuItem("Information");
		mntmInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInfoForm();
			}
		});
		mnExtras.add(mntmInformation);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane1 = new JDesktopPane();
		desktopPane1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane1, BorderLayout.CENTER);
		desktopPane1.setLayout(null);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		btnKunden = new JButton("Kunden");
		btnKunden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openKundenForm();
			}
		});
		toolBar.add(btnKunden);

		btnEssen = new JButton("Essen");
		btnEssen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEssenForm();
			}
		});
		toolBar.add(btnEssen);

		btnEssensbestellung = new JButton("Essensbestellung");
		btnEssensbestellung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEssensbestellungForm();
			}
		});
		toolBar.add(btnEssensbestellung);

		btnBeenden = new JButton("Beenden");
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(btnBeenden);
	}

// METHODEN *****************************************************************************
// FRAMES -------------------------------------------------------------------------------
	private void openKundenForm() {
		desktopPane1.removeAll();
		kundenForm1 = new UKunden(KundenListe1);
		kundenForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(kundenForm1);
		kundenForm1.setVisible(true);
	}

	private void openEssenForm() {
		desktopPane1.removeAll();
		essenForm1 = new UEssen(EssenListe1);
		essenForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(essenForm1);
		essenForm1.setVisible(true);
	}

	private void openEssensbestellungForm() {
		desktopPane1.removeAll();
		essensBestellungForm1 = new UEssensbestellung();
		essensBestellungForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(essensBestellungForm1);
		essensBestellungForm1.setVisible(true);
	}

	private void openJahrForm() {
		desktopPane1.removeAll();
		jahrForm1 = new UJahr();
		jahrForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(jahrForm1);
		jahrForm1.setVisible(true);
	}

	private void openMonatForm() {
		desktopPane1.removeAll();
		monatForm1 = new UMonat();
		monatForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(monatForm1);
		monatForm1.setVisible(true);
	}

	private void openInfoForm() {
		desktopPane1.removeAll();
		infoForm1 = new UInfo();
		infoForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		desktopPane1.add(infoForm1);
		infoForm1.setVisible(true);
	}

// DESIGN ----------------------------------------------------------------------------------------------
	private void resizePanes() {
		if (essenForm1 != null) {
			essenForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}
		if (kundenForm1 != null) {
			kundenForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}
		if (essensBestellungForm1 != null) {
			essensBestellungForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}
		if (jahrForm1 != null) {
			jahrForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}
		if (monatForm1 != null) {
			monatForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}
		if (infoForm1 != null) {
			infoForm1.setBounds(0, 0, desktopPane1.getWidth(), desktopPane1.getHeight());
		}

	}

// LISTEN -----------------------------------------------------------------------------------------------
	void connectDatabase() {
		// establish connection to database
		TDatabase database1 = TDatabase.getInstance();
		database1.connect();
	}
	void createLists() {
		EssenListe1 = new TEssensListe(new ArrayList<TEssen>()); // init
		KundenListe1 = new TKundenListe(new ArrayList<TKunde>()); // init		
	}

	void setListContent() {
		EssenListe1.setContent(); // fill list
		KundenListe1.setContent(); // fill list		
	}
} // end of class