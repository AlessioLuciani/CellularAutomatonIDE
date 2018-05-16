package grid.hexagon;

import java.awt.Color;
import java.awt.Graphics;

import grid.Cell;

/**cella esagonale*/
public class HexCell extends Cell {

	protected HexGraph hgraph;
	
	public HexCell(int myId, HexGraph graph) {
		super(myId);
		hgraph = graph;
	}
	
	@Override
	public int getKthNeighbor(int k) {
		if(k < 0 || k > 6) return -1;
		if(k == 0) return myId;
		
		int row = (myId-1) / hgraph.getWidth();
		if(k == 1) //alto sx
			return ((row%2 == 0 && (myId-1)%hgraph.getWidth() == 0) || row == 0) ? -1 : myId - hgraph.getWidth() - (row%2 == 0 ? 1 : 0);
		if(k == 2) //alto dx
			return ((row%2 == 1 && (myId-1)%hgraph.getWidth() == hgraph.getWidth()-1) || row == 0) ? -1 : myId - hgraph.getWidth() + (row%2 == 0 ? 0 : 1);
		if(k == 3) //sx
			return ((myId-1)%hgraph.getWidth() == 0) ? -1 : myId-1;
		if(k == 4) //dx
			return ((myId-1)%hgraph.getWidth() == hgraph.getWidth()-1) ? -1 : myId+1;
		if(k == 5) //basso sx
			return ((row%2 == 0 && (myId-1)%hgraph.getWidth() == 0) || row == hgraph.getHeight()-1) ? -1 : myId + hgraph.getWidth() - (row%2 == 0 ? 1 : 0);
		if(k == 6) //basso dx
			return ((row%2 == 1 && (myId-1)%hgraph.getWidth() == hgraph.getWidth()-1) || row == hgraph.getHeight()-1) ? -1 : myId + hgraph.getWidth() + (row%2 == 0 ? 0 : 1);
		return 0;
	}

	@Override
	public int getNumNeighbors() {
		return 6;
	}

	@Override
	public void render(Graphics g, Color borderColor) {
		int row = (myId-1) / hgraph.getWidth();
		int col = (myId-1) % hgraph.getWidth();
		int x[] = new int[6];
		int y[] = new int[6];
		if(row%2 == 0) {
			x[0] = col * hgraph.getSize() + hgraph.getSize() / 2;	
		} else {
			x[0] = col * hgraph.getSize() + hgraph.getSize();
		}
		
		y[0] = row * (hgraph.getSize() - hgraph.getSize()/4);
		x[1] = x[0] - hgraph.getSize()/2;
		y[1] = y[0] + hgraph.getSize()/4;
		x[2] = x[1];
		y[2] = y[1] + hgraph.getSize()/2;
		x[3] = x[0];
		y[3] = y[0] + hgraph.getSize();
		x[4] = x[2] + hgraph.getSize();
		y[4] = y[2];
		x[5] = x[1] + hgraph.getSize();
		y[5] = y[1];
		
		g.setColor(this.getState());
		g.fillPolygon(x, y, 6);
		if(borderColor != null) {
			g.setColor(borderColor);
			g.drawPolygon(x, y, 6);
		}
	}
}
