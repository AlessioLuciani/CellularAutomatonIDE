package testing_package;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import simulator.chart_panel.ChartPanel;

public class test_Chart {

	public static void main(String[] args) throws Exception {
		 
		ChartPanel f = new ChartPanel(null);

		HashMap<Color, Integer> FrequencyMap;
		
		FrequencyMap = new HashMap<>();
		int rand;
		/*for(int i =0;i<10000;i++) {
			FrequencyMap.clear();
			rand = new Random().nextInt(101);
		    FrequencyMap.put(Color.RED,rand );
		    
		    rand = new Random().nextInt(rand+1);
		    FrequencyMap.put(Color.WHITE, rand);
		    
		    rand = new Random().nextInt(rand+1);
		    FrequencyMap.put(Color.ORANGE, rand);
		    f.updateChartfromGraph(FrequencyMap);
		    Thread.currentThread().sleep(100);
		}*/
		FrequencyMap.put(Color.GREEN, 800);
		FrequencyMap.put(Color.GRAY, 200);
		FrequencyMap.put(Color.RED, 500);
		
		f.updateChartfromGraph();
		
		
		
	  }

}
