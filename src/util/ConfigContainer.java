package util;

import java.awt.Color;
import java.util.ArrayList;

import grid.GridConfCreator;
import grid.GridConfiguration;
import rules.Rule;

/**contiene tutti i dati di un automa: usato per esportarlo/importarlo*/
public class ConfigContainer {
	 
	private ArrayList<Integer> graphStates; //stati per il grafo nella configurazione iniziale
	private ArrayList<Integer> state; //stati
	private GridConfiguration grid; //griglia
	private ArrayList<String> rules; //regole
	
	public ConfigContainer(ArrayList<Integer> state, GridConfCreator grid, ArrayList<String> rules, ArrayList<Integer> graphStates) {
		this.graphStates = graphStates;
		this.state = state;
		this.grid = grid.getGridConfiguration();
		this.rules = rules;
	}
	
	public ArrayList<Color> getState() { 
		ArrayList<Color> statelist = new ArrayList<>();
		for (Integer states : state) {
			statelist.add(new Color(states));
		}
		return statelist;
	}
	
	public GridConfiguration getGrid() { return this.grid; }
	
	public ArrayList<Rule> getRule() { 
		ArrayList<Rule> forest = new ArrayList<>();
		
		for (String stringRule : rules) {
			forest.add(Rule.ruleFromString(stringRule));
		}
		return forest;
	}
	
	public ArrayList<Integer> getGraphStates() {
		return graphStates;
	}
	
	public void print() { System.out.println("n stati = " + this.state.size());
						  System.out.println("n regole = " + this.rules.size());
						  System.out.println(this.grid.toString());}

}
