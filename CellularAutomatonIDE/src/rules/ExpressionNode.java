package rules;

import graph.Graph;

/**nodo genenerico dell'albero rappresentante un'espressione */
public abstract class ExpressionNode {
    protected ExpressionNode [] children; //array dei figli

    /**valuta l'espressione associata all'albero (restituisce il valore di verità).
     * per una certa cella su un certo grafo (cioè griglia)
     * */
    protected abstract boolean evaluate(Graph graph, int cell);

    /**fornisce una stringa che descrive l'albero dell'espressione*/ //TODO: ancora non fa niente
    @Override
    public String toString() {
        String res = "";
        for(ExpressionNode child : children)
            res += child.toString();
        return res;
    }
}
