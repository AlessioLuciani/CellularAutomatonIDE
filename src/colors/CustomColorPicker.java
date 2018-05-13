package colors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.StyleSheet.BoxPainter;

public class CustomColorPicker extends JFrame {

	private static final long serialVersionUID = 1L;
	private Color selectedColor;
	
	public CustomColorPicker(List<Color> ColorList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		selectedColor = null;
		JButton btnOK = new JButton("OK");
		JButton btnCANCEL = new JButton("CANCEL");
		
		JPanel ButtonPanel = new JPanel();	//contiene i pulsanti ok e cancel
		JPanel MidPanel = new JPanel();		//contiene la paletta
		CustomPalette palette = new CustomPalette(ColorList);	//paleta dei colori
		
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel,BoxLayout.X_AXIS));
		ButtonPanel.setSize(btnOK.getWidth()+btnCANCEL.getWidth(), btnOK.getHeight());
		
		
		MidPanel.setLayout(null);
		MidPanel.setSize(palette.getWidth(),palette.getHeight());
		System.out.println(MidPanel.getSize());
		

		palette.setVisible(true);
		palette.setLocation(0, 0);
		
		
		
		MidPanel.add(palette);
		//midPanel.add("Previw forma")); 	//da fare il prima possibile
		
		ButtonPanel.add(btnOK);
		ButtonPanel.add(btnCANCEL);
		
		add(MidPanel,BorderLayout.CENTER);
		add(ButtonPanel,BorderLayout.SOUTH);

		setBounds(50, 50,Math.max(MidPanel.getWidth(),ButtonPanel.getWidth()),MidPanel.getHeight()+ButtonPanel.getHeight()+10);
		System.out.println(ButtonPanel.getSize());
		System.out.println(getSize());
	}	
	
	Action OKclick = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(((JButton)e.getSource()).getSize());
			
		}
	};
}
