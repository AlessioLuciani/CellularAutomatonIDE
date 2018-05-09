package testing_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.GridRenderPanel;
import grid.square.MatrixGraph;

public class Test_gridrender {
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		final int w = 100, h = 100, s = 10;
		Graph g = new MatrixGraph(w, h, s);
		System.out.println(g.getNumNodes());
		for(int i=1; i<=g.getNumNodes(); i++) {
			g.getCell(i).setState(Color.YELLOW);
		}
		
		GridConfiguration gconf = new GridConfiguration(CellForm.SQUARE, s, w, h);
		GridRenderPanel panel = new GridRenderPanel(g, gconf, Color.BLACK);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, gconf.getBufferImageWidth(), gconf.getBufferImageHeight());
		JButton b = new JButton();
		b.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {

				Random rand = new Random();
				for(int i=0; i<10; i++) {
					int ind = rand.nextInt(w*h) + 1;
					g.getCell(ind).setState(Color.BLUE);
				}
				panel.synchWithGraph(); 
		    }
		});
		frame.add(b, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
