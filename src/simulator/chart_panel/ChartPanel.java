package simulator.chart_panel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import simulator.Updater;
import util.StaticUtil;

public class ChartPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChart chart;
	private JPanel panel;
	private Updater updater;
	private Timer update_timer;
	public static final int UPDATE_DELAY = 200;
	
	private JLabel iterationLabel;
	private static final String iterationLabelStr = "Iterazione attuale: ";
	
	public ChartPanel(Updater updater) {
		setBounds(50,50,400,250);
		
		
		this.updater = updater;
	    chart = new PieChartBuilder().width(400).height(300).build();
	    
	    
	    chart.setTitle("Mio Grafico");
		   
	    panel = new XChartPanel<PieChart>(chart);
	    this.add(panel);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(WindowListener);
	    
		update_timer = new Timer(UPDATE_DELAY, updateListener);
	    
		iterationLabel = new JLabel(iterationLabelStr+"0");
		this.add(iterationLabel, BorderLayout.SOUTH);
	}
	
	public ChartPanel(Updater updater,int width, int height) {
		this(updater);
		setSize(width,height);
	}

	public void updateChartfromGraph() {
		HashMap<Color, Integer> FrequencyMap = updater.getFrequencyMap();
		
		for (Color key : FrequencyMap.keySet()) {
			if (chart.getSeriesMap().containsKey(StaticUtil.colorToRgbString(key))) {chart.updatePieSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));}
			else {
				chart.addSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));
				chart.getSeriesMap().get(StaticUtil.colorToRgbString(key)).setFillColor(key);
			}
		}
		iterationLabel.setText(iterationLabelStr+updater.getActualIteration());
		ChartPanel.this.repaint();
		
	}
	
	ActionListener updateListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {updateChartfromGraph();}
	};
	
	WindowAdapter WindowListener = new WindowAdapter() {
		
		@Override
		public void windowClosed(WindowEvent e) {
			super.windowClosing(e);
			update_timer.stop();
		}
		
		@Override
		public void windowOpened(WindowEvent e) {
			super.windowOpened(e);
			update_timer.start();
		}
	};
	
}
