package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import business.MenuItem;

public class RestaurantSerializator implements Serializable 
{
	
	public static  void serialize(ArrayList<MenuItem> menu,String filename){
	try {
		FileOutputStream fileOut=new FileOutputStream(filename);
		ObjectOutputStream out=new ObjectOutputStream(fileOut);
		out.writeObject(menu);
		out.close();
		fileOut.close();
		System.out.println("Serialized successfully");
	}catch (IOException e){
		e.printStackTrace();
	}
}
	
	public static ArrayList<MenuItem> deserialize(String filename){
		ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
		try{
			FileInputStream fileIn=new FileInputStream(filename);
			ObjectInputStream in=new ObjectInputStream(fileIn);
			menu= (ArrayList<MenuItem>) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Deserialized successfully");
			
		}catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return menu;
	}

}
