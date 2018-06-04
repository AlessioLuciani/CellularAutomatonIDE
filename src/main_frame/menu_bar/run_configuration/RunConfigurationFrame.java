package main_frame.menu_bar.run_configuration;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;

import grid.Graph;
import grid.GridConfiguration;
import main_frame.grid_initializer.GridInitializerPanel;
import rules.Rule;

/**frame che permette di definire i parametri di run*/
public class RunConfigurationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected JRadioButton rdbtnSincrono;
	protected JRadioButton rdbtnAsincrono;
	protected GridInitializerPanel gridInitializerPanel;
	
	public RunConfigurationFrame(Graph graph, GridConfiguration gridConf, ArrayList<Color> colors, ArrayList<Rule> rules) {
		setAutoRequestFocus(false);
		setLayout(new BorderLayout());
		setVisible(true);
		
		gridInitializerPanel = new GridInitializerPanel(graph, gridConf, colors, rules);
		add(gridInitializerPanel);
		
		JPanel pannelloDestra = new JPanel();
		add(pannelloDestra, BorderLayout.SOUTH);		
		
		// Creazione Layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.01, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		gridBagLayout.rowWeights = new double[]{0.01, 0.01, 0.1, 0.1, 0.0, 0.1, 0.1, 0.1};
		pannelloDestra.setLayout(gridBagLayout);
		
		rdbtnSincrono = new JRadioButton("Sincorno");
		rdbtnSincrono.addActionListener(onClickSincrono);
		rdbtnAsincrono = new JRadioButton("Asincrono");
		rdbtnAsincrono.addActionListener(onClickAsincrono);
		rdbtnSincrono.setSelected(true); //inizialmente sincrono
		
		GridBagConstraints gbc_rdbtnAsincrono = new GridBagConstraints();
		gbc_rdbtnAsincrono.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsincrono.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAsincrono.gridx = 1;
		gbc_rdbtnAsincrono.gridy = 3;
		pannelloDestra.add(rdbtnAsincrono, gbc_rdbtnAsincrono);
		GridBagConstraints gbc_rdbtnSincrono = new GridBagConstraints();
		gbc_rdbtnSincrono.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSincrono.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSincrono.gridx = 1;
		gbc_rdbtnSincrono.gridy = 5;
		pannelloDestra.add(rdbtnSincrono, gbc_rdbtnSincrono);
		
		// Chiude tutti i pannelli
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				gridInitializerPanel.onPanelClosed();
				get_exec_mode_callback();
				super.windowClosing(e);
			}
		});
	}
	
	public void get_exec_mode_callback(){}
	
	// Gestione RadioButton Sincrono/Asincrono
	ActionListener onClickAsincrono = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) { if (rdbtnSincrono.isSelected()) {rdbtnSincrono.setSelected(false);} }
	};
	
		ActionListener onClickSincrono = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) { if (rdbtnAsincrono.isSelected()) {rdbtnAsincrono.setSelected(false);} }
	};
}
