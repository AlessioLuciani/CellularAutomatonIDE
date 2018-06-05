package rules;

import java.awt.Color;
import java.util.HashSet;

import grid.Graph;

/**nodo genenerico dell'albero rappresentante un'espressione */
public class ExpressionNode {
    protected ExpressionNode [] children; //array dei figli
    
    protected TypeNode type;

    /**valuta l'espressione associata all'albero (restituisce il valore di verita').
     * per una certa cella su un certo grafo (cioe' griglia)
     * */
    public boolean evaluate(Graph graph, int cell) {return false;};
    
    /**restituisce tutti i colori presenti nell'espressione*/
    public HashSet<Color> getColors() {
    	HashSet<Color> ret = new HashSet<Color>();
    	getColors(ret);
    	return ret;
    }
    
    /**restituisce l'espressione in stringa: ma puo' usare tag html*/
    public String toHtmlString() {
    	return "<html>"+toHtmlStringRec()+"</html>";
    }
    
    /**utility ad html string*/
    protected String toHtmlStringRec() {
    	return toString();
    }
    
    /**mette nell'hashset tutti i colori presenti nell'espressione*/
    public void getColors(HashSet<Color> colors) {
    	if(children != null)
    		for(ExpressionNode en : children)
    			en.getColors(colors);
    }
    
    /**fa copia dell'albero*/
    public ExpressionNode copy() {
    	return null;
    }
    
    /**restituisce tutti gli attributi della classe come stringa*/
    public String getAttribute() {return "";}
}
