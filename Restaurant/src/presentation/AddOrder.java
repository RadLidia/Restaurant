package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.MenuItem;
import business.Restaurant;

public class AddOrder extends JPanel {
    private JTextField id;
    private JTextField table;
    private JButton addOrder;
    private JLabel labelID;
    private JLabel labelTable;
    List<JCheckBox> checkBoxList;
    private JFrame frame;
    private JPanel p;
    
    public AddOrder(final Restaurant r) {

    	id = new JTextField (5);
    	table = new JTextField (5);
    	addOrder = new JButton ("Add");
    	labelID = new JLabel ("Order ID:");
    	labelTable = new JLabel ("Table:");

    	frame = new JFrame("Add Order");
    	frame.setSize(new Dimension(309, 360));
	
    	p = new JPanel();
    	
    	id.setBounds (100, 35, 100, 25);
    	table.setBounds (10, 70, 100, 25);
		addOrder.setBounds (140, 280, 60, 25);
    	labelID.setBounds (45, 35, 55, 25);
    	labelTable.setBounds (60, 70, 40, 25);

    	p.add (labelID);
    	p.add (id);
    	p.add (labelTable);
    	p.add (table);
    	p.add (addOrder);

    
    	checkBoxList = new ArrayList<>();
        for (MenuItem menuItem : r.getMenu())
        {
            System.out.println("meniu: " + menuItem.getName());
            JCheckBox box = new JCheckBox (menuItem.getName());
            checkBoxList.add(box);
            p.add(box);
        }

    	frame.setContentPane(p);
    	frame.setVisible(false);

    }
    
    public void addCheckBox(Restaurant r, MenuItem m) {

    	System.out.println("meniu: " + m.getName());
    	JCheckBox box = new JCheckBox (m.getName());
    	checkBoxList.add(box);
    	p.add(box);

    	frame.setContentPane(p);
    	frame.setVisible(false);
    }

    public void setFrame(){
    	frame.repaint();
        frame.revalidate();
    	this.frame.setVisible(true);
    	
    }
    
    public void update(){
    	this.frame.revalidate();
    	
    }

    public JButton getAddOrder(){
    	return this.addOrder;
    }
    
    public String getID(){
		return id.getText();
	}
	public String getTable(){
		return table.getText();
	}

    void addListener(ActionListener listen) {
    	addOrder.addActionListener(listen);
    	
	}
    
    public void checkSelected(final Restaurant r) {
    	ArrayList<MenuItem> menu = (ArrayList<MenuItem>)r.getMenu();
        int size = menu.size();
        boolean exist = false;
        for (JCheckBox checkBox : checkBoxList)
        {
            if (checkBox.isSelected()){
            	exist = true;
            }
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }

    public MenuItem menuItemChecked(int i, Restaurant r) {

    	if (checkBoxList.get(i).isSelected()) {
    		for (MenuItem m : r.getMenu()) {
    			if (checkBoxList.get(i).getText().equals(m.getName()))
    				return m;           
    		}
    	}
    	return null;
    }
   
}
