package grid.square;

import grid.Graph;

/**grafo rappresentato a matrice*/
public class MatrixGraph extends Graph {
	
	protected int w, h, size;
	
	public MatrixGraph() {}
	
	public MatrixGraph(int w, int h, int size) {
		super();
		this.w = w;
		this.h = h;
		this.size = size;
		for(int i=1; i<=w*h; i++)
			nodes.add(new SquareCell(i, this));
	}
	
	/**metodo implementato per la matrice*/
	@Override
	public int getCellAtCoordinate(int x, int y) {
		int xround = x/getSize(); 
		int yround = y/getSize();
		int id = yround * getWidth() + xround;
		if(id + 1 > this.getNumNodes()) return -1;
		return id+1;
	}
	
	public void setSize(int size) {
		this.size = size;
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
	
	@Override
	public void setToGraph(Graph g) {
		super.setToGraph(g);
		if(g instanceof MatrixGraph) {
			w = ((MatrixGraph) g).getWidth();
			h = ((MatrixGraph) g).getHeight();
			size = ((MatrixGraph) g).getSize();
		}
	}
	
	@Override
	public String toString() {
		return "matrix "+w+" "+h+" "+size+"\n";
	}
}
