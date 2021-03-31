package business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order {
    private int orderID;
    private Date date;
    private String stringDate ;
    private int table;
    private ArrayList<MenuItem> components = new ArrayList<MenuItem>();
    
    public Order(int orderID, int table) {
    	
    		this.components = new ArrayList<MenuItem>();
    	 this.orderID = orderID;
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
         Date date = new Date();
         this.date = date;
         this.stringDate = dateFormat.format(date);
         this.table = table;
    }

    
    public Order() {
	}


	public int getOrderID(){
        return orderID;
    }
    public int getTable(){
        return table;
    }
    public Date getDate() {
        return date;
    }
    public String getStringDate() {
        return stringDate;
    }

    public ArrayList<MenuItem> getComponents() {

    	return this.components;

    }
    public void setComponents(ArrayList<MenuItem> list) {

    	this.components = list;

    }

    public int hashCode() {
    	int hash = 0;
    	hash = this.orderID + this.date.getDay()+this.date.getMonth()
    			+this.date.getYear()+ this.table;
    	return hash;  
    }
 
}

