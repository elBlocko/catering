package userinterface;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class UEssen extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UEssen frame = new UEssen();
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
	public UEssen() {
		setMaximizable(true);
		setTitle("Essen");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);

	}

}
