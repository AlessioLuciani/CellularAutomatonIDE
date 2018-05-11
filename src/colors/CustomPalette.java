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
	private static int cell_size = 30;				//dimenzione di ogni cella colorata
	private static int cell_spacing = 2;			//distanza tra le celle colrate
	private List<CircolarColorLabel> Cells;
	private CircolarColorLabel SelectedCell;
	
	public CustomPalette(List<Color> ColorList) {
		
		Cells = new ArrayList<>();					//lista di tutte le celle (non serve nel codice ma c'è in caso di utilizzo futuro)
		int nColor = ColorList.size();				//numero di celle colorate
		int nRows = (int)Math.sqrt(nColor);			//numero di righe della matrice delle celle
		int nCols = (int)(Math.sqrt(nColor)+1);		//numero di colonne della matrice delle celle
		SelectedCell = null;						//cella selezionata dall'utente
		
		setLayout(null);
		setSize((nCols+1)*(cell_size+cell_spacing),nRows*(cell_size+cell_spacing));
		
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
	
	//cambia la nuova selezionata con la vecchia e sistema la grafica
	private void ChangeSelected(CircolarColorLabel newCell) {
		if (SelectedCell != null) {
			SelectedCell.setClick(false);
			SelectedCell.repaint();
		}
		
		SelectedCell = newCell;
	}

	//getione degli eventi di click, enter e exit
	MouseAdapter mouse_event = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			ChangeSelected(((CircolarColorLabel)e.getSource()));		//aggiorno la cella selezionata
			((CircolarColorLabel)e.getSource()).setClick(true);;
			((CircolarColorLabel)e.getSource()).repaint();
			System.out.println(((CircolarColorLabel)e.getSource()).getColor());
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			((CircolarColorLabel)e.getSource()).setEntered(true);
			((CircolarColorLabel)e.getSource()).setExited(false);
			((CircolarColorLabel)e.getSource()).repaint();
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			((CircolarColorLabel)e.getSource()).setExited(true);
			((CircolarColorLabel)e.getSource()).setEntered(false);
			((CircolarColorLabel)e.getSource()).repaint();
		}
		
	};
		
	}
