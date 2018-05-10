package colors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class CustomPalette extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int cell_size = 150;
	private static int cell_spacing = 2;
	private List<CircolarColorLabel> Cells;
	
	public CustomPalette(List<Color> ColorList) {
		setLayout(null);
		Cells = new ArrayList<>();
		int nColor = ColorList.size();
		int nRows = (int)Math.sqrt(nColor);
		int nCols = (int)(Math.sqrt(nColor)+1);
		setSize(nColor*cell_size,nColor*cell_size);
		
		int cont=0;
		int x = 0;
		int y = 0;
		while(cont<nColor) {
			if (x>=nCols) {x=0;y++;}
			CircolarColorLabel cell = new CircolarColorLabel(ColorList.get((y*nCols)+x), cell_size);
			Cells.add(cell);
			cell.addMouseListener(mouse_event);
			cell.setLocation(cell_size*x+cell_spacing, cell_size*y);
			add(cell);
			cont++;
			x++;
		}
	}	

	MouseAdapter mouse_event = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((CircolarColorLabel)e.getSource()).switchClick();
			((CircolarColorLabel)e.getSource()).repaint();
			System.out.println("entered");
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			((CircolarColorLabel)e.getSource()).setEntered(true);
			((CircolarColorLabel)e.getSource()).setExited(false);
			((CircolarColorLabel)e.getSource()).repaint();
			System.out.println("entered");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			((CircolarColorLabel)e.getSource()).setExited(true);
			((CircolarColorLabel)e.getSource()).setEntered(false);
			System.out.println("exited");
			((CircolarColorLabel)e.getSource()).repaint();
		}
		
	};
		
	}
