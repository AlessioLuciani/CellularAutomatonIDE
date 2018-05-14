package main_frame.grid_initializer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import grid.*;
import util.colors.ColorSelector;

/**
 * Inizializzatore del pannello di simulazione.
 * Implementa la griglia di simulazione e permette di cambiare i colori
 * delle celle.
 */
public class GridInitializerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Carica il pannello con la griglia e i pulsanti necessari.
	 */
	public GridInitializerPanel(Graph graph, GridConfiguration gridConfiguration) {
		
		GridRenderPanel grid = new GridRenderPanel(graph, gridConfiguration, Color.BLACK);
		
		BorderLayout layout = new BorderLayout();
		
		setLayout(layout);
		add(grid);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		{
		
			JPanel sideBar = new JPanel();
			GridLayout gridLayout = new GridLayout(7, 1);
			sideBar.setLayout(gridLayout);
			
			{
			
				JButton btnHand = new JButton(new ImageIcon(GridInitializerPanel.class.getResource("res/hand_open.png")));
				btnHand.setSize(40, 40);
				btnHand.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));

						
					}
				});
				sideBar.add(btnHand);
		
				JButton btnBucket = new JButton(new ImageIcon(GridInitializerPanel.class.getResource("res/bucket.png")));
				btnBucket.setSize(40, 40);
				btnBucket.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

						
					}
				});
				sideBar.add(btnBucket);
			
			}
			
			add(sideBar, BorderLayout.WEST);
		}
	}
	


}
