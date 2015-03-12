package edu.scu.coen160.project;

import java.util.*;

public class Cafe {
	List<FoodItem> stock = new ArrayList<FoodItem>();
	Map<String, FoodItem> names = new HashMap<String, FoodItem>();

	public Cafe() {
	}

	public boolean orderFood(UserProfile user, FoodItem food, Card card) {
		if (user.makePurchase(food, card)) {
			return true;
		} else
			return false;
	}

	public boolean orderFood(UserProfile user, FoodItem food, int num,
			String pass) {
		if (user.makePurchase(food, num, pass)) {
			return true;
		} else
			return false;
	}

	public Object[] toStringArray() {
		Iterator<FoodItem> stockIterator = stock.iterator();
		ArrayList<String> strArrayList = new ArrayList<String>();

		while (stockIterator.hasNext()) {
			FoodItem foo = stockIterator.next();
			String str = foo.toString();
			strArrayList.add(str);
			names.put(str, foo);
		}
		return strArrayList.toArray();
	}
}
