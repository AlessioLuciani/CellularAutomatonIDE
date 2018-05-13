package colors;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jdk.jfr.SettingControl;





public class CircolarColorLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private int dim;
	private boolean isClicked;
	private boolean Entered;
	private boolean Exited;
	private static double opacity = 0.50; //percentuale di opacità
	
	public CircolarColorLabel(Color color,int dim) {
		this.color = color;
		isClicked = false;
		Exited = false;
		Entered = false;
		setSize(dim,dim);
		this.dim = dim;
		setMaximumSize(new Dimension(dim, dim));
		setMinimumSize(new Dimension(dim, dim));
		setBounds(0, 0, dim, dim);
		setVisible(true);
		
	}
	
	public CircolarColorLabel(Color color,int x, int y,int dim) {
		this.color = color;
		isClicked = false;
		setSize(dim, dim);
		this.dim = dim;
		setMaximumSize(new Dimension(dim, dim));
		setMinimumSize(new Dimension(dim,dim));
		setBounds(x, y, dim, dim);
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
	
	public void switchClick() {
		isClicked = !isClicked;
		repaint();		
	}
	
	public void setClick(boolean value) {this.isClicked = value;}
	
	
	
	public CircolarColorLabel Check() {
		Graphics g = getGraphics();
		paint(g);
		return this;
	}
	
	public void setEntered(boolean b) {Entered=b;}
	public void setExited(boolean b) {Exited=b;}
	public Color getColor() {return color;}
	
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, g.getClipBounds().width-3, g.getClipBounds().height-3);
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),(int)(color.getAlpha()*opacity) ));
		
		if (isClicked) {g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),255 ));}
		
		else { 
			if (Entered) {g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),255));}
			if (Exited) {g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),(int)(color.getAlpha()*opacity) ));}
			
		}
		
		
		g.fillOval(0, 0, g.getClipBounds().width-3, g.getClipBounds().height-3);
		
		
	}

	

}
