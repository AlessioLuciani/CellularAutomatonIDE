package grid.hexagon;

import java.awt.Color;
import java.awt.Graphics;

import grid.square.SquareCell;

/**cella esagonale*/
public class HexCell extends SquareCell {

	public HexCell(int myId, HexGraph graph) {
		super(myId, graph);
	}
	
	@Override
	public int getKthNeighbor(int k) {
		if(k < 0 || k > 6) return -1;
		if(k == 0) return myId;
		
		int row = (myId-1) / matrix.getWidth();
		if(k == 1) //alto sx
			return ((row%2 == 0 && (myId-1)%matrix.getWidth() == 0) || row == 0) ? -1 : myId - matrix.getWidth() - (row%2 == 0 ? 1 : 0);
		if(k == 2) //alto dx
			return ((row%2 == 1 && (myId-1)%matrix.getWidth() == matrix.getWidth()-1) || row == 0) ? -1 : myId - matrix.getWidth() + (row%2 == 0 ? 0 : 1);
		if(k == 3) //sx
			return ((myId-1)%matrix.getWidth() == 0) ? -1 : myId-1;
		if(k == 4) //dx
			return ((myId-1)%matrix.getWidth() == matrix.getWidth()-1) ? -1 : myId+1;
		if(k == 5) //basso sx
			return ((row%2 == 0 && (myId-1)%matrix.getWidth() == 0) || row == matrix.getHeight()-1) ? -1 : myId + matrix.getWidth() - (row%2 == 0 ? 1 : 0);
		if(k == 6) //basso dx
			return ((row%2 == 1 && (myId-1)%matrix.getWidth() == matrix.getWidth()-1) || row == matrix.getHeight()-1) ? -1 : myId + matrix.getWidth() + (row%2 == 0 ? 0 : 1);
		return 0;
	}

	@Override
	public int getNumNeighbors() {
		return 6;
	}

	@Override
	public void render(Graphics g, Color borderColor) {
		int row = (myId-1) / matrix.getWidth();
		int col = (myId-1) % matrix.getWidth();
		int x[] = new int[6];
		int y[] = new int[6];
		if(row%2 == 0) {
			x[0] = col * matrix.getSize() + matrix.getSize() / 2; //alto al centro
		} else {
			x[0] = col * matrix.getSize() + matrix.getSize();
		}
		
		y[0] = row * (matrix.getSize() - matrix.getSize()/4); 
		x[1] = x[0] - matrix.getSize()/2; //tutti gli altri in senso antiorario
		y[1] = y[0] + matrix.getSize()/4;
		x[2] = x[1];
		y[2] = y[1] + matrix.getSize()/2;
		x[3] = x[0];
		y[3] = y[0] + matrix.getSize();
		x[4] = x[2] + matrix.getSize();
		y[4] = y[2];
		x[5] = x[1] + matrix.getSize();
		y[5] = y[1];
		
		g.setColor(this.getState());
		g.fillPolygon(x, y, 6);
		if(borderColor != null) {
			g.setColor(borderColor);
			g.drawPolygon(x, y, 6);
		}
	}
}
