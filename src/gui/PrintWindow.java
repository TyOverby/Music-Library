package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PrintWindow extends JFrame {
	MusicTable m;
	
	public PrintWindow (MusicTable m) {
		this.m = m;
		setTitle("Print Music Library");
		JScrollPane printScroll = new JScrollPane(m);
		JPanel buttonPanel = new JPanel();
		JButton printButton = new JButton("Print");
		JButton cancelButton = new JButton("Cancel");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		printButton.addActionListener(new PrintListener());
		cancelButton.addActionListener(new CancelListener());

		buttonPanel.add(printButton);
		buttonPanel.add(cancelButton);


		add(printScroll, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setSize(600,600);
		setVisible(true);
	}
	
	private class PrintListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
			    m.print();
			} catch (PrinterException pe) {
			    JOptionPane.showMessageDialog(null, "An unexpected error occurred while sending the job to the printer.");
			}
		}
	}
	
	private class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	}
}

