package genetics.interesting_conf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import grid.Graph;
import grid.square.MatrixGraph;
import rules.Rule;

/**classe principale per trovare una soluzione al problema:
 * dati gli stati e le regole, vogliamo trovare una configurazione iniziale che termina in almeno k passi
 * */
public class Optimizer {
	
	public static final int SIZE_X = 10; //dimensioni del rettangolo in cui vogliamo generare la configurazione
	public static final int SIZE_Y = 10;
	
	public static final int INIT_ITER = 20; //numero di generazioni random iniziali
	
	/**modifica gli stati del grafo passato come parametro in modo che contenga la soluzione trovata*/
	public void findSolution(ArrayList<Color> states, ArrayList<Rule> rules, Graph graph, int k, int maxIter) {
		Random rand = new Random();
		int w = ((MatrixGraph)graph).getWidth();
		int h = ((MatrixGraph)graph).getHeight();
		
		ArrayList<Integer> cells = new ArrayList<>();
		int startx = w/2-SIZE_X/2; //il nostro gene e' un rettangolino al centro della configurazione
		int starty = h/2-SIZE_Y/2;
		for(int i=startx; i<startx+SIZE_X; i++)
			for(int j=starty; j<starty+SIZE_Y; j++) 
				cells.add(j * w + i + 1);
		
		SimulatorFitness fitFunc = new SimulatorFitness(graph, rules, k); //fitness function
		
		Gene actualGene = Gene.fromGraph(graph, cells);  //prendiamo quello che c'è già sul grafo per iniziare
		int actualFitness = fitFunc.evaluate(actualGene);
		Gene bestGene = actualGene;
		int bestFitness = actualFitness;
		
		for(int i=0; i<INIT_ITER; i++) { //iniziamo con un il migliore tra tanti geni casuali
			actualGene = Gene.randomGene(states, cells); 
			actualFitness = fitFunc.evaluate(actualGene); //valuta fitness del gene
			if(actualFitness > bestFitness) {
				bestFitness = actualFitness;
				bestGene = actualGene;
			}
		}
		
		actualGene = bestGene;
		actualFitness = bestFitness;
		int actualAge = 1; //eta' del gene attuale
		
		int actIter = maxIter;
		
		int ent = 0;
		
		while(bestFitness < k && actIter-- > 1) { //continuiamo procedura di evoluzione finche' non arriviamo a soluzione cercata (o finiamo iterazioni)
			
			Gene newGene = actualGene.mutate(states, cells); //muto il gene attuale per creare un nuovo gene
			int newFitness = fitFunc.evaluate(newGene); //valuto il nuovo gene
			
			int deltaFitness = newFitness - actualFitness - 1; //calcoliamo incremento di fitness (sottriamo 1 per non accettare al 100% soluzioni uguali al best)
			//NB: la probabilità aumenta all'aumentare dell'eta' e decrementa all'aumentare dell'errore (in modulo) [simulated annealing]
			double prob = deltaFitness < 0 ? Math.exp((double)deltaFitness / (double)(actualAge*3)) : 1; //calcoliamo probabilità di accettare il nuovo gene (in caso peggiori)
			double rnd = rand.nextDouble();
			
			if(deltaFitness >= 0 || prob > rnd) { //se il nuovo gene e' migliore di quello attuale (oppure abbiamo deciso di prenderlo, anche se peggiore) lo sostituisco
				
				System.out.println("Act Fitness: "+actualFitness+" "+newFitness+" "+prob+" "+deltaFitness+" "+actualAge+" "+rnd);
				
				ent++;
				actualFitness = newFitness;
				actualGene = newGene;
				actualAge = 1; 
				
				if(actualFitness > bestFitness) { //e mantengo il migliore globale
					bestFitness = actualFitness;
					bestGene = actualGene;
					
					System.out.println("BEST FITNESS: "+bestFitness);
				}
			}
			else //non ho migliorato la soluzione... aumento l'eta' di quella attuale
				actualAge++;
		}
		
		System.out.println("BEST FITNESS: "+bestFitness);
		System.out.println("ent: "+ent+"/"+maxIter);
		bestGene.setGraph(graph); //setta il grafo con il miglior gene che abbiamo generato
	}
	
}
