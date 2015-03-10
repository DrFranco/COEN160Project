package edu.scu.coen160.project;

import java.util.*;

public class VendingMachine {
	List<SnackItem> stock = new ArrayList<SnackItem>();
	Map<String,SnackItem> names = new HashMap<String,SnackItem>();
	
	public boolean orderFood(UserProfile user, SnackItem food, Card card) {
		if(user.makePurchase(food, card)){
			//stock.remove(food);
			return true;
		}
		else
			return false;
	}
	
	public boolean orderFood(UserProfile user, SnackItem food, int num, String pass) {
		if(user.makePurchase(food, num, pass)){
			//stock.remove(food);
			return true;
		}
		else
			return false;
	}
	
	public Object[] toStringArray(){
		Iterator<SnackItem> stockIterator = stock.iterator();
		ArrayList<String> strArrayList= new ArrayList<String>();
		
		while(stockIterator.hasNext())
		{
			SnackItem foo =stockIterator.next();
			String str = foo.toString();
			strArrayList.add(str);
			names.put(str,foo);
		}
		return strArrayList.toArray();
	}
}
