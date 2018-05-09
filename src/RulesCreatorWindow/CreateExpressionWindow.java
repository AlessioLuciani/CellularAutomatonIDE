package RulesCreatorWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import rules.AndNode;
import rules.ExpressionNode;
import rules.NotNode;
import rules.OrNode;
import rules.Rule;

public class CreateExpressionWindow extends JFrame {

	/* Form che consente di aggiungere nuove espressioni */
	
	private static final long serialVersionUID = 1L;

	private JPanel ButtonPanel;	//pannello bottoni
	private JButton btnA;
	private JButton btnB;
	private JButton btnAND;
	private JButton btnOR;
	private JButton btnNOT;
	private JButton btnTHEN;
	private JButton btn_add;
	private List exp_list;
	private EditExpressionPanel edit_panel = null;	//pannello a comparsa per inserire parametri
	private JPanel BottomGroupPanel ;
	private boolean flag_then;
	
	private ArrayList<ExpressionNode> tree;
	Color thenColor;
	
	public CreateExpressionWindow(int x, int y, int width, int height) {
		super();
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		//Inizializzazione Attributi
		exp_list = new List();
		exp_list.setMultipleMode(true);
		ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
		edit_panel = new EditExpressionPanel();
		btnA = new JButton("A");btnA.setName("btnA");
		btnB = new JButton("B");btnB.setName("btnB");
		btnAND = new JButton("AND");btnAND.setName("btnAND");
		btnOR = new JButton("OR");btnOR.setName("btnOR");
		btnNOT = new JButton("NOT");btnNOT.setName("btnNOT");
		btn_add = new JButton("+");btn_add.setName("btn_add");
		btnTHEN = new JButton("THEN");btnTHEN.setName("btnTHEN");check_then();
		flag_then = false;
		tree = new ArrayList<ExpressionNode>();
		
		//Gestione click bottoni
		btnA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				edit_panel = new EditExpressionPanel().formatA();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				check_then();
				revalidate();
				
			}});
		
		btnB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				edit_panel = new EditExpressionPanel().formatB();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				check_then();
				revalidate();
			}});
		
		btnAND.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {concatenate("AND");check_then();}});
		
		btnOR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {concatenate("OR");check_then();}});
		
		btnNOT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {concatenate("NOT");check_then();}});
		
		btnTHEN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flag_then = true;
				btnTHEN.setEnabled(false);
				btn_add.setText("Save");
				disable_buttons();
				edit_panel = new EditExpressionPanel().formatThen();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				revalidate();
			}});
		
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (edit_panel.getType()!=null) {
					if (flag_then) {
						//System.out.println(FinalString());
						thenColor = edit_panel.getThenColor();
						dispose();
					}
					else { 
						edit_panel.buildNode();
						ExpressionNode e = edit_panel.getExpr();
						tree.add(e);
						exp_list.add(e.toString());
						//exp_list.add(edit_panel.create_String());
					}
					
				}
				check_then();
			}});
		
		
		
		// il BottomPanel contiene ButtonPanel e edit_panel
		BottomGroupPanel = new JPanel();
		BottomGroupPanel.setLayout(new BoxLayout(BottomGroupPanel, BoxLayout.PAGE_AXIS));
		
		ButtonPanel.add(btnA, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btnB, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btnAND, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btnOR, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btnNOT, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btnTHEN, "cell 0 0,alignx center,aligny top");
		ButtonPanel.add(btn_add, "cell 0 0,alignx center,aligny top");
		
		add(exp_list,BorderLayout.CENTER);
		BottomGroupPanel.add(edit_panel);
		BottomGroupPanel.add(ButtonPanel);
		add(BottomGroupPanel,BorderLayout.SOUTH);
	}
	
	//disabilita tutti i pulsanti tranne btnTHEN e btn_add
	private void disable_buttons(){
		for (Component component : ButtonPanel.getComponents()) {
			component.setEnabled(!((component instanceof JButton)
					&&(!(component.getName().equals("btnTHEN")))
					&&(!(component.getName().equals("btn_add")))
					));
			}
		}
	
	//concatena tutte le righe selezionate secondo l'operatore "operand"
	private void concatenate(String operand) {
		//String concat_string = "";
		//String[] items = exp_list.getSelectedItems();
		int [] inds = exp_list.getSelectedIndexes();
	
		if(inds.length == 1) {
			if(operand.equals("NOT")) {
				ExpressionNode tmp = new NotNode(tree.get(inds[0]));
				tree.set(inds[0], tmp);
				exp_list.remove(inds[0]);
				exp_list.add(tmp.toString(), 0);
			}
		} 
		if(inds.length > 1) {
			ExpressionNode act;
			if(operand.equals("AND"))
				act = new AndNode(tree.get(inds[0]), tree.get(inds[1]));
			else
				act = new OrNode(tree.get(inds[0]), tree.get(inds[1]));
			
			for(int i=2; i<inds.length; i++) {
				ExpressionNode tmp;
				if(operand.equals("AND"))
					tmp = new AndNode(act, tree.get(inds[i]));
				else
					tmp = new OrNode(act, tree.get(inds[i]));
				act = tmp;
			}
			
			for(int i=inds.length-1; i>=0; i--) {
				exp_list.remove(inds[i]);
				tree.remove(inds[i]);
			}
			exp_list.add(act.toString());
			tree.add(act);
		}
		/*if (items.length==1) {if (operand.equals("NOT")) concat_string += "NOT ("+ items[0]+")";}
		else if (items.length>1) {
			concat_string = "(";
			for (int i = 0; i < items.length-1; i++) {
				concat_string+= (items[i]+" "+operand+" ");
				exp_list.remove(items[i]);
				//exp_list.remove(i);
			}
			concat_string+=items[items.length-1]+")";
		}
		else return;
		
		exp_list.remove(items[items.length-1]);
		exp_list.add(concat_string);
		System.out.println(concat_string);*/
	}
	
	public Rule getRule() {
		return new Rule(tree.get(0), thenColor);
	}
	
	//private String FinalString() {return "SE "+exp_list.getItem(0)+" ALLORA "+edit_panel.create_String();}
	
	private void check_then() {btnTHEN.setEnabled(exp_list.getItemCount()==1);}
	
}
