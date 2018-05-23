package genetics.interesting_conf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import grid.Graph;

/**un gene descrive una soluzione (subottimale) al problema*/
public class Gene {
	
	public static final double CHANGE_OUTC_PROB = 0.1; //con quale probabilità cambiare lo sfondo (con 1-cop si cambiano le celle)
	public static final int CELLS_TO_MUTATE = 3; //numero celle da mutare al massimo
	
	HashMap<Integer, Color> cells; //chiave: indice di una cella, colore: stato della cella
	Color outsideColor; //colore dello sfondo (cioè di tutte le celle che non sono nella map)
	
	public Gene() {
		cells = new HashMap<Integer, Color>();
	}
	
	/**restituisce un gene mutato (stati disponibili e lista delle celle disponibili)*/
	public Gene mutate(ArrayList<Color> states, ArrayList<Integer> indCells) {
		Collections.shuffle(states); //mischiamo gli stati
		
		Gene ng = new Gene();
		ng.outsideColor = this.outsideColor;
		for(int i : cells.keySet())
			ng.cells.put(i, cells.get(i));
		
		Random rand = new Random();
		
		int ctm = CELLS_TO_MUTATE;
		
		if(Math.random() < CHANGE_OUTC_PROB) { //con una certa probabilità mutiamo il colore d'outside
			int r = rand.nextInt(states.size()); //prendi colore random
			if(ng.outsideColor.equals(states.get(r))) //se e' uguale a quello che gia' c'è, prendine un altro
				r = (r+1)%states.size();
			ng.outsideColor = states.get(r); //cambia colore esterno
			
			ctm--; //se cambiamo lo sfondo mutiamo una cella in meno
		}
		
		for(int i=0; i<ctm; i++) {//mutiamo ctm celle interne
			int key = indCells.get(rand.nextInt(indCells.size())); //prendi chiave a caso
			int indc = rand.nextInt(states.size()); //colore a caso
			if(cells.get(key).equals(states.get(indc))) //se e' uguale a quello gia' presente, cambialo
				indc = (indc+1)%states.size();
			
			ng.cells.replace(key, states.get(indc)); //sostituisci valore per questa cella
		}
	
		return ng;
	}
	
	/**imposta gli stati del grafo in base al gene*/
	public void setGraph(Graph g) {
		for(int i=1; i<=g.getNumNodes(); i++)
			if(cells.containsKey(i))
				g.getCell(i).setState(cells.get(i));
			else
				g.getCell(i).setState(outsideColor);
	}
	
	/**restituisce gene casuale (prende lista dei colori disponibili, e set delle celle che fanno parte del gene)*/
	public static Gene randomGene(ArrayList<Color> colors, ArrayList<Integer> cells) {
		Random rand = new Random();
		Gene gene = new Gene();
		gene.outsideColor = colors.get(rand.nextInt(colors.size()));
		
		for(int ind : cells)
			gene.cells.put(ind, colors.get(rand.nextInt(colors.size())));
		
		return gene;
	}
}
