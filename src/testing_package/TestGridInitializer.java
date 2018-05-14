package testing_package;

import java.awt.Color;

import javax.swing.JFrame;

import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.square.MatrixGraph;
import main_frame.grid_initializer.GridInitializerPanel;


public class TestGridInitializer {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		
		final int w = 100, h = 100, s = 10;
		GridConfiguration gconf = new GridConfiguration(CellForm.SQUARE, s, w, h);
		Graph g = new MatrixGraph(gconf.getNumCellsX(), gconf.getNumCellsY(), gconf.getLen());
		
		for(int i=1; i<=g.getNumNodes(); i++) {
			g.getCell(i).setState(Color.YELLOW);
		}
		
		GridInitializerPanel gridInitializerPanel = new GridInitializerPanel(g, gconf);
		
		frame.add(gridInitializerPanel);
		frame.setVisible(true);
	}
}
