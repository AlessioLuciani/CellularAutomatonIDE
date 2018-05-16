package main_frame.states;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.JLabelJListRender;
import util.StaticUtil;
import util.colors.ColorSelector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StateChoser extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static final int LABEL_WIDTH = 50, LABEL_HEIGHT = 30;
	
	// Contenitore di tutti gli stati
	JList<JLabel> labelList;

	//lista degli stati
	ArrayList<Color> listColor;
	
	public StateChoser() {
		
		//lista degli stati
		listColor = new ArrayList<Color>();
		
		
		// Creazione Layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};	//nColumns
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};	//nRows
		gridBagLayout.columnWeights = new double[]{0.5, 0.1, 0.2};	//dimColumns
		gridBagLayout.rowWeights = new double[]{1.0, 0.125, 0.125, 0.125};	//dimRows
		setLayout(gridBagLayout);
		
		// Contenitore di tutti gli stati gia' scelti
		labelList = new JList<>();
		DefaultListModel<JLabel> listModel = new DefaultListModel<>();
		labelList.setModel(listModel);
		labelList.setCellRenderer(new JLabelJListRender());
		
		// Pulsante che richiama la procedura che inserisce gli stati
		JButton btnNew = new JButton("   New   ");
		btnNew.addActionListener(actionbtnNew);		
		btnNew.setText("   New    ");

		// Pulsante che richiama la procedura che elimina gli stati selezionati
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(actionbtnRemove);		
		btnRemove.setText("Remove");
		
		// Aggiunta contenitore stati
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		gbc_list.gridheight = 4;
		JScrollPane sp = new JScrollPane(labelList);
		add(sp, gbc_list);
		
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
	ActionListener actionbtnNew = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			@SuppressWarnings("serial")
			ColorSelector stateChoser = new ColorSelector() {
				@Override
				public void colorChosen(Color c) {
					if(!listColor.contains(c)) {
						JLabel elem = new JLabel(StaticUtil.colorToRgbString(c));
						elem.setBackground(c);
						elem.setForeground(StaticUtil.farthestColor(c, Color.WHITE, Color.BLACK));
						elem.setOpaque(true);
						elem.setSize(LABEL_WIDTH, LABEL_HEIGHT);		
						
						DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
						model.addElement(elem);
						listColor.add(c);
					}
				}
			};
			stateChoser.setBounds(100, 100, 250, 300);
			stateChoser.setVisible(true);
			
		}
	};
	
	// Rimozione degli stati selezionati
	ActionListener actionbtnRemove = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int [] inds = labelList.getSelectedIndices();
			for(int i=inds.length-1; i>=0; i--) {
				DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
				model.remove(inds[i]);
				listColor.remove(inds[i]);
			}
			
			// Dopo l'eliminaizione, seleziona tutte le righe restanti. In questo modo si deselezionano
			labelList.getSelectionModel().clearSelection();
		}
	};
	
	// Semplice funzione che restituisce l'insieme degli stati rappresentati ognuno da un colore
	public ArrayList<Color> getStates(){
		return this.listColor;
	}
}
