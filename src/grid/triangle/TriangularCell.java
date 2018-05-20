package grid.triangle;

import java.awt.Color;
import java.awt.Graphics;

import grid.square.SquareCell;

/**cella triangolare*/
public class TriangularCell extends SquareCell {

	public TriangularCell(int myId, TriangularGraph graph) {
		super(myId, graph);
	}
	
	@Override
	public int getKthNeighbor(int k) {
		if(k < 0 || k > 3) return -1;
		if(k == 0) return myId;
		int ng = -1;
		if(k == 3) //il terzo e' sempre quello "opposto"
			ng = myId + (myId%2 == 0 ? -matrix.getWidth() : matrix.getWidth());
		if(k == 2 && myId%matrix.getWidth() != 0) //il secondo e' sempre quello a destra
			ng = myId+1;
		if(k == 1 && myId%matrix.getWidth() != 1) //il primo e' sempre quello a sinistra
			ng = myId-1;
		return (ng <= matrix.getNumNodes() && ng > 0) ? ng : -1; //controlla che esista prima di restituire (altrimenti dai -1)
	}

	@Override
	public int getNumNeighbors() {
		return 3;
	}

	@Override
	public void render(Graphics g, Color borderColor) {
		int [] xs = new int[3];
		int [] ys = new int[3];
		
		boolean dline = ((myId-1)/matrix.getWidth())%2 == 1; //sei su riga dispari? (c'e' un punta bassa in piu')
		int row = (myId-1)/matrix.getWidth(); //riga 
		int col = ((myId-1)%matrix.getWidth()) / 2; //colonna (senza considerare i triangoli al contrario rispetto a quello considerato)
		
		if(myId%2 == 0) { //"punta" verso il basso
			xs[0] = col * matrix.getSize() + (dline ? 0 : matrix.getSize()/2); //alto-sx
			ys[0] = row * matrix.getSize();
			xs[1] = xs[0] + matrix.getSize(); //alto-dx
			ys[1] = ys[0];
			xs[2] = (xs[0] + xs[1]) / 2; //basso
			ys[2] = ys[0] + matrix.getSize();
		}
		else { //"punta" verso l'alto
			xs[0] = col * matrix.getSize() + (dline ? matrix.getSize()/2 : 0); //basso-sx
			ys[0] = (row + 1) * matrix.getSize();
			xs[1] = xs[0] + matrix.getSize(); //basso-dx
			ys[1] = ys[0];
			xs[2] = (xs[0] + xs[1]) / 2; //alto
			ys[2] = ys[0] - matrix.getSize();
		}
		
		g.setColor(this.getState());
		g.fillPolygon(xs, ys, 3);
		if(borderColor != null) {
			g.setColor(borderColor);
			g.drawPolygon(xs, ys, 3);
		}
	}
}
