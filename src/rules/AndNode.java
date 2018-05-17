package rules;

import grid.Graph;

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
    
    @Override
    public String toString() {
    	return "(" + children[0].toString()+" AND "+children[1].toString()+")";
    }
    
    @Override
    protected String toHtmlStringRec() {
    	return "(" + children[0].toHtmlStringRec()+" AND "+children[1].toHtmlStringRec()+")";
    }
}
