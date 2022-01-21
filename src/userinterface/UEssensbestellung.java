package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import logic.TEssen;
import logic.TEssensListeGlobal;
import logic.TKunde;
import logic.TKundenListeGlobal;
import logic.TKundenListeLokal;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class UEssensbestellung extends JInternalFrame {

	private static TKundenListeGlobal KundenListe1;
	private static TEssensListeGlobal EssenListe1;
	private JTable grdMain;
	private JTable grdEssen;
	private JComboBox<Integer> cBoxKundenNr;
	private int selectedKuNr;
	private JComboBox<String> cBoxDatum;
	private String selectedDate;

	private int selectedAnzahl = 1;

	private JLabel lblFirma12;

	private int rowIndexGrdMain = -1;

	private TKunde oKunde = null;;
	private TEssen oEssen = null;

	// INIT GRID HEADERS
	Object[] columns = { "ID", "Datum", "Anzahl", "Bezeichnung" };
	DefaultTableModel modelList = new DefaultTableModel();

	Object[] rowList = new Object[4];

	private int rowIndexGrdEssen = -1;

	// INIT GRID HEADERS
	Object[] columns2 = { "Nr.", "Bezeichnung", "Preis" };
	DefaultTableModel modelList2 = new DefaultTableModel();

	Object[] rowList2 = new Object[3];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UEssensbestellung frame = new UEssensbestellung(KundenListe1, EssenListe1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param essenListe1
	 * @param kundenListe1
	 */
	@SuppressWarnings("static-access")
	public UEssensbestellung(TKundenListeGlobal kundenListe1, TEssensListeGlobal essenListe1) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				set_cBoxKundenNr();
				set_cBoxDatum();
				setGrdEssenContent();
			}
		});
		this.KundenListe1 = kundenListe1;
		this.EssenListe1 = essenListe1;
		setResizable(true);
		setTitle("Essensbestellung");
		setBounds(100, 100, 792, 500);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Kundenauswahl");
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Firma: ");
		panel_4.add(lblNewLabel_1);

		lblFirma12 = new JLabel(" . . . ");
		panel_4.add(lblFirma12);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_6, BorderLayout.CENTER);

		JLabel lblNewLabel_2 = new JLabel("KundenNr.: ");
		panel_6.add(lblNewLabel_2);

		cBoxKundenNr = new JComboBox<Integer>();
		cBoxKundenNr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedKuNr = Integer.parseInt(cBoxKundenNr.getSelectedItem().toString());
				if (selectedDate != null) {
					setGridContent();
				}
				// System.out.println(selectedKuNr); // Werte doppelt
			}
		});

		panel_6.add(cBoxKundenNr);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_9.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_9, BorderLayout.SOUTH);

		JLabel lblNewLabel_4 = new JLabel("Anzahl: ");
		panel_9.add(lblNewLabel_4);

		JSpinner spinnerAnzahl = new JSpinner();
		spinnerAnzahl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectedAnzahl = (Integer) spinnerAnzahl.getValue();
				System.out.println(selectedAnzahl);
			}
		});
		spinnerAnzahl.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		panel_9.add(spinnerAnzahl);

		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_10.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_10, BorderLayout.CENTER);

		JLabel lblNewLabel_5 = new JLabel("Datum: ");
		panel_10.add(lblNewLabel_5);

		cBoxDatum = new JComboBox<String>();
		cBoxDatum.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedDate = cBoxDatum.getSelectedItem().toString();
				setGridContent();
			}
		});
		panel_10.add(cBoxDatum);

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_11.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_11, BorderLayout.NORTH);

		JLabel lblNewLabel_6 = new JLabel("Bestellung");
		panel_11.add(lblNewLabel_6);

		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.SOUTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		grdEssen = new JTable();
		grdEssen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndexGrdEssen = grdEssen.getSelectedRow();
			}
		});

		grdEssen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// my Method
		setGrdEssenHeader();
		scrollPane_1.setViewportView(grdEssen);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JButton btnDelete = new JButton("Bestellung l\u00F6schen");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowIndexGrdMain != -1) {
					deleteOrder();
					setGridContent();
				} else {
					JOptionPane.showMessageDialog(null, "Keine Bestellung zum löschen ausgewählt");

				}
			}
		});
		panel_2.add(btnDelete, BorderLayout.EAST);

		JButton btnAdd = new JButton("Bestellung hinzuf\u00FCgen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowIndexGrdEssen != -1 && selectedAnzahl > 0) {
					placeOrder();
					setGridContent();
				} else {
					JOptionPane.showMessageDialog(null, "Kein Essen ausgewählt oder Anzahl ist nicht größer Null");

				}

			}
		});
		panel_2.add(btnAdd, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		grdMain = new JTable();
		grdMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowIndexGrdMain = grdMain.getSelectedRow();
			}
		});

		grdMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// my Method
		setGrdMainHeader();
		scrollPane.setViewportView(grdMain);

	}

