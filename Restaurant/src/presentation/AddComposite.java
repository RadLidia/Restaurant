package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AddComposite{
	private JLabel labelName;
	private JTextField name;
	private JButton addButton;
	private JLabel labelMessage;
	private JFrame frame;

	public AddComposite() {
		
		labelName = new JLabel ("Composite name:");
		name = new JTextField (5);
		addButton = new JButton ("Add");
		labelMessage = new JLabel ("<html>*Select first the ingredients that <br>compose the composite product.</html>");

		frame = new JFrame("Add Composite Product");
		frame.setSize(new Dimension(265, 190));

		JPanel p = new JPanel();
		p.setLayout(null);

		labelName.setBounds (20, 25, 100, 25);
		name.setBounds (20, 50, 100, 25);
		addButton.setBounds (140, 35, 100, 40);
		labelMessage.setBounds (20, 100, 210, 40);

		p.add (labelName);
		p.add (name);
		p.add (addButton);
		p.add (labelMessage);

		frame.setContentPane(p);	
		frame.setVisible(false);
	}
	
	public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getAddButton(){
		return this.addButton;
	}
	
	public String getName(){
		return name.getText();
	}

	void addListener(ActionListener listen) {
		addButton.addActionListener(listen);
	}
}
