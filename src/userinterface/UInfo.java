package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class UInfo extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UInfo frame = new UInfo();
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
	public UInfo() {
		setResizable(true);
		setBounds(100, 100, 450, 300);

	}

}
