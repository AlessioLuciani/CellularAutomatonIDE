package testing_package;

import java.awt.Color;
import java.awt.Component;
import java.awt.color.ColorSpace;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
 
public class test_JList extends JFrame {
	
    private JList<JLabel> countryList;
    
    JLabel jlbl = new JLabel("ciao");
    
    public test_JList() {
    	
    	jlbl.setBackground(Color.RED);
    	jlbl.setSize(20, 30);
    	
        //create the model and add elements
        DefaultListModel<JLabel> listModel = new DefaultListModel<>();
        listModel.addElement(jlbl);
 
        //create the list
        countryList = new JList<>(listModel);
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
