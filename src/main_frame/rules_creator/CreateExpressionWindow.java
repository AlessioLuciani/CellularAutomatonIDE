package main_frame.rules_creator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/** Form che consente di aggiungere nuove espressioni */
public class CreateExpressionWindow extends JFrame {
	
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
	private java.util.List<Color> availableColors;
	
	private ArrayList<ExpressionNode> tree;
	Color thenColor;
	
	List listrules; //è la lista delle regole che va estesa
	ArrayList<Rule> forestRules; //alberi delle regole
	
	public CreateExpressionWindow(int x, int y, int width, int height, List l, ArrayList<Rule> forestRules, java.util.List<Color> availableColors) {
		super();
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		//Inizializzazione Attributi
		exp_list = new List();
		exp_list.setMultipleMode(true);
		ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.X_AXIS));
		this.availableColors = availableColors;
		edit_panel = new EditExpressionPanel(this.availableColors);
		btnA = new JButton("A");btnA.setName("btnA");
		btnB = new JButton("B");btnB.setName("btnB");
		btnAND = new JButton("AND");btnAND.setName("btnAND");
		btnOR = new JButton("OR");btnOR.setName("btnOR");
		btnNOT = new JButton("NOT");btnNOT.setName("btnNOT");
		btn_add = new JButton("+");btn_add.setName("btn_add");
		btnTHEN = new JButton("THEN");btnTHEN.setName("btnTHEN");check_then();
		flag_then = false;
		tree = new ArrayList<ExpressionNode>();
		listrules = l;
		this.forestRules = forestRules;
		
		//Gestione click bottoni
		btnA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit_panel = new EditExpressionPanel(availableColors).formatA();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				check_then();
				revalidate();
			}
		});
		
		btnB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				edit_panel = new EditExpressionPanel(availableColors).formatB();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				check_then();
				revalidate();
			}
		});
		
		btnAND.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				concatenate("AND");
				check_then();
			}
		});
		
		btnOR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				concatenate("OR");
				check_then();
			}
		});
		
		btnNOT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				concatenate("NOT");
				check_then();
			}
		});
		
		btnTHEN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				flag_then = true;
				btnTHEN.setEnabled(false);
				btn_add.setText("Save");
				disable_buttons();
				edit_panel = new EditExpressionPanel(availableColors).formatThen();
				BottomGroupPanel.remove(0);
				BottomGroupPanel.add(edit_panel, 0);
				revalidate();
			}
		});
		
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (edit_panel.getType()!=null && edit_panel.isCompleted()) { //controlla che il form sia compilato
					edit_panel.buildNode();
					if (flag_then) { //se entri qui, stai salvando la regola
						thenColor = edit_panel.getThenColor();
						listrules.add(CreateExpressionWindow.this.getRule().toString());
						forestRules.add(CreateExpressionWindow.this.getRule());
						dispose();
					}
					else {
						ExpressionNode e = edit_panel.getExpr();
						tree.add(e);
						exp_list.add(e.toString());
					}
				}
				check_then();
			}
		});
		
		
		
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
		int [] inds = exp_list.getSelectedIndexes();
	
		if(inds.length == 1) { //1 operando: controlla se è una not
			if(operand.equals("NOT")) {
				ExpressionNode tmp = new NotNode(tree.get(inds[0]));
				tree.set(inds[0], tmp);
				exp_list.remove(inds[0]);
				exp_list.add(tmp.toString(), 0);
			}
		}
		
		if(inds.length > 1) { //piu' di un operando: controlla se sono tante or/and
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
			
			for(int i=inds.length-1; i>=0; i--) { //dalla fine all'inizio per non creare problemi alle liste
				exp_list.remove(inds[i]);
				tree.remove(inds[i]);
			}
			exp_list.add(act.toString());
			tree.add(act);
		}
	}
	
	public Rule getRule() {
		return new Rule(tree.get(0), thenColor);
	}
	
	private void check_then() {btnTHEN.setEnabled(exp_list.getItemCount()==1);}
	
}
