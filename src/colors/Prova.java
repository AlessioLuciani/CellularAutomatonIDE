package colors;

import javax.swing.JFrame;

import grid.GridConfCreator;

public class Prova {

	public static void main(String [] args) {
        
    	
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ColorSelector col = new ColorSelector();
    	frame.add(col);
    	frame.setBounds(0, 0, 250, 250);
    	frame.setVisible(true);
    
    }
}
