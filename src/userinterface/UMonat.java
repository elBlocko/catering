package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class UMonat extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMonat frame = new UMonat();
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
	public UMonat() {
		setResizable(true);
		setTitle("Monats\u00FCbersicht");
		setBounds(100, 100, 450, 300);

	}

}
