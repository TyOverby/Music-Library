package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Splashscreen extends JWindow {

	private int duration;

	public Splashscreen(int d) {
		duration = d;
	}

	//Method for creating the splash screen
	public void showSplash() {

		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.black);

		// Set the splash screen bounds, centering the window
		int width = 800;
		int height =400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		// Build the splash screen
		ImageIcon image = new ImageIcon("Splashscreen.gif");
		JLabel label = new JLabel(image,JLabel.CENTER);
		JLabel copyrt = new JLabel
		("Copyright 2011, Tacoma Community College", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 16));
		add(label, BorderLayout.CENTER);
		add(copyrt, BorderLayout.SOUTH);
		Color oraRed = new Color(156, 20, 20,  255);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

		// Display it
		setVisible(true);

		// Wait a little while, while the gigantic program loads
		try { Thread.sleep(duration); } catch (Exception e) {}

		setVisible(false);

	}

	public void showSplashandHide() {
		showSplash();
	}
}
