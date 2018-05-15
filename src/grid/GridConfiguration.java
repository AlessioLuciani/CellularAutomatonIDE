package grid;

/**contiene configurazione di una griglia: forma celle, lato del più piccolo quadrato contentente la cella, 
 * numero di celle su asse x, numero celle su asse y*/
public class GridConfiguration {
	protected CellForm form;
	protected int len;
	protected int numCellsX, numCellsY;
	
	public GridConfiguration(CellForm form, int len, int nx, int ny) {
		this.form = form;
		this.len = len;
		numCellsX = nx;
		numCellsY = ny;
	}
	
	/**quanto deve essere larga un'immagine per contenete tutta la griglia?*/
	public int getBufferImageWidth() {
		switch(form) {
			case TRIANGLE: return len + (int)((double)len/2.0 * (getNumCellsX() - 1));
			default: return len * getNumCellsX();
		}
	}
	
	/**restituisce numero celle in orizzontale NB: potrebbe non essere il valore del campo*/
	public int getNumCellsX() {
		switch(form) {
			case TRIANGLE: return numCellsX%2 == 0 ? numCellsX+1 : numCellsX;
			default: return numCellsX;
		}
	}
	
	public int getNumCellsY() {
		return numCellsY;
	}
	
	/**quanto deve essere alta un'immagine per contenere tutta la griglia?*/
	public int getBufferImageHeight() {
		return len * numCellsY;
	}
	
	/**lato per contenere singola cella*/
	public int getLen() {
		return len;
	}
	
	/**restituisce forma cella*/
	public CellForm getForm() {
		return form;
	}
	
	@Override
	public String toString() {
		return form.name()+" "+len+" "+numCellsX+" "+numCellsY;
	}
}
