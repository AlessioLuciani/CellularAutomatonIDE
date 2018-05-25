package genetics.interesting_conf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import grid.Graph;
import rules.Rule;
import simulator.Updater;

/**funzione di fitness basata sul simulare realmente il gene*/
public class SimulatorFitness implements FitnessFunc {

	Graph graph;
	ArrayList<Rule> rules;
	
	int cyclesLen;
	int maxIter;
	
	public SimulatorFitness(Graph graph, ArrayList<Rule> rules, int maxIter, int cl) {
		this.graph = graph;
		this.rules = rules;
		this.maxIter = maxIter;
		cyclesLen = cl;
	}
	
	/**controlla se il nuovo stato forma un ciclo con la storia*/
	private boolean checkCycles(Queue<Set<Integer>> history, Set<Integer> newSet) {
		for(Set<Integer> st : history)
			if(st.size() == newSet.size()) {
				if(newSet.containsAll(st))
					return true;
			}
		return false;
	}
	
	@Override
	public int evaluate(Gene gene) {
		gene.setGraph(graph); //settiamo lo stato del grafo in base al gene
		Updater upd = new Updater(graph, rules); //crea updater
		
		//da aggiungere: si possono fare ottimizzazioni per terminare prima l'esecuzione dell'evaluate (per esempio vedere se troviamo cicli)
		
		Queue<Set<Integer>> history = new LinkedList<Set<Integer>>();
		
		int tot = 1;
		while(tot < maxIter) { //simula...
			Set<Integer> cells = new HashSet<>(upd.execStep());
			if(cells.size() == 0) //si è stabilizzato
				break;
			if(this.cyclesLen > 0 && checkCycles(history, cells)) //trovato ciclo "piccolo" (al piu' lungo cyclesLen)
				break;
			
			if(this.cyclesLen > 0) {
				//qui dobbiamo andare avanti...
				history.add(cells); //aggiungi nuovo set alla storia
				if(history.size() > this.cyclesLen) //se la storia e' piena, levane uno
					history.poll();
			}
			
			tot++;
		}
		
		return tot;
	}

}
