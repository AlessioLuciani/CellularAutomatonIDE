package simulator.asynchronous;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import grid.Graph;

/**
 * Gestore di celle affidate ai thread nella simulazione asincrona.
 */
public class CellsThreadsManager {
	
	/**
	 * Numero di celle nella simulazione.
	 */
	private int numCells;
	
	/**
	 * Celle del grafo.
	 */
	private ArrayList<Integer> cells;
	
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
		this.numCells = graph.getNumNodes();
		cells = new ArrayList<>();
		for (int i = 1; i <= numCells; i++) {
			this.cells.add(i);
		}
		this.cellsPerThread = numCells / numThreads;
		this.remainingCells = numCells % numThreads;
	}
	
	/**
	 * Restituisce le celle destinate al thread chiamante.
	 * @return
	 */
	public HashSet<Integer> getCells() {
		int ran;
		HashSet<Integer> randomCells = new HashSet<>();  //  Celle che vengono affidate al thread
		if (cells.size() > cellsPerThread + remainingCells) {  // Avviene su tutti i thread tranne l'ultimo
			for (int i = 0; i < cellsPerThread; i++) {
				ran = new Random().nextInt(cells.size());
				randomCells.add(cells.get(ran));
				cells.set(ran, cells.get(cells.size() - 1));
				cells.remove(cells.size() - 1);
			}
		}
		else {  // Avviene sull'ultimo thread
			for (int cell: cells) {
				randomCells.add(cell);
			}
			cells = new ArrayList<>();
		}
		return randomCells;
	}

}
