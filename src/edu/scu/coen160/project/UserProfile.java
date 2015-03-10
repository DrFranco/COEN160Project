package edu.scu.coen160.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class UserProfile extends Observable implements ActionListener {
	Card userCard;
	double funds;
	boolean fundsSet = false;
	int dailyCalories;
	boolean dailyCaloriesSet = false;
	String otherFoodPrefs = "";
	double expenses;
	int caloriesToday = 0;
	Timer timer;

	UserProfile() {
		timer = new Timer(86400000, this);

		expenses = 0;
		userCard = new Card();

		timer.start();
	}

	UserProfile(int num, String pass) {
		timer = new Timer(86400000, this);

		expenses = 0;
		userCard = new Card(num, pass);

		timer.start();
	}
	
	UserProfile(Card c) {
		timer = new Timer(86400000, this);

		expenses = 0;
		userCard = c;

		timer.start();
	}

	void addCaloricPreferences(int num) {
		if (num <= 0) {
			dailyCalories = -1;
			dailyCaloriesSet = false;
		}
		dailyCalories = num;
		dailyCaloriesSet = true;
		setChanged();
		notifyObservers();
	}

	void addFunds(double num) {
		funds = num;
		fundsSet = true;
		setChanged();
		notifyObservers();
	}

	boolean makePurchase(SnackItem food, Card c) {
		if (userCard.equals(c)) {
			funds -= food.price;
			caloriesToday += food.calories;
			setChanged();
			notifyObservers();
			return true;
		} else
			return false;
	}

	boolean makePurchase(SnackItem food, int num, String pass) {
		if (userCard.checkPassword(num, pass)) {
			funds -= food.price;
			caloriesToday += food.calories;
			setChanged();
			notifyObservers();
			return true;
		} else
			return false;
	}

	void addOtherFoodPrefs(String pref) {
		if (otherFoodPrefs == "")
			otherFoodPrefs += pref;
		else
			otherFoodPrefs += "; " + pref;
		setChanged();
		notifyObservers();
	}

	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == timer) {
			caloriesToday = 0;
		}
		setChanged();
		notifyObservers();
	}

	public String fundsToString() {
		if (fundsSet)
			return "" + funds;
		else
			return "not set";
	}

	public String dailyCaloriesToString() {
		if (dailyCaloriesSet)
			return "" + dailyCalories;
		else
			return "not set";
	}

	public boolean equals(Object x) {
		
		return x instanceof UserProfile && this.userCard.equals(((UserProfile) x).userCard);
	}
}
