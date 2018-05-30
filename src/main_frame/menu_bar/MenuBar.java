package main_frame.menu_bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;


import grid.CellForm;
import grid.Graph;
import grid.GridConfCreator;
import grid.GridConfiguration;
import grid.square.MatrixGraph;
import main_frame.errors_panel.ErrorsPanel;
import main_frame.menu_bar.run_configuration.RunConfigurationFrame;
import main_frame.rules_creator.RuleChoser;
import main_frame.states.StateChoser;
import util.ConfigContainer;
import rules.AndNode;
import rules.BaseExpressionNode1;
import rules.BaseExpressionNode2;
import rules.Rule;
import simulator.RunPanel;
import simulator.RunPanel_Prova_Async;
import util.ConflictFinder;
import util.ImageHandler;
import util.StaticUtil;

// import RunConfiguration;

public class MenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	private static final String NO_STATE_INSERTED_MESSAGE_ERROR = "Inserisci almeno uno stato!";

	// Istanza che permette di accedere a info come screenSize etc
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	Graph graph; //grafo memorizzato qui: ci serve per run e run configurations
	
	// Immagine che viene importata
	BufferedImage image;
	
	// Informazioni per test Errori
	StateChoser state;
	GridConfCreator grid;
	RuleChoser rules;
	ErrorsPanel errorPanel;
	
	public MenuBar(StateChoser state, GridConfCreator grid, RuleChoser rules, ErrorsPanel err) {
		
		//Inizializzo Informazioni per testing di Errori
		this.state = state;
		this.grid = grid;
		this.rules = rules;
		this.graph = new MatrixGraph();
		this.errorPanel = err;
		
		// Image
		image = null;
		
		// File
		JMenu menuFile = new JMenu("File");
			// Voce Import che permette di caricare una configurazione completa 
			JMenuItem importRule = new JMenuItem(); 
			importRule.addActionListener(importConfiguration);
			importRule.setText("Import...");
			// Voce Export che permette di salvare l'intera configurazione in fomrato JSON
			JMenuItem exportRule = new JMenuItem();
			exportRule.addActionListener(exportConfiguration);
			exportRule.setText("Export...");
			// Voce Import Image che
			JMenuItem importImage = new JMenuItem();
			importImage.addActionListener(importImg);
			importImage.setText("Import Image...");
		menuFile.add(importRule);
		menuFile.add(exportRule);
		menuFile.add(importImage);

		
		// Run
		JMenu menuRun = new JMenu("Run");
			// Voce Run che manda in esecuzione la configurazione creata dall'utente
			JMenuItem run = new JMenuItem("Run"); 
			run.addActionListener(runConfiguration);
			run.setText("Run");
			// Voce Run Configurations che permette di definire caratteristiche avanzate
			JMenuItem runConfiguration = new JMenuItem();
			runConfiguration.addActionListener(setConfiguration);
			runConfiguration.setText("Run Configuration...");
		menuRun.add(run);
		menuRun.add(runConfiguration);
		
		add(menuFile);
		add(menuRun);
	}
	
	// Metodo richiamato dal click di Run Configurations che apre il frame di definzione di caratteristiche avanzate
	ActionListener setConfiguration = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(state.getStates().size() > 0) {
				repairGraph(grid.getGridConfiguration(), state.getStates().get(0)); //ripara grafo prima di passarlo
				
				JFrame runConf = new RunConfigurationFrame(graph, grid.getGridConfiguration(), state.getStates(), rules.getRuleTrees());
				runConf.setBounds( (int)(screenSize.getWidth()/4),  (int)(screenSize.getHeight()/4), (int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.45));
				runConf.setVisible(true);
			}
			else
				StaticUtil.errorDialog(NO_STATE_INSERTED_MESSAGE_ERROR);
		}
	};
	
	// Metodo richiamato dal click di Run che avvia la simulazione usando la configurazione creata dall'utente
	ActionListener runConfiguration = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(errorPanel.update(state.getStates(), grid.getGridConfiguration(), rules.getRuleTrees(), graph, true)) { //non ci sono errori, possiamo fare run
				JFrame run = new JFrame();
				//passiamo delle copie al run panel... cosi se le modifichiamo nell'editor questo non si ripercuote sulla simulazione
				ArrayList<Rule> copyRules = new ArrayList<Rule>();
				for(Rule r : rules.getRuleTrees()) 
					copyRules.add(r.copy());
				
				/*if( variabile_sincrono o asincrono) {*/		
				//	RunPanel runPanel = new RunPanel(graph.copy(), grid.getGridConfiguration(), new ArrayList<Color>(state.getStates()), copyRules);
				//	run.add(runPanel);
				//}	//new RunPanel(graph.copy(), grid.getGridConfiguration(), new ArrayList<Color>(state.getStates()), copyRules); //crea run panel e setta parametri
				//else {
					RunPanel_Prova_Async runPanel = new RunPanel_Prova_Async(graph.copy(), grid.getGridConfiguration(), new ArrayList<Color>(state.getStates()), copyRules);
					run.add(runPanel);
				//}	//new RunPanel(graph.copy(), grid.getGridConfiguration(), new ArrayList<Color>(state.getStates()), copyRules); //crea run panel e setta parametri
				
				
				
				run.setBounds( (int)(screenSize.getWidth()/4),  (int)(screenSize.getHeight()/4), (int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.45));
				run.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				run.setVisible(true);
				run.addWindowListener(new WindowAdapter(){
	                public void windowClosing(WindowEvent e){
	                   runPanel.onClosing();
	                }
	            });
			}
		}
	};
	
	// Metodo richiamato dal click di Import che permette di importare configurazioni gia' esistenti
	ActionListener importConfiguration = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("importConfiguration");
			
			
			// Frame che permette di scegliere la destinazione e di dare un nome al file
			JFileChooser importPath = new JFileChooser();
			importPath.showOpenDialog(new JFrame());
			GridConfiguration gconf = new GridConfiguration(CellForm.SQUARE, 21, 100, 100);	
			
			//blu = vivo, giallo = morto
			ArrayList<Rule> rs = new ArrayList<>();
			//qualsiasi cella viva con meno di 2 vicini vivi muore
			BaseExpressionNode2 e1 = new BaseExpressionNode2(0, Color.BLUE); //cella deve essere viva
			BaseExpressionNode1 e2 = new BaseExpressionNode1(0, 1, Color.BLUE); //0 <= numero vivi <= 1
			AndNode e3 = new AndNode(e1, e2);
			rs.add(new Rule(e3, Color.YELLOW));
			
			//qualsiasi cella viva con pi� di 3 vicini vivi muore
			BaseExpressionNode1 e4 = new BaseExpressionNode1(4, 1000, Color.BLUE); //piu' di 3 vicini vivi
			AndNode e5 = new AndNode(e1, e4);
			rs.add(new Rule(e5, Color.YELLOW));
			
			//qualsiasi cella morta con esattamente 3 celle vive adiacenti diventa viva
			BaseExpressionNode2 e6 = new BaseExpressionNode2(0, Color.YELLOW); //cella morta
			BaseExpressionNode1 e7 = new BaseExpressionNode1(3, 3, Color.BLUE); //3 vivi
			AndNode e8 = new AndNode(e6, e7);
			rs.add(new Rule(e8, Color.BLUE));
			rules.initFromRules(rs);
			ArrayList<Color> c = new ArrayList<>();
			c.add(Color.BLUE);
			c.add(Color.YELLOW);
			state.initFromStatesList(c);
		}
	};
		
	// Metodo richiamato dal click di Export che esporta la configurazione creata dall'utente
	ActionListener exportConfiguration = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// Frame che permette di scegliere la destinazione e di dare un nome al file
			JFileChooser exportPath = new JFileChooser();
			exportPath.showSaveDialog(new JFrame());
			
			// Genero la path dove salvare il file
			String path = exportPath.getSelectedFile() + ".txt";
			if (path != "null.agm") {
				try { 
					// Creo il file su cui salvare il tutto
					File exportFile = new File(path); 
					exportFile.createNewFile();
					
					// Inizializzo il contenitore di tutte le informazioni da salvare
					ConfigContainer confContainer = new ConfigContainer(graph, state, grid, rules);
					
					// Inizializzo l'istanza JSON e converto l'oggetto
					Gson gson = new Gson();
					String dati = gson.toJson(confContainer);
					
					// salvo l'oggetto su file e chiudo il flusso
					FileWriter fw = new FileWriter(exportFile);
					fw.write(dati);
					fw.flush();
					fw.close();
				}
				catch (IOException e) {}
			}
		}
	};
	
	// Metodo richiamato dal click di import Image che importa una qualsiasi immagine selezionata dall'utente
	ActionListener importImg = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("importImage");
			
			// Selettore di immagini
			JFileChooser imageFinder = new JFileChooser();
			imageFinder.setFileSelectionMode(JFileChooser.FILES_ONLY);
			imageFinder.setDialogTitle("Select an image");
			imageFinder.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Immagine: png, jpg, jpeg", "png", "jpg", "jpeg");
			imageFinder.addChoosableFileFilter(filter);
			
			int returnValue = imageFinder.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
					
				try {
					
					File selectedImage = imageFinder.getSelectedFile();
					image = ImageIO.read(selectedImage);
				
					ImageHandler iH = new ImageHandler();
					
					// Rapporto d'aspetto immagine (Es. 16/9)
					double aspectRatio = (double) image.getWidth() / (double) image.getHeight();
					
					image = ImageHandler.resize(image, 300, (int) (300 / aspectRatio));
					
					image = iH.reduceColors(image);
					state.addStates(new ArrayList<>(iH.getFewColors()));
					
					// Creazione nuovo grafo
					GridConfiguration gridConfiguration = new GridConfiguration(CellForm.SQUARE, 10, image.getWidth(), image.getHeight());
					grid.initFromGridConf(gridConfiguration);
					graph = Graph.buildGraph(gridConfiguration, Color.BLACK);
					
					// Scrittura immagine nel grafo
					for (int y = 0; y <  image.getHeight(); y++) {
						for (int x = 0; x < image.getWidth(); x++) {
							graph.getCell(y*gridConfiguration.getNumCellsX()+x+1).setState(new Color(image.getRGB(x, y)));
						}
					}
				} catch (IOException e) {
					StaticUtil.errorDialog("Errore durante il caricamente dell'immagine");
				}
				
			}
				
		}
	};
	
	/**ripara il grafo da eventuali incongruenze*/
	private void repairGraph(GridConfiguration gconf, Color defaultState) {
		ConflictFinder cf = new ConflictFinder(null, gconf, null, graph);
		if(cf.graphConfConflicts()) { //conflitto...
			if(graph instanceof MatrixGraph) //proviamo a sistemare la dimensione delle celle... e' il massimo che possiamo aggiustare
				((MatrixGraph)graph).setSize(gconf.getLen());
			if(cf.graphConfConflicts()) { //ci sono ancora conflitti, allora dobbiamo per forza ricreare il grafo da zero
				graph = Graph.buildGraph(gconf, defaultState);
			}
		}
	}

}
