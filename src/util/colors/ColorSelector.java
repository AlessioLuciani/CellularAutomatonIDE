package util.colors;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * JPanel utilizzato per la scelta dei colori.
 */

public class ColorSelector extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Colore scelto.
	 */
	private Color chosenColor;
	
	/**
	 * Valore booleano che vale true se il colore è stato scelto.
	 */
	private boolean isColorChosen = false;
	
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
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		setLocation((int) rect.getMaxX()/2, (int) rect.getMaxY()/2);
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
				onOkPressed();
			}
			
		});
		panel_2.add(btnOk);
		
		
		// Pulsanti colori
		dotPink = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_pink.png")));
		dotPink.setBorder(BorderFactory.createEmptyBorder());
		dotPink.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        
				resetAllColors();
				dotPink.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_pink.png")));
				chosenColor = new Color(229, 73, 159);
		    }
		});
		colorsFirstRow.add(dotPink);
		
		dotRed = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_red.png")));
		dotRed.setBorder(BorderFactory.createEmptyBorder());
		dotRed.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				resetAllColors();
				dotRed.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_red.png")));
				chosenColor = new Color(206, 16, 45);
		    }
		});
		colorsFirstRow.add(dotRed);
		
		dotOrange = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_orange.png")));
		dotOrange.setBorder(BorderFactory.createEmptyBorder());
		dotOrange.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotOrange.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_orange.png")));
				chosenColor = new Color(224, 90, 38);
		    }
		});
		colorsFirstRow.add(dotOrange);
		
		dotYellow = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_yellow.png")));
		dotYellow.setBorder(BorderFactory.createEmptyBorder());
		dotYellow.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotYellow.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_yellow.png")));
				chosenColor = new Color(255, 255, 40);
		    }
		});
		colorsFirstRow.add(dotYellow);
		
		dotBlack = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_black.png")));
		dotBlack.setBorder(BorderFactory.createEmptyBorder());
		dotBlack.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotBlack.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_black.png")));
				chosenColor = new Color(0, 0, 0);
		    }
		});
		colorsSecondRow.add(dotBlack);
		
		dotGreen = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_green.png")));
		dotGreen.setBorder(BorderFactory.createEmptyBorder());
		dotGreen.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotGreen.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_green.png")));
				chosenColor = new Color(15, 198, 58);
		    }
		});
		colorsSecondRow.add(dotGreen);
		
		dotBlue = new JButton(new ImageIcon(ColorSelector.class.getResource("res/dot_blue.png")));
		dotBlue.setBorder(BorderFactory.createEmptyBorder());
		dotBlue.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
				dotBlue.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_checked_blue.png")));
				chosenColor = new Color(62, 171, 239);
		    }
		});
		colorsSecondRow.add(dotBlue);
		
		// Pulsante più
		
		
		plus = new JButton(new ImageIcon(ColorSelector.class.getResource("res/plus.png")));
		plus.setBorder(BorderFactory.createEmptyBorder());
		plus.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        				resetAllColors();
		        				
		        // Paletta colori complessa
		        			
				
			final JColorChooser chooser = new JColorChooser();
		    	//disabilitazione pannelli non utilizzati
			for ( AbstractColorChooserPanel el : chooser.getChooserPanels()) {
				if ((!el.getDisplayName().equals("RGB"))&&(!el.getDisplayName().equals("Colori campione"))){
					chooser.removeChooserPanel(el);
				}
			}
			    
			    
			    ActionListener okListener = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {  
			    	// Aggiungi colore scelto
			        chosenColor = chooser.getColor();
			    	 		        
			    	onOkPressed();
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
	 * Restituisce vero se il colore è stato scelto.
	 * @return
	 */
	public boolean isColorChosen() {
		return isColorChosen;
	}
	
	/**
	 * Rimuove il colore che è stato selezionato al momento.
	 */
	private void resetAllColors() {
		chosenColor = null;
		dotPink.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_pink.png")));
		dotRed.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_red.png")));
		dotOrange.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_orange.png")));
		dotYellow.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_yellow.png")));
		dotBlack.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_black.png")));
		dotGreen.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_green.png")));
		dotBlue.setIcon(new ImageIcon(ColorSelector.class.getResource("res/dot_blue.png")));
	}
	
	/**
	 * Viene chiamato al click dell'ok.
	 * Controlla se il colore è stato scelto, e in questo caso chiude la finestra.
	 */
	private void onOkPressed() {
		if (chosenColor != null) {
			closeWindow();
		}
		else {
			JOptionPane optionPane = new JOptionPane("Selezionare un colore", JOptionPane.WARNING_MESSAGE);
			JDialog dialog = optionPane.createDialog("Attenzione");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true); 
		}
	}
	
	/**
	 * Dichiara che è stato scelto un colore e
	 * chiude la finestra attualmente aperta.
	 */
	private void closeWindow() {
		isColorChosen = true;
		colorChosen(chosenColor);
		dispose();
	}
	
	/**fai override di questo per ottenere colore*/
	public void colorChosen(Color c) {
		
	}

	/**
	 * Chiude il selettore di colori.
	 */
	public void kill() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}

