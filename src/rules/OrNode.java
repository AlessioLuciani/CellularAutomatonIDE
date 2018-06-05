package rules;

import grid.Graph;

/**nodo rappresentante operatore logico or*/
public class OrNode extends ExpressionNode {

    /**prende i due operandi dell'or (che sono due espressioni a loro volta)*/
    public OrNode(ExpressionNode left, ExpressionNode right) {
        super();
        type = TypeNode.OR;
        children = new ExpressionNode[]{left, right};
    }

    @Override
    public boolean evaluate(Graph graph, int cell) {
        return children[0].evaluate(graph, cell) || children[1].evaluate(graph, cell);
    }
    
    @Override
    public String toString() {
    	return "(" + children[0].toString()+" OR "+children[1].toString()+")";
    }
    
    @Override
    protected String toHtmlStringRec() {
    	return "(" + children[0].toHtmlStringRec()+" OR "+children[1].toHtmlStringRec()+")";
    }
    
    @Override
    public ExpressionNode copy() {
    	return new OrNode(children[0].copy(), children[1].copy());
    }
    
    @Override
    public String getAttribute() { return this.type + " "; }
}
