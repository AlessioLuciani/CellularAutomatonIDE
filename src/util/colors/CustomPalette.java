package util.colors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class CustomPalette extends JPanel {
	
	/*
	 * Palette di colori inizializzata con una lista
	 */

	private static final long serialVersionUID = 1L;
	private static int cell_size = 30;				//dimenzione di ogni cella colorata
	private static int cell_spacing = 2;			//distanza tra le celle colrate
	private List<CircolarColorLabel> Cells;
	private CircolarColorLabel SelectedCell;
	
	public CustomPalette(List<Color> ColorList) {
		
		Cells = new ArrayList<>();					//lista di tutte le celle (non serve nel codice ma c'è in caso di utilizzo futuro)
		int nColor = ColorList.size();				//numero di celle colorate
		int nRows = 0;								//numero di righe della matrice delle celle
		int nCols = (int)(Math.sqrt(nColor)+1);		//numero di colonne della matrice delle celle
		SelectedCell = null;							//cella selezionata dall'utente
		
		setLayout(null);
		
		int cont=0;
		int x = 0;
		while(cont<nColor) {
			if (x>=nCols) {x=0;nRows++;}
			CircolarColorLabel cell = new CircolarColorLabel(ColorList.get((nRows*nCols)+x), cell_size);
			Cells.add(cell);
			cell.addMouseListener(mouse_event);
			cell.setLocation((cell_size*x)+cell_spacing, cell_size*nRows);
			add(cell);
			cont++;
			x++;
		}
		
		nRows++;
		setMinimumSize(new Dimension((nCols)*(cell_size+cell_spacing),(nRows*(cell_size+cell_spacing))));
		setSize((nCols)*(cell_size+cell_spacing),nRows*(cell_size+cell_spacing));
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
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			if(((CircolarColorLabel)e.getSource()).contains(e.getPoint())) {
				ChangeSelected(((CircolarColorLabel)e.getSource()));		//aggiorno la cella selezionata
				((CircolarColorLabel)e.getSource()).setClick(true);
				((CircolarColorLabel)e.getSource()).repaint();
			}
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
	
	public Color getSelectedColor(){
		if(SelectedCell!=null) return SelectedCell.getColor();
		return null;
	}
		
}