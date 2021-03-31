package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.MenuItem;
import business.Order;
import business.Restaurant;

public class WaiterView {
    private JLabel labelTitle;
    private JLabel labelOrders;
    private JTable table;
    private JButton addOrderButton;
    private JButton billButton;
    private JLabel lOrderID;
    private JLabel lDate;
    private JLabel lTable;
    private JLabel lItems;
    private JLabel lCheck;
    
    private JFrame frame;
	private DefaultTableModel model;

    public WaiterView(final Restaurant restaurant) {

    	labelTitle = new JLabel ("Waiter");
        labelOrders = new JLabel ("Orders:");
        addOrderButton = new JButton ("Add Order");
        billButton = new JButton ("Get Bill");
        lOrderID = new JLabel ("Order ID");
        lDate = new JLabel ("Date");
        lTable = new JLabel ("Table");
        lItems = new JLabel ("Items");
        lCheck = new JLabel ("Check");
        
        frame = new JFrame("Waiter");
		frame.setSize(new Dimension(500, 290));

		JPanel p = new JPanel();
		p.setLayout(null);
		
	    String[] columnNames = new String[] {"ID", "Date", "Table", "Items", "Check"};
	    Object[][] data = {{1, "22-09-2009", 4, "fasole,ardei", false},
	    {2, "22-08-2019", 2, "ciolan,ardei", false},
	    {3, "13-09-1999", 7, "cartofi pai, mici", false}
	    };
	    
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        model.setColumnIdentifiers(columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setBackground(Color.decode("#BDCAF9"));
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
	
        labelTitle.setBounds (185, 25, 100, 25);
        labelOrders.setBounds (40, 45, 100, 25);
        table.setBounds (35, 80, 415, 100);
        addOrderButton.setBounds (210, 195, 100, 25);
        billButton.setBounds (330, 195, 100, 25);
        lOrderID.setBounds (60, 60, 80, 25);
        lDate.setBounds (150, 60, 80, 25);
        lTable.setBounds (230, 60, 80, 25);
        lItems.setBounds (310, 60, 80, 25);
        lCheck.setBounds (390, 60, 80, 25);
        
        p.add (labelTitle);
        p.add (labelOrders);
        p.add (table);
        p.add (addOrderButton);
        p.add (billButton);
        p.add (lOrderID);
        p.add (lDate);
        p.add (lTable);
        p.add (lItems);
        p.add (lCheck);
        
        labelTitle.setFont(new Font("Cambria", Font.BOLD, 20));
        labelOrders.setFont(new Font("Cambria", Font.BOLD, 15));

		frame.setContentPane(p);	
		frame.setVisible(false);

    }
    
    public void setFrame(){
		this.frame.setVisible(true);
	}

    public JButton getAddOrderButton(){
		return this.addOrderButton;
	}
	public JButton getBillButton(){
		return this.billButton;
	}
	
    void addListener(ActionListener listen) {
    	addOrderButton.addActionListener(listen);
    	billButton.addActionListener(listen);
 
	}
    
    public int getTableSize() {
    	return table.getRowCount();
    }
    
    public void checkSelected() {
    	int size = table.getRowCount();
    	boolean exist = false;
    	for(int i = 0; i < size;i++){
    		if((boolean) table.getValueAt(i,4)){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }
    
    public Order menuItemChecked(int i, Restaurant r) {
    	
    	if((boolean) table.getValueAt(i,4)) {
    		
    		int id = (int) table.getValueAt(i,0);
    		String date = (String) table.getValueAt(i,1);
    		int tablee = (int) table.getValueAt(i,2);
    		String items = (String) table.getValueAt(i,3);
    		Order o = new Order(id, tablee);
    		ArrayList<MenuItem> list = r.getOrdersComposedBy(items);
    		o.setComponents(list);
    		return o;
    	}
    	return null;
    }

    public void addToTable(Order o, ArrayList<MenuItem> ordered , Restaurant r) {
    	
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.addRow(new Object[]{o.getOrderID(), o.getStringDate(), o.getTable(),r.getComposedBy(ordered), false});
    }
    
}
