package business;

import data.FileWriter;
//import data_layer.RestaurantSerializator;
import data.RestaurantSerializator;

import javax.swing.*;

import java.util.*;

public class Restaurant extends Observable implements IRestaurantProcessing {

    Map<Order, ArrayList<MenuItem>> orders;
    private ArrayList<MenuItem> menu;

    public Restaurant(){
        orders = new HashMap<Order, ArrayList<MenuItem>>();
        menu = RestaurantSerializator.deserialize("restaurant.ser");
        //menu = new ArrayList<MenuItem>();
        assert isWellFormed();
    }
    /**
     * Adds new item to the menu collection.
     * pre: item != null and !menuContains(item)
     * post: @result {@literal <=>} !menuContains(item)
     * @param item
     * @return boolean
     */
    public boolean createMenuItem(MenuItem item) {
        assert item!=null;
        assert !menuContains(item);
        assert isWellFormed();
        if(!menuContains(item)) {
            menu.add(item);
        }else {
            JOptionPane.showMessageDialog(null,"This item already exists!");
            assert isWellFormed();
            assert menuContains(item);
            return false;
        }
        assert isWellFormed();
        return true;
    }
    
    /**
     * Removes the item from the menu collection.
     * pre: item != null
     * post: menuContains(item) == false
     * @param item
     */
    public void deleteMenuItem(MenuItem item) {
        assert item != null;
        assert isWellFormed();
        menu.remove(item);
        assert isWellFormed();
        assert !menuContains(item);
    }
    
    /**
     * Replace the item with newItem in the collection.
     * pre: item != null {@literal &&} newItem != null
     * post: menuContains(item) == false {@literal &&} menuContains(newItem)==true
     * @param item
     * @param newItem
     */
    public void editMenuItem(MenuItem item, MenuItem newItem) {
        assert item != null && newItem != null;
        assert isWellFormed();
        deleteMenuItem(item);
        createMenuItem(newItem);
        assert isWellFormed();
        assert menuContains(item)==false && menuContains(newItem)==true;
        RestaurantSerializator.serialize(this.menu,"restaurant.ser");
    }
    
    /**
     * Creates and add new order to the current map of orders.
     * pre: o != null {@literal &&} list.size() {@literal >} 0
     * post: ordersContains(o,list) == true
     * @param o
     * @param list
     */
    public void createOrder(Order o, ArrayList<MenuItem> list) {
        assert o!=null && list.size()>0;
        assert isWellFormed();
        orders.put(o, list);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof CompositeProduct){
                this.setChanged();
                this.notifyObservers(list.get(i));
            }
        }
        assert isWellFormed();
        assert ordersContains(o,list);
    }
    
    /**
     * pre: list.size() {@literal >} 0
     * post: no change
     * @param list
     * @return float
     */
    public float computeOrderTotal(ArrayList<MenuItem> list) {
        assert list.size() > 0;
        float total = 0;
        for(int i = 0; i < list.size(); i++){
            total += list.get(i).computePrice();
        }
        assert isWellFormed();
        return total;
    }
    
    /**
     * Generates a .txt file with information extracted from order.
     * pre: list.size() {@literal >} 0 {@literal &&} o != null {@literal &&} ordersContains(o,list) == true
     * post: no change
     * @param list
     */
    public void generateBill(Order o, ArrayList<MenuItem> list) {
        assert list.size() > 0 && o != null;
    	FileWriter f = new FileWriter();
        StringBuilder s = new StringBuilder();
        s.append("Order : " + o.getOrderID() + "\n");
		s.append("Table : " + o.getTable() + "\n");
		s.append("Date : " + o.getDate() + "\n");
		s.append("Ingredients : " + "\n");
		for(int i = 0; i < list.size(); i++){
            s.append((i + 1) + ". " + list.get(i).getName() + "    price : " + list.get(i).computePrice() + "\n");
        }
		s.append("\n");
		s.append("Total : " + computeOrderTotal(list) + "\n");
        f.writeBill("bill.txt", s.toString());
        assert isWellFormed();
    }
    /**
     * Checks if the menu already contains the item to be added.
     * pre: item != null
     * post: no change
     * @param item
     * @return boolean
     */
    public boolean menuContains(MenuItem item) {
        assert item != null;
        if(item != null && menu.contains(item)){
            assert isWellFormed();
            return true;
        }
        assert isWellFormed();
        return false;
    }
    
    /**
     * Checks if orders map already contains the map.entry {@literal <o,list>}.
     * pre: item != null {@literal &&} list.size() {@literal >} 0
     * post: no change
     * @param o
     * @param list
     * @return boolean
     */
    public boolean ordersContains(Order o,ArrayList<MenuItem> list){
        assert o != null && list.size()>0;
        if(orders.containsKey(o) && orders.get(o) == list){
            assert isWellFormed();
            return true;
        }
        assert isWellFormed();
        return false;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }
    public Map<Order, ArrayList<MenuItem>> getOrders() {
    	return orders;
    }
    public ArrayList<MenuItem> getOrdersComposedBy (String s) {
    	ArrayList<MenuItem> items = new ArrayList<MenuItem>();
    	String[] arr = s.split(",");
    	for(int i = 0; i < arr.length; i++) {
    		for (MenuItem item: this.menu)
        	{
        		if (item.getName().equals(arr[i]))
        		{
        			items.add(item);
        		}
        		if(arr[i].equals("itself")) {
        			MenuItem empty = new BaseProduct("", 0);
        			items.add(empty);
        		}
        	}
    	}
		return items;
    }
    
    public String getComposedBy (ArrayList<MenuItem> list) {
    	
         Iterator i = list.iterator();
         StringBuilder sb = new StringBuilder();
         
         while(i.hasNext()) { 

         	MenuItem m = (MenuItem) i.next();
         	sb.append(m.getName() + ",");
         }
       
 		return sb.toString();
     }

    /**
     * verify if all orders from map have a collection of menu 
     * items associated and if the collection is not null
     * 
     * @return boolean
     */
    protected boolean isWellFormed(){
    	
        if(menu == null || orders == null)
            return false;
        
        for(Iterator<MenuItem> it = menu.iterator(); it.hasNext();){
            
        	if(it.next() == null){
                return false;
            }
        }
        for(Map.Entry<Order,ArrayList<MenuItem>> entry : orders.entrySet()){
            
        	if(entry.getValue() == null || entry.getKey() == null){
                return false;
            }
        }
        return true;
    }
}

