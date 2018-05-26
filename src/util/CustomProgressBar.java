package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**progress bar custom visibile su un frame esterno*/
public class CustomProgressBar extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JProgressBar bar;
	protected JLabel message;
	
	public CustomProgressBar(String title, int minv, int maxv, String initMsg) {
		super(title);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		this.setBounds(0, 0, 200, 120);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		bar = new JProgressBar(minv, maxv); //progress bar
		bar.setStringPainted(true);
		bar.setValue(minv);
		
		mainPanel.add(bar);
		
		message = new JLabel(initMsg); //label per dare messaggi
		mainPanel.add(message); 
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	taskCancelled();
		    }
		});
		
		JButton cancelB = new JButton("Cancel"); //pulsante per cancellare
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taskCancelled();
				CustomProgressBar.this.dispose();
			}
		});
		
		mainPanel.add(cancelB);
		
		add(mainPanel);
		this.setVisible(true);
	}
	
	/**aggiorna messaggio nella progress bar*/
	public void updateMessage(String msg) {
		SwingUtilities.invokeLater(new Runnable() { //assumiamo che possa essere richiamato da altri thread... quindi usiamo la sincronizzazione di swing
			@Override
			public void run() {
				message.setText(msg);
			}
		});
	}
	
	/**aggiorna valore della bar*/
	public void updateValue(int val) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				bar.setValue(val);
			}
		});
	}
	
	/**richiamata quando si preme cancel (o si chiude la finestra)*/
	public void taskCancelled() {
		
	}
}
