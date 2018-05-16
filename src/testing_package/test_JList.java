package testing_package;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
 
public class test_JList extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JList<JLabel> countryList;
    
    JLabel jlbl = new JLabel("ciao");
    
    public test_JList() {
    	
    	jlbl.setBackground(Color.RED);
    	jlbl.setOpaque(true);
    	jlbl.setSize(200, 30);
 
    	
        //create the model and add elements
        DefaultListModel<JLabel> listModel = new DefaultListModel<>();
        listModel.addElement(jlbl);
        
        JLabel j2 = new JLabel("OK");
        j2.setBackground(Color.blue);
        listModel.addElement(j2);

        //create the list
        countryList = new JList<>();
        countryList.setModel(listModel);
        countryList.setCellRenderer(new TestRender());
        DefaultListModel<JLabel> tmp = (DefaultListModel<JLabel>) countryList.getModel();
        tmp.addElement(j2);
        //countryList.setFixedCellWidth(200);
        //DefaultListModel<JLabel> listModel2 = new DefaultListModel<>();
        //listModel2.addElement(j2);
        //countryList.add(jlbl);
        //countryList.add(j2);
        add(countryList);
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Example");       
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test_JList();
            }
        });
    }       
}
