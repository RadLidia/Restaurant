package presentation;

//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddBase extends JPanel {
	private JLabel labelName;
	private JTextField name;
	private JLabel labelPrice;
	private JTextField price;
	private JButton addButton;
	private JFrame frame;

	public AddBase() {

		labelName = new JLabel ("Product name:");
		name = new JTextField (5);
		labelPrice = new JLabel ("Product price:");
		price = new JTextField (5);
		addButton = new JButton ("Add");

		frame = new JFrame("Add Base Product");
		frame.setSize(new Dimension(301, 175));

		JPanel p = new JPanel();
		p.setLayout(null);

		labelName.setBounds (20, 15, 100, 25);
		name.setBounds (20, 35, 100, 25);
		labelPrice.setBounds (20, 70, 100, 25);
		price.setBounds (20, 90, 100, 25);
		addButton.setBounds (165, 55, 100, 40);

		p.add (labelName);
		p.add (name);
		p.add (labelPrice);
		p.add (price);
		p.add (addButton);

		frame.setContentPane(p);	
		frame.setVisible(false);
	}
	public String getPrice(){
		return price.getText();
	}
	public String getName(){
		return name.getText();
	}
	
	public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getAddButton(){
		return this.addButton;
	}

	void addListener(ActionListener listen) {
		addButton.addActionListener(listen);
	}

}