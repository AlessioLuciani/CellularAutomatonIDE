package main_frame.rules_creator;

import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rules.BaseExpressionNode1;
import rules.BaseExpressionNode2;
import rules.ExpressionNode;
import util.IntegerDocument;
import util.colors.ColorPickerResultLabel;

public class EditExpressionPanel extends JPanel {

	/* Componente che mostra la frase della condizione e i campi da compilare*/
	
	private static final long serialVersionUID = 1L;
	static private final String[] TYPE_A = {"Il numero dei vicini di colore ", " è compreso tra ", " e "};
	static private final String[] TYPE_B = {"Il ","° vicino è di colore "};
	static private final String[] TYPE_THEN = {"La cella assume il colore "};
	
	//static final private int height = 10;
	private JLabel colore;
	private JTextField start;
	private JTextField end;
	private JTextField n_vicini;
	private String Type = null;
	private List<Color> availableColors;
	
	private ExpressionNode expr;
	private Color thenColor;
	
	
	public EditExpressionPanel(List<Color> Colors) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//setLayout(new FlowLayout());
		this.availableColors = Colors;
	}
	
	public EditExpressionPanel formatA() {
		Type = "A";
		//colore = new JLabel();colore.setText("   ");colore.setOpaque(true);colore.setBackground(Color.WHITE);
		colore = new ColorPickerResultLabel(availableColors);
		start = new JTextField();
		start.setDocument(new IntegerDocument());
		end = new JTextField();
		end.setDocument(new IntegerDocument());
		add(new JLabel(TYPE_A[0]));
		add(colore);
		add(new JLabel(TYPE_A[1]));
		add(start);
		add(new JLabel(TYPE_A[2]));
		add(end);
		return this;
	}
	
	public EditExpressionPanel formatB() {
		Type = "B";
		n_vicini = new JTextField();
		n_vicini.setDocument(new IntegerDocument());
		colore = new ColorPickerResultLabel(availableColors);
		add(new JLabel(TYPE_B[0]));
		add(n_vicini);
		add(new JLabel(TYPE_B[1]));
		add(colore);
		return this;
	}
	
	public EditExpressionPanel formatThen() {
		Type = "THEN";
		colore = new ColorPickerResultLabel(availableColors);
		add(new JLabel(TYPE_THEN[0]));
		add(colore);
		return this;	
	}
	
	public ExpressionNode getExpr() {
		return expr;
	}
	
	public Color getThenColor() {
		return thenColor;
	}
	
	/**crea campi expr/thenColor*/
	public void buildNode() {
		if(Type.equals("A")) {
			int a = Integer.parseInt(start.getText());
			int b = Integer.parseInt(end.getText());
			Color c = colore.getBackground(); //TODO: metti colore!
			expr = new BaseExpressionNode1(a, b, c);
		}
		if(Type.equals("B")) {
			int k = Integer.parseInt(n_vicini.getText());
			Color c = colore.getBackground(); //TODO: metti colore!
			expr = new BaseExpressionNode2(k, c);
		}
		if(Type.equals("THEN")) {
			thenColor = colore.getBackground(); //TODO: metti colore!
		}
	}
	
	/**controlla se tutti i campi del form sono completati*/
	public boolean isCompleted() {
		if(Type.equals("A")) 
			return !start.getText().equals("") && !end.getText().equals("") && !colore.getText().equals("");
		if(Type.equals("B")) 
			return !n_vicini.getText().equals("") && !colore.getText().equals("");
		if(Type.equals("THEN")) 
			return !colore.getText().equals("");
		return false;
	}
	
	public String getType() {
		return Type;
	}	
}
