package rules;

import java.awt.Color;

import util.StaticUtil;

/**definisce una regola dell'automa: definita dall'albero dell'espressione e dal nuovo colore che si assume se la condizione è vera*/
public class Rule {
    protected ExpressionNode root;
    protected Color newColor;

    /**radice dell'albero dell'espressione, nuovo stato in caso l'espressione sia vera*/
    public Rule(ExpressionNode root, Color newState) {
        this.root = root;
        newColor = newState;
    }
    
    public ExpressionNode getRoot() {
    	return root;
    }
    
    public Color getNewColor() {
    	return newColor;
    }
    
    @Override
    public String toString() {
    	String colorStr = StaticUtil.colorToRgbString(newColor);
    	return "SE "+root.toString()+" ALLORA nuovo colore = "+colorStr;
    }
    
    public String toHtmlString() {
    	String color = StaticUtil.getHtmlColorSpan(newColor);
    	return "<html>SE "+root.toHtmlStringRec()+" ALLORA nuovo colore = "+color+"</html>";
    }
}
