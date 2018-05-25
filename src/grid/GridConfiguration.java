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
			case TRIANGLE: return getLen() + (int)((double)getLen()/2.0 * (getNumCellsX() - 1));
			case HEXAGON: return getLen() * getNumCellsX() + getLen()/2 - 1;
			default: return getLen() * getNumCellsX();
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
		switch(form) {
			case HEXAGON: return (int)(3*(double)getLen()/4.0 * (double)numCellsY + (double)getLen()/4.0); 
			default: return getLen() * numCellsY;
		}
	}
	
	/**lato per contenere singola cella*/
	public int getLen() {
		switch(form) {
			case HEXAGON: 
				int add = len%4 == 0 ? 0 : 4 - (len%4); //deve essere divisibile per 4 altrimenti si accumulano troppi errori nei calcoli
				return len + add; 
			case TRIANGLE: return len + len%2; //divisibile per 2 per stesso motivo esagono
			default: return len;
		}
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
