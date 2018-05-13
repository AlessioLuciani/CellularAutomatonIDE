package testing_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import RulesCreatorWindow.EditExpressionPanel;
import colors.CustomColorPicker;
import colors.CustomPalette;

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
		List<Color> colorlist = new ArrayList<>();
 		colorlist.add(Color.RED);
 		colorlist.add(Color.PINK);
 		colorlist.add(Color.ORANGE);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.MAGENTA);
 		colorlist.add(Color.GREEN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.CYAN);
 		colorlist.add(Color.BLACK);
 		frame = new CustomColorPicker(colorlist);
 		//frame = new JFrame();
 		
		
		/*CircolarColorLabel lbl1 = new CircolarColorLabel(Color.RED, 10);
		CircolarColorLabel lbl2 = new CircolarColorLabel(Color.RED, 10);
		CircolarColorLabel lbl3 = new CircolarColorLabel(Color.RED, 10);
		CircolarColorLabel lbl4 = new CircolarColorLabel(Color.RED, 10);
		CircolarColorLabel lbl5 = new CircolarColorLabel(Color.RED, 10);
		lbl1.setName("1");
		lbl2.setName("2");
		lbl3.setName("3");
		lbl4.setName("4");
		lbl5.setName("5");
		List<CircolarColorLabel> labels = new ArrayList<>();
		labels.add(lbl1);
		labels.add(lbl2);
		labels.add(lbl3);
		labels.add(lbl4);
		labels.add(lbl5);*/
		frame.setVisible(true);
	}
}






