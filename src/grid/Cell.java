package grid;

import java.awt.Color;
import java.awt.Graphics;

/**singola cella del grafo, cioe' nodo*/
public abstract class Cell {
    protected Color state;
    protected int myId; //identificativo della cella
    
    public Cell() {}
    
    public Cell(int myId) {
    	this.myId = myId;
    	state = Color.BLACK;
    }

    /**stato cella*/
    public Color getState() {
        return state;
    }
    
    /**setta stato cella*/
    public void setState(Color c) {
    	state = c;
    }

    /**restituisce indice k-esimo vicino della cella (NB: vicino numero 0 e' il nodo stesso, gli altri sono indicizzati da 1 a N) NB: restituisce -1 se quel vicino non esiste*/
    public abstract int getKthNeighbor(int k);

    /**restituisce numero dei vicini del nodo*/
    public abstract int getNumNeighbors();
    
    /**metodo per stampare la cella con graphics*/
    public abstract void render(Graphics g, Color borderColor);
}