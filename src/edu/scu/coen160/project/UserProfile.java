package edu.scu.coen160.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class UserProfile implements ActionListener{
	Card userCard;
	double funds=-1;
	int dailyCalories = -1;
	String otherFoodPrefs;
	double expenses;
	int caloriesToday;
	Timer timer;
	
	UserProfile() {
		timer = new Timer(86400000, this);
		
		expenses = 0;
		userCard = new Card();
		
		timer.start();
	}
	
	UserProfile (int num, String pass) {
		timer = new Timer(86400000, this);
		
		expenses = 0;
		userCard = new Card(num, pass);
		
		timer.start();
	}
	
	void addCaloricPreferences(int num) {
		dailyCalories = num;
	}
	
	void addFunds(double num) {
		funds = num;
	}
	
	boolean makePurchase(double amt, Card c) {
		if (userCard.equals(c)){
			funds-=amt;
			return true;
		}
		else
			return false;
	}
	boolean makePurchase(double amt, int num, String pass) {
		if (userCard.checkPassword(num, pass)){
			funds-=amt;
			return true;
		}
		else
			return false;
	}
	
	void addOtherFoodPrefs(String pref) {
		if(otherFoodPrefs == "")
			otherFoodPrefs += pref;
		else
			otherFoodPrefs += "; " + pref;
	}

	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == timer)	{
			caloriesToday = 0;
		}
	}
	
}
