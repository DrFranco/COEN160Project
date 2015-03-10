package edu.scu.coen160.project;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CampusSmartCafe extends JFrame {
	ArrayList<Card> userCards;
	ArrayList<UserProfile> users;

	public CampusSmartCafe() {
		super("CampusSmartCafe");

		// create map(buttons)
		JPanel mapPanel = createMapPanel();
		mapPanel.setBorder(new TitledBorder(null, "Campus Map",
				TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION));

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
		campusMap.setLayout(new FlowLayout());
		return campusMap;
	}

	public static void main(String args[]) {
		CampusSmartCafe cafe = new CampusSmartCafe();
		cafe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cafe.setVisible(true);
	}
}
