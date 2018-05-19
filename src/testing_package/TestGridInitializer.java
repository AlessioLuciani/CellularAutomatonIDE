package testing_package;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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
		
		final int w = 100, h = 100, s = 15;
		GridConfiguration gconf = new GridConfiguration(CellForm.TRIANGLE, s, w, h);
		Graph g = Graph.buildGraph(gconf, Color.YELLOW);
	
		ArrayList<Color> colors = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			colors.add(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
		}
		
		
		GridInitializerPanel gridInitializerPanel = new GridInitializerPanel(g, gconf, colors);
		

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		frame.setLocation((int) rect.getMaxX()/2, (int) rect.getMaxY()/2);
		
		
		frame.add(gridInitializerPanel);
		frame.setVisible(true);
		
	}
}
