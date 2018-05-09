package colors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;



public class CircolarColorLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean isClicked;
	
	public CircolarColorLabel(Color color) {
		this.color = color;
		isClicked = false;
		setSize(30, 30);
		setMaximumSize(new Dimension(30, 30));
		setMinimumSize(new Dimension(30, 30));
		setBounds(0, 0, 50, 50);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CircolarColorLabel label = (CircolarColorLabel)arg0.getSource();
				
				isClicked = !isClicked;
				label.repaint();
				System.out.println("Clicked "+isClicked);
				
			}
		});
		
	}
	
	public CircolarColorLabel(Color color,int x, int y) {
		this.color = color;
		isClicked = false;
		setSize(30, 30);
		setMaximumSize(new Dimension(30, 30));
		setMinimumSize(new Dimension(30, 30));
		setBounds(x, y, 50, 50);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CircolarColorLabel label = (CircolarColorLabel)arg0.getSource();
				
				isClicked = !isClicked;
				label.repaint();
				System.out.println("Clicked "+isClicked);
				
			}
		});
		
	}
	
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
	}
	
	public CircolarColorLabel Check() {
		Graphics g = getGraphics();
		paint(g);
		return this;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(color);
		//forma tonda
		//g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		
		//forma quadrata smussata
		g.fillRoundRect(0, 0, g.getClipBounds().width, g.getClipBounds().height, 10, 10);
		if (isClicked) {
			
			//bordo alla selezione
			g.setColor(Color.RED);
			//g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			g.drawRoundRect(0, 0, g.getClipBounds().width, g.getClipBounds().height, 15, 15);
			
			//X alla selezione
			//g.drawLine(0, 0,g.getClipBounds().width , g.getClipBounds().height);
			//g.drawLine(g.getClipBounds().width, 0, 0, g.getClipBounds().height);
		}
		
		
	}

}
