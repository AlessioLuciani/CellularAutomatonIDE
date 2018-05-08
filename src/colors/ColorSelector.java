package colors;


import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * JPanel utilizzato per la scelta dei colori.
 * 
 *                                                         WORK IN PROGRESS!!
 */

public class ColorSelector extends JPanel {

	JButton dotPink;
	JButton dotRed;
	JButton dotOrange;
	JButton dotYellow;
	JButton dotBlack;
	JButton dotGreen;
	JButton dotBlue;
	JButton dotCyan;

	public ColorSelector() {
		
		// Layout generale
		setLayout(new GridLayout(3, 1, 0, 0));
		
		// Pannelli spaziatori
		JPanel upperText = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel colorsFirstRow = new JPanel();
		JPanel colorsSecondRow = new JPanel();
		upperText.setBorder(BorderFactory.createEmptyBorder());
		panel_1.setBorder(BorderFactory.createEmptyBorder());
		panel_2.setBorder(BorderFactory.createEmptyBorder());
		colorsFirstRow.setBorder(BorderFactory.createEmptyBorder());
		colorsSecondRow.setBorder(BorderFactory.createEmptyBorder());
		upperText.setLayout(new GridLayout(3, 3, 0, 0));
		colorsFirstRow.setLayout(new GridLayout(1, 4, 0, 0));
		colorsSecondRow.setLayout(new GridLayout(1, 4, 0, 0));
		add(upperText);
		upperText.add(panel_1);
		upperText.add(panel_2);
		add(colorsFirstRow);
		add(colorsSecondRow);
		
		// Etichetta TextArea 
		JTextArea txtrSelCol = new JTextArea();
		panel_2.add(txtrSelCol);
		txtrSelCol.setText("Seleziona un colore:");
		txtrSelCol.setEditable(false);
		txtrSelCol.setOpaque(false);
		
		
		// Colori
		
		dotPink = new JButton(new ImageIcon(getClass().getResource("dot_pink.png")));
		dotPink.setBorder(BorderFactory.createEmptyBorder());
		dotPink.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotPink.setIcon(new ImageIcon(getClass().getResource("dot_checked_pink.png")));
		    }
		});
		colorsFirstRow.add(dotPink);
		
		dotRed = new JButton(new ImageIcon(getClass().getResource("dot_red.png")));
		dotRed.setBorder(BorderFactory.createEmptyBorder());
		dotRed.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotRed.setIcon(new ImageIcon(getClass().getResource("dot_checked_red.png")));
		    }
		});
		colorsFirstRow.add(dotRed);
		
		dotOrange = new JButton(new ImageIcon(getClass().getResource("dot_orange.png")));
		dotOrange.setBorder(BorderFactory.createEmptyBorder());
		dotOrange.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotOrange.setIcon(new ImageIcon(getClass().getResource("dot_checked_orange.png")));
		    }
		});
		colorsFirstRow.add(dotOrange);
		
		dotYellow = new JButton(new ImageIcon(getClass().getResource("dot_yellow.png")));
		dotYellow.setBorder(BorderFactory.createEmptyBorder());
		dotYellow.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotYellow.setIcon(new ImageIcon(getClass().getResource("dot_checked_yellow.png")));
		    }
		});
		colorsFirstRow.add(dotYellow);
		
		dotBlack = new JButton(new ImageIcon(getClass().getResource("dot_black.png")));
		dotBlack.setBorder(BorderFactory.createEmptyBorder());
		dotBlack.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotBlack.setIcon(new ImageIcon(getClass().getResource("dot_checked_black.png")));
		    }
		});
		colorsSecondRow.add(dotBlack);
		
		dotGreen = new JButton(new ImageIcon(getClass().getResource("dot_green.png")));
		dotGreen.setBorder(BorderFactory.createEmptyBorder());
		dotGreen.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotGreen.setIcon(new ImageIcon(getClass().getResource("dot_checked_green.png")));
		    }
		});
		colorsSecondRow.add(dotGreen);
		
		dotBlue = new JButton(new ImageIcon(getClass().getResource("dot_blue.png")));
		dotBlue.setBorder(BorderFactory.createEmptyBorder());
		dotBlue.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotBlue.setIcon(new ImageIcon(getClass().getResource("dot_checked_blue.png")));
		    }
		});
		colorsSecondRow.add(dotBlue);
		
		dotCyan = new JButton(new ImageIcon(getClass().getResource("dot_cyan.png")));
		dotCyan.setBorder(BorderFactory.createEmptyBorder());
		dotCyan.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
				resetAllColors();
				dotCyan.setIcon(new ImageIcon(getClass().getResource("dot_checked_cyan.png")));
		    }
		});
		colorsSecondRow.add(dotCyan);

	}
	
	private void resetAllColors() {
		dotPink.setIcon(new ImageIcon(getClass().getResource("dot_pink.png")));
		dotRed.setIcon(new ImageIcon(getClass().getResource("dot_red.png")));
		dotOrange.setIcon(new ImageIcon(getClass().getResource("dot_orange.png")));
		dotYellow.setIcon(new ImageIcon(getClass().getResource("dot_yellow.png")));
		dotBlack.setIcon(new ImageIcon(getClass().getResource("dot_black.png")));
		dotGreen.setIcon(new ImageIcon(getClass().getResource("dot_green.png")));
		dotBlue.setIcon(new ImageIcon(getClass().getResource("dot_blue.png")));
		dotCyan.setIcon(new ImageIcon(getClass().getResource("dot_cyan.png")));
	}

}
