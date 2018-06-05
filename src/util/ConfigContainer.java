package util;

import java.awt.Color;
import java.util.ArrayList;

import grid.Graph;
import grid.GridConfCreator;
import grid.GridConfiguration;
import main_frame.rules_creator.RuleChoser;
import main_frame.states.StateChoser;
import rules.Rule;

public class ConfigContainer {
	
	//private Graph graph; 
	private ArrayList<Integer> state;
	private GridConfiguration grid;
	public ArrayList<String> rules;
	
	public ConfigContainer(ArrayList<Integer> state, GridConfCreator grid, ArrayList<String> rules) {
		//this.graph = graph.copy();
		this.state = state;
		this.grid = grid.getGridConfiguration();
		this.rules = rules;
		
		
	}
	
	//public Graph getGraph() { return this.graph; }
	
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
	
	public void print() { System.out.println("n stati = " + this.state.size());
						  System.out.println("n regole = " + this.rules.size());
						  System.out.println(this.grid.toString());}

}
