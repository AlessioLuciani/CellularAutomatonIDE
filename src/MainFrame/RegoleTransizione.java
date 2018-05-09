package MainFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.List;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;


public class RegoleTransizione extends JPanel{

	private static final long serialVersionUID = 1L;

	public RegoleTransizione() {
		setLayout(null);
		
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
					JFrame prova = new ProvaCreaRegole(list);
					prova.pack();
					prova.setVisible(true);
					prova.setBounds(100, 100, 450, 300);
					
				}
			});
			
		// Rimozione regole gia' inserite
			btnRemove.addMouseListener(new MouseAdapter() {		
				public void mouseClicked(MouseEvent e) {
					for (String el : list.getSelectedItems() ) {
						list.remove(el);
					}
					
					// Dopo l'eliminaizione, seleziona tutte le righe restanti. In questo modo si deselezionano
					for (int i = 0; i < list.getItemCount(); i++) {
						list.deselect(i);
					}
				}
			});
	}
}
