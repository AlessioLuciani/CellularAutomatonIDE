package grid.square;

import java.awt.Color;
import java.awt.Graphics;

import grid.Cell;

/**cella quadrata*/
public class SquareCell extends Cell {

	protected int myId;
	protected MatrixGraph matrix;
	
	/**dobbiamo passargli il suo id (cosi sa chi è), e matrice di cui fa parte*/
	public SquareCell(int myId, MatrixGraph graph) {
		this.myId = myId;
		matrix = graph;
	}
	
	/**
	 * restituisce vicini secondo questo schema:
	 * 1 2 3
	 * 4 0 5
	 * 6 7 8
	 * */
	@Override
	public int getKthNeighbor(int k) {
		if(k == 0)
			return myId;
		
		int r = (myId-1) / matrix.getWidth();
		int c = (myId-1) % matrix.getWidth();
		if(k == 1 || k == 4 || k == 6) r--;
		if(k == 3 || k == 5 || k == 8) r++;
		if(k == 1 || k == 2 || k == 3) c--;
		if(k == 6 || k == 7 || k == 8) c++;
		if(r < 0 || c < 0 || r >= matrix.getHeight() || c >= matrix.getWidth() || k < 0 || k > 8)
			return -1;
		return matrix.getWidth() * r + c + 1;
	}

	@Override
	public int getNumNeighbors() {
		return 8;
	}

	@Override
	public void render(Graphics g, Color borderColor) {
		int r = (myId-1) / matrix.getWidth();
		int c = (myId-1) % matrix.getWidth();
		int len = matrix.getSize();
		g.setColor(this.getState());
		g.fillRect(c * len, r * len, len, len);
		if(borderColor != null) {
			g.setColor(borderColor);
			g.drawRect(c * len, r * len, len, len);
		}
	}
}
