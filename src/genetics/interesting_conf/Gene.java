package genetics.interesting_conf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import grid.Graph;

/**un gene descrive una soluzione (subottimale) al problema*/
public class Gene {
	
	public static final double CHANGE_OUTC_PROB = 0.06; //con quale probabilità cambiare lo sfondo (con 1-cop si cambiano le celle)
	public static final int CELLS_TO_MUTATE = 2; //numero celle da mutare al massimo
	
	HashMap<Integer, Color> cells; //chiave: indice di una cella, colore: stato della cella
	Color outsideColor; //colore dello sfondo (cioè di tutte le celle che non sono nella map)
	
	public Gene() {
		cells = new HashMap<Integer, Color>();
	}
	
	/**restituisce il risultato del crossover tra questo gene e il gene 'other'*/
	public Gene crossover(ArrayList<Integer> indCells, Gene other) {
		Gene ret = new Gene();
		Random rand = new Random();
		if(rand.nextBoolean()) //prendiamo uno dei due colori di outside
			ret.outsideColor = this.outsideColor;
		else
			ret.outsideColor = other.outsideColor;
		
		boolean rb = rand.nextBoolean(); //in base al valore booleano scelgo quale metà prendere con l'altro gene
		int start2nd = rb ? 0 : indCells.size()/2;
		int end2nd = rb ? indCells.size()/2 : indCells.size();
		
		for(int i=0; i<indCells.size(); i++)
			if(i < start2nd || i >= end2nd) //fuori dal range del secondo... prendi da questo gene
				ret.cells.put(indCells.get(i), this.cells.get(indCells.get(i)));
			else
				ret.cells.put(indCells.get(i), other.cells.get(indCells.get(i)));
		
		return ret;
	}
	
	/**restituisce un gene mutato (stati disponibili e lista delle celle disponibili)*/
	public Gene mutate(ArrayList<Color> states, ArrayList<Integer> indCells) {
		Collections.shuffle(states); //mischiamo gli stati
		
		Gene ng = new Gene();
		ng.outsideColor = this.outsideColor;
		for(int i : cells.keySet())
			ng.cells.put(i, cells.get(i));
		
		Random rand = new Random();
		
		int ctm = new Random().nextInt(CELLS_TO_MUTATE) + 1;
		
		if(Math.random() < CHANGE_OUTC_PROB) { //con una certa probabilità mutiamo il colore d'outside
			int r = rand.nextInt(states.size()); //prendi colore random
			if(ng.outsideColor.equals(states.get(r))) //se e' uguale a quello che gia' c'è, prendine un altro
				r = (r+1)%states.size();
			ng.outsideColor = states.get(r); //cambia colore esterno
			
			ctm--; //se cambiamo lo sfondo mutiamo una cella in meno
		}
		
		for(int i=0; i<ctm; i++) {//mutiamo ctm celle interne
			int ind = rand.nextInt(indCells.size()-i);
			int key = indCells.get(ind);  //prendi chiave a caso
			int indc = rand.nextInt(states.size()); //colore a caso
			if(cells.get(key).equals(states.get(indc))) //se e' uguale a quello gia' presente, cambialo
				indc = (indc+1)%states.size();
			
			ng.cells.replace(key, states.get(indc)); //sostituisci valore per questa cella
			
			indCells.set(ind, indCells.get(indCells.size()-i-1)); //metti indice scelto in fondo cosi non la estraiamo di nuovo
			indCells.set(indCells.size()-i-1, key);
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
	
	/**restituisce gene dal grafo*/
	public static Gene fromGraph(Graph g, ArrayList<Integer> cells) {
		Gene gene = new Gene();
		for(int c : cells)
			gene.cells.put(c, g.getCell(c).getState());
		for(int i=1; i<=g.getNumNodes(); i++)
			if(!gene.cells.containsKey(i)) {
				gene.outsideColor = g.getCell(i).getState();
				break;
			}
		return gene;
	}
}
