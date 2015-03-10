package edu.scu.coen160.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CampusSmartCafe extends JFrame implements ActionListener {
	static ArrayList<Card> userCards;
	static ArrayList<UserProfile> users;
	static ArrayList<Integer> numbers;

	UserProfile currentUser = null;

	JButton v1, v2, v3, v4, v5, v6, v7, v8, c1, c2, c3, c4, c5;
	JPanel mapPanel;

	JButton newUser, logIn, enterFunds, enterCal, enterOther, viewExp,
			viewDiet, logOut;
	JPanel optionsPanel;

	JLabel num, funds, expenses, dayCal, calTod, otherPref;
	DetailsPanel detailPanel;

	public CampusSmartCafe() {
		super("CampusSmartCafe");
		numbers = new ArrayList<Integer>();
		users = new ArrayList<UserProfile>();
		userCards = new ArrayList<Card>();
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
		funds = new JLabel("");
		expenses = new JLabel("");
		dayCal = new JLabel("");
		calTod = new JLabel("");
		otherPref = new JLabel("");
		otherPref.setMaximumSize(new Dimension(20,0));
		detailPanel.add(num);
		detailPanel.add(funds);
		detailPanel.add(expenses);
		detailPanel.add(dayCal);
		detailPanel.add(calTod);
		detailPanel.add(otherPref);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(mapPanel,BorderLayout.WEST);
		contentPane.add(optionsPanel,BorderLayout.EAST);
		contentPane.add(detailPanel,BorderLayout.SOUTH);

		pack();
	}

	public static void main(String args[]) {
		CampusSmartCafe cafe = new CampusSmartCafe();
		cafe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cafe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub

		if (arg.getSource() == newUser) {
			createAccount();
		}
		if (arg.getSource() == logIn) {
			logIn();
		}
		if (arg.getSource() == enterFunds) {
			fundHandler();
		}
		if(arg.getSource() == enterCal){
			calHandler();
		}
		if(arg.getSource()==enterOther){
			otherHandler();
		}
		if(arg.getSource()==viewExp){
			expHandler();
		}
		if(arg.getSource()==viewDiet){
			dietHandler();
		}

		if (arg.getSource() == logOut) {
			logOut();
		}
	}
	
	private void expHandler(){
		
	}
	private void dietHandler() {
		
	}
	
	
	private void otherHandler(){
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
			System.out.println(currentUser.fundsToString());
		}
	}
	
	private void calHandler(){
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			int number =-1;
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
				JOptionPane.showMessageDialog(getFrames()[0],
						"Invalid number", "Calories error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			currentUser.addCaloricPreferences(number);
		}
	}
	
	private void fundHandler(){
		if (currentUser == null) {
			JOptionPane.showMessageDialog(getFrames()[0],
					"You have to log in first!", "Error",
					JOptionPane.ERROR_MESSAGE);
			logIn();
		} else {
			double number =200.0;
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
				JOptionPane.showMessageDialog(getFrames()[0],
						"Invalid number", "Funds error",
						JOptionPane.ERROR_MESSAGE);
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
			System.out.println("booooogig");
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

		for (int i = 0; i < 5; i++) {
			mapPanel.add(blankSpace());
		}

		c1 = new JButton("Cafe");
		c1.addActionListener(this);
		mapPanel.add(c1);

		for (int i = 0; i < 5; i++) {
			mapPanel.add(blankSpace());
		}

		c2 = new JButton("Cafe");
		c2.addActionListener(this);
		mapPanel.add(c2);

		mapPanel.add(blankSpace());

		v2 = new JButton("Vending");
		v2.addActionListener(this);
		mapPanel.add(v2);

		for (int i = 0; i < 3; i++) {
			mapPanel.add(blankSpace());
		}

		v3 = new JButton("Vending");
		v3.addActionListener(this);
		mapPanel.add(v3);

		for (int i = 0; i < 12; i++) {
			mapPanel.add(blankSpace());
		}

		v4 = new JButton("Vending");
		v4.addActionListener(this);
		mapPanel.add(v4);

		for (int i = 0; i < 3; i++) {
			mapPanel.add(blankSpace());
		}

		c3 = new JButton("Cafe");
		c3.addActionListener(this);
		mapPanel.add(c3);

		mapPanel.add(blankSpace());

		v5 = new JButton("Vending");
		v5.addActionListener(this);
		mapPanel.add(v5);

		for (int i = 0; i < 5; i++) {
			mapPanel.add(blankSpace());
		}

		v6 = new JButton("Vending");
		v6.addActionListener(this);
		mapPanel.add(v6);

		for (int i = 0; i < 11; i++) {
			mapPanel.add(blankSpace());
		}

		c4 = new JButton("Cafe");
		c4.addActionListener(this);
		mapPanel.add(c4);

		for (int i = 0; i < 6; i++) {
			mapPanel.add(blankSpace());
		}

		v7 = new JButton("Vending");
		v7.addActionListener(this);
		mapPanel.add(v7);

		for (int i = 0; i < 11; i++) {
			mapPanel.add(blankSpace());
		}
		c5 = new JButton("Cafe");
		c5.addActionListener(this);
		mapPanel.add(c5);

		for (int i = 0; i < 6; i++) {
			mapPanel.add(blankSpace());
		}

		v8 = new JButton("Vending");
		v8.addActionListener(this);
		mapPanel.add(v8);

		for (int i = 0; i < 18; i++) {
			mapPanel.add(blankSpace());
		}
	}

	private JButton blankSpace() {
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		return button;
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

	public class DetailsPanel extends JPanel implements Observer {
		UserProfile current = null;

		public void add(JComponent component) {
			super.add(component);
		}

		public DetailsPanel() {
			// TODO Auto-generated constructor stub
			setLayout(new GridLayout(0, 1));
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			if (current == null) {
				System.out.println("yup");
				// clear(current);
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
			funds.setText("Funds:\t\t\t\t" + current.fundsToString());
			expenses.setText("Expenses:\t\t\t\t" + current.expenses);
			dayCal.setText("DailyCalories:\t\t\t\t"
					+ current.dailyCaloriesToString());
			calTod.setText("Calories Today:\t\t\t\t" + current.caloriesToday);
			otherPref.setText("Other Preferences:\t\t\t\t" + current.otherFoodPrefs);
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
		}
	}
}
