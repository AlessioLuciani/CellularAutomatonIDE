package grid;

import java.awt.Color;

/**singola cella del grafo, cioè nodo*/
public abstract class Cell {
    protected Color state;

    /**stato cella*/
    public Color getState() {
        return state;
    }

    /**restituisce indice k-esimo vicino della cella (NB: vicino numero 0 è il nodo stesso, gli altri sono indicizzati da 1 a N)*/
    public abstract int getKthNeighbor(int k);

    /**restituisce numero dei vicini del nodo*/
    public abstract int getNumNeighbors();
}