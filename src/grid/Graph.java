package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**grafo rappresentare una griglia*/
public class Graph {

    /**lista dei nodi del grafo*/
    protected ArrayList<Cell> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    /**aggiunge cella alla lista*/
    public void addCell(Cell c) {
        nodes.add(c);
    }

    /**restituisce una cella (indicizzate da 1 a N)*/
    public Cell getCell(int cell) {
        if(cell <= 0 || cell > nodes.size()) return null;
        return nodes.get(cell-1);
    }
    
    /**stampa grafo sulla graphics passata (con eventuale colore del bordo celle, null per non avere bordo)*/
    public void drawGraph(Graphics g, Color borderColor) {
    	for(Cell c : nodes)
    		c.render(g, borderColor);
    }
    
    /**restituisce indice cella alle coordinate specificate (si assume che il grafo abbia una rappresentazione grafica definita); -1 se non esiste*/
    public int getCellAtCoordinate(int x, int y) {
    	return -1;
    }
    
    /**numero nodi nel grafo*/
    public int getNumNodes() {
    	return nodes.size();
    }
}
