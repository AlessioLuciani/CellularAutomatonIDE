package genetics.interesting_conf;

/**interfaccia per la funzione di fitness*/
public interface FitnessFunc {
	
	/**deve solo essere in grado di valutare un gene*/
	public int evaluate(Gene gene);
}
