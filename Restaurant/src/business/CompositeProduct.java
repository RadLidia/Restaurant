package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class CompositeProduct extends MenuItem implements Serializable{
    private String name;
    private float price;
    private ArrayList<MenuItem> components = new ArrayList<MenuItem>();

	public CompositeProduct(String name, float price) {
		 this.name = name;
		 this.price = this.computePrice();
	}

	public CompositeProduct(String name) {
   	 this.name = name;
	}
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void addComponent(MenuItem newItem) {	
    	components.add(newItem);	
	}

    public void setComponents(ArrayList<MenuItem> list) {
    	this.components = list;
    }
    
    public String getComposedBy () {
        Iterator<MenuItem> i = components.iterator();
        StringBuilder sb = new StringBuilder();
        
        while(i.hasNext()) { 
        	MenuItem m = (MenuItem) i.next();
        	sb.append(m.getName() + ",");
        }
		return sb.toString();
    }

    public float computePrice() {
    	Iterator<MenuItem> i = components.iterator();

    	float total = 0;
    	while(i.hasNext()) { 

    		MenuItem m = (MenuItem) i.next();
    		total += m.computePrice();
    	}
    	
      return total;
    }
}
