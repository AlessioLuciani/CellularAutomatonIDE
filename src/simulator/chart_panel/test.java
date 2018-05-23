package simulator.chart_panel;

import java.awt.Color;
import java.util.HashMap;

public class test {

	public static void main(String[] args) throws Exception {
		 
		ChartPanel f = new ChartPanel();

		HashMap<Color, Integer> FrequencyMap;
		FrequencyMap = new HashMap<>();
	    
	    FrequencyMap.put(Color.RED, 50);
	    FrequencyMap.put(Color.WHITE, 25);
	    FrequencyMap.put(Color.ORANGE, 25);
	    
	    f.updateSeriesfromMap(FrequencyMap);
	    
	    Thread.currentThread().sleep(3000);
	    
	    FrequencyMap.clear();
	    
	    FrequencyMap.put(Color.RED, 50);
	    FrequencyMap.put(Color.WHITE, 22);
	    FrequencyMap.put(Color.BLUE, 28);
	    
	    f.updateSeriesfromMap(FrequencyMap);
	    
	  }

}
