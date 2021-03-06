package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import logic.TEssensListeGlobal;
import logic.TKunde;
import logic.TKundenListeGlobal;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

public class UJahr extends JInternalFrame {
	private JTable grdMain;
	private int selectedKuNr;
	private JComboBox<Integer> cBoxKundenNr;
	private TKunde oKunde = null;
	private static TKundenListeGlobal KundenListe1;
	private static TEssensListeGlobal EssenListe1;
	private JLabel lblFirma12;
	private JLabel lblAusgabe;
	// INIT GRID HEADERS
	Object[] columns = { "Jahr", "Brutto" };
	DefaultTableModel modelList = new DefaultTableModel();

	Object[] rowList = new Object[2];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UJahr frame = new UJahr(KundenListe1, EssenListe1);
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
	public UJahr(TKundenListeGlobal kundenListe1, TEssensListeGlobal essenListe1) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				set_cBoxKundenNr();
				setGridContent();
			}
		});
		this.KundenListe1 = kundenListe1;
		this.EssenListe1 = essenListe1;
		setResizable(true);
		setTitle("Jahres\u00FCbersicht");
		setBounds(100, 100, 450, 300);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel, BorderLayout.NORTH);

		lblFirma12 = new JLabel(" . . . ");
		panel.add(lblFirma12);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_4, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Gesamtumsatz");
		panel_4.add(lblNewLabel_1);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.SOUTH);

		JButton btnClose = new JButton("Schlie\u00DFen");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_5.add(btnClose);

		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_7, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Brutto");
		panel_7.add(lblNewLabel_2);

		lblAusgabe = new JLabel(" . . . ");
		panel_7.add(lblAusgabe);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_8, BorderLayout.CENTER);

		JLabel lblNewLabel_4 = new JLabel("KundenNr.: ");
		panel_8.add(lblNewLabel_4);

		cBoxKundenNr = new JComboBox<Integer>();
		cBoxKundenNr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedKuNr = Integer.parseInt(cBoxKundenNr.getSelectedItem().toString());
				lblAusgabe.setText("0,00 ?");
				getSelectedKunde();
				setLabelFirma12();
				setGridContent();
			}
		});
		panel_8.add(cBoxKundenNr);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		grdMain = new JTable();
		grdMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// my Method
		setGrdMainHeader();
		scrollPane.setViewportView(grdMain);

	}

// METHODEN ***************************************************************
	private void set_cBoxKundenNr() {

		// mit einer Schleife alle KundenNr. auslesen und in das String Array
		// comboBoxContent ?bergeben
		for (TKunde tempKunde : KundenListe1) {
			cBoxKundenNr.addItem(tempKunde.getID());

		}

	}

	private void getSelectedKunde() {
		for (TKunde tempKunde : KundenListe1) {
			if (tempKunde.getID() == selectedKuNr) {
				oKunde = tempKunde;
				break;
			}
		}
	}

	private void setLabelFirma12() {
		if (oKunde != null) {
			if (oKunde.getFirma2() != null) {
				lblFirma12.setText(oKunde.getFirma1() + ' ' + oKunde.getFirma2());
			} else {
				lblFirma12.setText(oKunde.getFirma1());
			}

		}
	}

	private void setGridContent() {

		List<String> dateArray = new ArrayList<String>();
		int Year;
		int nextYear;
		modelList.setRowCount(0);
		if (oKunde != null) {
			for (int i = 0; i < oKunde.getEssen().size(); i++) {
				dateArray.add(oKunde.getEssen().get(i).getDatum());
				Collections.sort(dateArray);
			}

			for (int j = 0; j < dateArray.size(); j++) {
				Year = Integer.parseInt(dateArray.get(j).split("\\-")[0]);
				if ((j + 1) != dateArray.size()) {
					nextYear = Integer.parseInt(dateArray.get(j + 1).split("\\-")[0]);
				} else {
					nextYear = 0;

				}
				if (Year != nextYear) {

					double BruttoPreis = oKunde.getEssen().getJahresBrutto(selectedKuNr, Year);

					rowList[0] = Year;
					rowList[1] = String.format("%.2f", BruttoPreis) + " ?";

					modelList.addRow(rowList);
				}
				double GesamtPreis = oKunde.getEssen().getGesamtBrutto(selectedKuNr);
				lblAusgabe.setText(String.valueOf(String.format("%.2f", GesamtPreis) + " ?"));
			}
			
		}

	}

	private void setGrdMainHeader() {

		modelList.setColumnIdentifiers(columns);
		grdMain.setModel(modelList);

	}

}
