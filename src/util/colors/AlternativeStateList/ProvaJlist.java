package util.colors.AlternativeStateList;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import util.colors.ColoredRGBLabel;



public class ProvaJlist {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		
		JLabel lbl = new ColoredRGBLabel(Color.ORANGE);
		JLabel lbl1 = new ColoredRGBLabel(Color.RED);
		JLabel lbl2 = new ColoredRGBLabel(Color.BLUE);
		JLabel lbl3 = new ColoredRGBLabel(Color.GREEN);
		
		JList<JLabel> labelList = new JList<>();
		ListModel<JLabel> listModel = new DefaultListModel<>();
		labelList.setModel(listModel);
		labelList.setFixedCellHeight(30);
		labelList.setCellRenderer(new JLabelJListRender());
		labelList.setVisible(true);
		labelList.setSize(100,100);
		
		
		DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
		model.addElement( lbl);
		model.addElement( lbl1);
		model.addElement( lbl2);
		model.addElement( lbl3);
		model.addElement( lbl);
		model.addElement( lbl1);
		model.addElement( lbl2);
		model.addElement( lbl3);
		model.addElement( lbl);
		model.addElement( lbl1);
		model.addElement( lbl2);
		model.addElement( lbl3);
		
		JScrollPane sp = new JScrollPane(labelList);
		frame.add(sp);
		frame.setVisible(true);

	}

}
