package util.colors;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import util.StaticUtil;

public class ColoredRGBLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean isSelected;
	
	public ColoredRGBLabel(Color color) {
		this.color = color;
		setName(StaticUtil.colorToRgbString(color));
		setVisible(true);
		setOpaque(true);
	}
	
	public ColoredRGBLabel(Color color, String name) {
		this.color = color;
		setName(name);
		setVisible(true);
		setOpaque(true);
	}
	
	public void setSelected(boolean value) {isSelected=value;}
	public Color getColor() {return color;}
	
	@Override
	protected void paintComponent(Graphics g) {
		//per l'heigth se non vogliamo farla scalare dobbiamo usare quella della Label this.getHeigth
		//altrimentri usiamo quella della Graphics g.getClipBounds().getHeight();
		super.paintComponent(g);
		int x = (int)g.getClipBounds().getWidth();
		int y = (int)getHeight();
		
		//controllo selezione altrimenti gradiente
		if (isSelected) {g.setColor(Color.LIGHT_GRAY);}
		else {
			int diff = 0;
			while((y-diff)>0) {
				g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 30));
				g.fillRect(0, 0, y-diff, y-diff);
				diff+=2;
		}
		
		//print della scritta rgb
		g.setColor(color);}
		Font f = new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,y/2);
		g.setFont(f);
		g.drawString(StaticUtil.colorToRgbString(color), y+5,y-(y/4));
		g.drawRect(0, 0, x-1, y-1);
	}

}
