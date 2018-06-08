package main_frame.states;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import util.AlternativeJLabelJListRender;
import util.StaticUtil;
import util.colors.ColorSelector;
import util.colors.ColoredRGBLabel;

import javax.swing.BorderFactory;
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
	
	// Contenitore di tutti gli stati
	JList<JLabel> labelList;

	//lista degli stati
	ArrayList<Color> listColor;
	
	private String title = "State List";
	
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
		labelList.setCellRenderer(new AlternativeJLabelJListRender());
		labelList.setFixedCellHeight(30);
		
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
		
		Border compound;
		compound = BorderFactory.createCompoundBorder(null, null);
		compound = BorderFactory.createTitledBorder(compound, title, TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		this.setBorder(compound);
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
						DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
						model.addElement(makeLabel(c));	
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
	
	//aggiunge colore alla jlist
	private JLabel makeLabel(Color c) {return new ColoredRGBLabel(c,StaticUtil.colorToRgbString(c));}
	
	/**inizializza il componente partendo da una lista di colori*/
	public void initFromStatesList(ArrayList<Color> colors) {
		this.listColor.clear();
		this.listColor.addAll(colors); //copia lista
		
		DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
		model.removeAllElements(); //rimuovi tutti stati
		for(Color c : colors) //aggiungi colori a jlist
			model.addElement(makeLabel(c));
	}
	
	// Semplice funzione che restituisce l'insieme degli stati rappresentati ognuno da un colore
	public ArrayList<Color> getStates(){
		return this.listColor;
	}
	
	/**
	 * Aggiunge una lista di colori agli stati.
	 * @param colors
	 */
	public void addStates(ArrayList<Color> colors) {
		for (Color color : colors) {
			if(!listColor.contains(color)) {
				DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
				model.addElement(makeLabel(color));	
				listColor.add(color);
			}
		}
	}
	
	// Semplice funzione che restituisce l'insieme degli stati in formato RGB(intero)
		public ArrayList<Integer> getStatesRGB(){
			ArrayList<Integer> colorlist = new ArrayList<>();
			for (Color color : this.listColor) {
				colorlist.add(color.getRGB());
			}
			return colorlist;
		}
		
		// Funzione che permette di caricare un elenco di stati e visualizzarli nel modulo
		public void setStates(ArrayList<Color> newStates){
			DefaultListModel<JLabel> model = (DefaultListModel<JLabel>)labelList.getModel();
			model.removeAllElements();
			this.listColor.removeAll(listColor);
			for (Color color : newStates) {
				model.addElement(makeLabel(color));
				this.listColor.add(color);
			}
		}
}
