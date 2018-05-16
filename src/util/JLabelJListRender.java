package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**render per jlabel in jlist*/
public class JLabelJListRender  extends DefaultListCellRenderer {
	
	private static final long serialVersionUID = 1L;

	@Override
    public Component getListCellRendererComponent(JList<?>list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		this.setText(((JLabel)value).getText());
        if(!isSelected) {
            this.setForeground(((JLabel)value).getForeground());
            this.setBackground(((JLabel)value).getBackground());
        } else {
        	this.setForeground(Color.BLUE);
        	this.setBackground(Color.WHITE);
        }
        return this;
    }
}
