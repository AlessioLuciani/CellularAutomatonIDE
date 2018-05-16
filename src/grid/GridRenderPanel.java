package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**panel che renderizza una certa griglia (gestendo anche zoom e scrolling) */
public class GridRenderPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final double BASE_ZOOM = 0.45; //1.3;
	private Graph graph;
	private Color borderColor;
	
	private Image buffer;
	
	private double zoomFactor, deltaX, deltaY; //parametri di zoom e traslazione lungo il pannello
	
	protected MouseMotionListener motionListener; //listener per movimenti mouse sul panel
	protected MouseWheelListener wheelListener; //listener per uso della rotella sul panel
	protected MouseListener mouseListener;
	
	private int tmpX, tmpY; //variabili temporanee necessarie ai listener
	
	/**gli passiamo il grafo da renderizzare e il colore del bordo delle celle (null per non averlo)*/
	public GridRenderPanel(Graph g, GridConfiguration gconf, Color borderColor) {
		graph = g;
		this.borderColor = borderColor;
		buffer = new BufferedImage(gconf.getBufferImageWidth(), gconf.getBufferImageHeight(), BufferedImage.TYPE_INT_RGB);
		this.setBounds(0, 0, gconf.getBufferImageWidth(), gconf.getBufferImageHeight());
		
		zoomFactor = 1;
		deltaX = 0;
		deltaY = 0;
		
		initListeners();
		this.addMouseMotionListener(motionListener);
		this.addMouseWheelListener(wheelListener);
		this.addMouseListener(mouseListener);
		
		synchWithGraph();
	}
	
	/**metodo da chiamare nel caso in cui gli stati dei nodi siano stati modificati: ridisegna TUTTI i nodi del grafo sul buffer (che poi sarà stampato a video)*/
	public void synchWithGraph() {
		graph.drawGraph(buffer.getGraphics(), borderColor);
		this.invalidate();
		this.repaint();
	}
	
	/**ridisegna solo alcune celle del grafo sul buffer, e poi lo ristampa*/
	public void synchWithGraph(ArrayList<Integer> inds) {
		for(int ind : inds)
			graph.getCell(ind).render(buffer.getGraphics(), borderColor);
		this.invalidate();
		this.repaint();
		if(GridRenderPanel.this.getParent() != null) {
			GridRenderPanel.this.getParent().invalidate();
			GridRenderPanel.this.getParent().repaint();
		}	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(zoomFactor < 1)
			((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AffineTransform transf = new AffineTransform(); //serve per gestire trasformazioni tipo zoom/traslazione
		transf.scale(zoomFactor, zoomFactor);//trasformazioni processate in LIFO: prima ci spostiamo poi zoomiamo
		transf.translate(deltaX, deltaY); 
		((Graphics2D)g).transform(transf);
		g.drawImage(buffer, 0, 0, buffer.getWidth(null), buffer.getHeight(null), 0, 0, buffer.getWidth(null), buffer.getHeight(null), null);
		//g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	/**restituisce indice cella a delle determinate coordinate (prese sul panel, con i listener del mouse); -1 se non esiste*/
	public int getCellAtCoordinate(int x, int y) {
		double tmpX = x, tmpY = y;
		tmpX /= zoomFactor; //togliamo lo zoom
		tmpY /= zoomFactor;
		tmpX -= deltaX; //togliamo le traslazioni
		tmpY -= deltaY;
		if(tmpX >= buffer.getWidth(null) || tmpY >= buffer.getHeight(null) || tmpX < 0 || tmpY < 0) return -1;
		return graph.getCellAtCoordinate((int)tmpX, (int)tmpY);
	}
	
	protected void mousePressedCallback(MouseEvent evt) {
		tmpX = evt.getX();
		tmpY = evt.getY();
		
		/*int c = getCellAtCoordinate(evt.getX(), evt.getY());
		if(c != -1) {
			graph.getCell(c).setState(Color.RED);
			ArrayList<Integer> al = new ArrayList<>();
			al.add(c);
			GridRenderPanel.this.synchWithGraph(al);
		}*/
	}
	
	protected void mouseDraggedCallback(MouseEvent evt) {
		double dx = (evt.getX() - tmpX)/zoomFactor; //calcola spostamento su x e y
		double dy = (evt.getY() - tmpY)/zoomFactor;
		tmpX = evt.getX();
		tmpY = evt.getY();
		
		//calcolati il valore minimo che possono raggiungere i delta (sono sempre negativi)
		double mindx = Math.min(-((double)buffer.getWidth(null) - (double)GridRenderPanel.this.getWidth()/zoomFactor), 0);
		double mindy = Math.min(-((double)buffer.getHeight(null) - (double)GridRenderPanel.this.getHeight()/zoomFactor), 0);
		
		deltaX = Math.max(Math.min(0, deltaX + dx), Math.min(mindx, deltaX)); //aggiorna spostamenti globali
		deltaY = Math.max(Math.min(0, deltaY + dy), Math.min(mindy, deltaY));

		GridRenderPanel.this.invalidate();
		GridRenderPanel.this.repaint();
		if(GridRenderPanel.this.getParent() != null) {
			GridRenderPanel.this.getParent().invalidate();
			GridRenderPanel.this.getParent().repaint();
		}	
	}
	
	protected void mouseWheelMovedCallback(MouseWheelEvent evt) {
		int unit = Math.abs(evt.getUnitsToScroll());
		
		if(-evt.getUnitsToScroll() > 0)
			zoomFactor *= BASE_ZOOM * (double)unit;
		if(-evt.getUnitsToScroll() < 0)
			zoomFactor /= BASE_ZOOM * (double)unit;
		GridRenderPanel.this.invalidate();
		GridRenderPanel.this.repaint();
		GridRenderPanel.this.getParent().invalidate();
		GridRenderPanel.this.getParent().repaint();	
	}
	
	/**inizializza mouse listeners*/
	private void initListeners() {
		
		mouseListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				mousePressedCallback(evt);
			}
		};
		
		motionListener = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) { //trascinando il mouse si sposta l'immagine
				mouseDraggedCallback(evt);
			}
		};
		
		wheelListener = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent evt) {
				mouseWheelMovedCallback(evt);
			}
		};
	}
}