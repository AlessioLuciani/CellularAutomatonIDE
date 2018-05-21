package run_frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import grid.Graph;
import grid.GridConfiguration;
import grid.GridRenderPanel;
import main_frame.grid_initializer.GridInitializerPanel;
import rules.Rule;
import simulator.Updater;

public class runFrame extends GridInitializerPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JSlider speedSlider;
	private static final int minSpeed = 1;
	private static final int maxSpeed = 100;
	private Timer timer;
	private Timer t;
	private Updater updater;
	
	public runFrame(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors, ArrayList<Rule> rules) {
		super(graph, gridConfiguration, colors);
		sideBar.remove(btnColorAll);
		
		RunCommandPanel CommandPanel = new RunCommandPanel(sideBar.getWidth());
		sideBar.add(CommandPanel);
		
		speedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, (minSpeed+maxSpeed)/2);
		speedSlider.setPreferredSize(new Dimension(sideBar.getWidth(), speedSlider.getHeight()));
		sideBar.add(speedSlider);
		
		setMouseListener(CommandPanel, 0);
		setMouseListener(speedSlider, 0);
		
		updater = new Updater(graph, rules);
		
		//timer = new Timer();		
		t = new Timer(100, CommandPanel.timerEnded);
		
	}
	
	protected class RunCommandPanel extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton btnStepForward;
		private JButton btnStepBack;
		private JButton btnStart;
		private JButton btnStop;
		
		
		public RunCommandPanel(int width) {
			setLayout(new GridLayout(2,2));
			
			btnStepForward = new JButton(">>");
			btnStepForward.addActionListener(onStepForward);
			
						
			btnStepBack = new JButton("<<");
			btnStepBack.addActionListener(onStepBack);
			
			btnStart = new JButton("run");
			btnStart.addActionListener(onStart);
			
			btnStop = new JButton("stop");
			btnStop.addActionListener(onStop);			
			
			
			add(btnStepBack);
			add(btnStepForward);
			add(btnStart);
			add(btnStop);
			
			runFrame.this.setMouseListener(btnStepForward, 0);
			runFrame.this.setMouseListener(btnStepBack, 0);
			runFrame.this.setMouseListener(btnStart, 0);
			runFrame.this.setMouseListener(btnStop, 0);
			
			
			
		}
		
		ActionListener onStepForward = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("STEP FORWARD");
				updateStep();
			}
		};
		
		ActionListener onStepBack = new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("STEP BACK");
					//updateStep();
				}
			};
			
		ActionListener onStop = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("STOP");
				//runFrame.this.timer.cancel();
				runFrame.this.t.stop();
			}
		};
		
		ActionListener onStart = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PLAY");
				/*runFrame.this.timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  updateStep();
					  }
				}, 100, 100);*/
				
				runFrame.this.t.start();
				
			}
		};
		
		ActionListener timerEnded = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStep();
				
			}
		};
		
		private void updateStep() {
			System.out.println("aggiorno");
			runFrame.this.grid.synchWithGraph(updater.execStep());
			runFrame.this.invalidate();
			runFrame.this.repaint();
		}
		
	}

	
	
	

}
