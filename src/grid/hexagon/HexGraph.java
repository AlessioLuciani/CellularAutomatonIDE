package grid.hexagon;

import grid.square.MatrixGraph;
import util.Line;

public class HexGraph extends MatrixGraph {
	public HexGraph(int w, int h, int size) {
		this.w = w;
		this.h = h;
		this.size = size;
		for(int i=1; i<=w*h; i++)
			nodes.add(new HexCell(i, this));	
	}
	
	@Override
	public int getCellAtCoordinate(int x, int y) {
		int row = y / (size / 4); //dividi orizzontalmente l'esagono in 4 parti (2 triangoli, 2 rettangoli)
		if(row%3 > 0) {
			int r = row / 3;
			if((r%2 == 0 && x > size * w) || (r%2 == 1 && x < size/2))
				return -1;
			int col = (x - (r%2 == 0 ? 0 : size/2)) / size;
			return r * this.getWidth() + col + 1;
		} else {
			int r = row / 3;
			int c = x / (size/2);
			
			int x1 = size/2 * c;
			int x2 = x1 + size/2;
			int rr = size/4 + r * 3*size/4; //punto basso (o alto, perchè le coordinate y sono invertite) sulla diagonale
			int y1, y2;
			if( (r%2 == 0 && c%2 == 0) || (r%2 == 1 && c%2 == 1)) { //diagonale da basso sx a alto dx
				y1 = rr;
				y2 = rr - size/4;
			} else { //diagonale da alto sx a basso dx
				y1 = rr - size/4;
				y2 = rr;
			}
	
			Line l = new Line(x1, y1, x2, y2);
			if( !l.pointUpLine(x, y) )
				r--;
			int col = (x - (r%2 == 0 ? 0 : size/2));
			if(col > 0) col /= size;
			
			int cell = r * this.getWidth() + col + 1;
			if(col >= this.getWidth() || col < 0 || r < 0 || r >= this.getHeight()) return -1;
			if(cell >= 1 && cell <= this.getNumNodes())
				return cell;
		}
		return -1;
	}
	
}