// METHODEN ***********************************************************************************************

	private void set_cBoxKundenNr() {

		// mit einer Schleife alle KundenNr. auslesen und in das String Array
		// comboBoxContent übergeben
		for (TKunde tempKunde : KundenListe1) {
			cBoxKundenNr.addItem(tempKunde.getID());

		}

	}

	private void set_cBoxDatum() {
		String[] date = { "28.11.2021", "29.11.2021", "30.11.2021" };
		for (int i = 0; i < date.length; i++) {
			cBoxDatum.addItem(date[i]);

		}

	}

	private void setGridContent() {
		for (TKunde tempKunde : KundenListe1) {
			if (tempKunde.getID() == selectedKuNr) {
				oKunde = tempKunde;
				break;
			}
		}

		modelList.setRowCount(0);
		for (int i = 0; i < oKunde.getEssen().size(); i++) {
			if (selectedDate.equals(oKunde.getEssen().get(i).getDatum())) {
				rowList[0] = oKunde.getEssen().get(i).getKundenEssenID();
				rowList[1] = oKunde.getEssen().get(i).getDatum();
				rowList[2] = oKunde.getEssen().get(i).getAnzahl();
				rowList[3] = oKunde.getEssen().get(i).getBezeichnung();

				modelList.addRow(rowList);
			}
		}

		if (oKunde.getFirma2() != null) {
			lblFirma12.setText(oKunde.getFirma1() + ' ' + oKunde.getFirma2());
		} else {
			lblFirma12.setText(oKunde.getFirma1());
		}
	}

	private void setGrdMainHeader() {

		modelList.setColumnIdentifiers(columns);
		grdMain.setModel(modelList);

	}

	private void setGrdEssenHeader() {
		modelList2.setColumnIdentifiers(columns2);
		grdEssen.setModel(modelList2);

	}

	private void setGrdEssenContent() {

		modelList2.setRowCount(0);
		for (int i = 0; i < EssenListe1.size(); i++) {

			rowList2[0] = EssenListe1.get(i).getID(); // EssenNr
			rowList2[1] = EssenListe1.get(i).getBezeichnung();
			rowList2[2] = String.format("%.2f", EssenListe1.get(i).getPreis()) + " €";

			modelList2.addRow(rowList2);
		}
	}

	private void placeOrder() {
		TEssen oEssenNeu;
		
		
		// Ausgewähltes Objekt aus grdEssen
		// hinzuzufügende Bestellung
		oEssen = null;
		int EssenNr = EssenListe1.get(rowIndexGrdEssen).getID();
		for (TEssen tempEssen : EssenListe1) {
			if (tempEssen.getID() == EssenNr) {
				oEssen = tempEssen;
				break;
			}
		}
		
		// ausgewählter Kunde anhand der gewählten ID in der ComboBox
		oKunde = null;
		for (TKunde tempKunde : KundenListe1) {
			if (tempKunde.getID() == selectedKuNr) {
				oKunde = tempKunde;
				break;
			}
		}

		// Wenn es diese Bestellung schon gibt
		// Dazu vergleiche alle EssensNummer-Werte in der lokalen KundenListe mit der
		// EssensNr des ausgewählten Objekts und das Datum
		for (TEssen tempEssen : oKunde.getEssen()) {
			if (oEssen.getBezeichnung().equals(tempEssen.getBezeichnung()) && selectedDate.equals(tempEssen.getDatum())) {
				int neueAnzahl = selectedAnzahl + tempEssen.getAnzahl();
				tempEssen.setAnzahl(neueAnzahl);
				tempEssen.saveUpdate(tempEssen.getKundenEssenID(),neueAnzahl);
				return;
			}
		}
		
		// Ansonsten neues Objekt in der lokalen Liste erstellen und Eintrag in der tblEssenKunden hinzufügen
		int tempKundenEssenID = oEssen.saveOrder(selectedKuNr, EssenNr, selectedAnzahl, selectedDate);

		oEssenNeu = new TEssen(-1, oEssen.getBezeichnung(), oEssen.getKategorie(), oEssen.getPreis(), selectedAnzahl,
				selectedDate, tempKundenEssenID);

		oKunde.getEssen().add(oEssenNeu); // getEssen() ist die lokale EssensListe an einem Kunden
		((TKundenListeLokal) oEssen.getKunden()).add(oKunde);

		

	}
	
	public void deleteOrder() {
		oKunde = null;
		for (TKunde tempKunde : KundenListe1) {
			if (tempKunde.getID() == selectedKuNr) {
				oKunde = tempKunde;
				break;
			}
		}
		int KundenEssenID = oKunde.getEssen().get(rowIndexGrdMain).getKundenEssenID();
		oKunde.deleteOrder(KundenEssenID);	
		oKunde.getEssen().remove(oKunde.getEssen().get(rowIndexGrdMain));
		
	}	
}
