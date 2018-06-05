package simulator.asynchronous;

import java.util.HashSet;
import java.util.Random;

import grid.GridRenderPanel;
import simulator.Updater;

public class CellControllerThread extends Thread {
	
	private int sleepTime;
	private HashSet<Integer> ControlledCells;
	private Updater updater;
	private volatile GridRenderPanel grid;
	protected static final int MAX_SLEEP = 250;//4900;
	protected static final int MIN_SLEEP = 100;
	
	
	public CellControllerThread(String name,GridRenderPanel grid, HashSet<Integer> cellsList,Updater updater) {
		this.setName(name);
		this.ControlledCells = cellsList;
		this.updater = updater;
		this.grid = grid;
		this.sleepTime = new Random().nextInt(MAX_SLEEP-MIN_SLEEP+1)+MIN_SLEEP;
	}


	@Override
	public void run() {
		try {
			while (!this.isInterrupted()) { //continua ad aggiornare celle finchè non è interrotto
				this.updateAllCells(); //aggiorna celle
				sleep(sleepTime); //dorme un po'
				sleepTime = new Random().nextInt(MAX_SLEEP-MIN_SLEEP+1)+MIN_SLEEP; //cambia termo di dormita
			}
			//System.out.println("Exit: "+this.getName());
		}catch(InterruptedException e) {/*System.out.println("Exit exc: "+this.getName());*/}
	}

	private void updateAllCells() {
		synchronized(this.grid) { //prende i lock su griglia e updater e poi può aggiornarsi
			synchronized(this.updater) {
				this.updater.setCellsToUpdate(ControlledCells);
				this.grid.synchWithGraph(this.updater.execStep());
			}
		}
	}
}
