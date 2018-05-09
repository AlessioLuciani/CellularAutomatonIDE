package MainFrame;

import javax.swing.JPanel;

import RulesCreatorWindow.CreateExpressionWindow;
import rules.Rule;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.List;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class RegoleTransizione extends JPanel{

	private static final long serialVersionUID = 1L;

	ArrayList<Rule> forest;//lista degli alberi delle regole
	
	public RegoleTransizione() {
		setLayout(null);
		
		forest = new ArrayList<Rule>();
		
		//Istanza che permettere di ottenere informazioni relative al monitor (Es: Width, Height, X, Y..)
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Contenitore di tutte le regole di transizione gia' create
			List list = new List();
			list.setMultipleSelections(true);
			list.setBounds(15, 0, (int)(screenSize.getWidth()*0.6), 165);
			add(list);

		// Pulsante che richiama la procedura che crea le regole di transizione
			JButton btnNew = new JButton("New");
			btnNew.setBounds(list.getWidth()+list.getX()+15, 131, 89, 23);
			add(btnNew);
			
		// Pulsante che richiama la procedura che elimina le regole di transizione selezionate
			JButton btnRemove = new JButton("Remove");
			btnRemove.setBounds(list.getWidth()+list.getX()+15, 91, 89, 23);
			add(btnRemove);
			
		// Aggiunta di una nuova regola
			btnNew.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					/*
					 * Aggiunta Del Codice di Creazione Regole
					 * 
					 * */
					JFrame prova = new CreateExpressionWindow(100, 100, 450, 300, list, forest);
					prova.pack();
					prova.setVisible(true);
					
				}
			});
			
		// Rimozione regole gia' inserite
			btnRemove.addMouseListener(new MouseAdapter() {		
				public void mouseClicked(MouseEvent e) {
					int [] inds = list.getSelectedIndexes();
					for(int i=inds.length-1; i>=0; i--) {
						list.remove(inds[i]);
						forest.remove(inds[i]);
					}
					/*for (String el : list.getSelectedItems() ) {
						list.remove(el);
					}*/
					
					// Dopo l'eliminaizione, seleziona tutte le righe restanti. In questo modo si deselezionano
					for (int i = 0; i < list.getItemCount(); i++) {
						list.deselect(i);
					}
				}
			});
	}
	
	public ArrayList<Rule> getRuleTrees() {
		return forest;
	}
}
