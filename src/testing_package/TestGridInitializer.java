package testing_package;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.hexagon.HexGraph;
import grid.square.MatrixGraph;
import grid.triangle.TriangularGraph;
import jdk.nashorn.api.tree.ForInLoopTree;
import main_frame.grid_initializer.GridInitializerPanel;
import util.colors.CustomColorPicker;


public class TestGridInitializer {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		
		final int w = 150, h = 100, s = 10;
		GridConfiguration gconf = new GridConfiguration(CellForm.HEXAGON, s, w, h);
		Graph g = new HexGraph(gconf.getNumCellsX(), gconf.getNumCellsY(), gconf.getLen());
		
		for(int i=1; i<=g.getNumNodes(); i++) {
			g.getCell(i).setState(Color.YELLOW);
		}
		
		ArrayList<Color> colors = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			colors.add(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
		}
		
		
		GridInitializerPanel gridInitializerPanel = new GridInitializerPanel(g, gconf, colors);
		
		frame.add(gridInitializerPanel);
		frame.setVisible(true);
		
	}
}
