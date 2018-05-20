package main_frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import grid.GridConfCreator;
import main_frame.rules_creator.RuleChoser;
import main_frame.states.StateChoser;
import main_frame.errors_panel.ErrorsPanel;
import main_frame.menu_bar.MenuBar;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Main {

	private JFrame frame;
	
	//componenti principali
	private GridConfCreator gridPanel;
	private StateChoser statePanel;
	private RuleChoser rulePanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		
		// Istanza che permette di accedere a info come screenSize etc
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setBounds(100, 100, (int)(screenSize.getWidth()*0.65), (int)(screenSize.getHeight()*0.70));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pannello = new JPanel();
		frame.getContentPane().add(pannello);
		
		// Creazione layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.01, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		gridBagLayout.rowWeights = new double[]{0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		pannello.setLayout(gridBagLayout);
		
		//Pannello visualizzazione errori
		ErrorsPanel errorPanel = new ErrorsPanel();
		// Aggiunta pannello configurazione griglia
		GridBagConstraints gbc_errorPanel = new GridBagConstraints();
		gbc_errorPanel.fill = GridBagConstraints.BOTH;
		gbc_errorPanel.gridx = 17;
		gbc_errorPanel.gridy = 22;
		gbc_errorPanel.gridheight = 6;
		gbc_errorPanel.gridwidth = 8;
		pannello.add(errorPanel, gbc_errorPanel);
		
		//Pannello configurazione griglia
		gridPanel = new GridConfCreator();
		// Aggiunta pannello configurazione griglia
		GridBagConstraints gbc_gridPanel = new GridBagConstraints();
		gbc_gridPanel.fill = GridBagConstraints.BOTH;
		gbc_gridPanel.gridx = 1;
		gbc_gridPanel.gridy = 1;
		gbc_gridPanel.gridheight = 3;
		gbc_gridPanel.gridwidth = 1;
		pannello.add(gridPanel, gbc_gridPanel);
		
		// Pannello gestione stati
		statePanel = new StateChoser();
		// Aggiunta pannello gestione stati
		GridBagConstraints gbc_statePanel = new GridBagConstraints();
		gbc_statePanel.fill = GridBagConstraints.BOTH;
		gbc_statePanel.gridx = 3;
		gbc_statePanel.gridy = 1;
		gbc_statePanel.gridheight = 3;
		gbc_statePanel.gridwidth = 5;
		pannello.add(statePanel, gbc_statePanel);
		
		// Pannello gestione regole di transizione
		rulePanel = new RuleChoser(statePanel.getStates());
		// Aggiunta pannello gestione regole
		GridBagConstraints gbc_rulePanel = new GridBagConstraints();
		gbc_rulePanel.fill = GridBagConstraints.BOTH;
		gbc_rulePanel.gridx = 1;
		gbc_rulePanel.gridy = 23;
		gbc_rulePanel.gridheight = 5;
		gbc_rulePanel.gridwidth = 16;
		pannello.add(rulePanel, gbc_rulePanel);
		
		// MenuBar
		JMenuBar menuBar = new MenuBar(statePanel, gridPanel, rulePanel, errorPanel);
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
	}

}