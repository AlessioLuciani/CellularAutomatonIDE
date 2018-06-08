package genetics.interesting_conf.graphic;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.IntegerDocument;

/**pannello per prendere input dei parametri da usare per trovare configurazioni interessanti*/
public class InterestingConfInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField sizeXf, sizeYf, cycLenf, kf;
	
	public InterestingConfInputPanel(int defSizeX, int defSizeY, int defCycLen, int defK) {
		JLabel sizeXlbl = new JLabel("Larghezza rettangolo");
		JLabel sizeYlbl = new JLabel("Altezza rettangolo");
		JLabel cycLenlbl = new JLabel("Minimo periodo cicli");
		JLabel klbl = new JLabel("Minimo numero iterazioni");
		
		sizeXf = new JTextField(3); 
		sizeXf.setDocument(new IntegerDocument());
		sizeXf.setText(defSizeX+"");
		sizeYf = new JTextField(3);
		sizeYf.setDocument(new IntegerDocument());
		sizeYf.setText(defSizeY+"");
		cycLenf = new JTextField(3); 
		cycLenf.setDocument(new IntegerDocument());
		cycLenf.setText(defCycLen+"");
		kf = new JTextField(3); 
		kf.setDocument(new IntegerDocument());
		kf.setText(defK+"");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(sizeXlbl);
		p1.add(sizeXf);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(sizeYlbl);
		p2.add(sizeYf);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(cycLenlbl);
		p3.add(cycLenf);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		p4.add(klbl);
		p4.add(kf);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
	}
	
	public int getSizeX() {
		if(sizeXf.getText().equals("")) return 1;
		return Math.max(1, Integer.parseInt(sizeXf.getText()));
	}
	
	public int getSizeY() {
		if(sizeYf.getText().equals("")) return 1;
		return Math.max(1, Integer.parseInt(sizeYf.getText()));
	}
	
	public int getCycLen() {
		if(cycLenf.getText().equals("")) return 0;
		return Math.max(0, Integer.parseInt(cycLenf.getText()) - 1);
	}
	
	public int getK() {
		if(kf.getText().equals("")) return 1;
		return Integer.parseInt(kf.getText());
	}
}
