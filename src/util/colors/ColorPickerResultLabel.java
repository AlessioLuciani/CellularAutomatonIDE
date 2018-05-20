package util.colors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColorPickerResultLabel extends JLabel{

	/**
	 * Label che avvia una palette e setta come background il colore ritornato
	 */
	private static final long serialVersionUID = 1L;
	private List<Color> availableColors;
	JFrame colorPicker;
	public ColorPickerResultLabel(List<Color> availableColors) {
		this.availableColors = availableColors;
		
		addMouseListener(onColorClicked);
		setSize(10,20);
		setText("   ");
		setOpaque(true);
		if(availableColors.size() > 0)
			setBackground(availableColors.get(0));
	}
	
	MouseAdapter onColorClicked = new MouseAdapter() {		
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			if(ColorPickerResultLabel.this.contains(e.getPoint())) {
				colorPicker = new CustomColorPicker(availableColors);
				colorPicker.addWindowListener(onCloseWindow);
				colorPicker.setVisible(true);
			}
		}
	};
	
	WindowAdapter onCloseWindow = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			super.windowClosing(e);
			setColor(((CustomColorPicker)e.getSource()).getSelectedColor());
			onWindowClosed();
		}			
	};
	
	protected void onWindowClosed() {}
	
	public void closeWindow() {
		if (colorPicker != null) colorPicker.dispose();
	}
	
	private void setColor(Color color){
		if (color==null) return;
		this.setBackground(color);
		this.repaint();
	}
	
	public Color getColor() {return this.getBackground();}		
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0,0, (int)g.getClipBounds().getWidth()-1, (int)g.getClipBounds().getHeight()-1);
	}
}