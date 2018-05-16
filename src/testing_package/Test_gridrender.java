package testing_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.GridRenderPanel;
import grid.hexagon.HexGraph;
import grid.square.MatrixGraph;
import grid.triangle.TriangularGraph;

public class Test_gridrender {
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		final int w = 100, h = 100, s = 25;
		GridConfiguration gconf = new GridConfiguration(CellForm.HEXAGON, s, w, h);
		Graph g = new HexGraph(gconf.getNumCellsX(), gconf.getNumCellsY(), gconf.getLen()); //new MatrixGraph(w, h, s);
		
		for(int i=1; i<=g.getNumNodes(); i++) {
			g.getCell(i).setState(Color.YELLOW);
		}
		
		GridRenderPanel panel = new GridRenderPanel(g, gconf, Color.BLACK) {
			@Override
			public void mouseDraggedCallback(MouseEvent evt) {
				super.mouseDraggedCallback(evt);
			}
		};
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(0, 0, 800, 800);
		
		JButton b7 = new JButton("cambia stati");
		b7.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> li = new ArrayList<Integer>();
				Random rand = new Random();
				for(int i=0; i<10; i++) {
					int ind = rand.nextInt(w*h) + 1;
					li.add(ind);
					g.getCell(ind).setState(Color.BLUE);
				}
				panel.synchWithGraph(li); 
				frame.invalidate();
				frame.repaint();
		    }
		});
		//frame.setLayout(new GridLayout(2, 2, 0, 0));
		frame.add(b7, BorderLayout.SOUTH);//frame.add(b7);
		//frame.add(new Label("a"));
		//frame.add(new Label("b"));
		frame.add(panel);
		frame.setVisible(true);
	}
}
