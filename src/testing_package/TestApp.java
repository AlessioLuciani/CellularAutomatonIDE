package testing_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import util.colors.ColorPickerFrame;

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
 		colorlist.add(Color.GREEN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.CYAN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.GREEN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.CYAN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.GREEN);
 		colorlist.add(Color.BLACK);
 		colorlist.add(Color.CYAN);
 		colorlist.add(Color.BLACK);
		frame = ColorPickerFrame.inizialize(colorlist);
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
		//frame.setVisible(true);
 		HashMap<Color, Integer> m = new HashMap<>();
 		m.put(Color.WHITE, 1);
 		m.put(Color.RED, 1);
 		m.put(Color.BLUE, 2);
 		
 		
 		for (Color k : m.keySet()) {
			System.out.println(k+"   ->"+m.get(k));
		} 
 		
 		

 		m.put(Color.BLUE, m.get(Color.BLUE)+1); 		
 		for (Color k : m.keySet()) {
			System.out.println(k+"   ->"+m.get(k));
		} 
 		
 		
 		
 		
 		
	}
}
