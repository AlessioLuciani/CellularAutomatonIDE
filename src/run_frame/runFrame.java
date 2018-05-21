package run_frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import grid.Graph;
import grid.GridConfiguration;
import main_frame.grid_initializer.GridInitializerPanel;

public class runFrame extends GridInitializerPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JSlider speedSlider;
	
	public runFrame(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors) {
		super(graph, gridConfiguration, colors);
		sideBar.remove(btnColorAll);
		sideBar.add(new RunCommandPanel(sideBar.getWidth(),5,10));
		
		speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 10);
		speedSlider.setPreferredSize(new Dimension(sideBar.getWidth(), speedSlider.getHeight()));
		sideBar.add(speedSlider);
		
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
		
		
		public RunCommandPanel(int width,int speedMin,int speedMax) {
			setLayout(new GridLayout(2,2));
			
			btnStepForward = new JButton(">>");
			btnStepForward.addActionListener(onStepForward);
			
						
			btnStepBack = new JButton("<<");
			btnStepBack.addActionListener(onStepBack);
			
			btnStart = new JButton("a");
			btnStart.addActionListener(onStart);
			
			btnStop = new JButton("b");
			btnStop.addActionListener(onStop);			
			
			add(btnStepForward);
			add(btnStepBack);
			add(btnStart);
			add(btnStop);
			
			
		}
		
		ActionListener onStepForward = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("STEP FORWARD");
				
			}
		};
		
		ActionListener onStepBack = new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("STEP BACK");
					
				}
			};
			
		ActionListener onStop = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("STOP");
				
			}
		};
		
		ActionListener onStart = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PLAY");
				
			}
		};
	}

	
	
	

}
