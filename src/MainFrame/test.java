package MainFrame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JPanel;

public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setBounds(100, 100, (int)(screenSize.getWidth()*0.8), (int)(screenSize.getHeight()*0.8));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 0));
		
		// Modulo gestione regole di transizione. Si trova nel BottomPanel
		JPanel RegoleTransizione = new RegoleTransizione();

		
		
		// Parte superiore della finestra
			JPanel TopPanel = new JPanel();
			TopPanel.setLayout(new GridLayout(2, 0));
		
		// Parte inferiore della finestra
			JPanel BottomPanel = new JPanel();
			BottomPanel.setLayout(new GridLayout(2, 0));
			
			BottomPanel.add(new Component() {private static final long serialVersionUID = 1L;});
			
		// Aggiunta modulo per la creazione delle regole	
			BottomPanel.add(RegoleTransizione);
		
		// Visualizzazione della finestra
			frame.getContentPane().add(TopPanel);
			frame.getContentPane().add(BottomPanel);

	}

}
