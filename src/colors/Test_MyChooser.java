package colors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class Test_MyChooser {
  public static void main(String[] argv) {
	JFrame frame = new JFrame();
	frame.setBounds(100, 100, 500, 500);
	frame.setLayout(new BorderLayout());
	 
    JColorChooser chooser = new MyChooser(new ArrayList<>());
    chooser.setVisible(true);	
	
	ActionListener ok = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	ActionListener cancel = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	JDialog diagol = MyChooser.createDialog(frame, "tit", false, chooser, ok , cancel );
    diagol.setVisible(true);
    frame.setVisible(true);
  }
}

