package testing_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import grid.CellForm;
import grid.Graph;
import grid.GridConfiguration;
import grid.GridRenderPanel;
import rules.AndNode;
import rules.BaseExpressionNode1;
import rules.BaseExpressionNode2;
import rules.Rule;
import simulator.Updater;

public class Test_gridrender {
	static int t = 100;
	static Timer timer = new Timer();
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		final int w = 100, h = 100, s = 20;
		
		GridConfiguration gconf = new GridConfiguration(CellForm.SQUARE, s, w, h);
		
		Graph g = Graph.buildGraph(gconf, Color.YELLOW); 
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		//blu = vivo, giallo = morto
		
		//qualsiasi cella viva con meno di 2 vicini vivi muore
		BaseExpressionNode2 e1 = new BaseExpressionNode2(0, Color.BLUE); //cella deve essere viva
		BaseExpressionNode1 e2 = new BaseExpressionNode1(0, 1, Color.BLUE); //0 <= numero vivi <= 1
		AndNode e3 = new AndNode(e1, e2);
		rules.add(new Rule(e3, Color.YELLOW));
		
		//qualsiasi cella viva con più di 3 vicini vivi muore
		BaseExpressionNode1 e4 = new BaseExpressionNode1(4, 1000, Color.BLUE); //piu' di 3 vicini vivi
		AndNode e5 = new AndNode(e1, e4);
		rules.add(new Rule(e5, Color.YELLOW));
		
		//qualsiasi cella morta con esattamente 3 celle vive adiacenti diventa viva
		BaseExpressionNode2 e6 = new BaseExpressionNode2(0, Color.YELLOW); //cella morta
		BaseExpressionNode1 e7 = new BaseExpressionNode1(3, 3, Color.BLUE); //3 vivi
		AndNode e8 = new AndNode(e6, e7);
		rules.add(new Rule(e8, Color.BLUE));
		
		int cannone[] = {1106, 1107, 1207, 1206, 1116, 1216, 1316, 1417, 1017, 918, 919, 1518, 1519, 1421, 1322, 1222, 1122, 1223, 
						1021, 1220, 1126, 1127, 1027, 1026, 926, 927, 828, 1228, 830, 730, 1230, 1330, 940, 941, 1041, 1040};
		
		int test[] = {2525, 2626,2727,2826,2926,2924,2925,2823,2622,2523,2722,2718,2619,2719,2618,2329,2530, 2430, 2330, 2429, 2529,
						1106, 1107, 1207, 1206, 1116, 1216, 1316, 1417, 1017, 918, 919, 1518, 1519, 1421, 1322, 1222, 1122, 1223, 
								1021, 1220, 1126, 1127, 1027, 1026, 926, 927, 828, 1228, 830, 730, 1230, 1330, 940, 941, 1041, 1040};
		
		int ghianda[] = {5251, 5252, 5052, 5154, 5255, 5256, 5257};
		int diehard[] = {3860, 3861, 3961, 3965, 3966, 3967, 3766};
		int blockchain[] = {3444, 3446, 3346, 3248, 3148, 3048, 3150, 3050, 2950, 3051};
		int spaceship[] = {304, 504, 605, 606, 607, 608, 508, 408, 307};
		int rpentomino[] = {4242, 4243, 4342, 4341, 4442};
		
		int cosorandom[] = {919, 1016, 1215, 1218, 1119, 1120, 1021, 921, 820, 720, 719, 817, 917};
		
		for(int i : cannone)
			g.getCell(i).setState(Color.BLUE);
		
		Updater exe = new Updater(g, rules);
		
		GridRenderPanel panel = new GridRenderPanel(g, gconf, Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(0, 0, 800, 800);
		
		
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  panel.synchWithGraph(exe.execStep());
				  frame.invalidate();
				  frame.repaint();
			  }
		}, 100, 100);
		//timer.cancel();
		
		JButton b7 = new JButton("faster");
		b7.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				
				/*ArrayList<Integer> li = new ArrayList<Integer>();
				Random rand = new Random();
				for(int i=0; i<10; i++) {
					int ind = rand.nextInt(w*h) + 1;
					li.add(ind);
					g.getCell(ind).setState(Color.BLUE);
				}*/
				/*panel.synchWithGraph(exe.execStep()); 
				frame.invalidate();
				frame.repaint();*/
				timer.cancel();
				timer = new Timer();
				timer.purge();
				t -= 10;
				timer.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  panel.synchWithGraph(exe.execStep());
						  frame.invalidate();
						  frame.repaint();
					  }
				}, t, t);
		    }
		});
	
		frame.add(b7, BorderLayout.SOUTH);
		frame.add(panel);
		frame.setVisible(true);
	}
}
