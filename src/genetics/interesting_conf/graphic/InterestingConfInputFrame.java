package genetics.interesting_conf.graphic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**finestra per dare input della configurazione interessante*/
public class InterestingConfInputFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	protected InterestingConfInputPanel icinp;
	
	public InterestingConfInputFrame(int defSizeX, int defSizeY, int defCycLen, int defK) {
		setTitle("parametri algoritmo genetico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		icinp = new InterestingConfInputPanel(defSizeX, defSizeY, defCycLen, defK);
		
		add(icinp);
		
		JPanel tmp = new JPanel();
		tmp.setLayout(new FlowLayout());
		JButton btnClose = new JButton("OK");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onClosed(icinp.getSizeX(), icinp.getSizeY(), icinp.getCycLen(), icinp.getK());
				InterestingConfInputFrame.this.dispose();
			}
		});
		tmp.add(btnClose);
		add(tmp, BorderLayout.SOUTH);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		       onClosed(icinp.getSizeX(), icinp.getSizeY(), icinp.getCycLen(), icinp.getK());
		    }
		});
	}
	
	/**chiamata quando si chiude: si possono prendere parametri*/
	public void onClosed(int sizeX, int sizeY, int cycLen, int k) {
		
	}
}
