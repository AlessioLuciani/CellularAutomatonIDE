package rules;

import grid.Graph;
import main_frame.rules_creator.EditExpressionPanel;
import util.StaticUtil;

import java.awt.Color;
import java.util.HashSet;

/**
 * nodo di un'espressione di base di tipo1, cioe':
 * se il numero di vicini di colore 'c' e' compreso tra 'a' e 'b', allora....
 * */
public class BaseExpressionNode1 extends ExpressionNode {

    protected int num1, num2;
    protected Color colr;

    /**parametri: a <= #num vicini <= b t.c. colore = c*/
    public BaseExpressionNode1(int a, int b, Color c) {
    	type = TypeNode.BE1;
        num1 = Math.min(a, b);
        num2 = Math.max(a, b);
        colr = c;
    }

    @Override
    public boolean evaluate(Graph graph, int cell) {
        if(graph.getCell(cell) == null) return false; //fai check sugli indici

        int numGoodNeighs = 0; //contatore del numero di vicini di colore c

        int N = graph.getCell(cell).getNumNeighbors();
        for(int i=1; i<=N; i++) { //scorro vicini della cella
            int idn = graph.getCell(cell).getKthNeighbor(i);
            if(idn < 1) continue;
            if(graph.getCell(idn).getState().equals(colr)) //controllo colore del vicino
                numGoodNeighs++;
        }

        return numGoodNeighs >= num1 && numGoodNeighs <= num2; //controllo se il numero di vicini nel range specificato
    }
    
    @Override
    protected String toHtmlStringRec() {
    	String html = StaticUtil.getHtmlColorSpan(colr);
    	return EditExpressionPanel.TYPE_A[0] + html + EditExpressionPanel.TYPE_A[1] + num1 + EditExpressionPanel.TYPE_A[2] + num2;
    	//return "Il numero dei vicini di colore " + html + " è compreso tra " + num1 +  " e " + num2;
    }
    
    @Override
    public void getColors(HashSet<Color> colors) {
    	super.getColors(colors);
    	colors.add(colr);
    }
    
    @Override
    public ExpressionNode copy() {
    	return new BaseExpressionNode1(num1, num2, colr);
    }
    
    @Override
    public String getAttribute() { 
    	return this.type + " " + this.num1 + " " + this.num2 + " " + this.colr.getRGB() + " ";
    }
}
