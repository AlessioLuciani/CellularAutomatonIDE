package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**panel che renderizza una certa griglia */
public class GridRenderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Graph graph;
	private Color borderColor;
	
	private Image buffer;
	
	/**gli passiamo il grafo da renderizzare e il colore del bordo delle celle (null per non averlo)*/
	public GridRenderPanel(Graph g, GridConfiguration gconf, Color borderColor) {
		graph = g;
		this.borderColor = borderColor;
		buffer = new BufferedImage(gconf.getBufferImageWidth(), gconf.getBufferImageHeight(), BufferedImage.TYPE_INT_RGB);
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
		g.drawImage(buffer, 0, 0, buffer.getWidth(null), buffer.getHeight(null), 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
}
