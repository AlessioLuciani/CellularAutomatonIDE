package colors;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import grid.GridConfCreator;

public class Prova {

	public static void main(String [] args) {
    	
    	
    	
    	ArrayList<Color> colori = new ArrayList<>();
    	
    	for (int i = 0; i < 3; i++) {
    		ColorSelector col = new ColorSelector();
        	col.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        	col.setVisible(true);
        	
        	while(!col.isColorChosen()) {}
        	
        	colori.add(col.getChosenColor());
        	
		}
    	
    	System.out.println(colori.toString());
    	
    }
	
}
