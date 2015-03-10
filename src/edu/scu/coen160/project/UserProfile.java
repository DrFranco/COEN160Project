package edu.scu.coen160.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class UserProfile extends Observable implements ActionListener {
	Card userCard;
	double budget;
	double funds;
	boolean fundsSet = false;
	int dailyCalories;
	int caloriesRemaining;
	boolean dailyCaloriesSet = false;
	String otherFoodPrefs = "";
	double expenses;
	int caloriesToday = 0;
	Timer timer1, timer2;

	UserProfile() {
		timer1 = new Timer(86400000, this);
		timer2 = new Timer(30 * 86400000, this);
		expenses = 0;
		userCard = new Card();

		timer1.start();
		// timer2.start();
	}

	UserProfile(int num, String pass) {
		timer1 = new Timer(86400000, this);
		timer2 = new Timer(30 * 86400000, this);
		expenses = 0;
		userCard = new Card(num, pass);

		timer1.start();
		// timer2.start();
	}

	UserProfile(Card c) {
		timer1 = new Timer(86400000, this);
		timer2 = new Timer(30 * 86400000, this);
		expenses = 0;
		userCard = c;

		timer1.start();
		// timer2.start();
	}

	void addCaloricPreferences(int num) {
		if (num <= 0) {
			dailyCalories = -1;
			dailyCaloriesSet = false;
		}
		dailyCalories = num;
		caloriesRemaining = num;
		dailyCaloriesSet = true;
		setChanged();
		notifyObservers();
	}

	void addFunds(double num) {
		funds = num;
		budget = num;
		fundsSet = true;
		// timer2.start();
		setChanged();
		notifyObservers();
	}

	void setBudget(double bud, double fun) {
		if (bud > 0) {
			budget = bud;
			funds = fun;
			fundsSet = true;
			expenses = bud - fun;
		}
	}

	boolean makePurchase(SnackItem food, Card c) {
		if (userCard.equals(c)) {
			if (fundsSet)
				funds -= food.price;
			expenses += food.price;
			if (dailyCaloriesSet)
				caloriesRemaining -= food.calories;
			caloriesToday += food.calories;
			setChanged();
			notifyObservers();
			return true;
		} else
			return false;
	}

	boolean makePurchase(SnackItem food, int num, String pass) {
		if (userCard.checkPassword(num, pass)) {
			if (fundsSet)
				funds -= food.price;
			expenses += food.price;
			if (dailyCaloriesSet)
				caloriesRemaining -= food.calories;
			caloriesToday += food.calories;
			setChanged();
			notifyObservers();
			return true;
		} else
			return false;
	}

	void addOtherFoodPrefs(String pref) {
		if (otherFoodPrefs == null || otherFoodPrefs == "")
			otherFoodPrefs += pref;
		else
			otherFoodPrefs += pref + "; ";
		setChanged();
		notifyObservers();
	}

	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == timer1) {
			caloriesToday = 0;
			if (dailyCaloriesSet)
				caloriesRemaining = dailyCalories;
		}
		if (arg.getSource() == timer2) {
			expenses = 0;
			if (fundsSet)
				funds = budget;
		}
		setChanged();
		notifyObservers();
	}

	public String fundsToString() {
		if (fundsSet)
			return String.format("%.2f", funds);
		else
			return "not set";
	}

	public String budgetToString() {
		if (fundsSet)
			return String.format("%.2f", budget);
		else
			return "not set";
	}

	public String dailyCaloriesToString() {
		if (dailyCaloriesSet || dailyCalories > 0)
			return "" + dailyCalories;
		else
			return "not set";
	}

	public String caloriesRemainingToString() {
		if (dailyCaloriesSet || dailyCalories > 0)
			return "" + caloriesRemaining;
		else
			return "not set";
	}

	public boolean equals(Object x) {

		return x instanceof UserProfile
				&& this.userCard.equals(((UserProfile) x).userCard);
	}
}
