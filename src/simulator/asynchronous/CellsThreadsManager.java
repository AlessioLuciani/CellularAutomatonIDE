package simulator.asynchronous;

import java.util.ArrayList;
import java.util.Random;

import grid.Cell;
import grid.Graph;

/**
 * Gestore di celle affidate ai thread nella simulazione asincrona.
 */
public class CellsThreadsManager {

	/**
	 * Numero di thread nella simulazione.
	 */
	private int numThreads;
	
	/**
	 * Numero di celle nella simulazione.
	 */
	private int numCells;
	
	/**
	 * Celle del grafo.
	 */
	private ArrayList<Cell> cells;
	
	/**
	 * Numero N di celle destinate ad ogni thread. L'ultimo thread riceverà un numero >= N di celle.
	 */
	private int cellsPerThread;
	
	/**
	 * Numero di celle che avanzano.
	 */
	private int remainingCells;
	
	/**
	 * Costruttore del Manager, deve essere chiamato all'inizio di una simulazione asincrona.
	 * @param numThreads
	 * @param graph
	 */
	public CellsThreadsManager(int numThreads, Graph graph) {
		this.numThreads = numThreads;
		this.numCells = graph.getNumNodes();
		this.cells = graph.getNodes();
		this.cellsPerThread = numCells / numThreads;
		this.remainingCells = numCells % numThreads;
	}
	
	/**
	 * Restituisce le celle destinate al thread chiamante.
	 * @return
	 */
	public ArrayList<Cell> getCells() {
		int ran;
		ArrayList<Cell> randomCells = new ArrayList<>();  //  Celle che vengono affidate al thread
		if (cells.size() > cellsPerThread + remainingCells) {  // Avviene su tutti i thread tranne l'ultimo
			for (int i = 0; i < cellsPerThread; i++) {
				ran = new Random().nextInt(cells.size());
				randomCells.add(cells.get(ran));
				cells.remove(ran);
			}
		}
		else {  // Avviene sull'ultimo thread
			for (Cell cell: cells) {
				randomCells.add(cell);
			}
			cells = new ArrayList<>();
		}
		return randomCells;
	}

}
