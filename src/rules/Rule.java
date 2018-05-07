package rules;

import java.awt.Color;

/**definisce una regola dell'automa: definita dall'albero dell'espressione e dal nuovo colore che si assume se la condizione è vera*/
public class Rule {
    protected ExpressionNode root;
    protected Color newColor;

    /**radice dell'albero dell'espressione, nuovo stato in caso l'espressione sia vera*/
    public Rule(ExpressionNode root, Color newState) {
        this.root = root;
        newColor = newState;
    }
}
