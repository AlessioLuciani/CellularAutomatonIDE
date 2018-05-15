package util.colors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomColorPicker extends JFrame {
	
	/*
	 * ColorPicker inizializzabile con una lista di colori
	 */

	private static final long serialVersionUID = 1L;
	private Color selectedColor;
	private JButton btnOK;
	private JPanel ButtonPanel;		//contiene i pulsanti ok e cancel
	private JPanel MidPanel;		//contiene la paletta
	private CustomPalette palette;
	
	public CustomColorPicker(List<Color> ColorList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		selectedColor = null;
		btnOK = new JButton("OK");
		btnOK.addActionListener(OKclick);
		btnOK.setSize(83, 26);
		
		ButtonPanel = new JPanel();	//contiene i pulsanti ok e cancel
		MidPanel = new JPanel();		//contiene la paletta
		palette = new CustomPalette(ColorList);	//paleta dei colori
		
		ButtonPanel.setLayout(new GridLayout(1,2));
		ButtonPanel.setSize(btnOK.getWidth(), btnOK.getHeight());
		
		
		//MidPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		MidPanel.setLayout(null);
		MidPanel.setSize(palette.getWidth(),palette.getHeight());
				
		MidPanel.add(palette);
		//midPanel.add("Previw forma")); 	//da fare il prima possibile
		
		ButtonPanel.add(btnOK);
		
		getContentPane().add(MidPanel,BorderLayout.CENTER);
		getContentPane().add(ButtonPanel,BorderLayout.SOUTH);

		setSize(new Dimension(Math.max(MidPanel.getWidth(),ButtonPanel.getWidth())+10,MidPanel.getHeight()+ButtonPanel.getHeight()+32));
	}	
	
	private void setChoosedColor(){this.selectedColor = palette.getSelectedColor();}
	public void colorChosen() {} //da reimplementare
	public Color getSelectedColor() {return selectedColor;} //ritorna il colore selezionato
	private void launchWarning(){JOptionPane.showMessageDialog(this, "Seleziona un Colore", "Nessun colore selezionato", JOptionPane.WARNING_MESSAGE);}
	
	ActionListener OKclick = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setChoosedColor();
			if (selectedColor==null){launchWarning();}
			else{dispose();}
			
		}
	};
	
		
		
}