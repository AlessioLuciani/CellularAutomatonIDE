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
	
	private Graph graph; 
	private ArrayList<Color> state;
	private GridConfiguration grid;
	private ArrayList<Rule> rules;
	
	public ConfigContainer(Graph graph, StateChoser state, GridConfCreator grid, RuleChoser rules) {
		this.graph = graph;
		this.state = state.getStates();
		this.grid = grid.getGridConfiguration();
		this.rules = rules.getRuleTrees();
	}
	
	public Graph getGraph() { return this.graph; }
	
	public ArrayList<Color> getState() { return this.state; }
	
	public GridConfiguration getGrid() { return this.grid; }
	
	public ArrayList<Rule> getRule() { return this.rules; }

}
