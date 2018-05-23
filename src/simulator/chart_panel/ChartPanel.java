package simulator.chart_panel;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.XChartPanel;

import grid.Graph;
import grid.GridConfiguration;
import util.StaticUtil;

public class ChartPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChart chart;
	private JPanel panel;
	private Graph graph;
	
	public ChartPanel(Graph graph) {
		setBounds(50,50,400,250);
		
		
		this.graph = graph;
	    chart = new PieChartBuilder().width(400).height(300).build();
	    
	    
	    chart.setTitle("Mio Grafico");
		   
	    panel = new XChartPanel<PieChart>(chart);
	    this.add(panel);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public ChartPanel(Graph graph,int width, int height) {
		this(graph);
		setSize(width,height);
	}

	public void updateChartfromGraph(HashMap<Color, Integer> FrequencyMap) {
		//FrequencyMap = graph.getFrequencyMap();
		for (Color key : FrequencyMap.keySet()) {
			if (chart.getSeriesMap().containsKey(StaticUtil.colorToRgbString(key))) {chart.updatePieSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));}
			else {
				chart.addSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));
				chart.getSeriesMap().get(StaticUtil.colorToRgbString(key)).setFillColor(key);
			}
		}
		
		ChartPanel.this.repaint();
		
	}
}
