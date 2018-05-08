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
	
	@Override
	public String toString() {
		return form.name()+" "+len+" "+numCellsX+" "+numCellsY;
	}
}
