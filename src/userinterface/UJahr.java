package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class UJahr extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UJahr frame = new UJahr();
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
	public UJahr() {
		setResizable(true);
		setTitle("Jahres\u00FCbersicht");
		setBounds(100, 100, 450, 300);

	}

}
