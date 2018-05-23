package genetics.interesting_conf;

import java.util.ArrayList;

import grid.Graph;
import rules.Rule;
import simulator.Updater;

/**funzione di fitness basata sul simulare realmente il gene*/
public class SimulatorFitness implements FitnessFunc {

	Graph graph;
	ArrayList<Rule> rules;
	
	int maxIter;
	
	public SimulatorFitness(Graph graph, ArrayList<Rule> rules, int maxIter) {
		this.graph = graph;
		this.rules = rules;
		this.maxIter = maxIter;
	}
	
	@Override
	public int evaluate(Gene gene) {
		gene.setGraph(graph); //settiamo lo stato del grafo in base al gene
		Updater upd = new Updater(graph, rules); //crea updater
		
		//da aggiungere: si possono fare ottimizzazioni per terminare prima l'esecuzione dell'evaluate (per esempio vedere se troviamo cicli)
		
		int tot = 1;
		while(tot < maxIter && upd.execStep().size() > 0) //aggiorna finche' non convergi a una situazione stabile (oppure arrivi al valore cercato)
			tot++;
		
		return tot;
	}

}
