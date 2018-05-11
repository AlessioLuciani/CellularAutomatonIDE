package MainFrame;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;


public class StateChooser extends JPanel{
	
	private static final long serialVersionUID = 1L;

	List list;

	public StateChooser() {
		
		// Creazione Layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};	//nColumns
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};	//nRows
		gridBagLayout.columnWeights = new double[]{0.5, 0.1, 0.2};	//dimColumns
		gridBagLayout.rowWeights = new double[]{1.0, 0.125, 0.125, 0.125};	//dimRows
		setLayout(gridBagLayout);
		
		// Contenitore di tutti gli stati gia' scelti
		list= new List();
		list.setMultipleMode(true);
		
		// Pulsante che richiama la procedura che inserisce gli stati
		JButton btnNew = new JButton("   New   ");
		btnNew.setAction(actionbtnNew);		
		btnNew.setText("   New    ");

		// Pulsante che richiama la procedura che elimina gli stati selezionati
		JButton btnRemove = new JButton("Remove");
		btnRemove.setAction(actionbtnRemove);		
		btnRemove.setText("Remove");
		
		// Aggiunta contenitore stati
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		gbc_list.gridheight = 4;
		add(list, gbc_list);
		
		// Aggiunta pulsante New
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.anchor = GridBagConstraints.WEST;
		gbc_btnNew.gridx = 2;
		gbc_btnNew.gridy = 2;
		add(btnNew, gbc_btnNew);
		
		// Aggiunta pulsante New
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.anchor = GridBagConstraints.WEST;
		gbc_btnRemove.gridx = 2;
		gbc_btnRemove.gridy = 3;
		add(btnRemove, gbc_btnRemove);
		
		
		
	
	}
	
	// Apertura modulo per l'inserimento stati
	@SuppressWarnings("serial")
	Action actionbtnNew = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			/*
			 *  Codice scelta stati
			 * 
			 * 
			 * */
			// JFrame ruleCreator = new CreateExpressionWindow(100, 100, 450, 300, list, forest);
			// ruleCreator.pack();
			// ruleCreator.setVisible(true);
		}
	};
	
	// Rimozione degli stati selezionati
	@SuppressWarnings("serial")
	Action actionbtnRemove = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int [] inds = list.getSelectedIndexes();
			for(int i=inds.length-1; i>=0; i--) {
				list.remove(inds[i]);
			}
			
			// Dopo l'eliminaizione, seleziona tutte le righe restanti. In questo modo si deselezionano
			for (int i = 0; i < list.getItemCount(); i++) {
				list.deselect(i);
			}
		}
	};
}
