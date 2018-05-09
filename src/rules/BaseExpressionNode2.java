package rules;

import grid.Graph;

import java.awt.Color;

/**
 * nodo di un'espressione di base di tipo2, cio√®:
 * se il 'k'-esimo vicino √® di colore 'c', allora...
 * NB: assumiamo che il vicino 0 sia la cella stessa, quindi gli altri vicini sono indicizzati da 1 a N
 * */
public class BaseExpressionNode2 extends ExpressionNode {

    protected int indChild;
    protected Color colr;

    /**
     * parametri: 'k'-esimo vicino e colore 'c'
     */
    public BaseExpressionNode2(int k, Color c) {
        indChild = k;
        colr = c;
    }

    @Override
    protected boolean evaluate(Graph graph, int cell) {
        if(graph.getCell(cell) == null) return false; //fai check sugli indici
        if(indChild < 0 || indChild > graph.getCell(cell).getNumNeighbors()) return false; //non ha abbastanza vicini

        int indn = graph.getCell(cell).getKthNeighbor(indChild);//controlla condizione dell'espressione
        return graph.getCell(indn).getState().equals(colr);
    }
    
    @Override
    public String toString() {
    	return "Il " + indChild + "∞ vicino Ë di colore " + colr;
    }
}