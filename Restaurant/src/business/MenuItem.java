package business;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {

    public float computePrice(){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public String setName(){
        throw new UnsupportedOperationException();
    }

    public float getPrice(){
        throw new UnsupportedOperationException();
    }

    public void setPrice(float p){
        throw new UnsupportedOperationException();
    }

    public String getComposedBy () {
        throw new UnsupportedOperationException();
    }
    
    public void setComponents(ArrayList<MenuItem> list) {
        throw new UnsupportedOperationException();
    }

}