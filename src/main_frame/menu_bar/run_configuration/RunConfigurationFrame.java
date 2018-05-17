package main_frame.menu_bar.run_configuration;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;

import grid.Graph;
import grid.GridConfiguration;
import grid.square.MatrixGraph;
import util.ConflictFinder;

/**frame che permette di definire i parametri di run*/
public class RunConfigurationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JRadioButton rdbtnSincrono;
	JRadioButton rdbtnAsincrono;
	
	Graph graph;
	
	public RunConfigurationFrame(Graph graph, GridConfiguration gridConf, Color defaultGraphColor) {
		setAutoRequestFocus(false);
		setVisible(true);
		
		this.graph = graph;
		repairGraph(gridConf, defaultGraphColor);
		
		JPanel pannello = new JPanel();
		getContentPane().add(pannello);		
		
		// Creazione Layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.01, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		gridBagLayout.rowWeights = new double[]{0.01, 0.01, 0.1, 0.1, 0.0, 0.1, 0.1, 0.1};
		pannello.setLayout(gridBagLayout);
		
		rdbtnSincrono = new JRadioButton("Sincorno");
		rdbtnSincrono.addActionListener(onClickSincrono);
		
		rdbtnAsincrono = new JRadioButton("Asincrono");
		rdbtnAsincrono.addActionListener(onClickAsincrono);
		GridBagConstraints gbc_rdbtnAsincrono = new GridBagConstraints();
		gbc_rdbtnAsincrono.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsincrono.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAsincrono.gridx = 1;
		gbc_rdbtnAsincrono.gridy = 3;
		pannello.add(rdbtnAsincrono, gbc_rdbtnAsincrono);
		GridBagConstraints gbc_rdbtnSincrono = new GridBagConstraints();
		gbc_rdbtnSincrono.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSincrono.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSincrono.gridx = 1;
		gbc_rdbtnSincrono.gridy = 5;
		pannello.add(rdbtnSincrono, gbc_rdbtnSincrono);
	}
	
	// Gestione RadioButton Sincrono/Asincrono
	ActionListener onClickAsincrono = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) { if (rdbtnSincrono.isSelected()) {rdbtnSincrono.setSelected(false);} }
	};
	
		ActionListener onClickSincrono = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) { if (rdbtnAsincrono.isSelected()) {rdbtnAsincrono.setSelected(false);} }
	};

	/**ripara il grafo da eventuali incongruenze*/
	private void repairGraph(GridConfiguration gconf, Color defaultState) {
		ConflictFinder cf = new ConflictFinder(null, gconf, null, graph);
		if(cf.graphConfConflicts()) { //conflitto...
			if(graph instanceof MatrixGraph) //proviamo a sistemare la dimensione delle celle... e' il massimo che possiamo aggiustare
				((MatrixGraph)graph).setSize(gconf.getLen());
			if(cf.graphConfConflicts()) //ci sono ancora conflitti, allora dobbiamo per forza ricreare il grafo da zero
				graph.setToGraph(Graph.buildGraph(gconf, defaultState));
		}
	}
}
