package colors;


import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * JPanel utilizzato per la scelta dei colori.
 * 
 *                                                         WORK IN PROGRESS!!
 */

public class ColorSelector extends JFrame {
	
	private Color chosenColor;
	
	private JButton dotPink;
	private JButton dotRed;
	private JButton dotOrange;
	private JButton dotYellow;
	private JButton dotBlack;
	private JButton dotGreen;
	private JButton dotBlue;
	private JButton plus;
	
    

	public ColorSelector() {
		
		// Layout generale
		setLayout(new GridLayout(3, 1, 0, 0));
		
		setSize(250, 300);
		setResizable(false);
		
		
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
		upperText.setLayout(new GridLayout(3, 3, 80, 0));
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
		
		
		// Button OK
		JButton btnOk = new JButton();
		btnOk.setText("OK");
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
			
		});
		panel_2.add(btnOk);
		
		
		// Pulsanti colori
		
		dotPink = new JButton(new ImageIcon(getClass().getResource("dot_pink.png")));
		dotPink.setBorder(BorderFactory.createEmptyBorder());
		dotPink.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        
				resetAllColors();
				dotPink.setIcon(new ImageIcon(getClass().getResource("dot_checked_pink.png")));
				chosenColor = new Color(229, 73, 159);
		    }
		});
		colorsFirstRow.add(dotPink);
		
		dotRed = new JButton(new ImageIcon(getClass().getResource("dot_red.png")));
		dotRed.setBorder(BorderFactory.createEmptyBorder());
		dotRed.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				resetAllColors();
				dotRed.setIcon(new ImageIcon(getClass().getResource("dot_checked_red.png")));
				chosenColor = new Color(206, 16, 45);
		    }
		});
		colorsFirstRow.add(dotRed);
		
		dotOrange = new JButton(new ImageIcon(getClass().getResource("dot_orange.png")));
		dotOrange.setBorder(BorderFactory.createEmptyBorder());
		dotOrange.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotOrange.setIcon(new ImageIcon(getClass().getResource("dot_checked_orange.png")));
				chosenColor = new Color(224, 90, 38);
		    }
		});
		colorsFirstRow.add(dotOrange);
		
		dotYellow = new JButton(new ImageIcon(getClass().getResource("dot_yellow.png")));
		dotYellow.setBorder(BorderFactory.createEmptyBorder());
		dotYellow.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotYellow.setIcon(new ImageIcon(getClass().getResource("dot_checked_yellow.png")));
				chosenColor = new Color(255, 255, 40);
		    }
		});
		colorsFirstRow.add(dotYellow);
		
		dotBlack = new JButton(new ImageIcon(getClass().getResource("dot_black.png")));
		dotBlack.setBorder(BorderFactory.createEmptyBorder());
		dotBlack.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotBlack.setIcon(new ImageIcon(getClass().getResource("dot_checked_black.png")));
				chosenColor = new Color(0, 0, 0);
		    }
		});
		colorsSecondRow.add(dotBlack);
		
		dotGreen = new JButton(new ImageIcon(getClass().getResource("dot_green.png")));
		dotGreen.setBorder(BorderFactory.createEmptyBorder());
		dotGreen.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotGreen.setIcon(new ImageIcon(getClass().getResource("dot_checked_green.png")));
				chosenColor = new Color(15, 198, 58);
		    }
		});
		colorsSecondRow.add(dotGreen);
		
		dotBlue = new JButton(new ImageIcon(getClass().getResource("dot_blue.png")));
		dotBlue.setBorder(BorderFactory.createEmptyBorder());
		dotBlue.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotBlue.setIcon(new ImageIcon(getClass().getResource("dot_checked_blue.png")));
				chosenColor = new Color(62, 171, 239);
		    }
		});
		colorsSecondRow.add(dotBlue);
		
		// Pulsante più
		
		
		plus = new JButton(new ImageIcon(getClass().getResource("plus.png")));
		plus.setBorder(BorderFactory.createEmptyBorder());
		plus.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
		        				
		        // Paletta colori complessa
		        			
				
				final MyChooser chooser = new MyChooser(new ArrayList<>()) ;
			    ActionListener okListener = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			    	  
			    	// Aggiungi colore scelto
			        chosenColor = chooser.getColor();
			        
			        closeWindow();
			      }
			    };
	
			    ActionListener cancelListener = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			        
			      }
			    };
	
			    boolean modal = false;
	
			    JDialog dialog = JColorChooser.createDialog(new JPanel(), "Seleziona un colore", modal, chooser, okListener,
			        cancelListener);
	
			    
			    dialog.setVisible(true);
		    }
		});
		colorsSecondRow.add(plus);

	}
	



	/**
	 * Restituisce il colore che è stato scelto.
	 * @return
	 */
	public Color getChosenColor() {
		return chosenColor;
	}
	
	/**
	 * Rimuove il colore che è stato selezionato al momento.
	 */
	private void resetAllColors() {
		chosenColor = null;
		dotPink.setIcon(new ImageIcon(getClass().getResource("dot_pink.png")));
		dotRed.setIcon(new ImageIcon(getClass().getResource("dot_red.png")));
		dotOrange.setIcon(new ImageIcon(getClass().getResource("dot_orange.png")));
		dotYellow.setIcon(new ImageIcon(getClass().getResource("dot_yellow.png")));
		dotBlack.setIcon(new ImageIcon(getClass().getResource("dot_black.png")));
		dotGreen.setIcon(new ImageIcon(getClass().getResource("dot_green.png")));
		dotBlue.setIcon(new ImageIcon(getClass().getResource("dot_blue.png")));
	}
	
	/**
	 * Chiude la finestra attualmente aperta.
	 */
	private void closeWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
