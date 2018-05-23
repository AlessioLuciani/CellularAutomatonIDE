package simulator.chart_panel;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import util.StaticUtil;

public class ChartPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChart chart;
	private List<PieSeries> seriesList;
	private JPanel panel;
	
	public ChartPanel() {
		setBounds(50,50,500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 
	    seriesList = new ArrayList<>();
	    chart = new PieChartBuilder().width(500).height(500).build();
	      // Create Chart

	    
	    
	    chart.setTitle("Mio Grafico");
	    /*seriesList.add(new PieSeries("1", 0));*/
	    seriesList.add(new PieSeries("2", new myNumber(80.0f)));
	    seriesList.add(new PieSeries("3", new myNumber(20.0f)));
	    /*seriesList.add(new PieSeries("4", 25));
	    seriesList.add(new PieSeries("5", 30));*/
	    
		   
	    panel = new XChartPanel<PieChart>(chart);
	    this.add(panel);
	    setVisible(true);
	    
	    Timer t = new Timer(100,updateChart);
	    //t.start();
	}
	
	public void updateSeriesfromMap(HashMap<Color, Integer> FrequencyMap) {
		for (String pieSeries : chart.getSeriesMap().keySet()) {System.out.println(pieSeries);}
		for (Color key : FrequencyMap.keySet()) {
			if (chart.getSeriesMap().containsKey(key)) {
				//System.out.println("gia presente, aggiorno: "+StaticUtil.colorToRgbString(key));
				chart.updatePieSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));
			}
			else {
				//System.out.println("non presente, creo: "+StaticUtil.colorToRgbString(key));
				chart.addSeries(StaticUtil.colorToRgbString(key),FrequencyMap.get(key));
			}
		}
		
	}

	ActionListener updateChart = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			boolean flag = false;
			float c;
			for (PieSeries series : seriesList) {
				if (!flag) c = -1.0f;
				else c = 1.0f;
				flag = !flag;
				chart.updatePieSeries(series.getName(), new myNumber(series.getValue().floatValue()+c));
				series.setValue(new myNumber(series.getValue().floatValue()+c));
			}
			System.out.println();
			panel.repaint();
			ChartPanel.this.repaint();
		}
	};
		
	class myNumber extends Number{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private float number;
		
		public myNumber(float n) {
			number = n;
		}
		
		@Override
		public double doubleValue() {return (double)number;}

		@Override
		public float floatValue() {return number;}

		@Override
		public int intValue() {return (int)number;}

		@Override
		public long longValue() {return (long)number;}
		
	}

	
	}
