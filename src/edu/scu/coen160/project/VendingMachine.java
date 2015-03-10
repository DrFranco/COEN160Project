package edu.scu.coen160.project;

import java.util.ArrayList;

public class VendingMachine {
	ArrayList<SnackItem> stock;
	
	public boolean orderFood(UserProfile user, SnackItem food, Card card) {
		if(user.makePurchase(food, card)){
			stock.remove(food);
			return true;
		}
		else
			return false;
	}
	
	public boolean orderFood(UserProfile user, SnackItem food, int num, String pass) {
		if(user.makePurchase(food, num, pass)){
			stock.remove(food);
			return true;
		}
		else
			return false;
	}
}
