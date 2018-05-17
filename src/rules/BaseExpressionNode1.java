package rules;

import grid.Graph;
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
    public String toString() {
    	String colorStr = StaticUtil.colorToRgbString(colr);
    	return "Il numero dei vicini di colore " + colorStr + " è compreso tra " + num1 +  " e " + num2;
    }
    
    @Override
    protected String toHtmlStringRec() {
    	String html = StaticUtil.getHtmlColorSpan(colr);
    	return "Il numero dei vicini di colore " + html + " è compreso tra " + num1 +  " e " + num2;
    }
    
    @Override
    public void getColors(HashSet<Color> colors) {
    	super.getColors(colors);
    	colors.add(colr);
    }
}
