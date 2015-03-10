package edu.scu.coen160.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CampusSmartCafe extends JFrame implements ActionListener {
	ArrayList<Card> userCards;
	ArrayList<UserProfile> users;
	
	JButton v1,v2,v3,v4,v5,v6,v7,v8,c1,c2,c3,c4,c5;
	JPanel mapPanel;

	public CampusSmartCafe() {
		super("CampusSmartCafe");

		// create map(buttons) map done, still need buttons
		generateMap();
		
		// create options panel

		// create output panel

		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(mapPanel);

		pack();
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

	public static void main(String args[]) {
		CampusSmartCafe cafe = new CampusSmartCafe();
		cafe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cafe.setVisible(true);
	}
	
	
	public JButton blankSpace() {
		JButton button = new JButton();
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	//ugly long generation of map, but works fine
	private void generateMap(){
		mapPanel = createMapPanel();
		mapPanel.setBorder(new TitledBorder(null, "Campus Map",
				TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION));
		
		v1 = new JButton("Vending");
		v1.addActionListener(this);
		// j.setMargin(new Insets(1,1,1,1));
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
		
		for (int i = 0; i <6 ; i++) {
			mapPanel.add(blankSpace());
		}
		
		v7 = new JButton("Vending");
		v7.addActionListener(this);
		mapPanel.add(v7);
		
		for (int i = 0; i <11 ; i++) {
			mapPanel.add(blankSpace());
		}
		c5 = new JButton("Cafe");
		c5.addActionListener(this);
		mapPanel.add(c5);
		
		for (int i = 0; i <6 ; i++) {
			mapPanel.add(blankSpace());
		}
		
		v8 = new JButton("Vending");
		v8.addActionListener(this);
		mapPanel.add(v8);
		
		for (int i = 0; i <18 ; i++) {
			mapPanel.add(blankSpace());
		}
	}

}
