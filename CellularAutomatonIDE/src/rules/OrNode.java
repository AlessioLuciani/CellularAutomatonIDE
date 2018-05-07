package rules;

import graph.Graph;

/**nodo rappresentante operatore logico or*/
public class OrNode extends ExpressionNode {

    /**prende i due operandi dell'or (che sono due espressioni a loro volta)*/
    public OrNode(ExpressionNode left, ExpressionNode right) {
        super();
        children = new ExpressionNode[]{left, right};
    }

    @Override
    public boolean evaluate(Graph graph, int cell) {
        return children[0].evaluate(graph, cell) || children[1].evaluate(graph, cell);
    }
}
