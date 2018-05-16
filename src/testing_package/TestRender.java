package testing_package;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class TestRender extends DefaultListCellRenderer {
	@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
    {
        //JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value instanceof JLabel)
        {
            this.setText(((JLabel)value).getText());
            this.setBackground(((JLabel)value).getBackground());
            this.setSize(new Dimension(10, 20));
        }
        return this;
    }
}
