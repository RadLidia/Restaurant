package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.*;

import business.CompositeProduct;

public class ChefView implements Observer {
    private JTextArea text;
    private JLabel labelTitle;
	private JFrame frame;
	static int count;

    public ChefView() {
    	text = new JTextArea (5, 5);
        labelTitle = new JLabel ("Chef:");

        frame = new JFrame("Chef");
		frame.setSize(new Dimension(272, 253));

		JPanel p = new JPanel();
		p.setLayout(null);
       
		text.setBounds (45, 40, 180, 200);
        labelTitle.setBounds (118, 20, 100, 25);

        p.add (text);
        p.add (labelTitle);

        labelTitle.setFont(new Font("Cambria", Font.BOLD, 20));

		frame.setContentPane(p);	
		frame.setVisible(false);
    }
    
    public void setFrame(){
		this.frame.setVisible(true);
	}
    
    public void update(Observable o, Object arg) {
        if(arg instanceof CompositeProduct){
            count++;
            text.append(count + ". " + "To cook : ");
            text.append(((CompositeProduct) arg).getName() + "\n");
        }
    }
    
    public ChefView getChefView() {
        return this;
    }
  
}

