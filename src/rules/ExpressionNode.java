package rules;

import java.awt.Color;
import java.util.HashSet;

import grid.Graph;

/**nodo genenerico dell'albero rappresentante un'espressione */
public abstract class ExpressionNode {
    protected ExpressionNode [] children; //array dei figli

    /**valuta l'espressione associata all'albero (restituisce il valore di verita').
     * per una certa cella su un certo grafo (cioe' griglia)
     * */
    protected abstract boolean evaluate(Graph graph, int cell);
    
    /**restituisce tutti i colori presenti nell'espressione*/
    public HashSet<Color> getColors() {
    	HashSet<Color> ret = new HashSet<Color>();
    	getColors(ret);
    	return ret;
    }
    
    /**mette nell'hashset tutti i colori presenti nell'espressione*/
    public void getColors(HashSet<Color> colors) {
    	if(children != null)
    		for(ExpressionNode en : children)
    			en.getColors(colors);
    }
}
