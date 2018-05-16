package util.colors;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;

import util.StaticUtil;

public class ColoredRGBLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	
	public ColoredRGBLabel(Color color) {
		this.color = color;
		setVisible(true);
		setOpaque(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (int)g.getClipBounds().getWidth();
		int y = (int)g.getClipBounds().getHeight();
		
		g.setColor(color);
		g.fillRect(0, 0, y, y);
		//g.fillRect(0, 0, Label2.this.getHeight(), Label2.this.getHeight());g.setColor(Color.BLACK);
		//g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		Font f = new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,y/2);
		g.setFont(f);
		//int centeredY = (int)(fm.getAscent()+fm.getDescent())/2;
		g.drawString(StaticUtil.colorToRgbString(color), y+5,y-(y/4));
		//g.drawLine(0, y/2, x, y/2);
		g.drawRect(0, 0, x-1, y-1);
	}

}
