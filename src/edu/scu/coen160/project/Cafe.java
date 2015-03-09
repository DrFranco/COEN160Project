package edu.scu.coen160.project;

public class Cafe {
	FoodItem []stock;
	
	
	public boolean orderFood(UserProfile user, FoodItem food, Card card) {
		double amt = food.price;
		if(user.makePurchase(amt, card)){
			
		}
	}
	
	public boolean orderFood(UserProfile user, FoodItem food, int num, String pass) {
		double amt
	}
}
