package grid.triangle;

import grid.square.MatrixGraph;
import util.Line;

/**grafo di celle triangolari*/
public class TriangularGraph extends MatrixGraph {

	/**w deve essere dispari (usando grid configuration non ci sono problemi)*/
	public TriangularGraph(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
		for(int i=1; i<=w*h; i++)
			nodes.add(new TriangularCell(i, this));
	}
	
	@Override
	public int getCellAtCoordinate(int x, int y) {
		int row = y/this.getSize();
		int col = x/(this.getSize()/2);
		int psum = row * this.getWidth() + col;

		if( (row%2 == 0 && col%2 == 1) || (row%2 == 1 && col%2 == 0) ) { //diagonale da alto-sx a basso-dx
			int x1 = col * this.getSize() / 2; //mi riduco a un rettangolino
			int y1 = row * this.getSize();
			int x2 = x1 + this.getSize()/2;
			int y2 = y1 + this.getSize();
			Line line = new Line(x1, y1, x2, y2); //diagonale del triangolino
			//devo vedere se il punto sta sopra o sotto diagonale e gestire un paio di casi speciali
			if(!line.pointUpLine(x, y)) { //in realta' queste condizioni dovrebbero essere negate, ma visto che l'asse y cresce verso il basso, sono ok cosi
				if(col != this.getWidth()) 
					psum++;
				else
					return -1;
			} else
				if(col == 0) //cliccato sul "nero" a sx
					return -1;
		}
		else { //diagonale da basso-sx a alto-dx
			int x1 = col * this.getSize() / 2;
			int y1 = (row+1) * this.getSize();
			int x2 = x1 + this.getSize()/2;
			int y2 = y1 - this.getSize();
			Line line = new Line(x1, y1, x2, y2);
			if(line.pointUpLine(x, y)) { //in realta' queste condizioni dovrebbero essere negate, ma visto che l'asse y cresce verso il basso, sono ok cosi
				if(col != this.getWidth()) 
					psum++;
				else
					return -1;
			} else
				if(col == 0)
					return -1;
		}
		
		return psum;
	}
}
