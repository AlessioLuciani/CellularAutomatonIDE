package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
	
	private Graph graph;
	private Color borderColor;
	private GridConfiguration gridConf;
	
	private Image buffer;
	
	private double zoomFactor, deltaX, deltaY; //parametri di zoom e traslazione lungo il pannello
	
	protected MouseMotionListener motionListener; //listener per movimenti mouse sul panel
	protected MouseWheelListener wheelListener; //listener per uso della rotella sul panel
	protected MouseListener mouseListener;
	
	private int tmpX, tmpY; //variabili temporanee necessarie ai listener
	
	/**gli passiamo il grafo da renderizzare e il colore del bordo delle celle (null per non averlo)*/
	public GridRenderPanel(Graph g, GridConfiguration gconf, Color borderColor) {
		graph = g;
		this.gridConf = gconf;
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
	}
	
	@Override
	public void paintComponent(Graphics g) {
		AffineTransform transf = new AffineTransform(); //serve per gestire trasformazioni tipo zoom/traslazione
		transf.scale(zoomFactor, zoomFactor);//trasformazioni processate in LIFO: prima ci spostiamo poi zoomiamo
		transf.translate(deltaX, deltaY); 
		((Graphics2D)g).transform(transf);
		g.drawImage(buffer, 0, 0, buffer.getWidth(null), buffer.getHeight(null), 0, 0, buffer.getWidth(null), buffer.getHeight(null), null);
		//g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	/**inizializza mouse listeners*/
	private void initListeners() {
		
		mouseListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				tmpX = evt.getX();
				tmpY = evt.getY();
			}
		};
		
		motionListener = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) { //trascinando il mouse si sposta l'immagine
				double dx = (evt.getX() - tmpX)/zoomFactor; //calcola spostamento su x e y
				double dy = (evt.getY() - tmpY)/zoomFactor;
				tmpX = evt.getX();
				tmpY = evt.getY();
				
				deltaX = Math.max(Math.min(0, deltaX + dx), -buffer.getWidth(null) + gridConf.getLen()); //aggiorna spostamenti globali
				deltaY = Math.max(Math.min(0, deltaY + dy), -buffer.getHeight(null) + gridConf.getLen());
	
				GridRenderPanel.this.invalidate();
				GridRenderPanel.this.repaint();
				if(GridRenderPanel.this.getParent() != null) {
					GridRenderPanel.this.getParent().invalidate();
					GridRenderPanel.this.getParent().repaint();
				}
			}
		};
		
		wheelListener = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent evt) {
				if(-evt.getUnitsToScroll() > 0)
					zoomFactor *= 1.3;
				if(-evt.getUnitsToScroll() < 0)
					zoomFactor /= 1.3;
				GridRenderPanel.this.invalidate();
				GridRenderPanel.this.repaint();
				GridRenderPanel.this.getParent().invalidate();
				GridRenderPanel.this.getParent().repaint();
			}
		};
	}
}
