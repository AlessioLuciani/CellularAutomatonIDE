package grid.square;

import grid.Graph;

/**grafo rappresentato a matrice*/
public class MatrixGraph extends Graph {
	
	protected int w, h, size;
	
	public MatrixGraph(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
		for(int i=1; i<=w*h; i++)
			nodes.add(new SquareCell(i, this));
	}
	
	/**dimensione singola cella*/
	public int getSize() {
		return size;
	}
	
	/**larghezza matrice*/
	public int getWidth() {
		return w;
	}
	
	/**altezza matrice*/
	public int getHeight() {
		return h;
	}
}
