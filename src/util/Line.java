package util;

/**utility per rappresentare retta*/
public class Line {
	double m, q;
	
	/**retta passante per due punti*/
	public Line(int x1, int y1, int x2, int y2) {
		m = x2 != x1 ? (double)(y2-y1) / (double)(x2-x1) : Double.MAX_VALUE/2;
		q = y1 - m * x1;
	}
	
	/**true se il punto e' sopra la linea (intendo proprio sopra, non che vi appartiene), false se e' sotto*/
	public boolean pointUpLine(int x, int y) {
		double yr = m * (double)x + q;
		return y > yr;
	}
}
