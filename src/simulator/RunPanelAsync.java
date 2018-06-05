package simulator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import grid.Graph;
import grid.GridConfiguration;
import rules.Rule;
import simulator.asynchronous.CellControllerThread;
import simulator.asynchronous.CellsThreadsManager;

public class RunPanelAsync extends RunPanel {

	private static final long serialVersionUID = 1L;

	protected CellsThreadsManager CellManager;
	protected ArrayList<CellControllerThread> Threads;
	protected static final int MAXTHREADS = 100;
	
	public RunPanelAsync(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors, ArrayList<Rule> rules) {
		super(graph, gridConfiguration, colors, rules);
		this.sideBar.remove(speedSlider);
		this.timer = null;
		commandPanel.remove(commandPanel.btnStepForward);
		
		commandPanel.btnStart.removeActionListener(commandPanel.onStart);
		commandPanel.btnStop.removeActionListener(commandPanel.onStop);
		
		commandPanel.btnStart.addActionListener(new ActionListener() { //metto listener nuovo a start
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(commandPanel.btnStart.isEnabled()) {
					initCellThreads(); //inizializzo thread 
					for(CellControllerThread t : Threads) //starto tutti i thread
						t.start();
					
					commandPanel.btnStart.setEnabled(false);
					commandPanel.btnStop.setEnabled(true);
				}
			}
		});
		
		commandPanel.btnStop.addActionListener(new ActionListener() { //metto listener nuovo a stop
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(commandPanel.btnStop.isEnabled()) {
					for(CellControllerThread t : Threads)
						t.interrupt();
					
					commandPanel.btnStart.setEnabled(true);
					commandPanel.btnStop.setEnabled(false);
				}
			}
		});
	}
	
	private void initCellThreads() {
		int numThreads = Math.min(MAXTHREADS, graph.getNumNodes());
		CellManager = new CellsThreadsManager(numThreads, graph); //thread manager assegna celle ai thread
		Threads = new ArrayList<>();
		
		for (int i = 0; i < numThreads; i++) { //crea thread
			CellControllerThread thread =  new CellControllerThread(Integer.toString(i), grid, CellManager.getCells(), updater);
			Threads.add(thread);
		}
	}
	
	@Override
	public void onClosing() {
		super.onClosing();
		if(Threads != null)
			for(Thread t : Threads)
				t.interrupt();
	}
	
}
