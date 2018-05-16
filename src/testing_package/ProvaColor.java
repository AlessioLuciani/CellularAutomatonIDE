package testing_package;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import grid.GridConfCreator;
import util.colors.ColorSelector;

public class ProvaColor {

	public static void main(String [] args) {
    	
    	
    	
    	ArrayList<Color> colori = new ArrayList<>();
    	
    	//for (int i = 0; i < 3; i++) {
    	 {	ColorSelector col = new ColorSelector() {
    			@Override
    			public void colorChosen(Color c) {
    				System.out.println(c);
    			}
    		};

        	col.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        	col.setVisible(true);
        	
        	while(!col.isColorChosen()) {}
        	
        	colori.add(col.getChosenColor());
        	
		}
    	
    	System.out.println(colori.toString());
    	
    }
	
}
