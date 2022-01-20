package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import logic.*;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class UEssen extends JInternalFrame {

	private static TEssensListeGlobal EssenListe1;

	private JTextField tfSearchbar;
	private JTextField tfBezeichnung;
	private JTextField tfKategorie;
	private JTextField tfPreis;

	private JPanel panel;
	private JPanel panel_3;
	
	private int rowIndexGrdMain = -1;

	// INIT GRID HEADERS
	Object[] columns = { "EssenNr", "Bezeichnung", "Kategorie", "Preis" };
	DefaultTableModel modelList = new DefaultTableModel();

	Object[] rowList = new Object[4];
	private JTable grdMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UEssen frame = new UEssen(EssenListe1);
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
	 */
	public UEssen(TEssensListeGlobal essenListe1) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				setGridContent();
				panel_3.setVisible(false);
			}
		});
		this.EssenListe1 = essenListe1;
		setResizable(true);
		setTitle("Essen");
		setBounds(100, 100, 712, 467);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Neu");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
			}
		});
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(btnAdd);

		JButton btnDelete = new JButton("L\u00F6schen");
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

		JButton btnSave = new JButton("Speichern");
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
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3, BorderLayout.SOUTH);

		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		panel_3.add(lblBezeichnung);

		tfBezeichnung = new JTextField();
		tfBezeichnung.setColumns(10);
		panel_3.add(tfBezeichnung);

		JLabel lblNewLabel_4 = new JLabel("Kategorie:");
		panel_3.add(lblNewLabel_4);

		tfKategorie = new JTextField();
		tfKategorie.setColumns(10);
		panel_3.add(tfKategorie);

		JLabel lblNewLabel_5 = new JLabel("Preis:");
		panel_3.add(lblNewLabel_5);

		tfPreis = new JTextField();
		tfPreis.setColumns(10);
		panel_3.add(tfPreis);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblEssensbezeichnung = new JLabel("Essensbezeichnung:");
		panel_1.add(lblEssensbezeichnung);

		tfSearchbar = new JTextField();
		tfSearchbar.setColumns(10);
		panel_1.add(tfSearchbar);

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

	// METHODEN
	// ***************************************************************************************************
	void setGridContent() {
		// DecimalFormat formatter = new DecimalFormat("#,###0.00");
		modelList.setRowCount(0);
		for (int i = 0; i < EssenListe1.size(); i++) {
			
			rowList[0] = EssenListe1.get(i).getID(); // EssenNr
			rowList[1] = EssenListe1.get(i).getBezeichnung();
			rowList[2] = EssenListe1.get(i).getKategorie();
			rowList[3] = String.format("%.2f",EssenListe1.get(i).getPreis()) + " €";

			modelList.addRow(rowList);
		}
	}

	void setGrdMainHeader() {

		modelList.setColumnIdentifiers(columns);
		grdMain.setModel(modelList);

	}

	void deleteSelectedKunde() {

		int EssenNr = EssenListe1.get(rowIndexGrdMain).getID();
		EssenListe1.delete(EssenNr);
		for (TEssen Essen : EssenListe1) {
			if (Essen.getID() == EssenNr) {
				EssenListe1.remove(Essen);
				break;
			}
		}
		setGridContent();
	}

	void addKunde() {
		try {
			if (tfBezeichnung.getText().equals("") || tfKategorie.getText().equals("")
					|| tfPreis.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen!");
				return;
			}
			float tempPreis = 0;
			// Werte aus den Textfeldern holen
			String tempBezeichnung = tfBezeichnung.getText();
			String tempKategorie = tfKategorie.getText();
			try {
				tempPreis = Float.parseFloat(tfPreis.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Preis darf nur Zahlen mit Nachkommastellen");
			}

			TEssen tempEssen = new TEssen(-1, tempBezeichnung, tempKategorie, tempPreis,-1,null,-1); // Objekt
																							// erstellen
			int EssenNr = tempEssen.save(tempBezeichnung, tempKategorie, tempPreis); // Objekt in der Datenbank
																						// speichern
			tempEssen.setID(EssenNr); // Rückgabewert von "save" als neue KuNr setzen
			EssenListe1.add(tempEssen); // Objekt der Liste hinzufügen

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler beim erstellen eines neuen Kunden");
		}

		setGridContent();
		clearTextFields();
	}

	void clearTextFields() {

		tfBezeichnung.setText("");
		tfKategorie.setText("");
		tfPreis.setText("");
	}

	void searchGrdMain() {
		int i = 0;
		boolean match = false;
		if (tfSearchbar.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte etwas in das Suchfeld eingeben.");
			return;
		}
		for (TEssen tempEssen : EssenListe1) {
			if (tfSearchbar.getText().equals(tempEssen.getBezeichnung())) {
				modelList.setRowCount(0);
				rowList[0] = EssenListe1.get(i).getID(); // EssenNr
				rowList[1] = EssenListe1.get(i).getBezeichnung();
				rowList[2] = EssenListe1.get(i).getKategorie();
				rowList[3] = String.format("%.2f",EssenListe1.get(i).getPreis() + " €");

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

}
