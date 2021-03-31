package business;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable{
    private String name;
    private float price;

    public BaseProduct(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float computePrice() {
        return this.price;
    }
    
}
