package main_frame.grid_initializer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;



import grid.*;
import util.colors.ColorPickerResultLabel;

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
	
	private int currentCursor;
	private JButton btnHand, btnColor, btnColorAll;
	private ColorPickerResultLabel btnChosenColor;
	private boolean isGridDraggable = true;
	private boolean areCellsColorable = false;
	private boolean areAllCellsColorable = false;
	private Color chosenColor;

	/**
	 * Carica il pannello con la griglia e i pulsanti necessari.
	 */
	public GridInitializerPanel(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors) {
		
		
		// Pannello generale
		GridRenderPanel grid = new GridRenderPanel(graph, gridConfiguration, Color.BLACK) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void mouseDraggedCallback(MouseEvent evt) {
				if (isGridDraggable)
					super.mouseDraggedCallback(evt);
				if (areCellsColorable)
					mousePressedCallback(evt);
			}

			@Override
			protected void mousePressedCallback(MouseEvent evt) {
				
				// Prende la posizione del click
				super.mousePressedCallback(evt);
				
				// Gestisce la colorazione delle singole celle
				if (areCellsColorable) {
					int c = getCellAtCoordinate(evt.getX(), evt.getY());
					if(c != -1 && !chosenColor.equals(graph.getCell(c).getState())) {
						graph.getCell(c).setState(chosenColor);
						ArrayList<Integer> al = new ArrayList<>();
						al.add(c);
						this.synchWithGraph(al);
					}
				}
				
				// Gestisce la colorazione di tutte le celle
				if (areAllCellsColorable) {
					ArrayList<Integer> al = new ArrayList<>();
					for (int i = 1; i <= graph.getNumNodes(); i++) {
						if(i != -1 && !chosenColor.equals(graph.getCell(i).getState())) {
							graph.getCell(i).setState(chosenColor);
							al.add(i);
						}
					}
					this.synchWithGraph(al);
				}
			}
		};
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(grid);
		changeCursor(-1);
		currentCursor = -1;
		
		
		// Barra laterale
		JPanel sideBar = new JPanel();
		GridLayout gridLayout = new GridLayout(7, 1);
		sideBar.setLayout(gridLayout);
		add(sideBar, BorderLayout.WEST);
		
		// Inizializzazione pulsanti
		btnHand = new JButton(new ImageIcon(GridInitializerPanel.class.getResource("res/hand_open.png")));
		btnColor = new JButton(new ImageIcon(GridInitializerPanel.class.getResource("res/brush.png")));
		btnColorAll = new JButton(new ImageIcon(GridInitializerPanel.class.getResource("res/bucket.png")));
		btnChosenColor = new ColorPickerResultLabel(colors) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onWindowClosed() {
				super.onWindowClosed();
				GridInitializerPanel.this.chosenColor = getColor();
			}
		};

		// Mouse listener
		setMouseListener(sideBar, 0);
		setMouseListener(btnHand, 0);
		setMouseListener(btnColor, 0);
		setMouseListener(btnColorAll, 0);
		setMouseListener(btnChosenColor, 0);
		setMouseListener(grid, -1);
		
		// Pulsante mano
		
		btnHand.setSize(40, 40);
		btnHand.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cambia l'icona del cursore
				changeCursor(-1);
				currentCursor = -1;
				setMouseListener(grid, -1);
				isGridDraggable = true;
				areCellsColorable = false;
				areAllCellsColorable = false;
			}
		});
		sideBar.add(btnHand);
		
		// Pulsante colore
		
		btnColor.setSize(40, 40);
		btnColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cambia l'icona del cursore
				changeCursor(-3);
				currentCursor = -3;
				setMouseListener(grid, currentCursor);
				isGridDraggable = false;
				areCellsColorable = true;
				areAllCellsColorable = false;
				
			}
		});	
		sideBar.add(btnColor);
		
		// Pulsante colora tutto
		
		btnColorAll.setSize(40, 40);
		btnColorAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Cambia l'icona del cursore
				changeCursor(-4);
				currentCursor = -4;
				setMouseListener(grid, currentCursor);
				isGridDraggable = false;
				areCellsColorable = false;
				areAllCellsColorable = true;
			}
		});	
		sideBar.add(btnColorAll);
		
		// Pulsante colore scelto
		
		btnChosenColor.setSize(40,40);
	
		chosenColor = btnChosenColor.getColor();
		sideBar.add(btnChosenColor);
	}
	
	/**
	 * Imposta il listener del mouse.
	 * @param object
	 * @param evt
	 */
	private void setMouseListener(JComponent jComp, int cursorInt) {
		jComp.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent evt) {
				if (cursorInt == 0)
		        changeCursor(Cursor.DEFAULT_CURSOR);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	if (cursorInt == 0)
		        changeCursor(currentCursor);
		    }
		    
		    public void mousePressed(MouseEvent evt) {
		    	if (cursorInt == -1) 
		    	changeCursor(-2);
		    	else changeCursor(currentCursor);
		    }
		    
		    public void mouseReleased(MouseEvent evt) {
		    	changeCursor(currentCursor);
		    }
		
		});
	}
	
	/**
	 * Imposta l'icona del cursore.
	 * @param cursor
	 */
	private void changeCursor(int cursor) {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image;
		Cursor c;
		
		switch (cursor) {
		
		// Cursore mano aperta
		case -1: 
			image = toolkit.getImage(GridInitializerPanel.class.getResource("res/hand_open_small.png"));
			c = toolkit.createCustomCursor(image , new Point(getX(), 
			           getY()), "img");
			setCursor(c);
			break;
			
		// Cursore mano chiusa
		case -2: 
			image = toolkit.getImage(GridInitializerPanel.class.getResource("res/hand_closed_small.png"));
			c = toolkit.createCustomCursor(image , new Point(getX(), 
			           getY()), "img");
			setCursor(c);
			break;
			
		// Cursore colore
		case -3:
			image = toolkit.getImage(GridInitializerPanel.class.getResource("res/brush_small.png"));
			c = toolkit.createCustomCursor(image , new Point(getX(), 
			           getY()), "img");
			setCursor(c);
			break;
			
		// Cursore colora tutto
		case -4:
			image = toolkit.getImage(GridInitializerPanel.class.getResource("res/bucket_small.png"));
			c = toolkit.createCustomCursor(image , new Point(getX(), 
			           getY()), "img");
			setCursor(c);
			break;
			
		// Cursore default
		default:
			setCursor(new Cursor(cursor));
			break;
		}
	}

}
