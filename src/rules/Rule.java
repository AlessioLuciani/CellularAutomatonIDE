package rules;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;

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
    
    public String toHtmlString() {
    	String color = StaticUtil.getHtmlColorSpan(newColor);
    	return "<html>Ogni cella tale che: "+root.toHtmlStringRec()+" assume stato: "+color+"</html>";
    }
    
    /**restituisce copia della regola*/
    public Rule copy() {
    	ExpressionNode n = root.copy();
    	Color c = newColor;
    	return new Rule(n, c);
    }
    
    /**converte l'albero di regole in stringa*/
    public String ruleToString() { 
    	String stringa = this.newColor.getRGB() + " ";
    	stringa += ruletoString(root);
    	return stringa;
    }
    private String ruletoString(ExpressionNode nodo) {
    	String stringa = "";
    	stringa += nodo.getAttribute();
    	try {
    		stringa += ruletoString(nodo.children[0]);
    		stringa += ruletoString(nodo.children[1]);	
    	}catch (Exception e) {}
    	return stringa ;
    }
    
    /**crea l'albero di una regola partendo da una stringa*/
    public static Rule ruleFromString(String strRule) {
		String[] ruleBody;
		ruleBody = strRule.split(" ");
		LinkedList<String> linkedRule = new LinkedList<String>(Arrays.asList(ruleBody));
		Color newcolor = new Color(Integer.parseInt(linkedRule.get(0)));
		linkedRule.remove(0);
		Rule rule = new Rule(addNode(linkedRule), newcolor);
    	return rule;
	}
	private static ExpressionNode addNode(LinkedList<String> strNode) {
		ExpressionNode act = null;
		switch (strNode.get(0)) {
			case "AND": // 2 figli
				strNode.remove(0);
				act = new AndNode(addNode(strNode), addNode(strNode));
				break;
				
			case "OR": // 2 figli
				strNode.remove(0);
				act = new OrNode(addNode(strNode), addNode(strNode));
				break;
				
			case "NOT": // 1 figlo
				strNode.remove(0);
				act = new NotNode(addNode(strNode));
				break;
				
			case "BE1": // 3 figli
				int min = Integer.parseInt(strNode.get(1));
				int max = Integer.parseInt(strNode.get(2));
				int color = Integer.parseInt(strNode.get(3));
				strNode.remove(0);
				strNode.remove(0);
				strNode.remove(0);
				strNode.remove(0);
				act = new BaseExpressionNode1(min, max, new Color(color));
				break;
				
			case "BE2": // 2 figli
				int child = Integer.parseInt(strNode.get(1));
				int color1 = Integer.parseInt(strNode.get(2));
				strNode.remove(0);
				strNode.remove(0);
				strNode.remove(0);
				act = new BaseExpressionNode2(child, new Color(color1));
				break;
		
			default:
				break;
		}
		return act;
	}
}
