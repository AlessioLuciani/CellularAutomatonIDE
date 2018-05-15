package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.hexagon.HexGraph;
import grid.square.MatrixGraph;
import grid.triangle.TriangularGraph;
import rules.Rule;

/**classe che trova i possibili conflitti*/
public class ConflictFinder {
	
	public static final String STATE_RULE_CNF = "Stato inesistente usato nelle regole";
	public static final String STATE_INITCONF_CNF = "Stato inesistente usato nella configurazione iniziale";
	public static final String INITCONF_GRIDCONF_CNF = "La configurazione iniziale non rispetta le proprietà della griglia";
	
	/**restituisce set con la descrizione dei conflitti trovati (se il set e' vuoto non ci sono conflitti)*/
	public static HashSet<String> containsConflict(ArrayList<Color> stateColors, GridConfiguration gconf, ArrayList<Rule> rules, Graph graph) {
	/*	HashSet<String> errors = new HashSet<String>();
		HashSet<Integer> rgbColors = new HashSet<Integer>();
		for(Color c : stateColors)
			rgbColors.add(c.getRGB());
		
		for(Rule r : rules) { //cerchiamo colori delle regole negli stati
			if(!rgbColors.contains(r.getNewColor().getRGB()))
				errors.add(STATE_RULE_CNF);
			for(Color c : r.getRoot().getColorList())
				if(!rgbColors.contains(c.getRGB()))
					errors.add(STATE_RULE_CNF);
		}
		
		for(int i=1; i<=graph.getNumNodes(); i++) //controlla stati del grafo con stati disponibili
			if(!rgbColors.contains(graph.getCell(i).getState().getRGB()))
				errors.add(STATE_INITCONF_CNF);
		
		//controlla coerenza forma->tipo grafo
		if(!(graph instanceof MatrixGraph) && gconf.getForm() == CellForm.SQUARE)
			errors.add(INITCONF_GRIDCONF_CNF);
		if(!(graph instanceof TriangularGraph) && gconf.getForm() == CellForm.TRIANGLE)
			errors.add(INITCONF_GRIDCONF_CNF);
		if(!(graph instanceof HexGraph) && gconf.getForm() == CellForm.HEXAGON)
			errors.add(INITCONF_GRIDCONF_CNF);
		return errors;*/
		return null;
	}
}
