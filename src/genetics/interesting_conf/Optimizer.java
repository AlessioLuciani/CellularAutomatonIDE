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
	public static final int POOL_SIZE = 5; //numero di geni attuali che manteniamo
	
	protected Gene actualGene []; //informazioni sui geni attuali (magari da raggruppare in una classe)
	protected int actualFitness [];
	
	protected Gene bestGene;
	protected int bestFitness;
	
	protected ArrayList<Color> states;
	protected ArrayList<Rule> rules;
	protected Graph graph;
	protected int k, cycLen;
	
	public Optimizer(ArrayList<Color> states, ArrayList<Rule> rules, Graph graph, int k, int cycLen) {
		actualGene = new Gene[POOL_SIZE];
		actualFitness = new int[POOL_SIZE];
		
		this.states = states;
		this.rules = rules;
		this.graph = graph;
		this.k = k;
		this.cycLen = cycLen;
	}
	
	/**modifica gli stati del grafo passato come parametro in modo che contenga la soluzione trovata*/
	@Override
	public void run() {
		
		bestGene = null;
		bestFitness = -1;
		for(int i=0; i<POOL_SIZE; i++)
			actualGene[i] = null;
		
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

		double temperature = 1.0;
		double minTemp = 0.00001;
		double alphaPrm = 0.9;
		int coolLen = 100;
		
		int maxIter = 0; //calcoliamoci massimo numero di iterazioni
		double tmp = temperature;
		while(tmp >= minTemp) {
			maxIter++;
			tmp *= alphaPrm;
		}
		maxIter *= coolLen;
		maxIter += (POOL_SIZE-1);
		
		SimulatorFitness fitFunc = new SimulatorFitness(graph, rules, k, cycLen); //fitness function
		
		int actIter = 0;
		int ent = 0; //solo debug
		
		while(bestFitness < k && temperature >= minTemp && !isInterrupted()) { //continuiamo procedura di evoluzione finche' non arriviamo a soluzione cercata (o temperatura troppo bassa) (oppure veniamo interrotti)
			int ind = actIter % actualGene.length; //aggiorniamo ciclicamente tutti i geni attuali
			//ind = 0;
			if(actualGene[ind] == null) { //se questo gene va ancora creato...
				if(ind == 0) {
					actualGene[0] = Gene.fromGraph(graph, cells); //il gene 0 lo prendiamo dal grafo
					actualFitness[0] = fitFunc.evaluate(actualGene[0]);
				}
				else {
					actualGene[ind] = Gene.randomGene(states, cells);  //gli altri a caso
					actualFitness[ind] = fitFunc.evaluate(actualGene[ind]); //valuta fitness del gene
				}
			}
			else { //gene già creato... 
				
				int ind2 = rand.nextInt(actualGene.length); //prendiamo un secondo indice diverso dal primo (lo useremo nel crossover)
				if(ind == ind2) ind2 = (ind2+1) % actualGene.length;
				
				Gene newGene = null;
				boolean choiceMut = rand.nextBoolean();
				if(choiceMut) //a caso scegliamo se fare mutazione o crossover
					newGene = actualGene[ind].mutate(states, cells); //muto il gene attuale per creare un nuovo gene
				else
					newGene = actualGene[ind].crossover(states, cells, actualGene[ind2]); //faccio il crossover tra due geni
				
				int newFitness = fitFunc.evaluate(newGene); //valuto il nuovo gene
				
				//delta normalizzato
				double deltaFitness = (double)(newFitness - actualFitness[ind] - 1) / (double)actualFitness[ind]; //calcoliamo incremento di fitness (sottriamo 1 per non accettare al 100% soluzioni uguali al best)
				//NB: la probabilità aumenta all'aumentare dell'eta' e decrementa all'aumentare dell'errore (in modulo) [simulated annealing]
				double prob = deltaFitness < 0 ? Math.exp(deltaFitness / temperature) : 1; //calcoliamo probabilità di accettare il nuovo gene (in caso peggiori)
				double rnd = rand.nextDouble();
				
				if(deltaFitness >= 0 || prob > rnd) { //se il nuovo gene e' migliore di quello attuale (oppure abbiamo deciso di prenderlo, anche se peggiore) lo sostituisco
					
					System.out.println("Act Fitness: "+ind+" "+actualFitness[ind]+" "+newFitness+" "+prob+" "+deltaFitness+" "+rnd+" "+(choiceMut?"mutation":"crossover"));
					ent++;
					actualFitness[ind] = newFitness;
					actualGene[ind] = newGene;
				}
			}
			
			if(actualFitness[ind] > bestFitness) { //e mantengo il migliore globale
				bestFitness = actualFitness[ind];
				bestGene = actualGene[ind];
				
				bestGeneUpdated();
				System.out.println("BEST FITNESS: "+bestFitness);
			}
			
			actIter++;
			if(actIter % coolLen == 0) //ho fatto coolLen iterazioni... devo abbassare la temperatura
				temperature *= alphaPrm;
			
			iterationUpdated((int)Math.ceil((double)actIter / (double)maxIter * 100.0));
		}
		
		evolutionCompleted();
		
		System.out.println("BEST FITNESS: "+bestFitness);
		System.out.println("ent: "+ent+"/"+maxIter);
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
