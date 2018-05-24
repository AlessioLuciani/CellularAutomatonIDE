package simulator.asynchronous;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import grid.Cell;
import grid.Graph;
import grid.GridConfiguration;
import simulator.Updater;

public class CellControllerThread extends Thread {
	
	private int sleepTime;
	private HashSet<Integer> ControlledCells;
	private Updater updater;
	private GridConfiguration grid;
	protected static int MAX_SLEEP = 4900;
	protected static int MIN_SLEEP = 100;
	
	
	public CellControllerThread(GridConfiguration grid, HashSet<Integer> cellsList,Updater updater) {
		this.ControlledCells = cellsList;
		this.updater = updater;
		this.grid = grid;
		this.sleepTime = new Random().nextInt(MAX_SLEEP-MIN_SLEEP+1)+MIN_SLEEP;
	}


	@Override
	public void run() {
		while (!this.isInterrupted()) {
			this.updateAllCells();
			try {sleep(sleepTime);} catch (InterruptedException e) {}
			sleepTime = new Random().nextInt(MAX_SLEEP-MIN_SLEEP+1)+MIN_SLEEP; 
		}
	}


	private void updateAllCells() {
		this.updater.setCellsToUpdate(ControlledCells);
		this.grid.
		this.updater.execStep();
	}
	
	
}
