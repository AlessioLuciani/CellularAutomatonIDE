package simulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import grid.Graph;
import grid.GridConfiguration;
import main_frame.grid_initializer.GridInitializerPanel;
import rules.Rule;
import simulator.chart_panel.ChartPanel;

public class RunPanel extends GridInitializerPanel {

	private static final long serialVersionUID = 1L;

	protected JSlider speedSlider;
	private static final int minDelay = 1;
	private static final int maxDelay = 100;
	
	private Timer timer;
	private Updater updater;
	
	public RunPanel(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors, ArrayList<Rule> rules) {
		super(graph, gridConfiguration, colors, rules);
		sideBar.remove(btnColorAll); //rimuoviamo bottoni che qui non servono
		sideBar.remove(btnColorRandom);
		sideBar.remove(btnFindConfiguration);
		
		RunCommandPanel CommandPanel = new RunCommandPanel(sideBar.getWidth());
		sideBar.add(CommandPanel);
		
		speedSlider = new JSlider(JSlider.HORIZONTAL, minDelay, maxDelay, (minDelay+maxDelay)/2);
		speedSlider.setPreferredSize(new Dimension(sideBar.getWidth(), speedSlider.getHeight()));
		speedSlider.addChangeListener(new SpeedChangeListener());
		sideBar.add(speedSlider);
		
		setMouseListener(CommandPanel, 0);
		setMouseListener(speedSlider, 0);
		
		updater = new Updater(graph, rules);

		timer = new Timer((minDelay+maxDelay)/2, CommandPanel.timerEnded); //ritardo iniziale: metà del possibile
	}
	
	@Override
	public void onCellColored(Graph graph, int cell) {
		if(!timer.isRunning())
			updater.addCellToUpdate(cell);
	}
	
	/**da chiamare quando si vuole interrompere run*/
	public void onClosing() {
		if(timer.isRunning()) //se chiudi mentre sta eseguendo lo stoppo
			timer.stop();
	}
	
	//listener chiamato quando si sposta lo speed slider
	protected class SpeedChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent evt) {
			int newDelay = RunPanel.maxDelay - ((JSlider)evt.getSource()).getValue() + RunPanel.minDelay;
			RunPanel.this.timer.setDelay(newDelay);
		}
	}
	
	protected class RunCommandPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		protected JButton btnStepForward;
		protected JButton btnStatistics;
		protected JButton btnStart;
		protected JButton btnStop;
		
		
		public RunCommandPanel(int width) {
			setLayout(new GridLayout(2,2));
			
			btnStepForward = new JButton(">>");
			btnStepForward.addActionListener(onStepForward);
						 
			btnStatistics = new JButton("stat"); 
			btnStatistics.addActionListener(onStatistics);
			
			btnStart = new JButton("run");
			btnStart.addActionListener(onStart);
			
			btnStop = new JButton("stop");
			btnStop.addActionListener(onStop);			
			btnStop.setEnabled(false);
			
			add(btnStatistics);
			add(btnStepForward);
			add(btnStart);
			add(btnStop);
			
			RunPanel.this.setMouseListener(btnStepForward, 0);
			RunPanel.this.setMouseListener(btnStatistics, 0);
			RunPanel.this.setMouseListener(btnStart, 0);
			RunPanel.this.setMouseListener(btnStop, 0);
		}
		
		ActionListener onStepForward = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!RunPanel.this.timer.isRunning())
					updateStep();
			}
		};
		
		ActionListener onStatistics = new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					new ChartPanel(null).setVisible(true);
				}
			};
			
		ActionListener onStop = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(RunPanel.this.timer.isRunning()) {
					RunCommandPanel.this.btnStart.setEnabled(true);
					RunCommandPanel.this.btnStepForward.setEnabled(true);
					RunCommandPanel.this.btnStop.setEnabled(false);
					RunPanel.this.timer.stop();
					System.out.println("Iterazioni effettuate: "+RunPanel.this.updater.getActualIteration());
				}
			}
		};
		
		ActionListener onStart = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!RunPanel.this.timer.isRunning()) {
					RunCommandPanel.this.btnStart.setEnabled(false);
					RunCommandPanel.this.btnStepForward.setEnabled(false);
					RunCommandPanel.this.btnStop.setEnabled(true);
					RunPanel.this.timer.start();
				}
			}
		};
		
		ActionListener timerEnded = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStep();
			}
		};
		
		private boolean updateStep() {
			Set<Integer> s = updater.execStep(); //esegui step 
		
			if(s.size() == 0) {//se non vengono fatti aggiornamenti non fare nulla
				onStop.actionPerformed(null); //"simula" la pressione del tasto onStop
				return false;
			}
			
			RunPanel.this.grid.synchWithGraph(s); //altrimenti risincronizza grafo e stampa
			RunPanel.this.invalidate();
			RunPanel.this.repaint();
			return true;
		}
		
	}

}
