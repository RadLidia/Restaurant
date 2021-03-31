package Main;

import business.Restaurant;
import presentation.Controller;
import presentation.MainFrame;

public class Start {
	public static void main (String[] args) {

		Restaurant restaurant = new Restaurant();
		MainFrame login = new MainFrame(restaurant);
		Controller c = new Controller(login, restaurant);
		restaurant.addObserver(c.getView());
	}
}
