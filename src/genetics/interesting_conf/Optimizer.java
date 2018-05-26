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
public class Optimizer extends Thread {
	
	public static final int SIZE_X = 10; //dimensioni del rettangolo in cui vogliamo generare la configurazione
	public static final int SIZE_Y = 10;
	public static final int POOL_SIZE = 10; //numero di geni attuali che manteniamo
	
	protected Gene actualGene []; //informazioni sui geni attuali (magari da raggruppare in una classe)
	protected int actualFitness [];
	protected int actualAge [];
	
	protected Gene bestGene;
	protected int bestFitness;
	
	protected ArrayList<Color> states;
	protected ArrayList<Rule> rules;
	protected Graph graph;
	protected int k, maxIter, cycLen;
	
	public Optimizer(ArrayList<Color> states, ArrayList<Rule> rules, Graph graph, int k, int maxIter, int cycLen) {
		actualGene = new Gene[POOL_SIZE];
		actualFitness = new int[POOL_SIZE];
		actualAge = new int[POOL_SIZE];
		
		this.states = states;
		this.rules = rules;
		this.graph = graph;
		this.k = k;
		this.maxIter = maxIter;
		this.cycLen = cycLen;
	}
	
	/**modifica gli stati del grafo passato come parametro in modo che contenga la soluzione trovata*/
	@Override
	public void run() {
		Random rand = new Random();
		int w = ((MatrixGraph)graph).getWidth();
		int h = ((MatrixGraph)graph).getHeight();
		int sx = Math.min(w, SIZE_X);
		int sy = Math.min(h, SIZE_Y);
		
		ArrayList<Integer> cells = new ArrayList<>();
		int startx = w/2-sx/2; //il nostro gene e' un rettangolino al centro della configurazione
		int starty = h/2-sy/2;
		for(int i=startx; i<startx+sx; i++)
			for(int j=starty; j<starty+sy; j++) 
				cells.add(j * w + i + 1);	

		SimulatorFitness fitFunc = new SimulatorFitness(graph, rules, k, cycLen); //fitness function
		
		initPool(cells, fitFunc); //inizializziamo la pool di geni attuali
		
		int actIter = maxIter;
		int ent = 0; //solo debug
		
		while(bestFitness < k && actIter-- > 1 && !isInterrupted()) { //continuiamo procedura di evoluzione finche' non arriviamo a soluzione cercata (o finiamo iterazioni) (oppure veniamo interrotti)
			
			int ind = actIter % actualGene.length; //aggiorniamo ciclicamente tutti i geni attuali
			int ind2 = rand.nextInt(actualGene.length); //prendiamo un secondo indice diverso dal primo (lo useremo nel crossover)
			if(ind == ind2) ind2 = (ind2+1) % actualGene.length;
			
			Gene newGene = null;
			boolean choiceMut = rand.nextBoolean();
			if(choiceMut) //a caso scegliamo se fare mutazione o crossover
				newGene = actualGene[ind].mutate(states, cells); //muto il gene attuale per creare un nuovo gene
			else
				newGene = actualGene[ind].crossover(cells, actualGene[ind2]); //faccio il crossover tra due geni
			
			int newFitness = fitFunc.evaluate(newGene); //valuto il nuovo gene
			
			int deltaFitness = newFitness - actualFitness[ind] - 1; //calcoliamo incremento di fitness (sottriamo 1 per non accettare al 100% soluzioni uguali al best)
			//NB: la probabilità aumenta all'aumentare dell'eta' e decrementa all'aumentare dell'errore (in modulo) [simulated annealing]
			double prob = deltaFitness < 0 ? Math.exp((double)deltaFitness / (double)(actualAge[ind])) : 1; //calcoliamo probabilità di accettare il nuovo gene (in caso peggiori)
			double rnd = rand.nextDouble();
			
			if(deltaFitness >= 0 || prob > rnd) { //se il nuovo gene e' migliore di quello attuale (oppure abbiamo deciso di prenderlo, anche se peggiore) lo sostituisco
				
				System.out.println("Act Fitness: "+ind+" "+actualFitness[ind]+" "+newFitness+" "+prob+" "+deltaFitness+" "+actualAge[ind]+" "+rnd+" "+(choiceMut?"mutation":"crossover"));
				
				ent++;
				actualFitness[ind] = newFitness;
				actualGene[ind] = newGene;
				actualAge[ind] = 1; 
				
				if(actualFitness[ind] > bestFitness) { //e mantengo il migliore globale
					bestFitness = actualFitness[ind];
					bestGene = actualGene[ind];
					
					bestGeneUpdated();
					System.out.println("BEST FITNESS: "+bestFitness);
				}
			}
			else //non ho migliorato la soluzione... aumento l'eta' di quella attuale
				actualAge[ind]++;
			
			iterationUpdated(maxIter - actIter + 1);
		}
		
		evolutionCompleted();
		
		System.out.println("BEST FITNESS: "+bestFitness);
		System.out.println("ent: "+ent+"/"+maxIter);
	}

	/**inizializza i geni*/
	protected void initPool(ArrayList<Integer> cells, FitnessFunc fitFunc) {
		actualGene[0] = Gene.fromGraph(graph, cells);  //per il primo prendiamo quello dal grafo
		actualFitness[0] = fitFunc.evaluate(actualGene[0]);
		actualAge[0] = 1;
		bestGene = actualGene[0];
		bestFitness = actualFitness[0];
		
		for(int i=1; i<POOL_SIZE; i++) { //gli altri li prendiamo a caso
			actualGene[i] = Gene.randomGene(states, cells); 
			actualFitness[i] = fitFunc.evaluate(actualGene[i]); //valuta fitness del gene
			actualAge[i] = 1;
			if(actualFitness[i] > bestFitness) {
				bestFitness = actualFitness[i];
				bestGene = actualGene[i];
			}
		}
	}
	
	/**callback chiamata quando termina l'algoritmo genetico*/
	public void evolutionCompleted() {
	}
	
	/**callback usata quando si aggiorna il bestGene (da usare per riferire stato)*/
	public void bestGeneUpdated() {
	}
	
	/**callback per quando si aggiorna iterazione*/
	public void iterationUpdated(int actualIter) {
	}
	
	public Gene getBestGene() {
		return bestGene;
	}
}
