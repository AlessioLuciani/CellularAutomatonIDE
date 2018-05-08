package grid;

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
}
