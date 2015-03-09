package edu.scu.coen160.project;

public class SnackItem {
	public String name;
	public double price;
	public int calories;
	
	public SnackItem(String str, double cost, int cal) {
		name = str;
		price = cost;
		calories = cal;
	}
	
	public SnackItem() {
	}
}
