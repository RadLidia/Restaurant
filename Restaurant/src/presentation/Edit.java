package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Edit extends JPanel {
	private JLabel labelName;
	private JTextField name;
	private JButton editButton;
	private JLabel labelMessage;
	private JLabel labelPrice;
	private JTextField price;
	private JFrame frame;

	public Edit() {

		labelName = new JLabel ("Product name:");
		name = new JTextField (5);
		editButton = new JButton ("Edit");
		labelMessage = new JLabel ("<html>*Select first the component that you <br>want to edit.</html>");
		labelPrice = new JLabel ("Price:");
		price = new JTextField (5);

		frame = new JFrame("Edit Product");
		frame.setSize(new Dimension(298, 220));

		JPanel p = new JPanel();
		p.setLayout(null);

		labelName.setBounds (20, 15, 100, 25);
		name.setBounds (20, 40, 100, 25);
		editButton.setBounds (170, 55, 100, 40);
		labelMessage.setBounds (20, 135, 210, 40);
		labelPrice.setBounds (20, 65, 100, 25);
		price.setBounds (20, 90, 100, 25);

		p.add (labelName);
		p.add (name);
		p.add (editButton);
		p.add (labelMessage);
		p.add (labelPrice);
		p.add (price);
		
		frame.setContentPane(p);	
		frame.setVisible(false);
	}
	
	public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getEditButton(){
		return this.editButton;
	}
	
	public String getPrice(){
		return price.getText();
	}
	public String getName(){
		return name.getText();
	}
	

	void addListener(ActionListener listen) {
		editButton.addActionListener(listen);
	}
}
