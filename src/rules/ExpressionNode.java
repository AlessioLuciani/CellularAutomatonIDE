package rules;

import grid.Graph;

/**nodo genenerico dell'albero rappresentante un'espressione */
public abstract class ExpressionNode {
    protected ExpressionNode [] children; //array dei figli

    /**valuta l'espressione associata all'albero (restituisce il valore di verita').
     * per una certa cella su un certo grafo (cioe' griglia)
     * */
    protected abstract boolean evaluate(Graph graph, int cell);
}
