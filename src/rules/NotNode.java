package rules;

import grid.Graph;

/**nodo rappresentante operatore logico not*/
public class NotNode extends ExpressionNode {

    /**prende operando del not */
    public NotNode(ExpressionNode child) {
        children = new ExpressionNode[] {child};
    }

    @Override
    public boolean evaluate(Graph graph, int cell) {
        return !children[0].evaluate(graph, cell);
    }
}
