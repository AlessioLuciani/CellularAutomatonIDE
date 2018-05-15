package grid.hexagon;

import grid.square.MatrixGraph;

public class HexGraph extends MatrixGraph {
	public HexGraph(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
		for(int i=1; i<=w*h; i++)
			nodes.add(new HexCell(i, this));	
	}
}
