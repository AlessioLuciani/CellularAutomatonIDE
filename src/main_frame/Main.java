package main_frame;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main_frame.rules_creator.RuleChooser;
import main_frame.states.StateChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Main {

	private JFrame frame;

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

		frame.setBounds(100, 100, (int)(screenSize.getWidth()*0.8), (int)(screenSize.getHeight()*0.8));
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
		
		
		// Pannello gestione stati
		JPanel statePanel = new StateChooser();
		
		// Aggiunta pannello gestione stati
		GridBagConstraints gbc_statePanel = new GridBagConstraints();
		gbc_statePanel.fill = GridBagConstraints.BOTH;
		gbc_statePanel.gridx = 1;
		gbc_statePanel.gridy = 17;
		gbc_statePanel.gridheight = 5;
		gbc_statePanel.gridwidth = 2;
		pannello.add(statePanel, gbc_statePanel);
		
		// Pannello gestione regole di transizione
		JPanel rulePanel = new RuleChooser();
		
		// Aggiunta pannello gestione regole
		GridBagConstraints gbc_rulePanel = new GridBagConstraints();
		gbc_rulePanel.fill = GridBagConstraints.BOTH;
		gbc_rulePanel.gridx = 1;
		gbc_rulePanel.gridy = 23;
		gbc_rulePanel.gridheight = 5;
		gbc_rulePanel.gridwidth = 24;
		pannello.add(rulePanel, gbc_rulePanel);
	}

}