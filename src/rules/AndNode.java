package rules;

import graph.Graph;

/**nodo rappresentante operatore logico and*/
public class AndNode extends ExpressionNode {

    /**prende i due operandi dell'and (che sono due espressioni a loro volta)*/
    public AndNode(ExpressionNode left, ExpressionNode right) {
        super();
        children = new ExpressionNode[]{left, right};
    }

    @Override
    public boolean evaluate(Graph graph, int cell) {
        return children[0].evaluate(graph, cell) && children[1].evaluate(graph, cell);
    }
}
