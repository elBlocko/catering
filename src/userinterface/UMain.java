package userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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

public class UMain extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane1;
	private JButton btnKunden;
	private JButton btnEssen;
	private JButton btnEssensbestellung;
	private JButton btnBeenden;

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

	private void openKundenForm() {
		UKunden kundenForm1 = new UKunden();
		desktopPane1.add(kundenForm1);
		kundenForm1.setVisible(true);
	}

	private void openEssenForm() {
		UEssen essenForm1 = new UEssen();
		desktopPane1.add(essenForm1);
		essenForm1.setVisible(true);
	}

	private void openEssensbestellungForm() {
		UEssensbestellung essensBestellungForm1 = new UEssensbestellung();
		desktopPane1.add(essensBestellungForm1);
		essensBestellungForm1.setVisible(true);
	}

	private void openJahrForm() {
		UJahr jahrForm1 = new UJahr();
		desktopPane1.add(jahrForm1);
		jahrForm1.setVisible(true);
	}

	private void openMonatForm() {
		UMonat monatForm1 = new UMonat();
		desktopPane1.add(monatForm1);
		monatForm1.setVisible(true);
	}

	private void openInfoForm() {
		UInfo infoForm1 = new UInfo();
		desktopPane1.add(infoForm1);
		infoForm1.setVisible(true);
	}

} // end of class
