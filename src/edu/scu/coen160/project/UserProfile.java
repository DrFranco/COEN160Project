package edu.scu.coen160.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;
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
	}

	UserProfile(int num, String pass) {
		timer1 = new Timer(86400000, this);
		timer2 = new Timer(30 * 86400000, this);
		expenses = 0;
		userCard = new Card(num, pass);

		timer1.start();
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
		caloriesRemaining = num - caloriesToday;
		dailyCaloriesSet = true;
		setChanged();
		notifyObservers();
	}

	// Set the User's funds
	void addFunds(double num) {
		funds = num - expenses;
		budget = num;
		fundsSet = true;
		setChanged();
		notifyObservers();
	}

	// Set the User's expense variables from the database
	void setBudget(double bud, double fun) {
		if (bud > 0) {
			budget = bud;
			funds = fun;
			fundsSet = true;
			expenses = bud - fun;
		}
	}
	
	// Set the User's caloric variables from the database
	void setCalories(int cals, int calsToday) {
		if (cals > 0) {
			dailyCalories = cals;
			caloriesToday = calsToday;
			dailyCaloriesSet = true;
			caloriesRemaining = cals - calsToday;
		}
	}

	// Attempt to make a purchase, returning FALSE if failed
	boolean makePurchase(SnackItem food, Card c) {
		if (!fundsSet || funds - food.price <= 0) {
			JOptionPane.showMessageDialog(null, "Sorry, you do not have enough funds!");
			return false;
		}
		
		// Allow the user to exceed their caloric limit (but warn them they have!)
		if (dailyCaloriesSet && caloriesRemaining - food.calories <= 0) {
			JOptionPane.showMessageDialog(null, "Oops! You have exceeded your caloric limit.");
		}
			
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

	// Set the User's food preferences
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
