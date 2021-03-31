package business;

import java.io.PrintWriter;
import java.util.ArrayList;

public interface IRestaurantProcessing {

	//admin
	boolean createMenuItem(MenuItem m);
	void deleteMenuItem(MenuItem m);
	void editMenuItem(MenuItem item, MenuItem newItem);
	//waiter
	void createOrder(Order o, ArrayList<MenuItem> list);
	float computeOrderTotal(ArrayList<MenuItem> list);
	void generateBill(Order o, ArrayList<MenuItem> list);
	
	boolean menuContains(MenuItem item);
	boolean ordersContains(Order o,ArrayList<MenuItem> list);
	
}