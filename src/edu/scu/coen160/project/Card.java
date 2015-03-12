package edu.scu.coen160.project;

import java.math.BigInteger;
import java.util.Random;

public class Card {
	public int number;
	private String password;

	public Card(int num, String str) {
		number = num;
		password = str;
	}

	public Card() {
		Random rand = new Random();
		number = rand.nextInt(300);
		password = new BigInteger(90, rand).toString(32);
		password = password.substring(0, 6);
		System.out.println("Number: " + number + "   Password: " + password);
	}

	public boolean checkPassword(int num, String pass) {
		return (pass == password && num == number);
	}
	
	public String toString(){
		return "Card number:\t"+number+"\nPassword:\t"+password;
	}
	
	public boolean equals(Object o) {
		return o instanceof Card && ((Card) o).number == this.number;
	}
	
	public String password() {
		return password;
	}
}