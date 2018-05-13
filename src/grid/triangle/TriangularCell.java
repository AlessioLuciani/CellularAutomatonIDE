package grid.triangle;

import java.awt.Color;
import java.awt.Graphics;

import grid.Cell;

/**cella triangolare*/
public class TriangularCell extends Cell {
	
	TriangularGraph graph;
	
	public TriangularCell(int myId, TriangularGraph graph) {
		super(myId);
		this.graph = graph;
	}
	
	@Override
	public int getKthNeighbor(int k) {
		if(k < 0 || k > 3) return -1;
		if(k == 0) return myId;
		int ng = -1;
		if(k == 3) //il terzo e' sempre quello "opposto"
			ng = myId + (myId%2 == 0 ? -graph.getWidth() : graph.getWidth());
		if(k == 2 && myId%graph.getWidth() != 0) //il secondo e' sempre quello a destra
			ng = myId+1;
		if(k == 1 && myId%graph.getWidth() != 1) //il primo e' sempre quello a sinistra
			ng = myId-1;
		return (ng <= graph.getNumNodes() && ng > 0) ? ng : -1; //controlla che esista prima di restituire (altrimenti dai -1)
	}

	@Override
	public int getNumNeighbors() {
		return 3;
	}

	@Override
	public void render(Graphics g, Color borderColor) {
		int [] xs = new int[3];
		int [] ys = new int[3];
		
		boolean dline = ((myId-1)/graph.getWidth())%2 == 1; //sei su riga dispari? (c'e' un punta bassa in piu')
		int row = (myId-1)/graph.getWidth(); //riga 
		int col = ((myId-1)%graph.getWidth()) / 2; //colonna (senza considerare i triangoli al contrario rispetto a quello considerato)
		
		if(myId%2 == 0) { //"punta" verso il basso
			xs[0] = col * graph.getSize() + (dline ? 0 : graph.getSize()/2); //alto-sx
			ys[0] = row * graph.getSize();
			xs[1] = xs[0] + graph.getSize(); //alto-dx
			ys[1] = ys[0];
			xs[2] = (xs[0] + xs[1]) / 2; //basso
			ys[2] = ys[0] + graph.getSize();
		}
		else { //"punta" verso l'alto
			xs[0] = col * graph.getSize() + (dline ? graph.getSize()/2 : 0); //basso-sx
			ys[0] = (row + 1) * graph.getSize();
			xs[1] = xs[0] + graph.getSize(); //basso-dx
			ys[1] = ys[0];
			xs[2] = (xs[0] + xs[1]) / 2; //alto
			ys[2] = ys[0] - graph.getSize();
		}
		
		g.setColor(this.getState());
		g.fillPolygon(xs, ys, 3);
		if(borderColor != null) {
			g.setColor(borderColor);
			g.drawPolygon(xs, ys, 3);
		}
	}
}
