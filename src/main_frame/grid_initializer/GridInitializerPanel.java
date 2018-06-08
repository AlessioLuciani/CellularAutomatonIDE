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
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import genetics.interesting_conf.Optimizer;
import genetics.interesting_conf.graphic.InterestingConfInputFrame;
import grid.*;
import rules.Rule;
import util.CustomProgressBar;
import util.colors.ColorPickerResultLabel;

/**
 * Inizializzatore del pannello di simulazione.
 * Implementa la griglia di simulazione e permette di cambiare i colori
 * delle celle.
 */
public class GridInitializerPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int currentCursor;
	protected JButton btnHand, btnColor, btnColorAll;
	protected ColorPickerResultLabel btnChosenColor;
	protected JButton btnColorRandom;
	private boolean isGridDraggable = true;
	private boolean areCellsColorable = false;
	private boolean areAllCellsColorable = false;
	private Color chosenColor;
	protected JPanel sideBar;

	protected JButton btnFindConfiguration;
	protected JButton btnFindConfParams;
	
	protected GridRenderPanel grid;
	
	protected Graph graph;
	 
	protected int opK, opCycLen, opSizeX, opSizeY; //parametri per l'optimizer
	protected Optimizer optimizer; //trova configurazione interessante
	protected CustomProgressBar optProgressBar; //progress bar per mostrare status della configurazione interessante
	
	/**
	 * Carica il pannello con la griglia e i pulsanti necessari.
	 */
	public GridInitializerPanel(Graph graph, GridConfiguration gridConfiguration, ArrayList<Color> colors, ArrayList<Rule> rules) {
		
		opK = 1000; //per sicurezza diamo parametri per l'optimizer
		opCycLen = 3;
		opSizeX = 10;
		opSizeY = 10;
		
		this.graph = graph;
		
		// Pannello generale
		
		grid = new GridRenderPanel(graph, gridConfiguration, Color.BLACK) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void mouseDraggedCallback(MouseEvent evt) {
				//synchronized(this) {
					if (isGridDraggable)
						super.mouseDraggedCallback(evt);
					if (areCellsColorable)
						mousePressedCallback(evt);
				//}
			}

			@Override
			protected void mousePressedCallback(MouseEvent evt) {
				synchronized(this) {
					// Prende la posizione del click
					super.mousePressedCallback(evt);
					
					// Gestisce la colorazione delle singole celle
					if (areCellsColorable) {
						int c = getCellAtCoordinate(evt.getX(), evt.getY());
						if(c != -1 && !chosenColor.equals(graph.getCell(c).getState())) {
							Color oldColor = graph.getCell(c).getState();
							graph.getCell(c).setState(chosenColor);
							ArrayList<Integer> al = new ArrayList<>();
							al.add(c);
							this.synchWithGraph(al);
							GridInitializerPanel.this.onCellColored(graph, c, oldColor);
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
			}
		};
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(grid);
		
		changeCursor(-1);
		currentCursor = -1;
		
		
		// Barra laterale
		sideBar = new JPanel();
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
		btnColorRandom = new JButton("Random");
		btnFindConfiguration = new JButton("Cerca...");
		btnFindConfParams = new JButton("<html>Cerca <br> params.</html>");

		// Mouse listener
		setMouseListener(sideBar, 0);
		setMouseListener(btnHand, 0);
		setMouseListener(btnColor, 0);
		setMouseListener(btnColorAll, 0);
		setMouseListener(btnChosenColor, 0);
		setMouseListener(btnColorRandom, 0);
		setMouseListener(btnFindConfiguration, 0);
		setMouseListener(btnFindConfParams, 0);
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
		
		btnChosenColor.setSize(40, 40);
		chosenColor = btnChosenColor.getColor();
		sideBar.add(btnChosenColor);
		
		// Pulsante colori casuali
		
		btnColorRandom.setSize(40, 40);
		btnColorRandom.setFocusable(false);
		btnColorRandom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized(GridInitializerPanel.this.grid) { //ci sincronizziamo con la griglia perchè ci sono altri modi in cui potrebbe essere modificata
					Random random = new Random();
					int numCells = random.nextInt(graph.getNumNodes() - 1) + 1; //prendiamo un po' di celle a caso
					HashSet<Integer> randomCells = new HashSet<>();
					for (int i = 0; i < numCells; i++) {
						randomCells.add(random.nextInt(graph.getNumNodes() - 1) + 1);
					}
					for (int cellIndex: randomCells) { //diamo colori a caso
						Color randomColor = colors.get(random.nextInt(colors.size()));
						Cell cell = graph.getCell(cellIndex);
						if (!randomColor.equals(cell.getState())) 
							cell.setState(randomColor);
					}
					grid.synchWithGraph(randomCells); //risincronizziamo
				}
			}
		});
		sideBar.add(btnColorRandom);
		
		//bottone per trovare una configurazione interessante usando algoritmo genetico
		btnFindConfiguration.setSize(40, 40);
		btnFindConfiguration.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(btnFindConfiguration.isEnabled()) {
					btnFindConfiguration.setEnabled(false);
					
					ArrayList<Rule> copyRules = new ArrayList<Rule>();
					for(Rule r : rules) 
						copyRules.add(r.copy());
					
					optProgressBar = new CustomProgressBar("Sto cercando una configurazione...", 0, 100, "") { //creo progress bar mentre si fa ottimizzazione
						@Override
						public void taskCancelled() {
							if(optimizer != null)
								optimizer.interrupt();
						}
					};
					
					optimizer = new Optimizer(new ArrayList<>(colors), copyRules, graph.copy(), opK, opCycLen, opSizeX, opSizeY) {
						@Override
						public void evolutionCompleted() {
							synchronized(GridInitializerPanel.this.grid) { //ci sincronizziamo con la griglia perchè sono altri modi in cui potrebbe essere modificata
								if(GridInitializerPanel.this.graph != null) super.getBestGene().setGraph(GridInitializerPanel.this.graph); //quando l'evoluzione è completa settiamo il grafo
								if(grid != null) grid.synchWithGraph();
								if(btnFindConfiguration != null) SwingUtilities.invokeLater(() -> btnFindConfiguration.setEnabled(true));
								if(optProgressBar != null) SwingUtilities.invokeLater(() -> optProgressBar.dispose());
							}
						}
						
						@Override
						public void iterationUpdated(int iter) {
							synchronized(optProgressBar) { //ad ogni iterazione aggiorniamo la progress bar
								optProgressBar.updateValue(iter);
							}
						}
						
						@Override
						public void bestGeneUpdated() {
							synchronized(optProgressBar) { //quando troviamo una soluzione migliore lo comunichiamo
								optProgressBar.updateMessage("Migliore soluzione attuale: "+super.bestFitness);
							}	
						}
					};
					
					optimizer.start(); //lancia optimizer su altro thread
				}
			}
		});
		sideBar.add(btnFindConfiguration);
		
		btnFindConfParams.setSize(40, 20);
		btnFindConfParams.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("serial")
				InterestingConfInputFrame frame = new InterestingConfInputFrame(opSizeX, opSizeY, opCycLen+1, opK) {
					@Override
					public void onClosed(int sx, int sy, int cl, int k) {
						opSizeX = sx;
						opSizeY = sy;
						opCycLen = cl;
						opK = k;
					}
				};
				frame.setBounds(0, 0, 300, 300);
			}
		});
		sideBar.add(btnFindConfParams);
	}
	
	/**callback richiamata quando si colora una cella del grafo (col pennello)*/
	protected void onCellColored(Graph graph, int cell, Color oldColor) {
		
	}
	
	/**
	 * Imposta il listener del mouse.
	 * @param object
	 * @param evt
	 */
	protected void setMouseListener(JComponent jComp, int cursorInt) {
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
		    	switch (cursorInt) {
		    	case 0:
		    		changeCursor(Cursor.DEFAULT_CURSOR);
		    		break;
		    	case -1:
		    		changeCursor(-2);
		    		break;
		    	default:
		    		changeCursor(currentCursor);
		    		break;
		    	}
		    }
		    
		    public void mouseReleased(MouseEvent evt) {
		    	if (cursorInt == 0)
		    		changeCursor(Cursor.DEFAULT_CURSOR);
		    	else 
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
	
	/**
	 * Chiude eventuali pannelli rimasti aperti.
	 */
	public void onPanelClosed() {
		if(optimizer != null && optimizer.isAlive())
			optimizer.interrupt();
		if(optProgressBar != null)
			optProgressBar.dispose();
		grid.onClosing();
		btnChosenColor.closeWindow();
	}
}
