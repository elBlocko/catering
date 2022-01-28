package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import logic.*;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class UKunden extends JInternalFrame {
	public int i = 0;
	private JTextField tfSearchbar;
	private JTable grdMain;

	private static TKundenListeGlobal KundenListe1;

	private int rowIndexGrdMain = -1;

	// INIT GRID HEADERS
	Object[] columns = { "KuNr", "Firma1", "Firma2", "Strasse", "PLZ", "Ort" };
	DefaultTableModel modelList = new DefaultTableModel();

	Object[] rowList = new Object[6];

	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnAdd;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel_1;
	private JTextField tfFirma1;
	private JLabel lblNewLabel_2;
	private JTextField tfFirma2;
	private JLabel lblNewLabel_3;
	private JTextField tfStrasse;
	private JLabel lblNewLabel_4;
	private JTextField tfPLZ;
	private JLabel lblNewLabel_5;
	private JTextField tfOrt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UKunden frame = new UKunden(KundenListe1);
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
	 * @param kundenListe12
	 */
	@SuppressWarnings("static-access")
	public UKunden(TKundenListeGlobal kundenListe12) {
		this.KundenListe1 = kundenListe12;
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				setGridContent();
				panel_3.setVisible(false);
			}

			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				//
			}
		});
		setResizable(true);
		setTitle("Kunden");
		setBounds(100, 100, 838, 480);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2, BorderLayout.NORTH);

		btnAdd = new JButton("Neu");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
			}
		});
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnAdd);

		btnDelete = new JButton("L\u00F6schen");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rowIndexGrdMain != -1) {
					deleteSelectedKunde();
				} else {
					JOptionPane.showMessageDialog(null, "nichts zum löschen ausgewählt");

				}
			}
		});
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnDelete);

		btnSave = new JButton("Speichern");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel_3.isVisible()) {
					addKunde();
					panel_3.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "nichts zum speichern vorhanden");
				}
			}
		});
		panel_2.add(btnSave);

		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEADING);
		panel_3.add(panel_4, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel("Firma1: ");
		panel_4.add(lblNewLabel_1);

		tfFirma1 = new JTextField();
		tfFirma1.setColumns(10);
		panel_4.add(tfFirma1);

		lblNewLabel_2 = new JLabel("Firma2:");
		panel_4.add(lblNewLabel_2);

		tfFirma2 = new JTextField();
		tfFirma2.setColumns(10);
		panel_4.add(tfFirma2);

		panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_5, BorderLayout.SOUTH);

		lblNewLabel_3 = new JLabel("Strasse:");
		panel_5.add(lblNewLabel_3);

		tfStrasse = new JTextField();
		tfStrasse.setColumns(10);
		panel_5.add(tfStrasse);

		lblNewLabel_4 = new JLabel("PLZ:     ");
		panel_5.add(lblNewLabel_4);

		tfPLZ = new JTextField();
		tfPLZ.setColumns(10);
		panel_5.add(tfPLZ);

		lblNewLabel_5 = new JLabel("Ort:");
		panel_5.add(lblNewLabel_5);

		tfOrt = new JTextField();
		tfOrt.setColumns(10);
		panel_5.add(tfOrt);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Firmenfilter: ");
		panel_1.add(lblNewLabel);

		tfSearchbar = new JTextField();
		tfSearchbar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchGrdMain();
				}
			}
		});

		panel_1.add(tfSearchbar);
		tfSearchbar.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		grdMain = new JTable();
		grdMain.setForeground(UIManager.getColor("Table.foreground"));
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

	// METHODEN
	// ***************************************************************************************************
	void setGridContent() {
		modelList.setRowCount(0);
		for (int i = 0; i < KundenListe1.size(); i++) {

			rowList[0] = KundenListe1.get(i).getID();
			rowList[1] = KundenListe1.get(i).getFirma1();
			rowList[2] = KundenListe1.get(i).getFirma2();
			rowList[3] = KundenListe1.get(i).getStrasse();
			rowList[4] = KundenListe1.get(i).getPLZ();
			rowList[5] = KundenListe1.get(i).getOrt();

			modelList.addRow(rowList);
		}
	}

	void setGrdMainHeader() {

		modelList.setColumnIdentifiers(columns);
		grdMain.setModel(modelList);

	}

	void deleteSelectedKunde() {

		int KuNr = KundenListe1.get(rowIndexGrdMain).getID();
		int num = KundenListe1.getCountKundenBestellung(KuNr);
		if (num == 0) {
			int test = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich >>"
					+ KundenListe1.get(rowIndexGrdMain).getFirma1() + "<< aus den Stammdaten löschen?", "Löschen",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (test) {
			case 0: {
				// Yes option

				KundenListe1.delete(KuNr);
				for (TKunde Kunde : KundenListe1) {
					if (Kunde.getID() == KuNr) {
						KundenListe1.remove(Kunde);
						break;
					}
				}				
				setGridContent();
				break;
			}
			case 1: {
				
				break;
			}
			case 2: {
				
				break;
			}
			}
		}

		if (num > 0) {
			int test = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich >>"
					+ KundenListe1.get(rowIndexGrdMain).getFirma1()
					+ "<< aus den Stammdaten löschen? \r\n Es existieren zu dem Kunden noch Bestellungen ! \r\n" + "Die Bestellungen werden dann mitgelöscht !",
					"Löschen", JOptionPane.YES_NO_CANCEL_OPTION);
			switch (test) {
			case 0: {
				// Yes option

				KundenListe1.delete(KuNr);
				for (TKunde Kunde : KundenListe1) {
					if (Kunde.getID() == KuNr) {
						KundenListe1.remove(Kunde);
						break;
					}
				}
				setGridContent();
				break;
			}
			case 1: {
				
				break;
			}
			case 2: {
				
				break;
			}
			}
		}

	}

	void addKunde() {
		try {
			if (tfFirma1.getText().equals("") || tfOrt.getText().equals("") || tfPLZ.getText().equals("")
					|| tfStrasse.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen, nur Firma2 kann leer bleiben!");
				return;
			}

			if (tfFirma1.getText().length() >= 100 || tfFirma2.getText().length() >= 100
					|| tfOrt.getText().length() >= 100 || tfPLZ.getText().length() > 5
					|| tfStrasse.getText().length() >= 100) {
				JOptionPane.showMessageDialog(null, "Die Länge der maximalen Eingabe wurde überschritten");
				return;
			}

			// Werte aus den Textfeldern holen
			String tempFirma1 = tfFirma1.getText().trim();
			String tempFirma2 = tfFirma2.getText().trim();
			String tempStrasse = tfStrasse.getText().trim();
			String tempPLZ = tfPLZ.getText().trim();
			String tempOrt = tfOrt.getText().trim();

			TKunde tempKunde = new TKunde(-1, tempFirma1, tempFirma2, tempStrasse, tempPLZ, tempOrt); // Objekt
																										// erstellen
			int KuNr = tempKunde.save(tempFirma1, tempFirma2, tempStrasse, tempPLZ, tempOrt); // Objekt in der Datenbank
																								// speichern
			tempKunde.setID(KuNr); // Rückgabewert von "save" als neue KuNr setzen
			KundenListe1.add(tempKunde); // Objekt der Liste hinzufügen

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler beim erstellen eines neuen Kunden");
		}

		setGridContent();
		clearTextFields();
	}

	void clearTextFields() {

		tfFirma1.setText("");
		tfFirma2.setText("");
		tfStrasse.setText("");
		tfPLZ.setText("");
		tfOrt.setText("");
	}

	void searchGrdMain() {
		int i = 0;
		boolean match = false;
		if (tfSearchbar.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte etwas in das Suchfeld eingeben.");
			return;
		}
		for (TKunde tempKunde : KundenListe1) {
			if (tfSearchbar.getText().equals(tempKunde.getFirma1())
					|| tfSearchbar.getText().equals(tempKunde.getFirma2())) {
				modelList.setRowCount(0);
				rowList[0] = KundenListe1.get(i).getID();
				rowList[1] = KundenListe1.get(i).getFirma1();
				rowList[2] = KundenListe1.get(i).getFirma2();
				rowList[3] = KundenListe1.get(i).getStrasse();
				rowList[4] = KundenListe1.get(i).getPLZ();
				rowList[5] = KundenListe1.get(i).getOrt();

				modelList.addRow(rowList);
				match = true;
			}
			i++;
		}
		if (match == false) {
			JOptionPane.showMessageDialog(null,
					"Keine Einträge gefunden.\nHinweis: Es wird nur nach vollständigen Firmennamen gesucht!");

		}
	}

} // end of class
