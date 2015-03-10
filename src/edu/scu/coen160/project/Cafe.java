package edu.scu.coen160.project;

import java.util.ArrayList;

public class Cafe {
	ArrayList<FoodItem> stock;
	
	
	public boolean orderFood(UserProfile user, FoodItem food, Card card) {
		if(user.makePurchase(food, card)){
			stock.remove(food);
			return true;
		}
		else
			return false;
	}
	
	public boolean orderFood(UserProfile user, FoodItem food, int num, String pass) {
		if(user.makePurchase(food, num, pass)){
			stock.remove(food);
			return true;
		}
		else
			return false;
	}
	
	//need to implement where and when to pick up food
}
