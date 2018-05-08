package grid;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.IntegerDocument;

/**JPanel che permette la creazione di una grid configuration*/
public class GridConfCreator extends JPanel {

	private static final long serialVersionUID = 5921439522417418982L; //non ci dovrebbe servire
	
	private int minLen, maxLen, minX, maxX, minY, maxY;
	private JTextField lenField, xField, yField;
	private JComboBox<String> shapePicker;
	private CellForm [] forms = {CellForm.SQUARE, CellForm.HEXAGON, CellForm.TRIANGLE};
	
	/**crea grid creator, bisogna passare limiti per i campi numerici*/
	public GridConfCreator(int minLen, int maxLen, int minX, int maxX, int minY, int maxY) {
		this.minLen = minLen;
		this.maxLen = maxLen;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		String [] names = {"Quadrato", "Esagono", "Triangolo"};
		
		GridLayout mainLayout = new GridLayout(4, 1);
		this.setLayout(mainLayout);
		
		JPanel tmpPanel = new JPanel(new FlowLayout()); //panel di appoggio: usando flow layout non vengono text-field abnormi
		tmpPanel.add(new JLabel("Lato singola cella:")); //metto label <-> oggetto su ogni riga
		lenField = new JTextField(3);
		lenField.setDocument(new IntegerDocument());
		lenField.setText(""+(minLen+maxLen)/2);
		tmpPanel.add(lenField);
		this.add(tmpPanel);
		
		JPanel tmpPanel2 = new JPanel(new FlowLayout()); //identico a sopra ma con altro campo
		tmpPanel2.add(new JLabel("Numero celle su asse x:"));
		xField = new JTextField(3);
		xField.setDocument(new IntegerDocument());
		xField.setText(""+(minX+maxX)/2);
		tmpPanel2.add(xField);
		this.add(tmpPanel2);
		
		JPanel tmpPanel3 = new JPanel(new FlowLayout()); //identico a sopra ma con altro campo
		tmpPanel3.add(new JLabel("Numero celle su asse y:"));
		yField = new JTextField(3);
		yField.setDocument(new IntegerDocument());
		yField.setText(""+(minY+maxY)/2);
		tmpPanel3.add(yField);
		this.add(tmpPanel3);
		
		JPanel tmpPanel4 = new JPanel(new FlowLayout());
		tmpPanel4.add(new JLabel("Forma cella:")); //identico a sopra ma con altro campo
		shapePicker = new JComboBox<String>(names);
		tmpPanel4.add(shapePicker);
		this.add(tmpPanel4);
		
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createRaisedBevelBorder()));
	}
	
	public GridConfCreator() {
		this(1, 10, 1, 1000, 1, 1000); //valori di default (da aggiustare)
	}
	
	/**restituisce grid configuration creata con la gui*/
	public GridConfiguration getGridConfiguration() {
		int len = Math.min(maxLen, Math.max(Integer.parseInt(lenField.getText()), minLen));
		int lenx = Math.min(maxX, Math.max(Integer.parseInt(xField.getText()), minX));
		int leny = Math.min(maxY, Math.max(Integer.parseInt(yField.getText()), minY));
		return new GridConfiguration(forms[shapePicker.getSelectedIndex()], len, lenx, leny);
	}
	
}
