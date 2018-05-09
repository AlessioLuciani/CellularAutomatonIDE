package colors;

import java.awt.Color;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class MyChooser extends JColorChooser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyChooser(List<Color> colorList) {
		super();		
		for (AbstractColorChooserPanel panel : getChooserPanels()) this.removeChooserPanel(panel);
		addPalette();
	}
	
	public void addPalette(){
		addChooserPanel(new MyChooserPanel());
	}
	
	
	
}
