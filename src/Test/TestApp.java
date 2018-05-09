package Test;

import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import RulesCreatorWindow.CreateExpressionWindow;
import RulesCreatorWindow.EditExpressionPanel;

public class TestApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestApp window = new TestApp();
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
	public TestApp() {
		initialize();
		//test();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void test() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		
		
		EditExpressionPanel t = new EditExpressionPanel().formatA();
		frame.getContentPane().add(t);
		
	}
	private void initialize() {
		frame = new CreateExpressionWindow(100, 100, 400, 400);
		
		
		
	}
}






