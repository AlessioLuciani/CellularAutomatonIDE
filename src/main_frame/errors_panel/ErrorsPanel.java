package main_frame.errors_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import grid.Graph;
import grid.GridConfiguration;
import rules.Rule;
import util.ConflictFinder;
import util.StaticUtil;

/**panel per messaggi d'errore
 * */
public class ErrorsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String title = "Errors";
	private List messageList;
	private String message = "Errori trovati nell'automa!";
	
	public ErrorsPanel() {
		setLayout(new BorderLayout());
		
		Border compound;
		Border redline = BorderFactory.createLineBorder(Color.RED);
		compound = BorderFactory.createCompoundBorder(null, null);
		compound = BorderFactory.createCompoundBorder(redline, compound);
		compound = BorderFactory.createTitledBorder(compound, title, TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
		this.setBorder(compound);
		
		messageList = new List();
		this.add(messageList);
	}
	
	/**aggiorna messaggi d'errore, in base agli stati, configurazione della griglia, lista di regole e grafo
	 * restituisce true se non ci sono stati errori, false altrimenti*/
	public boolean update(ArrayList<Color> stateColors, GridConfiguration gconf, ArrayList<Rule> rules, Graph graph, boolean showDialog) {
		ConflictFinder cfinder = new ConflictFinder(stateColors, gconf, rules, graph);
		HashSet<String> strs = cfinder.getConflicts();
		messageList.removeAll();
		for(String s : strs)
			messageList.add(s);
		if(strs.size() > 0 && showDialog)
			StaticUtil.errorDialog(message);
		return strs.size() == 0;
	}
}
