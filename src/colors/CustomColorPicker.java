package colors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Polygon;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomColorPicker extends JFrame {

	private static final long serialVersionUID = 1L;
	private Color selectedColor;
	
	public CustomColorPicker(List<Color> ColorList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		selectedColor = null;
		JButton btnOK = new JButton("OK");
		JButton btnCANCEL = new JButton("CANCEL");
		
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new FlowLayout());
		midPanel.setBackground(Color.YELLOW);
		midPanel.setBounds(80, 80, 100, 100);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel,BoxLayout.X_AXIS));
		ButtonPanel.setSize(200,200);
		
		CustomPalette palette = new CustomPalette(ColorList);
		palette.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		
		setBounds(50, 50, (int)(palette.getSize().getWidth()+1), (int)(palette.getSize().getHeight()+1));

		
		midPanel.add(palette);
		//midPanel.add("Previw forma")); 	//da fare il prima possibile
		
		ButtonPanel.add(btnOK);
		ButtonPanel.add(btnCANCEL);
		
		add(midPanel,BorderLayout.CENTER);
		add(ButtonPanel,BorderLayout.SOUTH);

		
	}	
	
	
}
