package genetics.interesting_conf;

import java.awt.Color;
import java.util.ArrayList;

import grid.Graph;
import grid.square.MatrixGraph;
import rules.Rule;

/**classe principale per trovare una soluzione al problema:
 * dati gli stati e le regole, vogliamo trovare una configurazione iniziale che termina in almeno k passi
 * */
public class Optimizer {
	
	public static final int SIZE_X = 10; //dimensioni del rettangolo in cui vogliamo generare la configurazione
	public static final int SIZE_Y = 10;
	
	/**modifica gli stati del grafo passato come parametro in modo che contenga la soluzione trovata*/
	public void findSolution(ArrayList<Color> states, ArrayList<Rule> rules, Graph graph, int k, int maxIter) {
		int w = ((MatrixGraph)graph).getWidth();
		int h = ((MatrixGraph)graph).getHeight();
		
		ArrayList<Integer> cells = new ArrayList<>();
		int startx = w/2-SIZE_X/2; //il nostro gene e' un rettangolino al centro della configurazione
		int starty = h/2-SIZE_Y/2;
		for(int i=startx; i<startx+SIZE_X; i++)
			for(int j=starty; j<starty+SIZE_Y; j++) 
				cells.add(j * w + i + 1);
		
		SimulatorFitness fitFunc = new SimulatorFitness(graph, rules, k); //fitness function
		
		//cosa da aggiungere: meglio generare un certo numero di soluzioni casuali e iniziare con la migliore tra queste, piuttosto che con una casuale qualsiasi
		
		Gene actualGene = Gene.randomGene(states, cells); //iniziamo con gene casuale
		int actualFitness = fitFunc.evaluate(actualGene); //valuta fitness del gene
		
		Gene bestGene = actualGene;
		int bestFitness = actualFitness;
		int actIter = maxIter;
		
		int ent = 0;
		while(bestFitness < k && actIter-- > 0) { //continuiamo procedura di evoluzione finche' non arriviamo a soluzione cercata (o finiamo iterazioni)
			
			Gene newGene = actualGene.mutate(states, cells); //muto il gene attuale per creare un nuovo gene
			int newFitness = fitFunc.evaluate(newGene); //valuto il nuovo gene
			
			int deltaFitness = newFitness - actualFitness; //calcoliamo incremento di fitness
			//NB: la probabilità decrementa al diminuire dell'iterazione e decrementa all'aumenta dell'errore (in modulo) [simulated annealing]
			double prob = deltaFitness < 0 ? Math.exp((double)deltaFitness * 100.0 / (double)actIter) : 1; //calcoliamo probabilità di accettare il nuovo gene (in caso peggiori)
			
			if(deltaFitness > 0 || prob > Math.random()) { //se il nuovo gene e' migliore di quello attuale (oppure abbiamo deciso di prenderlo, anche se peggiore) lo sostituisco
				ent++;
				actualFitness = newFitness;
				actualGene = newGene;
				
				System.out.println("Act Fitness: "+actualFitness+" "+prob+" "+deltaFitness+" "+actIter+" "+((double)deltaFitness / (double)actIter));
				
				if(actualFitness > bestFitness) { //e mantengo il migliore globale
					bestFitness = actualFitness;
					bestGene = actualGene;
					
					System.out.println("BEST FITNESS: "+bestFitness);
				}
			}
		}
		
		System.out.println("BEST FITNESS: "+bestFitness);
		System.out.println("ent: "+ent+"/"+maxIter);
		bestGene.setGraph(graph); //setta il grafo con il miglior gene che abbiamo generato
	}
	
}
