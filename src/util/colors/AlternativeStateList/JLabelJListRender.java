package util.colors.AlternativeStateList;


import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import util.colors.ColoredRGBLabel;

/**render per jlabel in jlist*/
public class JLabelJListRender extends JLabel implements ListCellRenderer<JLabel> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected,
			boolean cellHasFocus) {
		ColoredRGBLabel lbl = (ColoredRGBLabel)value;
		lbl.setText(" ");
		lbl.setOpaque(true);
		lbl.setVisible(true);
		lbl.setSelected(false);
		if (isSelected) {lbl.setSelected(true);}
		
		return lbl;
	}
	
}
