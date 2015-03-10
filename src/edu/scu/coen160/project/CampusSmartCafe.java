package edu.scu.coen160.project;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CampusSmartCafe extends JFrame implements ActionListener {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://dbserver.engr.scu.edu/sdb_blarsen";

	static final String USER = "blarsen";
	static final String PASS = "00000887511";

	static ArrayList<Card> userCards;
	static ArrayList<UserProfile> users;
	static ArrayList<Integer> numbers;

	UserProfile currentUser = null;

	JButton v1, v2, v3, v4, v5, v6, v7, v8, c1, c2, c3, c4, c5;
	JPanel mapPanel;

	JButton newUser, logIn, enterFunds, enterCal, enterOther, viewExp,
			viewDiet, logOut;
	JPanel optionsPanel;

	JLabel num, budget, funds, expenses, dayCal, calRem, calTod, otherPref,
			location, timeLeft;
	DetailsPanel detailPanel;

	Timer timer;

	Component r1;
	Component r2;

	Cafe cafe1, cafe2, cafe3, cafe4, cafe5;
	VendingMachine vending1, vending2, vending3, vending4, vending5, vending6,
			vending7, vending8;

	// GraphView graphPanel;

	public CampusSmartCafe() {
		super("CampusSmartCafe");
		Connection con = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://129.210.16.40:3306/sdb_blarsen";
		String user = "blarsen";
		String password = "00000887511";
		PreparedStatement pst = null;
		try {
			// UserProfile userProf;

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("pass");
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT * FROM CampusCard");
			rs = pst.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getString(1));
				System.out.print(": ");
				System.out.println(rs.getString(2));
			}

		} catch (Exception ex) {
			Logger lgr = Logger.getLogger(CampusSmartCafe.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("help");

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(CampusSmartCafe.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		// DROP TABLE IF EXISTS CampusCard;

		cafe1 = new Cafe();
		cafe1.stock.add(new FoodItem("Chicken Noodle Soup", 5.99, 325, 1));
		cafe1.stock.add(new FoodItem("Tomato Soup", 4.99, 250, 2));
		cafe1.stock.add(new FoodItem("Tortilla Soup", 3.99, 275, 3));
		cafe1.stock.add(new FoodItem("Clam Chowder", 5.99, 350, 2));

		cafe2 = new Cafe();
		cafe2.stock.add(new FoodItem("Dog's Day Sandwich", 5.99, 325, 1));
		cafe2.stock.add(new FoodItem("Ham Shank", 4.99, 250, 2));
		cafe2.stock.add(new FoodItem("T-bone steak", 3.99, 275, 3));
		cafe2.stock.add(new FoodItem("Burger and Fries", 5.99, 350, 2));

		cafe3 = new Cafe();
		cafe3.stock.add(new FoodItem("Spagetthi and Meatballs", 5.99, 325, 1));
		cafe3.stock.add(new FoodItem("Spagetthi", 4.99, 250, 2));
		cafe3.stock.add(new FoodItem("Fettucini Alfredo", 3.99, 275, 3));

		cafe4 = new Cafe();
		cafe4.stock.add(new FoodItem("Chicken Noodle Soup", 5.99, 325, 1));
		cafe4.stock.add(new FoodItem("Tomato Soup", 4.99, 250, 2));
		cafe4.stock.add(new FoodItem("Tortilla Soup", 3.99, 275, 3));
		cafe4.stock.add(new FoodItem("Clam Chowder", 5.99, 350, 2));

		cafe5 = new Cafe();
		cafe5.stock.add(new FoodItem("Chicken Noodle Soup", 5.99, 325, 1));
		cafe5.stock.add(new FoodItem("Tomato Soup", 4.99, 250, 2));
		cafe5.stock.add(new FoodItem("Tortilla Soup", 3.99, 275, 3));
		cafe5.stock.add(new FoodItem("Clam Chowder", 5.99, 350, 2));

		vending1 = new VendingMachine();
		vending1.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending1.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending1.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending1.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending1.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending1.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending2 = new VendingMachine();
		vending2.stock.add(new SnackItem("Snickers", 1.29, 150));
		vending2.stock.add(new SnackItem("Butterfingers", 1.19, 100));
		vending2.stock.add(new SnackItem("Reese's", 1.39, 175));
		vending2.stock.add(new SnackItem("Twix", 2.00, 100));
		vending2.stock.add(new SnackItem("Beef Jerky", 2.25, 300));
		vending2.stock.add(new SnackItem("Fanta", 1.49, 190));

		vending3 = new VendingMachine();
		vending3.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending3.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending3.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending3.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending3.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending3.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending4 = new VendingMachine();
		vending4.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending4.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending4.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending4.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending4.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending4.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending5 = new VendingMachine();
		vending5.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending5.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending5.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending5.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending5.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending5.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending6 = new VendingMachine();
		vending6.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending6.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending6.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending6.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending6.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending6.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending7 = new VendingMachine();
		vending7.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending7.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending7.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending7.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending7.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending7.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		vending8 = new VendingMachine();
		vending8.stock.add(new SnackItem("Skittles", 1.29, 150));
		vending8.stock.add(new SnackItem("M & M's", 1.19, 100));
		vending8.stock.add(new SnackItem("Heath Bar", 1.39, 175));
		vending8.stock.add(new SnackItem("Chex Mix", 2.00, 100));
		vending8.stock.add(new SnackItem("Doritos", 2.25, 300));
		vending8.stock.add(new SnackItem("Mountain Dew", 1.49, 190));

		numbers = new ArrayList<Integer>();
		users = new ArrayList<UserProfile>();
		userCards = new ArrayList<Card>();

		timer = new Timer(1000, this);
		// create map(buttons) map done, still need buttons
		generateMap();
		// create output panel

		// create options panel
		optionsPanel = new JPanel(new GridLayout(0, 1, 0, 20));
		newUser = new JButton("Create New User");
		newUser.addActionListener(this);
		optionsPanel.add(newUser);
		logIn = new JButton("Login");
		logIn.addActionListener(this);
		optionsPanel.add(logIn);

		enterFunds = new JButton("Enter funds");
		enterFunds.addActionListener(this);
		optionsPanel.add(enterFunds);

		enterCal = new JButton("Enter caloric diet");
		enterCal.addActionListener(this);
		optionsPanel.add(enterCal);

		enterOther = new JButton("Enter other diets");
		enterOther.addActionListener(this);
		optionsPanel.add(enterOther);

		viewExp = new JButton("View Expenses");
		viewExp.addActionListener(this);
		optionsPanel.add(viewExp);

		viewDiet = new JButton("View Diet");
		viewDiet.addActionListener(this);
		optionsPanel.add(viewDiet);

		logOut = new JButton("Logout");
		logOut.addActionListener(this);
		optionsPanel.add(logOut);

		detailPanel = new DetailsPanel();
		detailPanel.setLayout(new GridLayout(0, 1));
		detailPanel.setBorder(new TitledBorder("User information"));
		num = new JLabel("No user currently logged in");
		budget = new JLabel("");
		funds = new JLabel("");
		expenses = new JLabel("");
		dayCal = new JLabel("");
		calRem = new JLabel("");
		calTod = new JLabel("");
		otherPref = new JLabel("");
		location = new JLabel("");
		timeLeft = new JLabel("");
		otherPref.setMaximumSize(new Dimension(20, 0));
		detailPanel.add(num);
		detailPanel.add(budget);
		detailPanel.add(funds);
		detailPanel.add(expenses);
		detailPanel.add(dayCal);
		detailPanel.add(calRem);
		detailPanel.add(calTod);
		detailPanel.add(otherPref);
		detailPanel.add(location);
		detailPanel.add(timeLeft);
		//
		// graphPanel = new GraphView();
		// graphPanel.add(blankSpace());
		// graphPanel.add(blankSpace());
		// graphPanel.add(blankSpace());
		// graphPanel.add(blankSpace());
		// graphPanel.setBorder(new TitledBorder("Graphs"));
		// graphPanel.setBounds(new Rectangle(500,500));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(mapPanel, BorderLayout.WEST);
		contentPane.add(optionsPanel, BorderLayout.EAST);
		contentPane.add(detailPanel, BorderLayout.SOUTH);
		// contentPane.add(graphPanel, BorderLayout.NORTH);

		pack();
	}

	public static void main(String args[]) {
		CampusSmartCafe cafe = new CampusSmartCafe();
		cafe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cafe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == newUser) {
			createAccount();
		} else if (arg.getSource() == logIn) {
			logIn();
		} else if (arg.getSource() == enterFunds) {
			fundHandler();
		} else if (arg.getSource() == enterCal) {
			calHandler();
		} else if (arg.getSource() == enterOther) {
			otherHandler();
		} else if (arg.getSource() == viewExp) {
			expHandler();
		} else if (arg.getSource() == viewDiet) {
			dietHandler();
		} else if (arg.getSource() == timer) {
			incrementClock();
		} else if (arg.getSource() == logOut) {
			logOut();
		} else
			selectHandler(arg.getSource());
	}

	private void incrementClock() {
		if (timeLeft.getText() == "Ready") {
			timer.stop();
		} else {
			String[] fields = timeLeft.getText().split(":");
			int minutes = Integer.parseInt(fields[0].trim());
			int seconds = Integer.parseInt(fields[1].trim());
			if (seconds != 0) {
				seconds--;
			} else if (minutes != 0) {
				seconds = 59;
				minutes--;
			} else {
				timeLeft.setText("Ready");
				return;
			}
			String output = "" + minutes + ":" + String.format("%02d", seconds);
			timeLeft.setText(output);
		}
	}

	private void selectHandler(Object o) {
		if (o == c1 || o == c2 || o == c3 || o == c4 || o == c5)
			cafeHandler(o);
		else
			vendingHandler(o);
	}

	private void cafeHandler(Object o) {
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			if (o == c1) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose an item of food to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						cafe1.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				cafe1.orderFood(currentUser, cafe1.names.get(str),
						currentUser.userCard);
				location.setText("Location:\t\t\t\t" + c1.getText());
				timeLeft.setText(cafe1.names.get(str).cookingTime + ":00");
				timer.start();
			}
			if (o == c2) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose an item of food to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						cafe2.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				cafe2.orderFood(currentUser, cafe2.names.get(str),
						currentUser.userCard);
				location.setText("Location:\t\t\t\t" + c2.getText());
				timeLeft.setText(cafe2.names.get(str).cookingTime + ":00");
				timer.start();
			}
			if (o == c3) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose an item of food to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						cafe3.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				cafe3.orderFood(currentUser, cafe3.names.get(str),
						currentUser.userCard);
				location.setText("Location:\t\t\t\t" + c3.getText());
				timeLeft.setText(cafe3.names.get(str).cookingTime + ":00");
				timer.start();
			}
			if (o == c4) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose an item of food to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						cafe4.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				cafe4.orderFood(currentUser, cafe4.names.get(str),
						currentUser.userCard);
				location.setText("Location:\t\t\t\t" + c4.getText());
				timeLeft.setText(cafe4.names.get(str).cookingTime + ":00");
				timer.start();
			}
			if (o == c5) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose an item of food to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						cafe5.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				cafe5.orderFood(currentUser, cafe5.names.get(str),
						currentUser.userCard);
				location.setText("Location:\t\t\t\t" + c5.getText());
				timeLeft.setText(cafe5.names.get(str).cookingTime + ":00");
				timer.start();
			}
		}
	}

	private void vendingHandler(Object o) {
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			if (o == v1) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending1.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending1.orderFood(currentUser, vending1.names.get(str),
						currentUser.userCard);
			}
			if (o == v2) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending2.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending2.orderFood(currentUser, vending2.names.get(str),
						currentUser.userCard);
			}
			if (o == v3) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending3.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending3.orderFood(currentUser, vending3.names.get(str),
						currentUser.userCard);
			}
			if (o == v4) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending4.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending4.orderFood(currentUser, vending4.names.get(str),
						currentUser.userCard);
			}
			if (o == v5) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending5.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending5.orderFood(currentUser, vending5.names.get(str),
						currentUser.userCard);
			}
			if (o == v6) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending6.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending6.orderFood(currentUser, vending6.names.get(str),
						currentUser.userCard);
			}
			if (o == v7) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending7.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending7.orderFood(currentUser, vending7.names.get(str),
						currentUser.userCard);
			}
			if (o == v8) {
				String str = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Choose a snack to order",
						"Make an Order", JOptionPane.PLAIN_MESSAGE, null,
						vending8.toStringArray(), null);
				try {
					if (str == null)
						throw new Exception();
				} catch (Exception e) {
					return;
				}
				vending8.orderFood(currentUser, vending8.names.get(str),
						currentUser.userCard);
			}
		}
	}

	private void expHandler() {
		// graph expenses
	}

	private void dietHandler() {
		// graph dietary profile
	}

	private void otherHandler() {
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			String str = JOptionPane.showInputDialog(getFrames()[0],
					"Any other dietary restrictions?");
			try {
				if (str == null || (str != null && ("".equals(str)))) {
					throw new Exception();
				}
			} catch (Exception e) {
				return;
			}

			currentUser.addOtherFoodPrefs(str);
		}
	}

	private void calHandler() {
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			int number = -1;
			String str = JOptionPane.showInputDialog(getFrames()[0],
					"How many calories per day do you want?");
			try {

				try {
					if (str == null || (str != null && ("".equals(str)))) {
						throw new Exception();
					}
				} catch (Exception e) {
					return;
				}
				number = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getFrames()[0], "Invalid number",
						"Calories error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			currentUser.addCaloricPreferences(number);
		}
	}

	private void fundHandler() {
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			double number = 200.0;
			String str = JOptionPane.showInputDialog(getFrames()[0],
					"How many funds per month do you have?");
			try {

				try {
					if (str == null || (str != null && ("".equals(str)))) {
						throw new Exception();
					}
				} catch (Exception e) {
					return;
				}
				number = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getFrames()[0], "Invalid number",
						"Funds error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			currentUser.addFunds(number);
		}
	}

	private void logOut() {
		if (currentUser != null) {
			detailPanel.clear(currentUser);
			currentUser = null;
		}

	}

	private void createAccount() {
		int n = JOptionPane.showConfirmDialog(getFrames()[0],
				"Would you like to set your own card number and pasword?",
				"Account setup", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			int number = -1;
			try {
				number = Integer.parseInt(JOptionPane.showInputDialog(
						getFrames()[0],
						"What you like your card number to be?", null));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(getFrames()[0], "Invalid number",
						"Card number error", JOptionPane.ERROR_MESSAGE);
				createAccount();
				return;

			}
			if (numbers != null && numbers.contains(number)) {
				JOptionPane.showMessageDialog(getFrames()[0],
						"The number you entered already exists",
						"Card number error", JOptionPane.ERROR_MESSAGE);
			} else {
				String pass1 = (String) JOptionPane.showInputDialog(
						getFrames()[0], "Please set a password", null);
				try {
					if (pass1 == null || (pass1 != null && ("".equals(pass1)))) {
						throw new Exception();
					}
				} catch (Exception e) {
					return;
				}
				UserProfile newProf = new UserProfile(number, pass1);

				Connection conn;
				Statement stmt = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");

					System.out.println("Connecting...");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);

					System.out.println("Connected!");
				} catch (Exception e) {
					// do nothing
				}

				numbers.add(number);
				users.add(newProf);
				userCards.add(newProf.userCard);
			}

		}
		if (n == JOptionPane.NO_OPTION) {
			UserProfile newProf = new UserProfile();
			while (userCards != null && userCards.contains(newProf.userCard)) {
				newProf = new UserProfile();
			}
			JOptionPane.showMessageDialog(getFrames()[0],
					newProf.userCard.toString(), "New User information",
					JOptionPane.INFORMATION_MESSAGE);
			numbers.add(newProf.userCard.number);
			users.add(newProf);
			userCards.add(newProf.userCard);
		}
	}

	private void logIn() {
		int number = -1;
		try {
			String str = JOptionPane.showInputDialog(getFrames()[0],
					"What is your card number?");
			try {
				if (str == null || (str != null && ("".equals(str)))) {
					throw new Exception();
				}
			} catch (Exception e) {
				return;
			}
			number = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getFrames()[0], "Invalid number",
					"Card number error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// add error detection
		if (numbers == null || !numbers.contains(number)) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"The number you entered does not exist",
					"Card number error", JOptionPane.ERROR_MESSAGE);
			logIn();
			return;
		}
		String pass = JOptionPane.showInputDialog(getFrames()[0],
				"What is your password?", null);
		try {
			if (pass == null || (pass != null && ("".equals(pass)))) {
				throw new Exception();
			}
		} catch (Exception e) {
			return;
		}
		UserProfile check = new UserProfile(number, pass);
		if (users.contains(check)) {
			currentUser = users.get(users.indexOf(check));
			detailPanel.setCurrent(currentUser);
		}
	}

	// ugly long generation of map, but works fine
	private void generateMap() {
		mapPanel = createMapPanel();
		mapPanel.setBorder(new TitledBorder(null, "Campus Map",
				TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION));

		v1 = new JButton("Vending");
		v1.addActionListener(this);
		mapPanel.add(v1);

		blankSpace(5);

		c1 = new JButton("Soup Stop");
		c1.addActionListener(this);
		mapPanel.add(c1);

		blankSpace(5);

		c2 = new JButton("Lazy Bone");
		c2.addActionListener(this);
		mapPanel.add(c2);

		blankSpace(1);

		v2 = new JButton("Vending");
		v2.addActionListener(this);
		mapPanel.add(v2);

		blankSpace(3);

		v3 = new JButton("Vending");
		v3.addActionListener(this);
		mapPanel.add(v3);

		blankSpace(12);

		v4 = new JButton("Vending");
		v4.addActionListener(this);
		mapPanel.add(v4);

		blankSpace(3);

		c3 = new JButton("Selvester's");
		c3.addActionListener(this);
		mapPanel.add(c3);

		blankSpace(1);

		v5 = new JButton("Vending");
		v5.addActionListener(this);
		mapPanel.add(v5);

		blankSpace(5);

		v6 = new JButton("Vending");
		v6.addActionListener(this);
		mapPanel.add(v6);

		blankSpace(11);

		c4 = new JButton("Snaxson");
		c4.addActionListener(this);
		mapPanel.add(c4);

		blankSpace(6);

		v7 = new JButton("Vending");
		v7.addActionListener(this);
		mapPanel.add(v7);

		blankSpace(11);

		c5 = new JButton("Meat Shack");
		c5.addActionListener(this);
		mapPanel.add(c5);

		blankSpace(6);

		v8 = new JButton("Vending");
		v8.addActionListener(this);
		mapPanel.add(v8);

		blankSpace(18);
	}

	private void blankSpace(int num) {
		for (int i = 0; i < num; i++) {
			JButton button = new JButton();
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			mapPanel.add(button);
		}
	}

	private BackgroundPanel createMapPanel() {
		final BackgroundPanel campusMap;
		Image image;
		try {
			image = ImageIO.read(new File("./src/map.jpg"));

		} catch (IOException e) {
			return null;
		}

		campusMap = new BackgroundPanel(image);
		campusMap.setLayout(new GridLayout(10, 10));
		// campusMap.makeComponentTransparent();
		return campusMap;
	}

	// class GraphView extends JPanel implements Observer {
	// UserProfile current = null;
	// boolean isCal;
	// public GraphView() {
	// setLayout(new GridLayout(0,1));
	// }
	//
	// public void update(Observable a, Object o){
	// if(current == null){
	// //clear(current);
	// }
	// else {
	// showGraph();
	// }
	// }
	//
	// public void setCurrent(UserProfile curr, boolean boo)
	// {
	// if(curr == null)
	// return;
	// else
	// {
	// current = curr;
	// current.addObserver(this);
	// showGraph();
	// }
	// }
	//
	// private void showGraph() {
	//
	// }
	//
	// }
	//
	//
	//

	public class DetailsPanel extends JPanel implements Observer {
		UserProfile current = null;

		public void add(JComponent component) {
			super.add(component);
		}

		public DetailsPanel() {
			setLayout(new GridLayout(0, 1));
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (current == null) {
				return;
			} else {
				showFields();
			}
		}

		public void setCurrent(UserProfile curr) {
			if (curr == null) {
				return;
			} else {
				current = curr;
				current.addObserver(this);
				showFields();
			}
		}

		private void showFields() {
			num.setText("Card number:\t\t\t\t" + current.userCard.number);
			budget.setText("Budget:\t\t\t\t" + current.budgetToString());
			funds.setText("Funds:\t\t\t\t" + current.fundsToString());
			expenses.setText("Expenses:\t\t\t\t"
					+ String.format("%.2f", current.expenses));
			dayCal.setText("DailyCalories:\t\t\t\t"
					+ current.dailyCaloriesToString());
			calRem.setText("Calories Remaining:\t\t\t\t"
					+ current.caloriesRemainingToString());
			calTod.setText("Calories Today:\t\t\t\t" + current.caloriesToday);
			otherPref.setText("Other Preferences:\t\t\t\t"
					+ current.otherFoodPrefs);
		}

		private void clear(UserProfile user) {
			user.deleteObserver(this);
			current = null;
			num.setText("No user currently logged in");
			funds.setText("");
			expenses.setText("");
			dayCal.setText("");
			calTod.setText("");
			otherPref.setText("");
			location.setText("");
			timer.stop();
			timeLeft.setText("");
			budget.setText("");
			calRem.setText("");
		}
	}
}
