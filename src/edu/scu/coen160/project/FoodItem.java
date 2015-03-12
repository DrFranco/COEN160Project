package edu.scu.coen160.project;

public class FoodItem extends SnackItem {
	public int cookingTime;
	
	public FoodItem(String str, double cost, int cal, int cook) {
		name = str;
		price = cost;
		calories = cal;
		cookingTime = cook;
	}
	
	@Override
	public String toString(){
		return super.toString()+" | time: " + cookingTime+" min(s)";
	}
}
