package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;
import business.Order;
import business.Restaurant;
import data.RestaurantSerializator;


public class Controller {
	private AdministratorView admin;
	private AddBase base;
	private AddComposite composite;
	private Edit edit;
	private AddOrder order;


	private WaiterView waiter;
	private ChefView chef;
	private Restaurant restaurant;
	private MainFrame login ;

	public Controller(MainFrame login, Restaurant res)
	{
		AdministratorView adm = new AdministratorView(res);
		AddBase base = new AddBase();
		AddComposite composite = new AddComposite();
		Edit edit = new Edit();
		this.restaurant = res;
		AddOrder order = new AddOrder(res);
		WaiterView waiter = new WaiterView(res);
		ChefView chef = new ChefView();
		this.admin = adm;
		this.base = base;
		this.composite = composite;
		this.edit = edit;
		this.waiter = waiter;
		this.order = order;
		this.chef = chef;
		this.admin.addListener(new Listener());
		this.base.addListener(new Listener());
		this.composite.addListener(new Listener());
		this.edit.addListener(new Listener());
		this.waiter.addListener(new Listener());
		this.order.addListener(new Listener());
		this.restaurant = res;
		this.login = login;
	    this.login.addListener(new Listener());
		
	}
	class Listener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == login.getAdmin()){
				admin.setFrame();
			}
			
			if (e.getSource() == login.getWaiter()){
				waiter.setFrame();
			}
			
			if (e.getSource() == login.getChef()){
				chef.setFrame();
			}
			
			if (e.getSource() == admin.getDeleteButton()){				
				int size = admin.getTableSize();
				admin.checkSelected();
				
				for(int i = 0; i < size; i++){
					MenuItem aux = admin.menuItemChecked(i, restaurant);
					if(aux != null){
						restaurant.deleteMenuItem(aux);
						admin.deleteFromTable(i);
						RestaurantSerializator.serialize(restaurant.getMenu(),"restaurant.ser");
					}
				}
            }

			if (e.getSource() == admin.getAddBaseButton()){
				base.setFrame();
			}
			
			if (e.getSource() == admin.getAddCompositeButton()){
				composite.setFrame();
			}
			if (e.getSource() == base.getAddButton()){
				String p = base.getPrice();
				float price = Float.parseFloat(p);
				String name = base.getName();
				
				MenuItem m = new BaseProduct(name, price);

				restaurant.createMenuItem(m);
				admin.addToTable(m);
				order.addCheckBox(restaurant, m);
				RestaurantSerializator.serialize(restaurant.getMenu(),"restaurant.ser");
				
			}
			if (e.getSource() == composite.getAddButton()){
				int size = admin.getTableSize();
				admin.checkSelected();
				String name = composite.getName();
				
				CompositeProduct comp = new CompositeProduct(name);
				
				for(int i = 0; i < size; i++){
					MenuItem aux = admin.menuItemChecked(i, restaurant);
					if(aux != null){
						((CompositeProduct) comp).addComponent(aux);
					}
				}
					
				float price = comp.computePrice();
				comp.setPrice(price);
				restaurant.createMenuItem(comp);
				admin.addCompositeToTable(comp);
				order.addCheckBox(restaurant, comp);
				RestaurantSerializator.serialize(restaurant.getMenu(),"restaurant.ser");

			}
			
			if (e.getSource() == admin.getEditButton()){
				edit.setFrame();
			}
			
			if (e.getSource() == edit.getEditButton()){
				
				int size = admin.getTableSize();
				admin.checkSelected();

				for(int i = 0; i < size; i++){
					MenuItem aux = admin.menuItemChecked(i, restaurant);
					if(aux != null){
						restaurant.deleteMenuItem(aux);
						admin.deleteFromTable(i);
						String p = edit.getPrice();
						float price = Float.parseFloat(p);
						String name = edit.getName();

						MenuItem m = new BaseProduct(name, price);
						restaurant.editMenuItem(aux, m);	
						admin.addToTable(m);
					}

				}
			}
			
			if (e.getSource() == waiter.getAddOrderButton()){
				order.setFrame();
			}
			
			if (e.getSource() == order.getAddOrder()){

//				order = new AddOrder(restaurant);
				
				order.checkSelected(restaurant);
				int id = Integer.parseInt(order.getID());
				int table = Integer.parseInt(order.getTable());

				Order o = new Order(id, table);
				ArrayList<MenuItem> menu = (ArrayList<MenuItem>) restaurant.getMenu();
				ArrayList<MenuItem> ordered = new ArrayList<MenuItem>();
				int size = menu.size();
				for(int i = 0; i < size; i++){
					MenuItem aux = order.menuItemChecked(i, restaurant);
					if(aux != null){
						ordered.add(aux);
					}
				}
				o.setComponents(ordered);
				restaurant.createOrder(o,ordered);
				waiter.addToTable(o, ordered, restaurant);
			}

			if (e.getSource() == waiter.getBillButton())
			{
				int size = waiter.getTableSize();
				waiter.checkSelected();

				HashMap<Order, ArrayList<MenuItem>> orders = (HashMap<Order, ArrayList<MenuItem>>) restaurant.getOrders();
				for(int i = 0; i < size; i++){
					Order o = waiter.menuItemChecked(i, restaurant);
					if(o != null){					
						
							ArrayList<MenuItem> ordered = o.getComponents();
							restaurant.generateBill(o,ordered);
						
					}
				}			
			}
		}	
	}
	
	public Observer getView() {
        return this.chef;
    }

}
